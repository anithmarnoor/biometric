package com.siddhrans.biometric.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.siddhrans.biometric.dao.AbstractDao;
import com.siddhrans.biometric.dao.UserBiometricDataDao;
import com.siddhrans.biometric.model.Leaves;
import com.siddhrans.biometric.model.User;
import com.siddhrans.biometric.model.UserBiometricData;
import com.siddhrans.biometric.utils.BiometricConstants;

@Repository("userBiometricDataDao")
public class UserBiometricDataDaoImpl extends AbstractDao<Integer, UserBiometricData>  implements UserBiometricDataDao {

	
	public List<UserBiometricData> findAll() {
		Criteria crit = createEntityCriteria();
        return (List<UserBiometricData>)crit.list();
	}

	
	public UserBiometricData findById(int id) {
		return getByKey(id);
	}
	
	
	public List<UserBiometricData> findByUserId(String userId) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("userId", Integer.parseInt(userId)));
		List<UserBiometricData> dataList = (List<UserBiometricData>)crit.list();
		return dataList;
	}

	
	public List<UserBiometricData> findByDateAndUserId(Integer year, Integer month, Integer date, Integer id) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("userId", id));
		crit.add(Restrictions.eq("year", year));
		crit.add(Restrictions.eq("month", month));
		crit.add(Restrictions.eq("date", date));
		List<UserBiometricData> dataList = (List<UserBiometricData>)crit.list();
		return dataList;
	}
	
	
	public List<UserBiometricData> findByYearAndMonth(Integer year, Integer month, Integer id) {
		Criteria crit = createEntityCriteria().addOrder(Order.asc("date"));
		crit.add(Restrictions.eq("userId", id));
		crit.add(Restrictions.eq("year", year));
		crit.add(Restrictions.eq("month", month));
		List<UserBiometricData> dataList = (List<UserBiometricData>)crit.list();
		return dataList;
	}
	
	public void save(UserBiometricData data) {
		persist(data);
	}

	
	public void deleteUserBiometricDataByUserId(String userId) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("userId", userId));
		List<UserBiometricData> dataList = (List<UserBiometricData>)crit.list();
		for(int i=0; i<dataList.size(); i++){
			UserBiometricData data = dataList.get(i);
			delete(data);
		}
	}
	
	@Override
	public List<UserBiometricData> findLeavesByUserInMonth(int userId, int month, int year) {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("id"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		criteria.add(Restrictions.eq("userId", userId));
		criteria.add(Restrictions.eq("month", month));
		criteria.add(Restrictions.eq("year", year));
		criteria.add(Restrictions.eq("status", BiometricConstants.TIME_OFF));
		List<UserBiometricData> leaves = (List<UserBiometricData>) criteria.list();
		if(leaves.size() >0){
			return leaves;
		}
		return null;
	}
}
