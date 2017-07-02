package com.siddhrans.biometric.service;

import java.util.List;

import com.siddhrans.biometric.model.User;
import com.siddhrans.biometric.model.UserBiometricData;

public interface UserBiometricDataService {
	
	UserBiometricData findById(int id);
	
	public List<UserBiometricData> findByUser(User user);

	List<UserBiometricData> findAll();

	public List<UserBiometricData> findByYearAndMonth(Integer year, Integer month, Integer id);

	void save(UserBiometricData document);

	void deleteBiometricDataByUser(User user);
}
