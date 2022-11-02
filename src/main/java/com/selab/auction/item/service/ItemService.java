package com.selab.auction.item.service;

import com.selab.auction.error.exception.item.DeleteItemException;
import com.selab.auction.error.exception.item.NotExistItemException;
import com.selab.auction.item.model.dto.ItemCreateRequest;
import com.selab.auction.item.model.dto.ItemResponse;
import com.selab.auction.item.model.dto.ItemUpdateRequest;
import com.selab.auction.item.model.dto.ItemsResponse;
import com.selab.auction.item.model.entity.Item;
import com.selab.auction.item.model.vo.Category;
import com.selab.auction.item.model.vo.ItemState;
import com.selab.auction.item.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional(readOnly = true)
    public ItemsResponse getAllItems() {
        List<Item> items = itemRepository.findAll();
        List<ItemResponse> responses = items.stream().filter(item -> !item.getState().equals(ItemState.INACTIVE)).map(ItemResponse::of).collect(Collectors.toList());
        return new ItemsResponse(responses);
    }

    @Transactional(readOnly = true)
    public ItemResponse getItemById(Long id) {
        Item item = itemRepository.findById(id).orElseThrow(NotExistItemException::new);
        if(item.getState().equals(ItemState.INACTIVE)) {
            throw new DeleteItemException();
        }
        return ItemResponse.of(item);
    }

    @Transactional
    public ItemResponse createItem(ItemCreateRequest request) {
        //TODO : 예외처리 - memberId, 가능한 price인지?

        return ItemResponse.of(itemRepository.save(Item.of(request)));
    }

    @Transactional
    public ItemResponse updateItemById(Long id, ItemUpdateRequest request) {
        Item item = itemRepository.findById(id).orElseThrow(NotExistItemException::new);
        item.update(request);
        return ItemResponse.of(item);
    }

    @Transactional
    public void deleteItemById(Long id) {
        Item item = itemRepository.findById(id).orElseThrow(NotExistItemException::new);
        item.updateState(ItemState.INACTIVE);
    }
}
