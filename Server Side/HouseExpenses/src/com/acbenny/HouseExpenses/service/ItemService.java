package com.acbenny.HouseExpenses.service;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.acbenny.HouseExpenses.dao.ItemDAO;
import com.acbenny.HouseExpenses.exception.ErrorCodes;
import com.acbenny.HouseExpenses.exception.ServiceException;
import com.acbenny.HouseExpenses.model.Item;
import com.acbenny.HouseExpenses.model.User;

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
	
	public Item createItem(String itemName, String price) throws ServiceException {
		BigDecimal bd = null;
		if (!(price == null || "".equals(price))) {
			bd = (new BigDecimal(price)).setScale(2, BigDecimal.ROUND_HALF_UP);
		}
		Item item = new Item();
		item.setItemName(itemName);
		item.setPrice(bd);
		try {
			itemDAO.createItem(item);
		}catch (DataIntegrityViolationException dataEx) {
			translateIntegrityException(dataEx);
		}
		return item;
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

	public Item getItem(String itemName,String price) throws ServiceException {
		Item item = null;
		try {
			item = itemDAO.getItemByName(itemName);
		}catch (EmptyResultDataAccessException e) {
			item = createItem(itemName, price);
		}
		return item;
	}
	
	private void translateIntegrityException(DataIntegrityViolationException ex) throws ServiceException {
		if ((ex.getCause()!=null)&&(ex.getCause() instanceof ConstraintViolationException)) {
			String constraintName = (((ConstraintViolationException)ex.getCause()).getConstraintName());
			if (constraintName != null) {
				if (constraintName.endsWith(User.getConstraintName("itemname"))) {
					throw new ServiceException(ErrorCodes.DUPLICATE_ITEM);
				}
			}
		}
		throw ex;
	}
}
