package com.siddhrans.biometric.dao;

import java.util.Date;
import java.util.List;

import com.siddhrans.biometric.model.BiometricData;

public interface BiometricDataDao {

	List<BiometricData> findAll();

	BiometricData findById(int id);
	
	public List<BiometricData> findByStartAndEndDate(Date startDate, Date endDate);

	void save(BiometricData document);

	void deleteBiometricDataById(int id);
}
