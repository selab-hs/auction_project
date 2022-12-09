package com.selab.auction.participation.controller;

import com.selab.auction.common.dto.ResponseDto;
import com.selab.auction.participation.model.dto.*;
import com.selab.auction.participation.service.AuctionCommentService;
import com.selab.auction.participation.service.AuctionService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/auction/participation")
public class AuctionController {
    private final AuctionService auctionService;
    private final AuctionCommentService auctionCommentService;

    @PostMapping
    @ApiOperation(value = "경매 참여", notes = "해당 상품 번호와 멤버 번호, 경매 참여 가격 입력받아 경매 참여 진행하기")
    public ResponseEntity<AuctionResponseDto> participateAuction(@RequestBody @Valid CreateAuctionDto createDto) {
        AuctionResponseDto response = auctionService.participateAuction(createDto);

        return ResponseDto.created(response);
    }

    @PostMapping("/immediate-purchase")
    @ApiOperation(value = "상품 즉시 구매", notes = "해당 상품 번호와 멤버 번호를 입력받아, 즉시 구매 가격으로 구매 진행하기")
    public ResponseEntity<AuctionResponseDto> immediatePurchaseItem(@RequestBody @Valid CreateImmediatePurchaseDto createDto) {
        AuctionResponseDto response = auctionService.immediatePurchaseItem(createDto);

        return ResponseDto.created(response);
    }

    @GetMapping
    @ApiOperation(value = "특정 경매 조회", notes = "해당 상품 번호의 경매 진행 여부 조회하기")
    public ResponseEntity<AuctionResponseDto> searchAuctionHistoryById(@Positive long id) {
        AuctionResponseDto response = auctionService.searchAuctionHistoryById(id);

        return ResponseDto.ok(response);
    }

    @PostMapping("/buy-comment")
    @ApiOperation(value = "구매자 코멘트 등록", notes = "해당 경매의 구매자가 판매자에 대한 코멘트와 별점 등록하기")
    public ResponseEntity<AuctionBuyCommentResponseDto> registerBuyComment(@RequestBody @Valid CreateBuyCommentDto commentDto) {
        AuctionBuyCommentResponseDto response = auctionCommentService.registerBuyComment(commentDto);

        return ResponseDto.created(response);
    }

    @PostMapping("/sale-comment")
    @ApiOperation(value = "판매자 코멘트 등록", notes = "해당 경매의 판매자가 구매자에 대한 코멘트와 별점 등록하기")
    public ResponseEntity<AuctionSaleCommentResponseDto> registerSaleComment(@RequestBody @Valid CreateSaleCommentDto commentDto) {
        AuctionSaleCommentResponseDto response = auctionCommentService.registerSaleComment(commentDto);

        return ResponseDto.created(response);
    }
}
