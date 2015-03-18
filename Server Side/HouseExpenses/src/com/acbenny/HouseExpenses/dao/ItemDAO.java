package com.acbenny.HouseExpenses.dao;

import java.util.List;

import com.acbenny.HouseExpenses.model.Item;

public interface ItemDAO {

	public void createItem(Item item);

	public List<Item> getItemList();

	public Item getItemByName(String itemName);
}
