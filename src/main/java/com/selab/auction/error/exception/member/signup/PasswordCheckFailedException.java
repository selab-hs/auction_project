package com.selab.auction.error.exception.member.signup;

import com.selab.auction.error.dto.ErrorMessage;
import com.selab.auction.error.exception.BusinessException;

public class PasswordCheckFailedException extends BusinessException {
    public PasswordCheckFailedException() {
        super(ErrorMessage.PASSWORD_CHECK_FAILED);
    }
}