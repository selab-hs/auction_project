package com.selab.auction.member.service;

import com.selab.auction.error.exception.member.NotExistMemberException;
import com.selab.auction.member.model.entity.Member;
import com.selab.auction.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberGradeUpdateService {
    private final MemberRepository memberRepository;

    public void updateMemberGrade(long memberId, double grade){
        Member member = memberRepository.findById(memberId).orElseThrow(NotExistMemberException::new);
        member.updateMemberGrade(grade);
    }
}
