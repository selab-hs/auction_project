package com.selab.auction.actionlog.repository;

import com.selab.auction.actionlog.model.SystemActionLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemActionLogRepository extends JpaRepository<SystemActionLog, Long> {
    
}
