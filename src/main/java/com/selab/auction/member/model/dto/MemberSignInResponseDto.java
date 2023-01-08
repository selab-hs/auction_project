package com.selab.auction.member.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberSignInResponseDto {
    private String accessToken;
    private String refreshToken;
    private String tokenType = "Bearer";

    public MemberSignInResponseDto(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public MemberSignInResponseDto(String token) {
        this.accessToken = accessToken;
    }
}
