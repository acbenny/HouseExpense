package com.acbenny.HouseExpenses.main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.acbenny.HouseExpenses.exception.ServiceException;
import com.acbenny.HouseExpenses.model.Item;
import com.acbenny.HouseExpenses.model.Share;
import com.acbenny.HouseExpenses.model.User;
import com.acbenny.HouseExpenses.service.ExpenseLogService;
import com.acbenny.HouseExpenses.service.ItemService;
import com.acbenny.HouseExpenses.service.UserService;

public class Test {

	public static void main(String[] args) {

		ApplicationContext context;
		context = new ClassPathXmlApplicationContext("context.xml");

		// dataSetUp(context);

		performActivity(context);

		((AbstractApplicationContext) context).close();
		System.out.println("End!!!");
	}

	public static void performActivity(ApplicationContext context) {
		UserService userService = (UserService) context.getBean("UserService");

		try {
			userService.printExpenses("USER2");
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void dataSetUp(ApplicationContext context) {
		ItemService itemService = (ItemService) context.getBean("ItemService");
		UserService userService = (UserService) context.getBean("UserService");
		ExpenseLogService expenseLogService = (ExpenseLogService) context
				.getBean("ExpenseLogService");
		try {
			Item item = itemService.getItem("Groceries", null);
			User user = userService.getUserByUsername("ACBENNY");
			Date date = new SimpleDateFormat("yyyy-mm-dd").parse("2014-12-25");
			Share share = null;
			List<Share> shareList = new ArrayList<Share>();
			share = new Share();
			share.setUser(user);
			share.setShareMultiplier(2);
			shareList.add(share);
			share = new Share();
			share.setUser(userService.getUserByUsername("USER2"));
			share.setShareMultiplier(1);
			shareList.add(share);
			share = new Share();
			share.setUser(userService.getUserByUsername("USER3"));
			share.setShareMultiplier(1);
			shareList.add(share);
			
			expenseLogService.createLog(user, date, item, 0, "30.456",
					shareList);
		} catch (ServiceException e) {
			System.out.println(e.getMessage());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
