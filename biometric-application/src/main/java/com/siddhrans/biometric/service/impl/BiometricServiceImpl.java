package com.siddhrans.biometric.service.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.siddhrans.biometric.dao.BiometricDAO;
import com.siddhrans.biometric.model.BiometricData;
import com.siddhrans.biometric.service.BiometricService;

public class BiometricServiceImpl implements BiometricService{
	
	BiometricDAO dao;
	
	public BiometricServiceImpl(){
		
	}

	public BiometricDAO getDao() {
		return dao;
	}

	public void setDao(BiometricDAO dao) {
		this.dao = dao;
	}

	@Transactional
	public void readBiometricData(BiometricData data) {
		// TODO Auto-generated method stub
		dao.readBiometricData(data);
	}
	
	@Transactional
	public void saveBiometricData(BiometricData data) {
		// TODO Auto-generated method stub
		dao.saveBiometricData(data);
	}
	
	@Transactional
	public void updateBiometricData(BiometricData data) {
		// TODO Auto-generated method stub
		dao.updateBiometricData(data);
	}
	
	@Transactional
	public List<BiometricData> fetchBiometricData(BiometricData data) {
		// TODO Auto-generated method stub
		return dao.fetchBiometricData(data);
	}

	@Transactional
	public void deleteBiometricData(int id) {
		// TODO Auto-generated method stub
		
	}
}
