package com.selab.auction.participation.service;

import com.selab.auction.error.exception.auction.CompletedAuctionException;
import com.selab.auction.error.exception.auction.WrongRequestPriceException;
import com.selab.auction.error.exception.item.WrongItemStateException;
import com.selab.auction.item.model.entity.Auction;
import com.selab.auction.item.model.entity.Item;
import com.selab.auction.item.model.vo.ItemState;
import com.selab.auction.item.service.ItemService;
import com.selab.auction.participation.model.dto.CreateAuctionDto;
import com.selab.auction.participation.model.dto.AuctionResponseDto;
import com.selab.auction.participation.repository.AuctionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;

@Service
@RequiredArgsConstructor
public class AuctionServiceImpl implements AuctionService{
    private final AuctionRepository auctionRepository;
    private final ItemService itemService;

    @Override
    @Transactional
    public AuctionResponseDto participateAuction(CreateAuctionDto createAuctionDto) {
        Item item = itemService.getItemEntityById(createAuctionDto.getItemId());

        if(item.getState().equals(ItemState.ACTIVE)){
            return validateFirstItemPrice(createAuctionDto, item);
        }

        if(item.getState().equals(ItemState.PROGRESS)) {
            return validateItemPrice(createAuctionDto, item);
        }

        if(item.getState().equals(ItemState.COMPLETE)) {
            throw new CompletedAuctionException();
        }

        throw new WrongItemStateException();
    }

    private AuctionResponseDto validateItemPrice(CreateAuctionDto createAuctionDto, Item item){
        Auction response = auctionRepository.findByItemId(item.getId()).stream()
                .max(Comparator.comparing(Auction::getAuctionPrice))
                .filter((auction)->createAuctionDto.getRequestPrice() > (auction.getAuctionPrice() + item.getPrice() * 0.01))
                .orElseThrow(WrongRequestPriceException::new);

        auctionRepository.save(response);

        return response.toResponseDto();
    }

    private AuctionResponseDto validateFirstItemPrice(CreateAuctionDto createAuctionDto, Item item){
        if(createAuctionDto.getRequestPrice() >= item.getPrice()){
            Auction response = auctionRepository.save(createAuctionDto.toEntity());
            item.updateState(ItemState.PROGRESS);

            return response.toResponseDto();
        }

        throw new WrongRequestPriceException();
    }
}
