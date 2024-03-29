package com.selab.auction.participation.model.dto;

import com.selab.auction.participation.model.entity.AuctionSaleComment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Positive;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateSaleCommentDto {

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


    public AuctionSaleComment toEntity(){
        return AuctionSaleComment.builder()
                .itemId(this.itemId)
                .buyMemberId(this.buyMemberId)
                .saleMemberId(this.saleMemberId)
                .comment(this.comment)
                .grade(this.grade)
                .build();
    }
}