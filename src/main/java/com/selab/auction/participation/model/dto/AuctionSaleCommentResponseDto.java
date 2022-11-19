package com.selab.auction.participation.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AuctionSaleCommentResponseDto {
    Long id;
    Long itemId;
    Long buyMemberId;
    Long saleMemberId;
    String comment;
    Double grade;


    @Builder
    public AuctionSaleCommentResponseDto(long id, long itemId, long buyMemberId, long saleMemberId, String comment, double grade){
        this.id = id;
        this.itemId = itemId;
        this.buyMemberId = buyMemberId;
        this.saleMemberId = saleMemberId;
        this.comment = comment;
        this.grade = grade;
    }
}
