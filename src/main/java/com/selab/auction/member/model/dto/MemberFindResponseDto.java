package com.selab.auction.member.model.dto;

import com.selab.auction.member.model.entity.Member;
import com.selab.auction.member.model.vo.MemberRole;
import com.selab.auction.member.model.vo.MemberState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class MemberFindResponseDto {
    private Long id;
    private String nickname;
    private Double grade;
    private MemberState state;
    private MemberRole role;

    public static MemberFindResponseDto of(Member member) {
        return MemberFindResponseDto.builder()
                .id(member.getId())
                .nickname(member.getNickname())
                .grade(member.getGrade())
                .state(member.getState())
                .role(member.getRole())
                .build();
    }
}