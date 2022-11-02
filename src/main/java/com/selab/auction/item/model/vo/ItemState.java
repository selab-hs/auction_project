package com.selab.auction.item.model.vo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ItemState {
    ACTIVE("활성"),
    INACTIVE("비활성"),
    PROGRESS("경매 진행중"),
    COMPLETE("경매 완료"),
    ;

    private final String description;
}
