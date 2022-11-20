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
    public AuctionResponseDto participateAuction(CreateAuctionDto createAuctionDto) {
        memberFindService.findById(createAuctionDto.getMemberId());
        Item item = itemService.getItemEntityById(createAuctionDto.getItemId());

        return switch (item.getState()) {
            case ACTIVE -> validateFirstItemPrice(createAuctionDto, item);
            case PROGRESS -> validateItemPrice(createAuctionDto, item);
            case COMPLETE -> throw new CompletedAuctionException();
            default -> throw new WrongItemStateException();
        };
    }

    @Override
    public AuctionResponseDto searchAuctionHistoryById(long id) {
        return auctionRepository.findById(id).orElseThrow(WrongAuctionIdException::new).toResponseDto();
    }

    private AuctionResponseDto validateItemPrice(CreateAuctionDto createAuctionDto, Item item) {
        Auction response = auctionRepository.findByItemId(item.getId()).stream()
                .max(Comparator.comparing(Auction::getAuctionPrice))
                .filter((auction) -> createAuctionDto.getRequestPrice() > (auction.getAuctionPrice() + item.getPrice() * 0.01))
                .orElseThrow(WrongRequestPriceException::new);

        auctionRepository.save(response);

        return response.toResponseDto();
    }

    private AuctionResponseDto validateFirstItemPrice(CreateAuctionDto createAuctionDto, Item item) {
        if (createAuctionDto.getRequestPrice() >= item.getPrice()) {
            Auction response = auctionRepository.save(createAuctionDto.toEntity());
            item.updateState(ItemState.PROGRESS);

            return response.toResponseDto();
        }

        throw new WrongRequestPriceException();
    }
}
