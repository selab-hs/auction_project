package com.selab.auction.error.exception.item;

import com.selab.auction.error.dto.ErrorMessage;
import com.selab.auction.error.exception.BusinessException;

public class WrongItemStateException extends BusinessException {
    public WrongItemStateException(){
        super(ErrorMessage.WRONG_ITEM_STATE);
    }
}
