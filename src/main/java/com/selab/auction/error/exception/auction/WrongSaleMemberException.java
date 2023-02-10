package com.selab.auction.error.exception.auction;

import com.selab.auction.error.dto.ErrorMessage;
import com.selab.auction.error.exception.BusinessException;

public class WrongSaleMemberException extends BusinessException {
    public WrongSaleMemberException() {
        super(ErrorMessage.WRONG_SALE_MEMBER);
    }
}
