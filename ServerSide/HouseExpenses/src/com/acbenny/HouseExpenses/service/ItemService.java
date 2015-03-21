package com.acbenny.HouseExpenses.service;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acbenny.HouseExpenses.exception.ErrorCodes;
import com.acbenny.HouseExpenses.exception.ServiceException;
import com.acbenny.HouseExpenses.model.entity.Item;
import com.acbenny.HouseExpenses.model.entity.User;
import com.acbenny.HouseExpenses.repository.ItemRepository;

@Service
public class ItemService {

	@Autowired
	ItemRepository itemRepository;

	@Transactional()
	public Item createItem(String itemName, String price)
			throws ServiceException {
		BigDecimal bd = null;
		if (!(price == null || "".equals(price))) {
			bd = (new BigDecimal(price)).setScale(2, BigDecimal.ROUND_HALF_UP);
		}
		Item item = new Item();
		item.setItemName(itemName);
		item.setPrice(bd);
		try {
			item = itemRepository.saveAndFlush(item);
		} catch (DataIntegrityViolationException dataEx) {
			translateIntegrityException(dataEx);
		}
		return item;
	}

	@Transactional(readOnly = true)
	public List<Item> getItemList(String searchString, Pageable pageSpec)
			throws ServiceException {
		List<Item> itemList = null;
		if (searchString != null && !"".equals(searchString)) {
			itemList = itemRepository.findByItemNameContainingIgnoreCase(searchString, pageSpec).getContent();
		}else {
			itemList = itemRepository.findAll(pageSpec).getContent();
		}
		if (itemList == null) {
			throw new ServiceException(ErrorCodes.NO_ITEMS);
		}
		return itemList;
	}

	@Transactional(readOnly = true)
	public Item getItem(String itemName, String price) throws ServiceException {
		Item item = itemRepository.findByItemName(itemName);
		if (item == null)
			item = createItem(itemName, price);
		return item;
	}

	private void translateIntegrityException(DataIntegrityViolationException ex)
			throws ServiceException {
		if ((ex.getCause() != null)
				&& (ex.getCause() instanceof ConstraintViolationException)) {
			String constraintName = (((ConstraintViolationException) ex
					.getCause()).getConstraintName());
			if (constraintName != null) {
				if (constraintName.endsWith(User.getConstraintName("itemname"))) {
					throw new ServiceException(ErrorCodes.DUPLICATE_ITEM);
				}
			}
		}
		throw ex;
	}
}
