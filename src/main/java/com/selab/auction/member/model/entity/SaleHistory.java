package com.selab.auction.member.model.entity;

import com.selab.auction.common.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(indexes = @Index(name = "sale_history_index", columnList = "id"))
public class SaleHistory extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long itemId;

    private Long memberId;

    private Long buyMemberId;

    private Long itemPrice;
}
