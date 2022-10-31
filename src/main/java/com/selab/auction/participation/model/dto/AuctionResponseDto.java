package com.selab.auction.participation.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AuctionResponseDto {
    long id;
    long itemId;
    long memberId;
    long auctionPrice;

    @Builder
    public AuctionResponseDto(long id, long itemId, long memberId, long auctionPrice){
        this.id = id;
        this.itemId = itemId;
        this.memberId = memberId;
        this.auctionPrice = auctionPrice;
    }
}
