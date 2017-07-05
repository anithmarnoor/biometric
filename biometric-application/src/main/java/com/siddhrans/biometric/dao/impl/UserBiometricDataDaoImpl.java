package com.siddhrans.biometric.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.siddhrans.biometric.dao.AbstractDao;
import com.siddhrans.biometric.dao.UserBiometricDataDao;
import com.siddhrans.biometric.model.User;
import com.siddhrans.biometric.model.UserBiometricData;

@Repository("userBiometricDataDao")
public class UserBiometricDataDaoImpl extends AbstractDao<Integer, UserBiometricData>  implements UserBiometricDataDao {

	@Override
	public List<UserBiometricData> findAll() {
		Criteria crit = createEntityCriteria();
        return (List<UserBiometricData>)crit.list();
	}

	@Override
	public UserBiometricData findById(int id) {
		return getByKey(id);
	}
	
	@Override
	public List<UserBiometricData> findByUserId(String userId) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("userId", Integer.parseInt(userId)));
		List<UserBiometricData> dataList = (List<UserBiometricData>)crit.list();
		return dataList;
	}

	@Override
	public List<UserBiometricData> findByYearAndMonth(Integer year, Integer month, Integer id) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("userId", id));
		crit.add(Restrictions.eq("year", year));
		crit.add(Restrictions.eq("month", month));
		List<UserBiometricData> dataList = (List<UserBiometricData>)crit.list();
		return dataList;
	}

	@Override
	public void save(UserBiometricData data) {
		persist(data);
	}

	@Override
	public void deleteUserBiometricDataByUserId(String userId) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("userId", userId));
		List<UserBiometricData> dataList = (List<UserBiometricData>)crit.list();
		for(int i=0; i<dataList.size(); i++){
			UserBiometricData data = dataList.get(i);
			delete(data);
		}
	}
}
