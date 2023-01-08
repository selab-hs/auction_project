package com.selab.auction.error.exception.member;

import com.selab.auction.error.dto.ErrorMessage;
import com.selab.auction.error.exception.BusinessException;

public class InvalidTokenException extends BusinessException {
    public InvalidTokenException() {
        super(ErrorMessage.INVALID_TOKEN);
    }
}
