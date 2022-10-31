package com.selab.auction.member.service;

import com.selab.auction.member.dto.MemberSignUpRequestDto;
import com.selab.auction.member.dto.MemberSignUpResponseDto;
import com.selab.auction.member.model.entity.Member;
import com.selab.auction.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberSignUpService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberSignUpResponseDto signUp(MemberSignUpRequestDto newMember) {
        String encodedPassword = passwordEncoder.encode(newMember.getPassword());

        Member member = new Member(
                newMember.getEmail(),
                encodedPassword,
                newMember.getNickname(),
                newMember.getAddress(),
                newMember.getPhone(),
                newMember.getSex()
        );

        memberRepository.save(member);

        MemberSignUpResponseDto savedDto = new MemberSignUpResponseDto(
                member.getId(),
                member.getEmail(),
                member.getPassword(),
                member.getNickname(),
                member.getAddress(),
                member.getPhone(),
                member.getSex(),
                member.getGrade(),
                member.getState()
        );

        return savedDto;
    }
}
