package com.selab.auction.participation.controller;

import com.selab.auction.participation.model.dto.AuctionResponseDto;
import com.selab.auction.participation.model.dto.CreateAuctionDto;
import com.selab.auction.participation.service.AuctionService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/auction")
public class AuctionController {
    private final AuctionService auctionService;

    @PostMapping
    @ApiOperation(value="경매 참여", notes = "해당 상품 번호와 멤버 번호, 경매 참여 가격 입력받아 경매 참여 진행하기")
    public ResponseEntity<AuctionResponseDto> participateAuction(@RequestBody @Valid CreateAuctionDto createDto){
        // requestBody Valid 적용해야함
        var response = auctionService.participateAuction(createDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }
}
