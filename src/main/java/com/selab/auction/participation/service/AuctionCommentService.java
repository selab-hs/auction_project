package com.selab.auction.participation.service;

import com.selab.auction.error.exception.auction.AlreadyExistsCommentException;
import com.selab.auction.error.exception.auction.WrongBuyMemberException;
import com.selab.auction.error.exception.auction.WrongSaleMemberException;
import com.selab.auction.error.exception.item.WrongItemStateException;
import com.selab.auction.item.model.dto.ItemResponse;
import com.selab.auction.item.model.vo.ItemState;
import com.selab.auction.item.service.ItemService;
import com.selab.auction.member.service.MemberGradeUpdateService;
import com.selab.auction.participation.model.dto.AuctionBuyCommentResponseDto;
import com.selab.auction.participation.model.dto.AuctionSaleCommentResponseDto;
import com.selab.auction.participation.model.dto.CreateBuyCommentDto;
import com.selab.auction.participation.model.dto.CreateSaleCommentDto;
import com.selab.auction.participation.model.entity.AuctionBuyComment;
import com.selab.auction.participation.model.entity.AuctionSaleComment;
import com.selab.auction.participation.repository.AuctionBuyCommentRepository;
import com.selab.auction.participation.repository.AuctionSaleCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// TODO : 코드 리펙토링 필요
@Service
@RequiredArgsConstructor
public class AuctionCommentService {
    private final AuctionBuyCommentRepository buyRepository;
    private final AuctionSaleCommentRepository saleRepository;
    private final ItemService itemService;
    private final MemberGradeUpdateService memberGradeUpdateService;
    private final AuctionService auctionService;

    // TODO : save and Flush 사용 이유는?
    // 사용자의 평균점수를 업데이트 해야하는데 해당 코멘트 점수는 반영이 되지 않아 해당 코멘트를 먼저 영속 컨텍스트에 반영하고 해당 코멘트의 점수를 포함한 회원의 평균점수를 업데이트하기위해 사용했었습니다
    @Transactional
    public AuctionBuyCommentResponseDto registerBuyComment(CreateBuyCommentDto commentDto) {
        long memberId = commentDto.getBuyMemberId();
        validateBuyMemberComment(commentDto.getItemId(), memberId);
        var auctionBuyComment = buyRepository.saveAndFlush(commentDto.toEntity(memberId));
        memberGradeUpdateService.updateMemberGrade(memberId, calculationMemberGrade(memberId));

        return auctionBuyComment.toResponseDto();
    }

    @Transactional
    public AuctionSaleCommentResponseDto registerSaleComment(CreateSaleCommentDto commentDto) {
        validateSaleMemberComment(commentDto.getItemId(), commentDto.getSaleMemberId());
        var auctionSaleComment = saleRepository.saveAndFlush(commentDto.toEntity());
        memberGradeUpdateService.updateMemberGrade(auctionSaleComment.getSaleMemberId(), calculationMemberGrade(auctionSaleComment.getSaleMemberId()));

        return auctionSaleComment.toResponseDto();
    }

    @Transactional(readOnly = true)
    public Double calculationMemberGrade(long memberId) {
        var searchByBuyMemberId = buyRepository.findByBuyMemberId(memberId);
        var searchBySaleMemberId = saleRepository.findBySaleMemberId(memberId);

        if (searchByBuyMemberId.size() == 0 && searchBySaleMemberId.size() == 0)
            return 0D;

        var buyMemberGradeSum = searchByBuyMemberId.stream()
                .mapToDouble(AuctionBuyComment::getGrade)
                .sum();

        var saleMemberGradeSum = searchBySaleMemberId.stream()
                .mapToDouble(AuctionSaleComment::getGrade)
                .sum();

        return (buyMemberGradeSum + saleMemberGradeSum) / (searchByBuyMemberId.size() + searchBySaleMemberId.size());
    }

    @Transactional(readOnly = true)
    public void validateSaleMemberComment(long itemId, long saleMemberId) {
        var item = validateCompletedItemState(itemId);

        saleRepository.findByItemId(itemId)
                .ifPresent(a -> {
                    throw new AlreadyExistsCommentException();
                });

        if(!(item.getMemberId() == saleMemberId)) {
            throw new WrongSaleMemberException();
        }
    }

    @Transactional(readOnly = true)
    public void validateBuyMemberComment(long itemId, long buyMemberId) {
        validateCompletedItemState(itemId);

        buyRepository.findByItemId(itemId)
                .ifPresent(a -> {
                    throw new AlreadyExistsCommentException();
                });

        if(!auctionService.searchMemberIdByItemId(itemId, buyMemberId)) {
            throw new WrongBuyMemberException();
        }
    }

    @Transactional(readOnly = true)
    public ItemResponse validateCompletedItemState(long itemId) {
        var item = itemService.getItemById(itemId);
        if(!item.getState().equals(ItemState.COMPLETE)) {
            throw new WrongItemStateException();
        }

        return item;
    }
}
