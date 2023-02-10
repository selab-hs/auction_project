package com.selab.auction.scheduler.service;

import com.selab.auction.item.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class SchedulerService {
    private final ItemService itemService;

    @Scheduled(cron = "0/30 * * * * *")
    public void runAfterTenMinuteRepeatItemPeriodValidation() {
        itemService.updateItemState();
    }
}
