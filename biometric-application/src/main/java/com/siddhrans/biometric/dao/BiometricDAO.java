package com.siddhrans.biometric.dao;

import java.util.List;

import com.siddhrans.biometric.model.BiometricData;

public interface BiometricDAO {
	public void readBiometricData(BiometricData data);
	public void saveBiometricData(BiometricData data);
	public void updateBiometricData(BiometricData data);
	public void deleteBiometricData(int id);
	public List<BiometricData> fetchBiometricData(BiometricData data);
}
