package com.selab.auction.error.exception.member.join;

import com.selab.auction.error.dto.ErrorMessage;
import com.selab.auction.error.exception.BusinessException;

public class RegexpPatternException extends BusinessException {
    public RegexpPatternException() {
        super(ErrorMessage.REGEXP_PATTERN);
    }
}
