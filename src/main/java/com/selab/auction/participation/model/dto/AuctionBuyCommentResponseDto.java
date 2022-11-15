package com.selab.auction.participation.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AuctionBuyCommentResponseDto {
    Long id;
    Long itemId;
    Long buyMemberId;
    Long saleMemberId;
    String comment;
    Long grade;


    @Builder
    public AuctionBuyCommentResponseDto(long id, long itemId, long buyMemberId, long saleMemberId, String comment, long grade){
        this.id = id;
        this.itemId = itemId;
        this.buyMemberId = buyMemberId;
        this.saleMemberId = saleMemberId;
        this.comment = comment;
        this.grade = grade;
    }
}
