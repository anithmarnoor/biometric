package com.siddhrans.biometric.dao;

import java.util.List;

import com.siddhrans.biometric.model.UserBiometricData;

public interface UserBiometricDataDao {

	public List<UserBiometricData> findAll();

	public UserBiometricData findById(int id);
	
	public List<UserBiometricData> findByUserId(String userId);
	
	public List<UserBiometricData> findByYearAndMonth(Integer year, Integer month, Integer id);

	public void save(UserBiometricData document);

	public void deleteUserBiometricDataByUserId(String userId);
}
