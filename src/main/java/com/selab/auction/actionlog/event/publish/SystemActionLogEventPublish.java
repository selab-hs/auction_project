package com.selab.auction.actionlog.event.publish;

import com.selab.auction.actionlog.event.model.SystemActionLogEventModel;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@RequiredArgsConstructor
public class SystemActionLogEventPublish {
    private final ApplicationEventPublisher applicationEventPublisher;

    public void publish(HttpServletRequest request) {
        var event = new SystemActionLogEventModel(request);
        applicationEventPublisher.publishEvent(event);
    }
}
