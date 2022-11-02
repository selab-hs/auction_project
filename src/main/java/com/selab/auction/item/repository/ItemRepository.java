package com.selab.auction.item.repository;

import com.selab.auction.item.model.entity.Item;
import com.selab.auction.item.model.vo.ItemState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
}
