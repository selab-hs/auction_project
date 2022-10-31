package com.selab.auction.item.controller;

import com.selab.auction.common.dto.ResponseDto;
import com.selab.auction.item.model.dto.ItemCreateRequest;
import com.selab.auction.item.model.dto.ItemResponse;
import com.selab.auction.item.model.dto.ItemsResponse;
import com.selab.auction.item.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/items")
public class ItemController {

    private final ItemService itemService;

    @GetMapping
    public ResponseEntity<ItemsResponse> getAllItems() {
        ItemsResponse items = itemService.getAllItems();
        return ResponseDto.ok(items);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemResponse> getItemById(@PathVariable("id") Long id) {
        ItemResponse item = itemService.getItemById(id);
        return ResponseDto.ok(item);
    }

    @PostMapping
    public ResponseEntity<ItemResponse> createItem(@RequestBody ItemCreateRequest request) {
        ItemResponse item = itemService.createItem(request);
        return ResponseDto.created(item);
    }
}
