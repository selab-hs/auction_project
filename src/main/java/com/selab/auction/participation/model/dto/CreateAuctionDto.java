package com.selab.auction.participation.model.dto;

import com.selab.auction.item.model.entity.Auction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateAuctionDto {
    Long itemId;
    Long memberId;
    Long requestPrice;

    public Auction toEntity(){
        return Auction.builder()
                .memberId(memberId)
                .itemId(itemId)
                .requestPrice(requestPrice)
                .build();
    }
}
