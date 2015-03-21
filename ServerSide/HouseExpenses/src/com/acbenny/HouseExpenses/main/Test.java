package com.acbenny.HouseExpenses.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.acbenny.HouseExpenses.exception.ServiceException;
import com.acbenny.HouseExpenses.model.entity.User;
import com.acbenny.HouseExpenses.service.UserService;

public class Test {

	public static void main(String[] args) {

		ApplicationContext context;
		context = new ClassPathXmlApplicationContext("context.xml");

		UserService userService = (UserService) context
				.getBean("UserService");

		User user = null;
		try {
			user = userService.getUserByUserName("USER2");
			System.out.println(user.toString());

		} catch (ServiceException e) {
			System.out.println("ERR" + e.getMessage());
		}


		// dataSetUp(context);

		// performActivity(context);

		((AbstractApplicationContext) context).close();
		System.out.println("End!!!");
	}

}