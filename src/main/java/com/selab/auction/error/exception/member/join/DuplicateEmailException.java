package com.selab.auction.error.exception.member.join;

import com.selab.auction.error.dto.ErrorMessage;
import com.selab.auction.error.exception.BusinessException;

public class DuplicateEmailException extends BusinessException {
    public DuplicateEmailException() {
        super(ErrorMessage.DUPLICATE_EMAIL);
    }
}
