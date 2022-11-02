package com.selab.auction.participation.model.dto;

import com.selab.auction.item.model.entity.Auction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Positive;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateAuctionDto {
    @Positive
    Long itemId;

    @Positive
    Long memberId;

    @Positive
    Long requestPrice;

    public Auction toEntity(){
        return Auction.builder()
                .memberId(memberId)
                .itemId(itemId)
                .requestPrice(requestPrice)
                .build();
    }
}
