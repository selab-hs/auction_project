package com.selab.auction.member.signin.service;

import com.selab.auction.error.exception.member.InvalidTokenException;
import com.selab.auction.member.signin.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberSignOutService {
    private final TokenProvider tokenProvider;

    public void logout(String accessToken, String refreshToken) {
        if (!tokenProvider.validToken(accessToken)) {
            throw new InvalidTokenException();
        }

        Authentication authentication = tokenProvider.getAuthentication(accessToken);

        Long memberId = Long.parseLong(authentication.getName());

    }
}
