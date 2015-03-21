package com.acbenny.HouseExpenses.repository;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.acbenny.HouseExpenses.model.entity.ExpenseLog;

public interface ExpenseLogRepository extends
		JpaRepository<ExpenseLog, Integer> {

	Page<ExpenseLog> findByDateTimeGreaterThanEqual(Date dateTime,
			Pageable pageSpec);

	Page<ExpenseLog> findByDateTimeLessThanEqual(Date dateTime,
			Pageable pageSpec);

	Page<ExpenseLog> findByDateTimeBetween(Date startDateTime,
			Date endDateTime, Pageable pageSpec);
}
