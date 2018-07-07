package com.siddhrans.biometric.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.siddhrans.biometric.dao.UserPayRollDao;
import com.siddhrans.biometric.model.User;
import com.siddhrans.biometric.model.UserPayRoll;
import com.siddhrans.biometric.service.UserPayRollService;

@Service("userPayRollService")
@Transactional
public class UserPayRollServiceImpl implements UserPayRollService {

	@Autowired
	UserPayRollDao userPayRollDao;

	@Override
	public List<UserPayRoll> findUserPayRollDetails(User user) {
		return userPayRollDao.findUserPayRollDetails(user);
	}

	@Override
	public void deleteUserPayRollDetails(User user) {
		userPayRollDao.deleteUserPayRollDetails(user);
	}

	@Override
	public void saveOrUpdateUserPayRollDetails(UserPayRoll payRoll) {
		userPayRollDao.saveOrUpdateUserPayRollDetails(payRoll);
	}
	
	@Override
	public void saveOrUpdateAllUserPayRollDetails(List<UserPayRoll> payRolls) {
		userPayRollDao.saveOrUpdateAllUserPayRollDetails(payRolls);
	}	
}
