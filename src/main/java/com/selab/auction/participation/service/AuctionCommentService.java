package com.selab.auction.participation.service;

import com.selab.auction.item.service.ItemService;
import com.selab.auction.member.service.MemberUpdateGradeService;
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

@Service
@RequiredArgsConstructor
public class AuctionCommentService{
    private final AuctionBuyCommentRepository buyRepository;
    private final AuctionSaleCommentRepository saleRepository;
    private final ItemService itemService;
    private final MemberUpdateGradeService memberUpdateGradeService;

    @Transactional
    public AuctionBuyCommentResponseDto registerBuyComment(CreateBuyCommentDto commentDto) {
        long memberId = itemService.getItemEntityById(commentDto.getItemId()).getMemberId();
        AuctionBuyComment auctionBuyComment = buyRepository.saveAndFlush(commentDto.toEntity(memberId));

        memberUpdateGradeService.updateMemberGrade(memberId, calculationMemberGrade(memberId));

        return auctionBuyComment.toResponseDto();
    }

    @Transactional
    public AuctionSaleCommentResponseDto registerSaleComment(CreateSaleCommentDto commentDto) {
        AuctionSaleComment auctionSaleComment = saleRepository.saveAndFlush(commentDto.toEntity());

        memberUpdateGradeService.updateMemberGrade(auctionSaleComment.getSaleMemberId(), calculationMemberGrade(auctionSaleComment.getSaleMemberId()));

        return auctionSaleComment.toResponseDto();
    }

    @Transactional(readOnly = true)
    public Double calculationMemberGrade(long memberId){
        List<AuctionBuyComment> searchByBuyMemberId =  buyRepository.findByBuyMemberId(memberId);
        List<AuctionSaleComment> searchBySaleMemberId =  saleRepository.findBySaleMemberId(memberId);

        if(searchByBuyMemberId.size()==0 && searchBySaleMemberId.size()==0)
            return 0D;

        double buyMemberGradeSum =  searchByBuyMemberId.stream()
                .mapToDouble(AuctionBuyComment::getGrade)
                .sum();

        double saleMemberGradeSum = searchBySaleMemberId.stream()
                .mapToDouble(AuctionSaleComment::getGrade)
                .sum();

        return (buyMemberGradeSum + saleMemberGradeSum) / (searchByBuyMemberId.size() + searchBySaleMemberId.size());
    }
}
