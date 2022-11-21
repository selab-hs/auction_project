package com.selab.auction.participation.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Positive;

@Getter
@NoArgsConstructor
public class AuctionBuyCommentResponseDto {
    @Positive
    Long id;

    @Positive
    Long itemId;

    @Positive
    Long buyMemberId;

    @Positive
    Long saleMemberId;
    String comment;

    @Positive
    @Max(5)
    Double grade;


    @Builder
    public AuctionBuyCommentResponseDto(long id, long itemId, long buyMemberId, long saleMemberId, String comment, double grade){
        this.id = id;
        this.itemId = itemId;
        this.buyMemberId = buyMemberId;
        this.saleMemberId = saleMemberId;
        this.comment = comment;
        this.grade = grade;
    }
}
