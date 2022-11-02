package com.selab.auction.error.exception.item;

import com.selab.auction.error.dto.ErrorMessage;
import com.selab.auction.error.exception.BusinessException;
import lombok.Getter;

@Getter
public class DeleteItemException extends BusinessException {
    public DeleteItemException() {
        super(ErrorMessage.DELETE_ITEM);
    }
}
