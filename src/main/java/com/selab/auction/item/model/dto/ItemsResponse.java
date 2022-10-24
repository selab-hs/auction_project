package com.selab.auction.item.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class ItemsResponse {
    private List<ItemResponse> items;
}
