package com.selab.auction.member.model.vo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberRole {
    ADMIN("관리자 계정"),
    USER("사용자 계정");

    private final String description;
}