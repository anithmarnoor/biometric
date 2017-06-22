package com.siddhrans.biometric.service;

import java.util.List;

import com.siddhrans.biometric.model.BiometricData;

public interface BiometricDataService {
	
	BiometricData findById(int id);

	List<BiometricData> findAll();

	public List<BiometricData> findByMonthAndYear(int month, int year);

	void saveDocument(BiometricData document);

	void deleteBiometricDataById(int id);
}
