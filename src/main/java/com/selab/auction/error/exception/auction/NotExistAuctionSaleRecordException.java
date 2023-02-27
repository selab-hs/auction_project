package com.selab.auction.error.exception.auction;

import com.selab.auction.error.dto.ErrorMessage;
import com.selab.auction.error.exception.BusinessException;

public class NotExistAuctionSaleRecordException extends BusinessException {
    public NotExistAuctionSaleRecordException() {
        super(ErrorMessage.NOT_EXISTS_AUCTION_SALE_RECORD);
    }
}
