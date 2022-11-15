package com.selab.auction.member.repository;

import com.selab.auction.member.model.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Boolean existsByEmail(String email);

    Boolean existsByNickname(String nickName);

    Optional<Member> findByEmail(String email);
}
