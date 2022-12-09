package com.selab.auction.participation.service;

import com.selab.auction.error.exception.auction.CompletedAuctionException;
import com.selab.auction.error.exception.auction.WrongAuctionIdException;
import com.selab.auction.error.exception.auction.WrongRequestPriceException;
import com.selab.auction.error.exception.item.WrongItemStateException;
import com.selab.auction.item.model.entity.Auction;
import com.selab.auction.item.model.entity.Item;
import com.selab.auction.item.model.vo.ItemState;
import com.selab.auction.item.service.ItemService;
import com.selab.auction.member.service.MemberFindService;
import com.selab.auction.participation.model.dto.AuctionResponseDto;
import com.selab.auction.participation.model.dto.CreateAuctionDto;
import com.selab.auction.participation.model.dto.CreateImmediatePurchaseDto;
import com.selab.auction.participation.repository.AuctionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;

// TODO : 코드 리펙토링 필요
@Service
@RequiredArgsConstructor
public class AuctionServiceImpl implements AuctionService {
    private final AuctionRepository auctionRepository;
    private final MemberFindService memberFindService;
    private final ItemService itemService;

    @Override
    @Transactional
    public AuctionResponseDto participateAuction(CreateAuctionDto createDto) {
        var item = validateAuctionRequest(createDto.getMemberId(), createDto.getItemId());

        return switch (item.getState()) {
            case ACTIVE -> validateFirstItemPrice(createDto, item);
            case PROGRESS -> validateItemPrice(createDto, item);
            case COMPLETE -> throw new CompletedAuctionException();
            default -> throw new WrongItemStateException();
        };
    }

    @Override
    @Transactional(readOnly = true)
    public AuctionResponseDto searchAuctionHistoryById(long id) {
        return auctionRepository.findById(id).orElseThrow(WrongAuctionIdException::new).toResponseDto();
    }

    @Override
    @Transactional
    public AuctionResponseDto immediatePurchaseItem(CreateImmediatePurchaseDto createDto) {
        var item = validateAuctionRequest(createDto.getMemberId(), createDto.getItemId());

        switch (item.getState()) {
            case ACTIVE, PROGRESS -> {
                var response = auctionRepository.save(createDto.toEntity(item.getImmediatelyPrice()));
                itemService.updateItemStateToCompleted(item);

                return response.toResponseDto();
            }
            case COMPLETE -> throw new CompletedAuctionException();
            default -> throw new WrongItemStateException();
        }
    }

    private AuctionResponseDto validateItemPrice(CreateAuctionDto createAuctionDto, Item item) {
        var response = auctionRepository.findByItemId(item.getId()).stream()
                .max(Comparator.comparing(Auction::getAuctionPrice))
                .filter((auction) -> createAuctionDto.getRequestPrice() > (auction.getAuctionPrice() + item.getPrice() * 0.01))
                .orElseThrow(WrongRequestPriceException::new);

        auctionRepository.save(response);

        return response.toResponseDto();
    }

    private AuctionResponseDto validateFirstItemPrice(CreateAuctionDto createAuctionDto, Item item) {
        if (createAuctionDto.getRequestPrice() >= item.getPrice()) {
            var response = auctionRepository.save(createAuctionDto.toEntity());
            item.updateState(ItemState.PROGRESS);

            return response.toResponseDto();
        }

        throw new WrongRequestPriceException();
    }

    public Item validateAuctionRequest(Long memberId, Long itemId) {
        memberFindService.findById(memberId);
        return itemService.getItemEntityById(itemId);
    }
}
