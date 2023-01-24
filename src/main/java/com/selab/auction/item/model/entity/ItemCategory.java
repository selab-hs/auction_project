package com.selab.auction.item.model.entity;

import com.selab.auction.common.BaseEntity;
import com.selab.auction.item.model.vo.Category;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// TODO : 카테고리는 enum이 아닌, DB 데이터로 관리 필요
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemCategory extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Category category;
}
