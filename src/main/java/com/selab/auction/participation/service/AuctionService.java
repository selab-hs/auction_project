package com.selab.auction.participation.service;

import com.selab.auction.participation.model.dto.CreateAuctionDto;
import com.selab.auction.participation.model.dto.AuctionResponseDto;

public interface AuctionService {

    AuctionResponseDto participateAuction(CreateAuctionDto createAuctionDto);
}
