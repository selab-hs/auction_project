package com.selab.auction.error.exception.member.find;

import com.selab.auction.error.dto.ErrorMessage;
import com.selab.auction.error.exception.BusinessException;
import lombok.Getter;

@Getter
public class NotExistMemberException extends BusinessException {
    public NotExistMemberException() {
        super(ErrorMessage.NOT_EXIST_MEMBER);
    }
}
