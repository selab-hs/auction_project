package com.selab.auction.error.exception.member.join;

import com.selab.auction.error.dto.ErrorMessage;
import com.selab.auction.error.exception.BusinessException;

public class EmailPatternException extends BusinessException {
    public EmailPatternException() {
        super(ErrorMessage.EMAIL_PATTERN);
    }
}
