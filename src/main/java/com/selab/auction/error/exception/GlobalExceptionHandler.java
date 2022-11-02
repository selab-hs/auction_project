package com.selab.auction.error.exception;

import com.selab.auction.error.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> processValidationError(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();

        StringBuilder builder = new StringBuilder();
//        builder.append("")
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            builder.append("잘못된 요청 : [");
            builder.append(fieldError.getField());
            builder.append("](은)는 ");
            builder.append(fieldError.getDefaultMessage());
            builder.append(" 입력된 값: [");
            builder.append(fieldError.getRejectedValue());
            builder.append("]");
        }

        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST)
                .description(builder.toString())
                .build();


        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}
