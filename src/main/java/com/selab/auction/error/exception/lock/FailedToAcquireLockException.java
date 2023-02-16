package com.selab.auction.error.exception.lock;

import com.selab.auction.error.dto.ErrorMessage;
import com.selab.auction.error.exception.BusinessException;

public class FailedToAcquireLockException extends BusinessException {
    public FailedToAcquireLockException(){
        super(ErrorMessage.FAILED_TO_ACQUIRE_LOCK);
    }
}
