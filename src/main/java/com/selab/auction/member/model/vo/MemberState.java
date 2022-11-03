package com.selab.auction.member.model.vo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberState {
    ACTIVE("활성 계정"),
    INACTIVE("휴면 계정");

    private final String description;
}