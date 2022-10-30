package com.selab.auction.error.exception;

import com.selab.auction.error.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(final BusinessException e) {
        log.error("BusinessException : {}", e.getErrorMessage());
        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(e.getErrorMessage().getStatus())
                .description(e.getErrorMessage().getDescription())
                .build();
        return ResponseEntity.status(e.getErrorMessage().getStatus())
                .body(errorResponse);
    }
}
