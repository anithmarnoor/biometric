package com.siddhrans.biometric.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.siddhrans.biometric.dao.AbstractDao;
import com.siddhrans.biometric.dao.BiometricMachineDao;
import com.siddhrans.biometric.model.BiometricMachine;

@Repository("biometricMachineDao")
public class BiometricMachineDaoImpl extends AbstractDao<Integer, BiometricMachine>  implements BiometricMachineDao {

	@Override
	public List<BiometricMachine> findAllMachines() {
		Criteria crit = createEntityCriteria();
        return (List<BiometricMachine>)crit.list();
	}

	@Override
	public BiometricMachine findMachineById(int id) {
		return getByKey(id);
	}

	@Override
	public BiometricMachine findMachineByModel(String machineModel) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("machineModel", machineModel));
		List<BiometricMachine> machineList = (List<BiometricMachine>)crit.list();
		return machineList.get(0);
	}

	@Override
	public void deleteMachineById(int id) {
		BiometricMachine machine =  getByKey(id);
        delete(machine);
	}

	@Override
	public void saveMachine(BiometricMachine machine) {
		persist(machine);
		
	}

	@Override
	public void editMachine(BiometricMachine machine) {
		update(machine);
	}


}
