package com.acbenny.HouseExpenses.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.acbenny.HouseExpenses.model.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUserName(String userName);
}
