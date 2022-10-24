package com.selab.auction.item.service;

import com.selab.auction.item.model.dto.ItemResponse;
import com.selab.auction.item.model.dto.ItemsResponse;
import com.selab.auction.item.model.entity.Item;
import com.selab.auction.item.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemsResponse getAllItems() {
        List<Item> items = itemRepository.findAll();
        List<ItemResponse> responses = items.stream().map(ItemResponse::of).collect(Collectors.toList());
        return new ItemsResponse(responses);
    }

//    public ItemResponse getItemById(Long id) {
//        Item item = itemRepository.findById(id).orElseThrow();
//
//    }
}
