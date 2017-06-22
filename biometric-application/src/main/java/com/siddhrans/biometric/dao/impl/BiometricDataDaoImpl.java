package com.siddhrans.biometric.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.siddhrans.biometric.dao.AbstractDao;
import com.siddhrans.biometric.dao.BiometricDataDao;
import com.siddhrans.biometric.model.BiometricData;

@Repository("biometricDataDao")
public class BiometricDataDaoImpl extends AbstractDao<Integer, BiometricData>  implements BiometricDataDao {

	@Override
	public List<BiometricData> findAll() {
		Criteria crit = createEntityCriteria();
        return (List<BiometricData>)crit.list();
	}

	@Override
	public BiometricData findById(int id) {
		return getByKey(id);
	}

	@Override
	public void save(BiometricData document) {
		List<BiometricData> dataList = findByMonthAndYear(document.getMonth(), document.getYear());
		if(dataList.size() <= 0){
			persist(document);
		} else {
			super.update(document);
		}
	}
	
	@Override
	public List<BiometricData> findByMonthAndYear(int month, int year) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("month", month));
		crit.add(Restrictions.eq("year", year));
		List<BiometricData> dataList = (List<BiometricData>)crit.list();
		return dataList;
	}
	

	@Override
	public void deleteBiometricDataById(int id) {
		BiometricData document =  getByKey(id);
        delete(document);
	}

}
