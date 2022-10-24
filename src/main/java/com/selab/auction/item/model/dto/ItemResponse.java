package com.selab.auction.item.model.dto;

import com.selab.auction.item.model.entity.Item;
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

    public static ItemResponse of(Item item) {
        return ItemResponse.builder()
                .id(item.getId())
                .name(item.getName())
                .price(item.getPrice())
                .build();
    }
}
