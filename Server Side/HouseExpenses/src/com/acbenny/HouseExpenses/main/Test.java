package com.acbenny.HouseExpenses.main;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.acbenny.HouseExpenses.model.User;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("HouseExpenses");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		
		User user = new User();
		user.setName("Shweta Gupta");
		user.setUsername("SG");
		user.setEmail("sfasdlkfkjas@bt.com");
		user.setPwdSalt("temp pwd dsafsalt");
		user.setPwdHash("temp pwd safashash");
		entityManager.persist(user);
		
		entityManager.getTransaction().commit();
		entityManager.close();
		
		System.out.println("End!!!");
		entityManagerFactory.close();
		
		entityManager=entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		
	}

}
