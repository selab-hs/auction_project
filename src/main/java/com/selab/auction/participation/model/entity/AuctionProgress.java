package com.selab.auction.participation.model.entity;

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
public class AuctionProgress extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long itemId;

    @Column
    private Long memberId;

    @Column
    private Long auctionPrice;

    @Builder
    public AuctionProgress(long itemId, long memberId, long requestPrice){
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
