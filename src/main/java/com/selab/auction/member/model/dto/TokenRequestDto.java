package com.selab.auction.member.model.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class TokenRequestDto {
    @NotBlank
    private String refreshToken;
    private String accessToken;
}