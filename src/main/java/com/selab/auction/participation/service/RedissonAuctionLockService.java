package com.selab.auction.participation.service;

import com.selab.auction.error.exception.lock.FailedToAcquireLockException;
import com.selab.auction.item.model.entity.Auction;
import com.selab.auction.participation.repository.AuctionRepository;
import com.selab.auction.participation.service.AuctionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
@RequiredArgsConstructor
public class RedissonAuctionLockService {
    private final RedissonClient redissonClient;
    private final AuctionRepository auctionRepository;

    @Transactional
    public void saveAuctionEntity(Auction auction) {
        RLock lock = redissonClient.getLock(String.format("auction:%d", auction.getId()));

        try {
            boolean isLocked = lock.tryLock(1, 3, TimeUnit.SECONDS);
            if (!isLocked) {
                log.info("Failed to acquire a lock");
                throw new FailedToAcquireLockException();
            }
            auctionRepository.save(auction);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (lock.isLocked()) {
                lock.unlock();
            }
        }
    }

}
