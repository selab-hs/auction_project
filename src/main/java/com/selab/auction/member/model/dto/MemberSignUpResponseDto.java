package com.selab.auction.member.model.dto;

import com.selab.auction.member.model.vo.MemberRole;
import com.selab.auction.member.model.vo.MemberState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberSignUpResponseDto {
    private Long id;
    private String email;
    private String password;
    private String nickname;
    private String address;
    private String phone;
    private String sex;
    private Double grade;
    private MemberState state;
    private MemberRole role;
}
