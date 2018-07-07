package com.siddhrans.biometric.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.siddhrans.biometric.dao.AbstractDao;
import com.siddhrans.biometric.dao.BiometricDataDao;
import com.siddhrans.biometric.model.BiometricData;

@Repository("biometricDataDao")
public class BiometricDataDaoImpl extends AbstractDao<Integer, BiometricData>  implements BiometricDataDao {

	
	public List<BiometricData> findAll() {
		Criteria crit = createEntityCriteria();
        return (List<BiometricData>)crit.list();
	}

	
	public BiometricData findById(int id) {
		return getByKey(id);
	}

	
	public void save(BiometricData document) {
		List<BiometricData> dataList = findByYearAndMonth(document.getYear(), document.getMonth());
		
		if(dataList.isEmpty() || dataList.size() <= 0){
			persist(document);
		} else {
			super.update(document);
		}
	}
	
	
	public List<BiometricData> findByYearAndMonth(String year, String month) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("year", year));
		crit.add(Restrictions.eq("month", month));
		List<BiometricData> dataList = (List<BiometricData>)crit.list();
		return dataList;
	}
	

	
	public void deleteBiometricDataById(int id) {
		BiometricData document =  getByKey(id);
        delete(document);
	}

}
