package com.selab.auction.auction;

import static org.assertj.core.api.Assertions.assertThat;
import com.selab.auction.item.model.entity.Auction;
import com.selab.auction.participation.repository.AuctionRepository;
import com.selab.auction.participation.service.RedissonAuctionLockService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootTest
public class AuctionSaveTest {

    @Autowired
    private RedissonAuctionLockService auctionLockService;

    @Autowired
    private AuctionRepository auctionRepository;

    @Test
    void AuctionSaveTest() throws InterruptedException{
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        CountDownLatch countDownLatch = new CountDownLatch(100);

        for (int i = 1; i < 101; i++) {
            int finalI = i;
            executorService.submit(() -> {
                try {
                    Auction auctionTest = new Auction(finalI, finalI, 3000);
                    auctionLockService.saveAuctionEntity(auctionTest);
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();

        Long resultNum = auctionRepository.count();

        assertThat(resultNum).isEqualTo(100);
    }

    @Test
    void AuctionSaveTest2() throws InterruptedException{
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        CountDownLatch countDownLatch = new CountDownLatch(100);

        for (int i = 1; i < 101; i++) {
            int finalI = i;
            executorService.submit(() -> {
                try {
                    Auction auctionTest = new Auction(finalI, finalI, 3000);
                    auctionRepository.save(auctionTest);
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();

        Long resultNum = auctionRepository.count();

        assertThat(resultNum).isEqualTo(100);
    }
}
