package com.selab.auction.redisson;

import com.selab.auction.error.exception.lock.FailedToAcquireLockException;
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
public class RedissonLockService {
    private final RedissonClient redissonClient;

    @Transactional
    public void acquireRock(String lockName) {
        RLock lock = redissonClient.getLock(lockName);

        try {
            boolean isLocked = lock.tryLock(1, 3, TimeUnit.SECONDS);
            if (!isLocked) {
                log.info("Failed to acquire a lock");
                throw new FailedToAcquireLockException();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (lock.isLocked()) {
                lock.unlock();
            }
        }
    }

}
