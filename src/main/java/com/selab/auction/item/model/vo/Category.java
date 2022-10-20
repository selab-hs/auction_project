package com.selab.auction.item.model.vo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Category {
    FOOD("음식"),
    LIFE("생활"),
    ;

    private final String description;
}
