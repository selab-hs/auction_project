package com.selab.auction.participation.model.dto;

import com.selab.auction.participation.model.entity.AuctionBuyComment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Positive;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateBuyCommentDto {
    @Positive
    Long itemId;

    @Positive
    Long buyMemberId;

    String comment;

    @Positive
    Long grade;

    public AuctionBuyComment toEntity(long saleMemberId){
        return AuctionBuyComment.builder()
                .itemId(itemId)
                .buyMemberId(buyMemberId)
                .saleMemberId(saleMemberId)
                .comment(comment)
                .grade(grade)
                .build();
    }
}
