package com.selab.auction.participation.service;

import com.selab.auction.item.model.entity.Item;
import com.selab.auction.item.service.ItemService;
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

@Service
@RequiredArgsConstructor
public class AuctionCommentService{
    private final AuctionBuyCommentRepository buyRepository;
    private final AuctionSaleCommentRepository saleRepository;
    private final ItemService itemService;

    public AuctionBuyCommentResponseDto registerBuyComment(CreateBuyCommentDto commentDto) {
        Item item = itemService.getItemEntityById(commentDto.getItemId());
        AuctionBuyComment auctionBuyComment = buyRepository.save(commentDto.toEntity(item.getMemberId()));

        return auctionBuyComment.toResponseDto();
    }

    public AuctionSaleCommentResponseDto registerSaleComment(CreateSaleCommentDto commentDto) {
        AuctionSaleComment auctionSaleComment = saleRepository.save(commentDto.toEntity());

        return auctionSaleComment.toResponseDto();
    }
}
