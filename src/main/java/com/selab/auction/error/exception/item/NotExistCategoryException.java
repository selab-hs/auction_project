package com.selab.auction.error.exception.item;

import com.selab.auction.error.dto.ErrorMessage;
import com.selab.auction.error.exception.BusinessException;
import lombok.Getter;

@Getter
public class NotExistCategoryException extends BusinessException {
    public NotExistCategoryException() {
        super(ErrorMessage.NOT_EXITS_CATEGORY);
    }
}
