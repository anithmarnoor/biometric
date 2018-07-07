package com.siddhrans.biometric.dao;

import java.util.List;

import com.siddhrans.biometric.model.BiometricMachine;

public interface BiometricMachineDao {

	List<BiometricMachine> findAllMachines();

	BiometricMachine findMachineById(int id);

	BiometricMachine findMachineByModel(String machineModel);

	void saveMachine(BiometricMachine machine);

	void deleteMachineById(int id);

	void editMachine(BiometricMachine machine);
}
