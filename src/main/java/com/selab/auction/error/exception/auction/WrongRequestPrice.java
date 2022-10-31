package com.selab.auction.error.exception.auction;

import com.selab.auction.error.dto.ErrorMessage;
import com.selab.auction.error.exception.BusinessException;

public class WrongRequestPrice extends BusinessException {
    public WrongRequestPrice() {
        super(ErrorMessage.WRONG_REQUEST_PRICE);
    }
}
