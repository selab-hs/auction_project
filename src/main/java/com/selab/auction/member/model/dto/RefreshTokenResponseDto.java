package com.selab.auction.member.model.dto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RefreshTokenResponseDto {
    private String accessToken;
    private String refreshToken;
    private String tokenType = "Bearer";

/*    public RefreshTokenResponseDto(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }*/
}
