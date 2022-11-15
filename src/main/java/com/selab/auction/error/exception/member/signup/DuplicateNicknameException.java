package com.selab.auction.error.exception.member.signup;

import com.selab.auction.error.dto.ErrorMessage;
import com.selab.auction.error.exception.BusinessException;

public class DuplicateNicknameException extends BusinessException {
    public DuplicateNicknameException() {
        super(ErrorMessage.DUPLICATE_NICKNAME);
    }
}
