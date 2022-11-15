package com.selab.auction.participation.repository;

import com.selab.auction.participation.model.entity.AuctionSaleComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuctionSaleCommentRepository extends JpaRepository<AuctionSaleComment, Long> {
}
