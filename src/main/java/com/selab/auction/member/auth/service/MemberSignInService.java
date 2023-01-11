package com.selab.auction.member.auth.service;

import com.selab.auction.error.exception.member.InacitveMemberException;
import com.selab.auction.error.exception.member.NotExistMemberException;
import com.selab.auction.error.exception.member.PasswordCheckFailedException;
import com.selab.auction.member.auth.token.TokenProvider;
import com.selab.auction.member.model.dto.MemberSignInRequestDto;
import com.selab.auction.member.model.entity.Member;
import com.selab.auction.member.model.entity.RefreshToken;
import com.selab.auction.member.model.vo.MemberState;
import com.selab.auction.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberSignInService {
    private final MemberRepository memberRepository;
    private final TokenProvider tokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenService refreshTokenService;

    public void validateSignIn(MemberSignInRequestDto requestDto) {
        Member member = memberRepository.findByEmail(requestDto.getEmail()).orElseThrow(NotExistMemberException::new);

        if (!passwordEncoder.matches(requestDto.getPassword(), member.getPassword())) {
            System.out.println(requestDto.getPassword() + ", " + member.getPassword());
            throw new PasswordCheckFailedException();
        }

        if (member.getState().equals(MemberState.INACTIVE)) {
            throw new InacitveMemberException();
        }
    }

    public Authentication getAuth(MemberSignInRequestDto requestDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        requestDto.getEmail(),
                        requestDto.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return authentication;
    }

    public String getAccessToken(Authentication authentication) {
        String accessToken = tokenProvider.generateToken(authentication);
        return accessToken;
    }

    public String getRefreshToken(Authentication authentication) {
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(authentication);
        return refreshToken.getToken();
    }
}
