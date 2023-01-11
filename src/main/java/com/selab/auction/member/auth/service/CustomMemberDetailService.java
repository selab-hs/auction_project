package com.selab.auction.member.auth.service;

import com.selab.auction.member.auth.token.MemberPrincipal;
import com.selab.auction.member.model.entity.Member;
import com.selab.auction.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomMemberDetailService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository
                .findByEmail(email).orElseThrow(()
                        -> new UsernameNotFoundException("해당 이메일(" + email + ")을 찾을 수 없습니다."));
        return MemberPrincipal.create(member);
    }

    public UserDetails loadUserById(Long id) {
        Member member = memberRepository
                .findById(id).orElseThrow(()
                        -> new UsernameNotFoundException("해당 id(" + id + ")의 회원을 찾을 수 없습니다."));
        return MemberPrincipal.create(member);
    }
}