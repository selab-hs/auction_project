package com.selab.auction.member.service;

import com.selab.auction.error.exception.member.find.NotExistMemberException;
import com.selab.auction.member.model.dto.MemberFindResponseDto;
import com.selab.auction.member.model.entity.Member;
import com.selab.auction.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberFindService {
    private final MemberRepository memberRepository;

    public MemberFindResponseDto findById(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(NotExistMemberException::new);
        return MemberFindResponseDto.of(member);
    }
}
