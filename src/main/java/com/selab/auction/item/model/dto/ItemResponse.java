package com.selab.auction.item.model.dto;

import com.selab.auction.common.State;
import com.selab.auction.item.model.entity.Item;
import com.selab.auction.item.model.vo.Category;
import com.selab.auction.item.model.vo.ItemState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
@AllArgsConstructor
public class ItemResponse {
    private Long id;
    private String name;
    private Long price;
    private Category category;
    private Long memberId;
    private String description;
    private Integer auctionPeriod;
    private Long immediatelyPrice;
    private ItemState state;


    public static ItemResponse of(Item item) {
        return ItemResponse.builder()
                .id(item.getId())
                .name(item.getName())
                .price(item.getPrice())
                .category(item.getCategory())
                .memberId(item.getMemberId())
                .description(item.getDescription())
                .auctionPeriod(item.getAuctionPeriod())
                .immediatelyPrice(item.getImmediatelyPrice())
                .state(item.getState())
                .build();
    }
}
