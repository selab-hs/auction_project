package com.selab.auction.error.exception.auction;

import com.selab.auction.error.dto.ErrorMessage;
import com.selab.auction.error.exception.BusinessException;

public class WrongAuctionIdException extends BusinessException {
    public WrongAuctionIdException() {
        super(ErrorMessage.WRONG_AUCTION_ID);
    }
}
