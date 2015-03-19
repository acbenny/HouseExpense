package com.acbenny.HouseExpenses.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.acbenny.HouseExpenses.dao.ExpenseLogDAO;
import com.acbenny.HouseExpenses.dao.ShareDAO;
import com.acbenny.HouseExpenses.exception.ErrorCodes;
import com.acbenny.HouseExpenses.exception.ServiceException;
import com.acbenny.HouseExpenses.model.ExpenseLog;
import com.acbenny.HouseExpenses.model.Item;
import com.acbenny.HouseExpenses.model.Share;
import com.acbenny.HouseExpenses.model.User;

@Service("ExpenseLogService")
public class ExpenseLogService {

	@Autowired
	@Qualifier("ExpenseLogDAO")
	ExpenseLogDAO expenseLogDAO;

	@Autowired
	@Qualifier("ShareDAO")
	ShareDAO shareDAO;

	public ExpenseLogDAO getExpenseLogDAO() {
		return expenseLogDAO;
	}

	public void setExpenseLogDAO(ExpenseLogDAO expenseLogDAO) {
		this.expenseLogDAO = expenseLogDAO;
	}

	@Transactional
	public ExpenseLog createLog(User loggedBy, Date dateTime, Item item,
			int quantity, String amount, List<Share> shareList)
			throws ServiceException {

		BigDecimal bdAmount = null;
		if (!(amount == null || "".equals(amount))) {
			bdAmount = (new BigDecimal(amount)).setScale(2, BigDecimal.ROUND_HALF_UP);
		} else if (item.getPrice() != null && quantity > 0) {
			bdAmount = item.getPrice().multiply(new BigDecimal(quantity));

		} else {
			throw new ServiceException(ErrorCodes.EXPENSE_AMOUNT_MISSING);
		}
		
		ExpenseLog log = new ExpenseLog();
		log.setLoggedBy(loggedBy);
		log.setDatetime(dateTime);
		log.setItem(item);
		log.setAmount(bdAmount);

		expenseLogDAO.createLog(log);

		for (Share share : shareList) {
			share.setExpenseLog(log);
			shareDAO.createShare(share);
		}

		return log;
	}

	public List<ExpenseLog> getLogList(Date startDate, Date endDate) {
		List<ExpenseLog> list = expenseLogDAO.getExpenseLogsBetweenDateRange(
				startDate,
				endDate);
		return list;
	}
}
