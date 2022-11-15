package com.selab.auction.participation.service;

import com.selab.auction.participation.model.dto.AuctionBuyCommentResponseDto;
import com.selab.auction.participation.model.dto.CreateAuctionDto;
import com.selab.auction.participation.model.dto.AuctionResponseDto;
import com.selab.auction.participation.model.dto.CreateBuyCommentDto;

public interface AuctionService {

    AuctionResponseDto participateAuction(CreateAuctionDto createAuctionDto);

    AuctionResponseDto searchAuctionHistoryById(long id);
}
