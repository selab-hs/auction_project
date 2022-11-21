package com.selab.auction.common.dto;

import org.springframework.http.ResponseEntity;

import java.io.Serializable;
import java.util.List;

public record PageDto<T>(
        List<T> data,
        int page,
        int size,
        int totalPages,
        long totalElements,
        boolean firstPage,
        boolean lastPage
) implements Serializable {
    public static <T> ResponseEntity<T> ok(T data) {
        return ResponseEntity.ok(data);
    }
}
