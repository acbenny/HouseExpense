package com.acbenny.HouseExpenses.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.acbenny.HouseExpenses.service.UserService;

public class Test {

	public static void main(String[] args) {

		ApplicationContext context;
		context = new ClassPathXmlApplicationContext("Beans.xml");

		UserService userService = (UserService) context.getBean("UserService");
		
		// userService.createUser("ACBENNY4", "Arun Benny2",
		// "acbenny4@gmail.com", "Password");

		userService.getUserByUsername("ACBENNY");

		// userService.listUsers();
		
		System.out.println("End!!!");
		
		((AbstractApplicationContext) context).close();
		
	}

}
