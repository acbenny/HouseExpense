package com.acbenny.HouseExpenses.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.acbenny.HouseExpenses.exception.ErrorCodes;
import com.acbenny.HouseExpenses.exception.ServiceException;
import com.acbenny.HouseExpenses.model.entity.ExpenseLog;
import com.acbenny.HouseExpenses.model.entity.Item;
import com.acbenny.HouseExpenses.model.entity.User;
import com.acbenny.HouseExpenses.repository.ExpenseLogRepository;

@Service
public class ExpenseLogService {

	@Autowired
	ExpenseLogRepository expenseLogRepository;

	@Transactional(propagation = Propagation.MANDATORY)
	public ExpenseLog createLog(User loggedBy, Date dateTime, Item item,
			int quantity, String amount) throws ServiceException {

		BigDecimal bdAmount = null;
		if (!(amount == null || "".equals(amount))) {
			bdAmount = (new BigDecimal(amount)).setScale(2,
					BigDecimal.ROUND_HALF_UP);
		} else if (item.getPrice() != null && quantity > 0) {
			bdAmount = item.getPrice().multiply(new BigDecimal(quantity));

		} else {
			throw new ServiceException(ErrorCodes.EXPENSE_AMOUNT_MISSING);
		}

		ExpenseLog log = new ExpenseLog();
		log.setLoggedBy(loggedBy);
		log.setDateTime(dateTime);
		log.setItem(item);
		log.setAmount(bdAmount);

		log = expenseLogRepository.save(log);

		return log;
	}

	@Transactional(readOnly=true)
	public List<ExpenseLog> getLogList(Date startDate, Date endDate,Pageable pageSpec) {
		List<ExpenseLog> expList=null;
 		if (startDate==null && endDate==null){
 			expList=expenseLogRepository.findAll(pageSpec).getContent();
		}else if (startDate!=null && endDate!=null){
 			expList=expenseLogRepository.findByDateTimeBetween(startDate, endDate,pageSpec).getContent();
		}else if (startDate==null){
			expList=expenseLogRepository.findByDateTimeLessThanEqual(endDate, pageSpec).getContent();
		}else if (endDate==null){
			expList=expenseLogRepository.findByDateTimeGreaterThanEqual(startDate, pageSpec).getContent();
		}
		return expList;
	}
}
