package com.selab.auction.item.model.dto;

public record ItemUpdateRequest(
        String name,
        Long price,
        String description,
        String category,
        Integer auctionPeriod,
        Long immediatelyPrice
) {
}
