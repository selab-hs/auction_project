package com.selab.auction.member.auth.token;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.selab.auction.member.model.entity.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MemberPrincipal implements UserDetails {
    private Long memberId;
    private String memberEmail;
    private String memberNickname;
    @JsonIgnore
    private String memberPw;

    private Collection<? extends GrantedAuthority> authorities;

    public MemberPrincipal(Long memberId, String memberEmail, String memberNickname, String memberPw,
                           Collection<? extends GrantedAuthority> authorities) {
        this.memberId = memberId;
        this.memberEmail = memberEmail;
        this.memberNickname = memberNickname;
        this.memberPw = memberPw;
        this.authorities = authorities;
    }

    public static MemberPrincipal create(Member member) {
        List<GrantedAuthority> authorities = member
                .getRoles()
                .stream()
                .map(memberRole -> new SimpleGrantedAuthority(memberRole.getName().name()))
                .collect(Collectors.toList());

        return new MemberPrincipal(
                member.getId(),
                member.getEmail(),
                member.getNickname(),
                member.getPassword(),
                authorities
        );

    }

    public Long getMemberId() {
        return memberId;
    }

    public String getMemberEmail() {
        return memberEmail;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return memberPw;
    }

    @Override
    public String getUsername() {
        return memberNickname;
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

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }

        if(obj == null || getClass() != obj.getClass()) {
            return false;
        }

        MemberPrincipal that = (MemberPrincipal) obj;

        return Objects.equals(memberId, that.memberId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberId);
    }
}
