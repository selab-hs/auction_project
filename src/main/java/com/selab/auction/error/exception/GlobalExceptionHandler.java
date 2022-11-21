package com.selab.auction.error.exception;

import com.selab.auction.error.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(final BusinessException e) {
        log.error("BusinessException : {}", e.getErrorMessage());

        var errorResponse = new ErrorResponse(e.getErrorMessage());

        return ResponseEntity
                .status(e.getErrorMessage().getStatus())
                .body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> processValidationError(MethodArgumentNotValidException e) {
        var bindingResult = e.getBindingResult();

        var description = bindingResult.getFieldErrors()
                .stream()
                .map(fieldError ->
                        "잘못된 요청 : [" + fieldError.getField() + "](은)는 " + fieldError.getDefaultMessage() + " 입력된 값: [" + fieldError.getRejectedValue() + "]"
                ).collect(Collectors.joining());

        var errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST, description);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);
    }
}
