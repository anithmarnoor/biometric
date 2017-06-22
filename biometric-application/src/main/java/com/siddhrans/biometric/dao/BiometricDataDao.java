package com.siddhrans.biometric.dao;

import java.util.List;

import com.siddhrans.biometric.model.BiometricData;

public interface BiometricDataDao {

	List<BiometricData> findAll();

	BiometricData findById(int id);
	
	public List<BiometricData> findByMonthAndYear(int month, int year);

	void save(BiometricData document);

	void deleteBiometricDataById(int id);
}
