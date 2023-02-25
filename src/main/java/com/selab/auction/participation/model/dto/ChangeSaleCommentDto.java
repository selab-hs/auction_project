package com.selab.auction.participation.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Positive;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChangeSaleCommentDto {
    @Positive
    Long id;

    @Positive
    Long saleMemberId;

    String comment;

    @Positive
    @Max(5)
    Double grade;
}
