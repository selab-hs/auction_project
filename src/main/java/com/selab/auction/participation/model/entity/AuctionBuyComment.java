package com.selab.auction.participation.model.entity;

import com.selab.auction.common.BaseEntity;
import com.selab.auction.participation.model.dto.AuctionBuyCommentResponseDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(indexes = {
        @Index(name="auction_buy_comment_index", columnList = "id")
}, name="auction_buy_comment")
public class AuctionBuyComment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long itemId;

    @Column
    private Long buyMemberId;

    @Column
    private Long saleMemberId;

    @Column
    private String comment;

    @Column
    private Double grade;

    @Builder
    public AuctionBuyComment(long itemId, long buyMemberId, long saleMemberId, String comment, double grade){
        this.itemId = itemId;
        this.buyMemberId = buyMemberId;
        this.saleMemberId = saleMemberId;
        this.comment = comment;
        this.grade = grade;
    }

    public AuctionBuyCommentResponseDto toResponseDto(){
        return AuctionBuyCommentResponseDto.builder()
                .id(this.id)
                .itemId(this.itemId)
                .buyMemberId(this.buyMemberId)
                .saleMemberId(this.saleMemberId)
                .comment(this.comment)
                .grade(this.grade)
                .build();
    }

    public void changeBuyComment(String comment, Double grade) {
        this.comment = comment;
        this.grade = grade;
    }
}
