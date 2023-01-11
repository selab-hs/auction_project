package com.selab.auction.error.exception.member;

import com.selab.auction.error.dto.ErrorMessage;
import com.selab.auction.error.exception.BusinessException;

public class RefreshTokenExpiredException extends BusinessException {
    public RefreshTokenExpiredException() {
        super(ErrorMessage.REFRESH_TOKEN_EXPIRED);
    }
}
