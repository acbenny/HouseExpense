package com.acbenny.HouseExpenses.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.acbenny.HouseExpenses.model.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {

	Item findByItemName(String itemName);

	Page<Item> findByItemNameContainingIgnoreCase(String searchString,
			Pageable pageSpec);
}
