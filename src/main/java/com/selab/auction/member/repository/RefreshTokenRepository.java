package com.selab.auction.member.repository;

import com.selab.auction.member.model.entity.Member;
import com.selab.auction.member.model.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    @Override
    Optional<RefreshToken> findById(Long id);

    Optional<RefreshToken> findByToken(String token);

    int deleteByMember(Member member);
}
