package com.siddhrans.biometric.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.siddhrans.biometric.dao.UserBiometricDataDao;
import com.siddhrans.biometric.model.User;
import com.siddhrans.biometric.model.UserBiometricData;
import com.siddhrans.biometric.service.UserBiometricDataService;

@Service("userBiometricDataService")
@Transactional
public class UserBiometricDataServiceImpl implements UserBiometricDataService {

	@Autowired
	UserBiometricDataDao biometricDao;
	
	@Override
	public UserBiometricData findById(int id) {
		 return biometricDao.findById(id);
	}

	@Override
	public List<UserBiometricData> findAll() {
		return biometricDao.findAll();
	}

	@Override
	public List<UserBiometricData> findByYearAndMonth(Integer year, Integer month, Integer id) {
		return biometricDao.findByYearAndMonth(year, month, id);
	}

	@Override
	public void save(UserBiometricData data) {
		biometricDao.save(data);
	}

	@Override
	public void deleteBiometricDataByUserId(String userId) {
		biometricDao.deleteUserBiometricDataByUserId(userId);
	}

	@Override
	public List<UserBiometricData> findByUserId(String userId) {
		return biometricDao.findByUserId(userId);
	}
}
