package com.selab.auction.participation.service;

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

import java.util.List;

// TODO : 코드 리펙토링 필요
@Service
@RequiredArgsConstructor
public class AuctionCommentService {
    private final AuctionBuyCommentRepository buyRepository;
    private final AuctionSaleCommentRepository saleRepository;
    private final ItemService itemService;
    private final MemberGradeUpdateService memberGradeUpdateService;

    // TODO : save and Flush 사용 이유는?
    @Transactional
    public AuctionBuyCommentResponseDto registerBuyComment(CreateBuyCommentDto commentDto) {
        var memberId = itemService.getItemEntityById(commentDto.getItemId()).getMemberId();
        var auctionBuyComment = buyRepository.saveAndFlush(commentDto.toEntity(memberId));

        memberGradeUpdateService.updateMemberGrade(memberId, calculationMemberGrade(memberId));

        return auctionBuyComment.toResponseDto();
    }

    @Transactional
    public AuctionSaleCommentResponseDto registerSaleComment(CreateSaleCommentDto commentDto) {
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
}
