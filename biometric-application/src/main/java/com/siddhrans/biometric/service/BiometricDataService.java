package com.siddhrans.biometric.service;

import java.util.Date;
import java.util.List;

import com.siddhrans.biometric.model.BiometricData;

public interface BiometricDataService {
	
	BiometricData findById(int id);

	List<BiometricData> findAll();

	public List<BiometricData>  findByStartAndEndDate(Date startDate, Date endDate);

	void saveDocument(BiometricData document);

	void deleteBiometricDataById(int id);
}
