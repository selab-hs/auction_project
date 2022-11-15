package com.selab.auction.item.model.entity;

import com.selab.auction.common.BaseEntity;
import com.selab.auction.participation.model.dto.AuctionResponseDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(indexes = {
        @Index(name="auction_index", columnList = "id")
}, name="auction")
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
    public Auction(long itemId, long memberId, long auctionPrice){
        this.itemId = itemId;
        this.memberId = memberId;
        this.auctionPrice = auctionPrice;
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
