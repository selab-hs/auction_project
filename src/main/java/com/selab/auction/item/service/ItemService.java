package com.selab.auction.item.service;

import com.selab.auction.error.exception.item.NotExistItemException;
import com.selab.auction.item.model.dto.ItemCreateRequest;
import com.selab.auction.item.model.dto.ItemResponse;
import com.selab.auction.item.model.dto.ItemsResponse;
import com.selab.auction.item.model.entity.Item;
import com.selab.auction.item.model.vo.Category;
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

    public ItemResponse getItemById(Long id) {
        // 조회 시 category id랑 category 같이 반환하기
        Item item = itemRepository.findById(id).orElseThrow(() -> new NotExistItemException());
        return ItemResponse.of(item);
    }

    public ItemResponse createItem(ItemCreateRequest request) {
        //예외처리 - categoryId, memberId, 가능한 price인지?
        return ItemResponse.of(itemRepository.save(Item.of(request)));
    }
}
