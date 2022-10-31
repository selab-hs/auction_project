package com.selab.auction.item.model.vo;

import com.selab.auction.error.exception.item.NotExistCategoryException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Category {
    FOOD("음식"),
    LIFE("생활"),
    ;

    private final String description;

    public static Category categoryConverter(String str) {
        for(Category c : Category.values()) {
            if(c.toString().equals(str)) {
                return c;
            }
        }
        throw new NotExistCategoryException();
    }
}
