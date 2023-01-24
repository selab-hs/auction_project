package com.selab.auction.actionlog.service;

import com.selab.auction.actionlog.model.SystemActionLog;
import com.selab.auction.actionlog.repository.SystemActionLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SystemActionLogService {
    private final SystemActionLogRepository systemActionLogRepository;

    public void insert(SystemActionLog systemActionLog) {
        systemActionLogRepository.save(systemActionLog);
    }
}
