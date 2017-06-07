package com.siddhrans.biometric.service;

import java.util.List;

import com.siddhrans.biometric.model.BiometricData;

public interface BiometricService {
	
	public void readBiometricData(BiometricData data);
	public void saveBiometricData(BiometricData data);
	public void updateBiometricData(BiometricData data);
	public void deleteBiometricData(int id);
	public List<BiometricData> fetchBiometricData(BiometricData data);
	public BiometricData fetchBiometricDataById(int id);
}
