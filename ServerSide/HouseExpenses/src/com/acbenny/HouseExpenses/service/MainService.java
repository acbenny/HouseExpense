package com.acbenny.HouseExpenses.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acbenny.HouseExpenses.exception.ServiceException;
import com.acbenny.HouseExpenses.model.ExpenseLogEntry;
import com.acbenny.HouseExpenses.model.UserShare;
import com.acbenny.HouseExpenses.model.entity.ExpenseLog;
import com.acbenny.HouseExpenses.model.entity.Share;
import com.acbenny.HouseExpenses.model.entity.User;

@Service
public class MainService {

	@Autowired
	private UserService userService;

	@Autowired
	private ExpenseLogService expenseLogService;

	@Autowired
	private ItemService itemService;

	@Autowired
	private ShareService shareService;

	@Transactional
	public void logExpense(ExpenseLogEntry logEntry) throws ServiceException {
		ExpenseLog log = expenseLogService.createLog(
				userService.getUserByUserName(logEntry.getLoggedByUserName()),
				logEntry.getLogDate(),
				itemService.getItem(logEntry.getItemName(),
						logEntry.getPricePerItem()), logEntry.getQuantity(),
						logEntry.getAmount());

		for (UserShare userShare : logEntry.getUserShareList()) {
			shareService.createShare(log,
					userService.getUserByUserName(userShare.getUserName()),
					userShare.getShareMultiplier());
		}
	}

	@Transactional(readOnly = true)
	public void printExpenses(String userName) throws ServiceException {
		User user = userService.getUserByUserName(userName);
		System.out.println("Expenses for " + userName);
		SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");
		for (Share share : user.getShareList()) {
			Date dt = share.getExpenseLog().getDateTime();
			String itemName = share.getExpenseLog().getItem().getItemName();
			BigDecimal totAmount = share.getExpenseLog().getAmount();
			int shareMultiplier = share.getShareMultiplier();
			int shareDivisor = share.getExpenseLog().getShareDivisor();
			BigDecimal sharePortion = totAmount.multiply(new BigDecimal(
					shareMultiplier));
			sharePortion = sharePortion.divide(new BigDecimal(shareDivisor));
			System.out.println(outputFormat.format(dt) + "----" + itemName
					+ "----" + totAmount.toString() + "----" + shareMultiplier
					+ "----" + shareDivisor + "----" + sharePortion.toString());

		}
	}

}
