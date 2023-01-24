package com.selab.auction.participation.service;

import com.selab.auction.participation.model.dto.*;

// TODO : interface 분리한 이유는?
public interface AuctionService {

    AuctionResponseDto participateAuction(CreateAuctionDto createAuctionDto);

    AuctionResponseDto searchAuctionHistoryById(long id);

    AuctionResponseDto immediatePurchaseItem(CreateImmediatePurchaseDto createDto);
}
