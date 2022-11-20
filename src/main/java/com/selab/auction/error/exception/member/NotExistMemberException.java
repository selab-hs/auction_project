package com.selab.auction.error.exception.member;

import com.selab.auction.error.dto.ErrorMessage;
import com.selab.auction.error.exception.BusinessException;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Slf4j
public class NotExistMemberException extends BusinessException {
    public NotExistMemberException() {
        super(ErrorMessage.NOT_EXIST_MEMBER);
    }
}
