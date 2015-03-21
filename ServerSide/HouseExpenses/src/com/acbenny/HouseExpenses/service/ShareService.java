package com.acbenny.HouseExpenses.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.acbenny.HouseExpenses.exception.ErrorCodes;
import com.acbenny.HouseExpenses.exception.ServiceException;
import com.acbenny.HouseExpenses.model.entity.ExpenseLog;
import com.acbenny.HouseExpenses.model.entity.Share;
import com.acbenny.HouseExpenses.model.entity.User;
import com.acbenny.HouseExpenses.repository.ShareRepository;

@Service
public class ShareService {

	@Autowired
	ShareRepository shareRepository;

	@Transactional(propagation = Propagation.MANDATORY)
	public Share createShare(ExpenseLog expenseLog, User user,
			int shareMultiplier) throws ServiceException {
		Share share = new Share();
		share.setExpenseLog(expenseLog);
		share.setUser(user);
		share.setShareMultiplier(shareMultiplier);
		try {
			share = shareRepository.saveAndFlush(share);
		} catch (DataIntegrityViolationException e) {
			throw new ServiceException(ErrorCodes.SHARE_INTEGRITY);
		}
		return share;
	}
}
