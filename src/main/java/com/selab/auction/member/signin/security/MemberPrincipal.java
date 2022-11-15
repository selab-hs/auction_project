package com.selab.auction.member.signin.security;

import com.selab.auction.member.model.entity.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class MemberPrincipal implements UserDetails {
    private String memberId;
    private String memberPw;

    public MemberPrincipal(String memberId, String memberPw) {
        this.memberId = memberId;
        this.memberPw = memberPw;
    }

    public static MemberPrincipal create(Member member) {
        return new MemberPrincipal(
                member.getEmail(),
                member.getPassword()
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return memberPw;
    }

    @Override
    public String getUsername() {
        return memberId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
