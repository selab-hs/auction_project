package com.selab.auction.item.service;

import com.selab.auction.error.exception.lock.FailedToAcquireLockException;
import com.selab.auction.item.model.dto.ItemUpdateRequest;
import com.selab.auction.item.model.entity.Item;
import com.selab.auction.item.model.vo.ItemState;
import com.selab.auction.item.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemLockService {
    private final RedissonClient redissonClient;
    private final ItemRepository itemRepository;

    private RLock getItemLock(Long itemId) {
        return redissonClient.getLock(String.format("item:%d", itemId));
    }

    @Transactional
    public Item createItem(Item item) {
        RLock lock = getItemLock(item.getId());

        try {
            boolean isLocked = lock.tryLock(1, 3, TimeUnit.SECONDS);
            if (!isLocked) {
                log.info("Failed to acquire a lock");
                throw new FailedToAcquireLockException();
            }
            itemRepository.save(item);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (lock.isLocked()) {
                lock.unlock();
            }
        }
        return item;
    }

    @Transactional
    public void updateItemState(Item item, ItemState itemState) {
        RLock lock = getItemLock(item.getId());

        try {
            boolean isLocked = lock.tryLock(1, 3, TimeUnit.SECONDS);
            if (!isLocked) {
                log.info("Failed to acquire a lock");
                throw new FailedToAcquireLockException();
            }
            item.updateState(itemState);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (lock.isLocked()) {
                lock.unlock();
            }
        }
    }

    public void updateItem(Item item, ItemUpdateRequest request) {
        RLock lock = getItemLock(item.getId());

        try {
            boolean isLocked = lock.tryLock(1, 3, TimeUnit.SECONDS);
            if (!isLocked) {
                log.info("Failed to acquire a lock");
                throw new FailedToAcquireLockException();
            }
            item.update(request);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (lock.isLocked()) {
                lock.unlock();
            }
        }
    }
}
