package com.selab.auction.item;

import com.selab.auction.item.model.dto.ItemCreateRequest;
import com.selab.auction.item.model.dto.ItemUpdateRequest;
import com.selab.auction.item.model.entity.Item;
import com.selab.auction.item.model.vo.ItemState;
import com.selab.auction.item.service.ItemLockService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ItemSaveTest {

    @Autowired
    private ItemLockService itemLockService;

    @Test
    void 아이템저장_동시성_테스트() throws InterruptedException{
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        CountDownLatch countDownLatch = new CountDownLatch(100);

        for (int i = 1; i < 101; i++) {
            Long finalL = (long) i;
            executorService.submit(() -> {
                try {
                    ItemCreateRequest itemRequestTest = new ItemCreateRequest("테스트 아이템", 30000L, "음식상품", "FOOD", finalL, 4, 35000L);
                    itemLockService.createItem(Item.of(itemRequestTest));
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();

        assertThat(countDownLatch.getCount()).isZero();
    }

    @Test
    void 아이템_정보변경_테스트(){
        ItemCreateRequest itemRequestTest = new ItemCreateRequest("테스트 아이템", 30000L, "음식상품", "FOOD", 1L, 4, 35000L);
        Item itemTest = itemLockService.createItem(Item.of(itemRequestTest));

        ItemUpdateRequest itemUpdateRequest = new ItemUpdateRequest("테스트 아이템2", 34000L, "음식상품", "FOOD", 5, 37000L);
        itemLockService.updateItem(itemTest, itemUpdateRequest);


        assertThat(itemTest.getPrice()).isEqualTo(34000L);
        assertThat(itemTest.getAuctionPeriod()).isEqualTo(5);
        assertThat(itemTest.getImmediatelyPrice()).isEqualTo(37000L);
    }

    @Test
    void 아이템_상태변경_테스트(){
        ItemCreateRequest itemRequestTest = new ItemCreateRequest("테스트 아이템", 30000L, "음식상품", "FOOD", 1L, 4, 35000L);
        Item itemTest = itemLockService.createItem(Item.of(itemRequestTest));

        itemLockService.updateItemState(itemTest, ItemState.INACTIVE);

        assertThat(itemTest.getState()).isSameAs(ItemState.INACTIVE);
    }
}