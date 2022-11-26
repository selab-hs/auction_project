package com.selab.auction.item.repository;

import com.selab.auction.item.model.entity.Item;
import com.selab.auction.item.model.vo.ItemState;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    @Transactional(readOnly = true)
    Page<Item> findAllByStateNot(Pageable pageable, ItemState state);


    List<Item> findByState(ItemState state);
}
