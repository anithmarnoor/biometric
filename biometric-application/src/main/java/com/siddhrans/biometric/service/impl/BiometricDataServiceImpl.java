package com.siddhrans.biometric.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.siddhrans.biometric.dao.BiometricDataDao;
import com.siddhrans.biometric.dao.BiometricMachineDao;
import com.siddhrans.biometric.model.BiometricData;
import com.siddhrans.biometric.model.BiometricMachine;
import com.siddhrans.biometric.service.BiometricDataService;

@Service("biometricDataService")
@Transactional
public class BiometricDataServiceImpl implements BiometricDataService {

	@Autowired
	BiometricDataDao biometricDao;
	
	@Autowired
	BiometricMachineDao biometricMachineDao;

	public BiometricData findById(int id) {
		 return biometricDao.findById(id);
	}

	@Override
	public List<BiometricData> findAll() {
		return biometricDao.findAll();
	}

	@Override
	public List<BiometricData> findByYearAndMonth(String year, String month) {
		return biometricDao.findByYearAndMonth(year, month);
	}

	@Override
	public void saveDocument(BiometricData document) {
		biometricDao.save(document);
	}

	@Override
	public void deleteBiometricDataById(int id) {
		biometricDao.deleteBiometricDataById(id);
	}


	@Override
	public List<BiometricMachine> findAllMachines() {
		return biometricMachineDao.findAllMachines();
	}


	@Override
	public BiometricMachine findMachineById(int id) {
		return biometricMachineDao.findMachineById(id);
	}


	@Override
	public BiometricMachine findMachineByModel(String machineModel) {
		return biometricMachineDao.findMachineByModel(machineModel);
	}


	@Override
	public void deleteMachineById(int id) {
		biometricMachineDao.deleteMachineById(id);
	}

	@Override
	public void saveMachine(BiometricMachine machine) {
		biometricMachineDao.saveMachine(machine);
	}

	@Override
	public void editMachine(BiometricMachine machine) {
		biometricMachineDao.editMachine(machine);
	}
}
