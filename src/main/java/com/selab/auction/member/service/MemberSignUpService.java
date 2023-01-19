package com.selab.auction.member.service;

import com.selab.auction.error.exception.member.DuplicateEmailException;
import com.selab.auction.error.exception.member.DuplicateNicknameException;
import com.selab.auction.error.exception.member.PasswordCheckFailedException;
import com.selab.auction.member.model.dto.MemberSignUpRequestDto;
import com.selab.auction.member.model.dto.MemberSignUpResponseDto;
import com.selab.auction.member.model.entity.Member;
import com.selab.auction.member.model.entity.MemberRole;
import com.selab.auction.member.model.vo.MemberRoleName;
import com.selab.auction.member.repository.MemberRepository;
import com.selab.auction.member.repository.MemberRoleRepository;
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
    private final MemberRoleRepository memberRoleRepository;

    public MemberSignUpResponseDto signUp(MemberSignUpRequestDto newMember) {
        String encodedPassword = passwordEncoder.encode(newMember.getPassword());

        MemberRole memberRole = memberRoleRepository.findByName(MemberRoleName.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Member Role not set"));

        if(!newMember.getPassword().equals(newMember.getCheckPassword())) {
            throw new PasswordCheckFailedException();
        }

        Member member = Member.builder()
                .email(newMember.getEmail())
                .password(encodedPassword)
                .nickname(newMember.getNickname())
                .address(newMember.getAddress())
                .phone(newMember.getPhone())
                .gender(newMember.getGender())
                .build();

        if (memberRepository.existsByEmail(member.getEmail())) {
            throw new DuplicateEmailException();
        }

        if (memberRepository.existsByNickname(member.getNickname())) {
            throw new DuplicateNicknameException();
        }

        memberRepository.save(member);

        return MemberSignUpResponseDto.builder()
                .id(member.getId())
                .email(member.getEmail())
                .password(member.getPassword())
                .nickname(member.getNickname())
                .address(member.getAddress())
                .phone(member.getPhone())
                .gender(member.getGender())
                .grade(member.getGrade())
                .state(member.getState())
                .role(memberRole.getName())
                .build();
    }
}
