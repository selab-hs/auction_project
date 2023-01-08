package com.selab.auction.error.exception.member;

import com.selab.auction.error.dto.ErrorMessage;
import com.selab.auction.error.exception.BusinessException;

public class InacitveMemberException extends BusinessException {
    public InacitveMemberException() {
        super(ErrorMessage.INACTIVE_MEMBER);
    }
}
