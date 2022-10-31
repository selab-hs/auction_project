package com.selab.auction.item.controller;

import com.selab.auction.common.dto.ResponseDto;
import com.selab.auction.common.dto.SwaggerNote;
import com.selab.auction.item.model.dto.ItemCreateRequest;
import com.selab.auction.item.model.dto.ItemResponse;
import com.selab.auction.item.model.dto.ItemsResponse;
import com.selab.auction.item.service.ItemService;
import io.swagger.annotations.ApiOperation;
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
    @ApiOperation(value = "모든 상품 목록 조회", notes = SwaggerNote.ITEM_READ_ALL_NOTE)
    public ResponseEntity<ItemsResponse> getAllItems() {
        ItemsResponse items = itemService.getAllItems();
        return ResponseDto.ok(items);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "단건 상품 조회", notes = SwaggerNote.ITEM_READ_ONE_NOTE)
    public ResponseEntity<ItemResponse> getItemById(@PathVariable("id") Long id) {
        ItemResponse item = itemService.getItemById(id);
        return ResponseDto.ok(item);
    }

    @PostMapping
    @ApiOperation(value = "상품 등록 및 생성", notes = SwaggerNote.ITEM_CREATE_NOTE)
    public ResponseEntity<ItemResponse> createItem(@RequestBody ItemCreateRequest request) {
        ItemResponse item = itemService.createItem(request);
        return ResponseDto.created(item);
    }
}
