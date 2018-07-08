package com.siddhrans.biometric.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.siddhrans.biometric.dao.AbstractDao;
import com.siddhrans.biometric.dao.AvailableLeavesDao;
import com.siddhrans.biometric.model.LeaveTypes;
import com.siddhrans.biometric.model.LeavesAvailable;
import com.siddhrans.biometric.model.User;

@Repository("availableLeavesDaoImpl")
@Transactional
public class AvailableLeavesDaoImpl extends AbstractDao<Integer, LeavesAvailable> implements AvailableLeavesDao {

	@Override
	public LeavesAvailable findAvailableLeavesByUserAndType(User user, LeaveTypes leaveTypes) {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("id"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		criteria.add(Restrictions.eq("leaveType", leaveTypes));
		criteria.add(Restrictions.eq("user", user));
		List<LeavesAvailable> leavesAvailable = (List<LeavesAvailable>) criteria.list();
		if(leavesAvailable.size() >0){
			return leavesAvailable.get(0);
		}
		return null;
	}

	@Override
	public void saveLeavesAvailable(LeavesAvailable leavesAvailable) {
		persist(leavesAvailable);
	}

	@Override
	public void updateLeavesAvailable(LeavesAvailable leavesAvailable) {
		saveOrUpdate(leavesAvailable);
	}

	@Override
	public void deleteLeavesAvailable(LeavesAvailable leavesAvailable) {
		delete(leavesAvailable);
	}

	@Override
	public List<LeavesAvailable> findAvailableLeavesByUser(User user) {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("id"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		criteria.add(Restrictions.eq("user", user));
		List<LeavesAvailable> leavesAvailable = (List<LeavesAvailable>) criteria.list();
		if(leavesAvailable.size() >0){
			return leavesAvailable;
		}
		return null;
	}	
}
