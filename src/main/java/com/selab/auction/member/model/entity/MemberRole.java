package com.selab.auction.member.model.entity;

import com.selab.auction.member.model.vo.MemberRoleName;
import lombok.*;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class MemberRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column
    private MemberRoleName name;

    public MemberRole(MemberRoleName name) {
        this.name = name;
    }
}
