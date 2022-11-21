package com.selab.auction.member.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberSignInResponseDto {
    private String accessToken;
    private String tokenType = "Bearer ";

    public MemberSignInResponseDto(String accessToken) {
        this.accessToken = accessToken;
    }
}
