package com.selab.auction.participation.service;

import com.selab.auction.participation.model.dto.*;

public interface AuctionService {

    AuctionResponseDto participateAuction(CreateAuctionDto createAuctionDto);

    AuctionResponseDto searchAuctionHistoryById(long id);

    AuctionResponseDto immediatePurchaseItem(CreateImmediatePurchaseDto createDto);
}
