package com.selab.auction.participation.repository;

import com.selab.auction.participation.model.entity.AuctionSaleComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuctionSaleCommentRepository extends JpaRepository<AuctionSaleComment, Long> {
    List<AuctionSaleComment> findBySaleMemberId(Long saleMemberId);
}
