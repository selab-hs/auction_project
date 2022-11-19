package com.selab.auction.participation.repository;

import com.selab.auction.participation.model.entity.AuctionBuyComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuctionBuyCommentRepository extends JpaRepository<AuctionBuyComment, Long> {
    List<AuctionBuyComment> findByBuyMemberId(long memberId);
}
