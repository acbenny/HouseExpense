package com.acbenny.HouseExpenses.dao;

import java.util.Date;
import java.util.List;

import com.acbenny.HouseExpenses.model.ExpenseLog;

public interface ExpenseLogDAO {

	public void createLog(ExpenseLog log);
	
	public List<ExpenseLog> getExpenseLogsBetweenDateRange(Date startDate,
			Date endDate);

}
