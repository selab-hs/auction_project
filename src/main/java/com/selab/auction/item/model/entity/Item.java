package com.selab.auction.item.model.entity;

import com.selab.auction.common.BaseEntity;
import com.selab.auction.common.State;
import com.selab.auction.item.model.dto.ItemCreateRequest;
import com.selab.auction.item.model.vo.Category;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "member_id", nullable = false)
    private Long memberId;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(name = "item_name", nullable = false)
    private String name;

    @Column(name = "item_price", nullable = false)
    private Long price;

    @Column(name = "item_description")
    private String description;

    private Integer auctionPeriod;

    @Column(nullable = false)
    private Long immediatelyPrice;

    @Enumerated(EnumType.STRING)
    private State state;

    @Builder
    public Item(Long memberId, Category category, String name, Long price, String description, Integer auctionPeriod, Long immediatelyPrice, State state) {
        this.memberId = memberId;
        this.category = category;
        this.name = name;
        this.price = price;
        this.description = description;
        this.auctionPeriod = auctionPeriod;
        this.immediatelyPrice = immediatelyPrice;
        this.state = state;
    }

    public static Item of(ItemCreateRequest request) {
        return Item.builder()
                .memberId(request.memberId())
                .category(Category.categoryConverter(request.category()))
                .name(request.name())
                .price(request.price())
                .description(request.description())
                .auctionPeriod(request.auctionPeriod())
                .immediatelyPrice(request.immediatelyPrice())
                .state(State.ACTIVE)
                .build();
    }
}
