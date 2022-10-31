package com.selab.auction.item.model.dto;

public record ItemCreateRequest(
        String name,
        Long price,
        String description,
        String category,
        Long memberId,
        Integer auctionPeriod,
        Long immediatelyPrice
) {
}
