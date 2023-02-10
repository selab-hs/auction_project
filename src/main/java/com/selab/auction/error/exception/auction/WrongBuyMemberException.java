package com.selab.auction.error.exception.auction;

import com.selab.auction.error.dto.ErrorMessage;
import com.selab.auction.error.exception.BusinessException;

public class WrongBuyMemberException extends BusinessException {
    public WrongBuyMemberException() {
        super(ErrorMessage.WRONG_BUY_MEMBER);
    }
}
