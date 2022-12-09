package com.selab.auction.participation.model.dto;

import com.selab.auction.item.model.entity.Auction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Positive;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateImmediatePurchaseDto {
    @Positive
    Long itemId;

    @Positive
    Long memberId;

    public Auction toEntity(Long immediatelyPrice){
        return Auction.builder()
                .memberId(this.memberId)
                .itemId(this.itemId)
                .auctionPrice(immediatelyPrice)
                .build();
    }
}
