package com.selab.auction.item.service;

import com.selab.auction.error.exception.item.DeleteItemException;
import com.selab.auction.error.exception.item.NotExistItemException;
import com.selab.auction.item.model.dto.ItemCreateRequest;
import com.selab.auction.item.model.dto.ItemResponse;
import com.selab.auction.item.model.dto.ItemUpdateRequest;
import com.selab.auction.item.model.entity.Item;
import com.selab.auction.item.model.vo.ItemState;
import com.selab.auction.item.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional(readOnly = true)
    public Page<ItemResponse> getAllItems(Pageable pageable) {
        return itemRepository.findAllByStateNot(pageable, ItemState.INACTIVE)
                .map(ItemResponse::of);
    }

    // TODO : JPA에서 조회할때부터 상태값 조회하기
    @Transactional(readOnly = true)
    public ItemResponse getItemById(Long id) {
        Item item = itemRepository.findById(id).orElseThrow(NotExistItemException::new);
        if (item.getState().equals(ItemState.INACTIVE)) {
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

    @Transactional(readOnly = true)
    public Item getItemEntityById(Long id) {
        return itemRepository.findById(id).orElseThrow(NotExistItemException::new);
    }

    @Transactional(readOnly = true)
    public List<Item> getItemsEntityByItemStateProgress(){
        return itemRepository.findByState(ItemState.PROGRESS);
    }

    @Transactional
    public void updateItemStateToCompleted(Item item){
        item.updateState(ItemState.COMPLETE);
    }

    @Transactional
    public void updateItemStateByScheduling() {
        getItemsEntityByItemStateProgress().stream()
                .filter((item) -> LocalDateTime.now().isAfter(
                        item.getCreatedAt().plusDays(item.getAuctionPeriod())
                ))
                .peek((item) -> log.info(item.getId() + "번 아이템 경매 기간 만료"))
                .forEach(this::updateItemStateToCompleted);
    }
}
