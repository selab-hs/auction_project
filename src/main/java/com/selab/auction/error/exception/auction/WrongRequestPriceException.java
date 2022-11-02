package com.selab.auction.error.exception.auction;

import com.selab.auction.error.dto.ErrorMessage;
import com.selab.auction.error.exception.BusinessException;

public class WrongRequestPriceException extends BusinessException {
    public WrongRequestPriceException() {
        super(ErrorMessage.WRONG_REQUEST_PRICE);
    }
}
