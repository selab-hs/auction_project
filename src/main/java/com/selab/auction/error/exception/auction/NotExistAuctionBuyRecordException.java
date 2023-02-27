package com.selab.auction.error.exception.auction;

import com.selab.auction.error.dto.ErrorMessage;
import com.selab.auction.error.exception.BusinessException;

public class NotExistAuctionBuyRecordException extends BusinessException {
    public NotExistAuctionBuyRecordException() {
        super(ErrorMessage.NOT_EXISTS_AUCTION_BUY_RECORD);
    }
}
