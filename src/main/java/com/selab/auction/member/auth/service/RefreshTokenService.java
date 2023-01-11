package com.selab.auction.member.auth.service;

import com.selab.auction.error.exception.member.RefreshTokenExpiredException;
import com.selab.auction.member.model.entity.RefreshToken;
import com.selab.auction.member.repository.MemberRepository;
import com.selab.auction.member.repository.RefreshTokenRepository;
import com.selab.auction.member.auth.token.MemberPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class RefreshTokenService {
    @Value("${app.jwtRefreshExpirationInMs}")
    private Long refreshTokenDurationMs;

    private final RefreshTokenRepository refreshTokenRepository;
    private final MemberRepository memberRepository;

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshToken createRefreshToken(Authentication authentication) {
        MemberPrincipal memberPrincipal = (MemberPrincipal) authentication.getPrincipal();

        RefreshToken refreshToken = new RefreshToken();

        refreshToken.setMember(memberRepository.findById(memberPrincipal.getMemberId()).get());
        refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
        refreshToken.setToken(UUID.randomUUID().toString());

        refreshToken = refreshTokenRepository.save(refreshToken);

        return refreshToken;
    }

    public RefreshToken verifyExpiration(RefreshToken refreshToken) {
        if (refreshToken.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(refreshToken);
            throw new RefreshTokenExpiredException();
        }

        return refreshToken;
    }

    @Transactional
    public int deleteByMemberId(Long memberId) {
        return refreshTokenRepository.deleteByMember(memberRepository.findById(memberId).get());
    }
}
