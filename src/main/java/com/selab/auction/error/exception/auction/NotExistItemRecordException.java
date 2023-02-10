package com.selab.auction.error.exception.auction;

import com.selab.auction.error.dto.ErrorMessage;
import com.selab.auction.error.exception.BusinessException;

public class NotExistItemRecordException extends BusinessException {
    public NotExistItemRecordException() {
        super(ErrorMessage.NOT_EXISTS_AUCTION_ITEM_RECORD);
    }
}
