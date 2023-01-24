package com.selab.auction.actionlog.event.subscribe;

import com.selab.auction.actionlog.event.model.SystemActionLogEventModel;
import com.selab.auction.actionlog.model.SystemActionLog;
import com.selab.auction.actionlog.service.SystemActionLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SystemActionLogEventSubscribe {
    private final SystemActionLogService systemActionLogService;

    @Async(value = "systemActionLogExecutor")
    @EventListener(SystemActionLogEventModel.class)
    public void subscribe(SystemActionLogEventModel model) {
        var systemActionLog = new SystemActionLog(
                model.getIpAddress(),
                model.getPath(),
                model.getMethod(),
                model.getUserAgent(),
                model.getHost(),
                model.getReferer()
        );

        systemActionLogService.insert(systemActionLog);
    }
}
