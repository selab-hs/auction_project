package com.selab.auction.error.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
public class ErrorResponse {
    private HttpStatus status;
    private String description;

    public ErrorResponse(ErrorMessage message) {
        this.status = message.getStatus();
        this.description = message.getDescription();
    }

    @Builder
    public ErrorResponse(HttpStatus status, String description) {
        this.status = status;
        this.description = description;
    }
}
