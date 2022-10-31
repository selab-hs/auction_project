package com.selab.auction.item.model.entity;

import com.selab.auction.common.BaseEntity;
import com.selab.auction.participation.model.dto.AuctionResponseDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Auction extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long itemId;

    @Column(nullable = false)
    private Long memberId;

    @Column(name = "item_price", nullable = false)
    private Long auctionPrice;

    @Builder
    public Auction(long itemId, long memberId, long requestPrice){
        this.itemId = itemId;
        this.memberId = memberId;
        this.auctionPrice = requestPrice;
    }

    public AuctionResponseDto toResponseDto(){
        return AuctionResponseDto.builder()
                .id(this.id)
                .itemId(this.itemId)
                .memberId(this.memberId)
                .auctionPrice(this.auctionPrice)
                .build();
    }
}
