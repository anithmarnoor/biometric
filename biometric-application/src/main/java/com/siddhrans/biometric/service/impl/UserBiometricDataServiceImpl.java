package com.siddhrans.biometric.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.siddhrans.biometric.dao.UserBiometricDataDao;
import com.siddhrans.biometric.model.Leaves;
import com.siddhrans.biometric.model.User;
import com.siddhrans.biometric.model.UserBiometricData;
import com.siddhrans.biometric.service.UserBiometricDataService;

@Service("userBiometricDataService")
@Transactional
public class UserBiometricDataServiceImpl implements UserBiometricDataService {

	@Autowired
	UserBiometricDataDao biometricDao;
	
	
	public UserBiometricData findById(int id) {
		 return biometricDao.findById(id);
	}

	
	public List<UserBiometricData> findAll() {
		return biometricDao.findAll();
	}

	
	public List<UserBiometricData> findByDateAndUserId(Integer year, Integer month, Integer date, Integer id) {
		return biometricDao.findByDateAndUserId(year, month, date, id);
	}

	
	public List<UserBiometricData> findByYearAndMonth(Integer year, Integer month, Integer id) {
		return biometricDao.findByYearAndMonth(year, month, id);
	}
	
	
	public void save(UserBiometricData data) {
		biometricDao.save(data);
	}

	
	public void deleteBiometricDataByUserId(String userId) {
		biometricDao.deleteUserBiometricDataByUserId(userId);
	}

	
	public List<UserBiometricData> findByUserId(String userId) {
		return biometricDao.findByUserId(userId);
	}
	
	@Override
	public List<UserBiometricData> findLeavesByUserInMonth(int userId, int month, int year) {
		return biometricDao.findLeavesByUserInMonth(userId, month, year);
	}
}
