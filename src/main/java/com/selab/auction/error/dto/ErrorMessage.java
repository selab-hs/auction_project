package com.selab.auction.error.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorMessage {
    NOT_EXIST_ITEM(HttpStatus.NOT_FOUND, "존재하지 않는 상품입니다"),
    NOT_EXITS_CATEGORY(HttpStatus.NOT_FOUND, "존재하지 않는 카테고리입니다"),
    DELETE_ITEM(HttpStatus.NOT_FOUND, "삭제된 상품입니다");
    ;

    private final HttpStatus status;
    private final String description;
}
