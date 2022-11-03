package com.selab.auction.member.model.entity;

import com.selab.auction.common.BaseEntity;
import com.selab.auction.member.model.vo.MemberRole;
import com.selab.auction.member.model.vo.MemberState;
import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(length = 30, nullable = false, unique = true)
    private String nickname;

    @Column(nullable = false)
    private String address;

    @Column(length = 20, nullable = false)
    private String phone;

    @Column(length = 20, nullable = false)
    private String sex;

    @Column
    private Double grade;

    @Column(length = 20, nullable = false)
    private MemberState state;

    @Column
    private MemberRole role;

    @Builder
    public Member(String email, String password, String nickname, String address, String phone, String sex) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.address = address;
        this.phone = phone;
        this.sex = sex;
        this.grade = 0.0;
        this.state = MemberState.ACTIVE;
        this.role = MemberRole.USER;
    }
}
