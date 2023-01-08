package com.selab.auction.error.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorMessage {
    NOT_EXIST_ITEM(HttpStatus.NOT_FOUND, "존재하지 않는 상품입니다"),
    NOT_EXITS_CATEGORY(HttpStatus.NOT_FOUND, "존재하지 않는 카테고리입니다"),

    DELETE_ITEM(HttpStatus.NOT_FOUND, "삭제된 상품입니다"),
    WRONG_ITEM_STATE(HttpStatus.BAD_REQUEST, "잘못된 아이템 정보입니다"),
    WRONG_AUCTION_ID(HttpStatus.BAD_REQUEST, "잘못된 경매 번호 요청입니다"),
    WRONG_REQUEST_PRICE(HttpStatus.NOT_FOUND, "잘못된 경매 요청가격입니다"),
    COMPLETED_AUCTION(HttpStatus.BAD_REQUEST, "이미 경매가 끝난 상품입니다"),

    NOT_BLANK(HttpStatus.BAD_REQUEST, "필수값이 누락되었습니다."),
    EMAIL_PATTERN(HttpStatus.BAD_REQUEST, "이메일 형식이 아닙니다."),
    REGEXP_PATTERN(HttpStatus.BAD_REQUEST, "값 형식이 다릅니다."),
    PASSWORD_CHECK_FAILED(HttpStatus.BAD_REQUEST, "비밀번호가 다릅니다."),

    DUPLICATE_EMAIL(HttpStatus.BAD_REQUEST, "중복된 이메일이 존재합니다."),
    DUPLICATE_NICKNAME(HttpStatus.BAD_REQUEST, "중복된 닉네임이 존재합니다."),

    NOT_EXIST_MEMBER(HttpStatus.NOT_FOUND, "존재하지 않는 회원입니다."),
    INACTIVE_MEMBER(HttpStatus.UNAUTHORIZED, "휴면 회원입니다."),

    INVALID_TOKEN(HttpStatus.BAD_REQUEST, "유효하지 않은 토큰입니다."),
    ;

    private final HttpStatus status;
    private final String description;
}
