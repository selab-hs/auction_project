package com.selab.auction.error.exception.item;

import com.selab.auction.error.dto.ErrorMessage;
import com.selab.auction.error.exception.BusinessException;
import lombok.Getter;

@Getter
public class NotExistItemException extends BusinessException {
    public NotExistItemException() {
        super(ErrorMessage.NOT_EXIST_ITEM);
    }
}
