package com.selab.auction.scheduler.service;

import com.selab.auction.item.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class SchedulerService {

    private final ItemService itemService;

    @Scheduled(cron = "0 0/10 * * * *")
    @Transactional
    public void runAfterTenMinuteRepeatItemPeriodValidation() {
        log.info("10분 주기 아이템 경매 기간 점검 실행 시간  => time : " + LocalTime.now());

        itemService.updateItemStateByScheduling();
    }
}
