package com.siddhrans.biometric.service;

import java.util.List;

import com.siddhrans.biometric.model.BiometricData;
import com.siddhrans.biometric.model.BiometricMachine;

public interface BiometricDataService {
	
	List<BiometricMachine> findAllMachines();
	
	BiometricMachine findMachineById(int id);
	
	BiometricMachine findMachineByModel(String machineModel);
	
	void saveMachine(BiometricMachine machine);
	
	void deleteMachineById(int id);
	
	void editMachine(BiometricMachine machine);
	
	BiometricData findById(int id);

	List<BiometricData> findAll();

	public List<BiometricData> findByYearAndMonth(String year, String month);

	void saveDocument(BiometricData document);

	void deleteBiometricDataById(int id);
}
