package com.selab.auction.error.exception.auction;

import com.selab.auction.error.dto.ErrorMessage;
import com.selab.auction.error.exception.BusinessException;

public class AlreadyExistsCommentException extends BusinessException {
    public AlreadyExistsCommentException(){
        super(ErrorMessage.DELETE_ITEM);
    }
}
