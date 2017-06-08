package com.siddhrans.biometric.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.siddhrans.biometric.dao.BiometricDAO;
import com.siddhrans.biometric.model.BiometricData;
import com.siddhrans.biometric.service.BiometricService;

@Service
public class BiometricServiceImpl implements BiometricService{
	
	BiometricDAO biometricDAO;
	
	public BiometricServiceImpl(){
		
	}

	public BiometricDAO getBiometricDAO() {
		return biometricDAO;
	}



	public void setBiometricDAO(BiometricDAO biometricDAO) {
		this.biometricDAO = biometricDAO;
	}



	@Transactional
	public void readBiometricData(BiometricData data) {
		// TODO Auto-generated method stub
		biometricDAO.readBiometricData(data);
	}
	
	@Transactional
	public void saveBiometricData(BiometricData data) {
		// TODO Auto-generated method stub
		biometricDAO.saveBiometricData(data);
	}
	
	@Transactional
	public void updateBiometricData(BiometricData data) {
		// TODO Auto-generated method stub
		biometricDAO.updateBiometricData(data);
	}
	
	@Transactional
	public List<BiometricData> fetchBiometricData(BiometricData data) {
		// TODO Auto-generated method stub
		return biometricDAO.fetchBiometricData(data);
	}

	@Transactional
	public void deleteBiometricData(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public BiometricData fetchBiometricDataById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
}
