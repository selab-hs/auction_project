package com.selab.auction.error.exception.member.join;

import com.selab.auction.error.dto.ErrorMessage;
import com.selab.auction.error.exception.BusinessException;

public class NotBlankException extends BusinessException {
    public NotBlankException() {
        super(ErrorMessage.NOT_BLANK);
    }
}
