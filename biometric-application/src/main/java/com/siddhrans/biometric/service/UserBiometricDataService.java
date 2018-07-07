package com.siddhrans.biometric.service;

import java.util.List;

import com.siddhrans.biometric.model.Leaves;
import com.siddhrans.biometric.model.UserBiometricData;

public interface UserBiometricDataService {
	
	public UserBiometricData findById(int id);
	
	public List<UserBiometricData> findByUserId(String userId);

	public List<UserBiometricData> findAll();

	public List<UserBiometricData> findByDateAndUserId(Integer year, Integer month,Integer date, Integer id);
	
	public List<UserBiometricData> findByYearAndMonth(Integer year, Integer month, Integer id);

	public void save(UserBiometricData document);

	public void deleteBiometricDataByUserId(String userId);
	
	List<UserBiometricData> findLeavesByUserInMonth(int userId, int month, int year);
}
