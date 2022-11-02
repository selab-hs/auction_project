package com.selab.auction.member.service;

import com.selab.auction.error.exception.member.join.DuplicateEmailException;
import com.selab.auction.error.exception.member.join.DuplicateNicknameException;
import com.selab.auction.error.exception.member.join.PasswordCheckFailedException;
import com.selab.auction.member.dto.MemberSignUpRequestDto;
import com.selab.auction.member.dto.MemberSignUpResponseDto;
import com.selab.auction.member.model.entity.Member;
import com.selab.auction.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberSignUpService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberSignUpResponseDto signUp(MemberSignUpRequestDto newMember) {
        String encodedPassword = passwordEncoder.encode(newMember.getPassword());

        if(!Objects.equals(newMember.getPassword(), newMember.getCheckPassword())) {
            throw new PasswordCheckFailedException();
        }

        Member member = Member.builder()
                .email(newMember.getEmail())
                .password(encodedPassword)
                .nickname(newMember.getNickname())
                .address(newMember.getAddress())
                .phone(newMember.getPhone())
                .sex(newMember.getSex())
                .build();

        Optional<Member> findByEmailMember = memberRepository.findByEmail(member.getEmail());
        if (findByEmailMember.isPresent()) {
            throw new DuplicateEmailException();
        }

        Optional<Member> findByNicknameMember = memberRepository.findByNickname(member.getNickname());
        if (findByNicknameMember.isPresent()) {
            throw new DuplicateNicknameException();
        }

        memberRepository.save(member);

        MemberSignUpResponseDto savedDto = MemberSignUpResponseDto.builder()
                .id(member.getId())
                .email(member.getEmail())
                .password(member.getPassword())
                .nickname(member.getNickname())
                .address(member.getAddress())
                .phone(member.getPhone())
                .sex(member.getSex())
                .grade(member.getGrade())
                .state(member.getState())
                .role(member.getRole())
                .build();

        return savedDto;
    }
}