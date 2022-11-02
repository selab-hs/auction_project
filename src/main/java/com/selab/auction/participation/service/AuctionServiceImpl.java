package com.selab.auction.participation.service;

import com.selab.auction.error.exception.auction.WrongRequestPrice;
import com.selab.auction.item.model.entity.Auction;
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
        var item = itemService.getItemById(createAuctionDto.getItemId());

        var auction = auctionRepository.findByItemId(item.getId()).stream()
                .max(Comparator.comparing(Auction::getAuctionPrice));

        if(auction.isPresent()){
            if(validateItemPrice(createAuctionDto.getRequestPrice(), auction.get().getAuctionPrice(), item.getPrice())){
                Auction response = auctionRepository.save(createAuctionDto.toEntity());
                return response.toResponseDto();
            }

            throw new WrongRequestPrice();
        }

        if(validateFirstItemPrice(createAuctionDto.getRequestPrice(), item.getPrice())){
            Auction response = auctionRepository.save(createAuctionDto.toEntity());
            return response.toResponseDto();
        }

        throw new WrongRequestPrice();
    }

    private boolean validateItemPrice(long requestPrice, long auctionPrice, long firstItemPrice){
        return requestPrice > (auctionPrice + firstItemPrice * 0.01);
    }

    private boolean validateFirstItemPrice(long requestPrice, long firstItemPrice){
        return requestPrice >= firstItemPrice;
    }
}
