package com.selab.auction.member.signin.service;

import com.selab.auction.error.exception.member.NotExistMemberException;
import com.selab.auction.error.exception.member.PasswordCheckFailedException;
import com.selab.auction.error.exception.member.InacitveMemberException;
import com.selab.auction.member.signin.security.TokenProvider;
import com.selab.auction.member.model.dto.MemberSignInRequestDto;
import com.selab.auction.member.model.entity.Member;
import com.selab.auction.member.model.vo.MemberState;
import com.selab.auction.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
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

    public String handleSignIn(MemberSignInRequestDto memberSignInRequestDto) {
        Member member = memberRepository.findByEmail(memberSignInRequestDto.getEmail()).orElseThrow(NotExistMemberException::new);

        if (!passwordEncoder.matches(memberSignInRequestDto.getPassword(), member.getPassword())) {
            throw new PasswordCheckFailedException();
        }

        if (member.getState().equals(MemberState.INACTIVE)) {
            throw new InacitveMemberException();
        }

        return tokenProvider.createToken(memberSignInRequestDto.getEmail());
    }
}
