package com.selab.auction.error.exception.auction;

import com.selab.auction.error.dto.ErrorMessage;
import com.selab.auction.error.exception.BusinessException;

public class CompletedAuctionException extends BusinessException {
    public CompletedAuctionException(){
        super(ErrorMessage.COMPLETED_AUCTION);
    }
}
