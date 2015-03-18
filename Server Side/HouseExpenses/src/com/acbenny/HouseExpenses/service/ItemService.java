package com.acbenny.HouseExpenses.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.acbenny.HouseExpenses.dao.ItemDAO;
import com.acbenny.HouseExpenses.model.Item;

@Service("ItemService")
public class ItemService {

	@Autowired
	@Qualifier("ItemDAO")
	ItemDAO itemDAO;

	public ItemDAO getItemDAO() {
		return itemDAO;
	}

	public void setItemDAO(ItemDAO itemDAO) {
		this.itemDAO = itemDAO;
	}
	
	public void createItem(String itemName, String price) {
		BigDecimal bd = (new BigDecimal(price)).setScale(2, RoundingMode.HALF_UP);
		Item item = new Item();
		item.setItemName(itemName);
		item.setPrice(bd);
		itemDAO.createItem(item);
	}

	public void listItems() {
		List<Item> itemList = itemDAO.getItemList();
		if (itemList == null) {
			System.out.println("No items in DB");
		} else {
			for (Item item : itemList) {
				System.out.println(item.toString());
			}
		}
	}

	public void getItemByName(String itemname) {
		Item item = itemDAO.getItemByName(itemname);
		System.out.println(item.toString());
	}
}
