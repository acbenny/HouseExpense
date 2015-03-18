package com.acbenny.HouseExpenses.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.acbenny.HouseExpenses.exception.DAOException;
import com.acbenny.HouseExpenses.model.Item;
import com.acbenny.HouseExpenses.model.User;
import com.acbenny.HouseExpenses.service.ItemService;
import com.acbenny.HouseExpenses.service.UserService;

public class Test {

	public static void main(String[] args) {

		ApplicationContext context;
		context = new ClassPathXmlApplicationContext("context.xml");

		ItemService itemService = (ItemService) context.getBean("ItemService");
		try {
			Item item = itemService.getItem("Groceries", "");
			System.out.println(item.toString());			
		} catch (DAOException e) {
			System.out.println(e.getMessage());
		}

		// userService.getUserByUsername("ACBENNY");

		// ItemService itemService = (ItemService)
		// context.getBean("ItemService");

		// itemService.createItem("Milk", "20.415");

		// itemService.listItems();
		System.out.println("End!!!");
		
		((AbstractApplicationContext) context).close();
		
	}

}
