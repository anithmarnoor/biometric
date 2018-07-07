package com.siddhrans.biometric.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.siddhrans.biometric.dao.AbstractDao;
import com.siddhrans.biometric.dao.LeaveTypesDao;
import com.siddhrans.biometric.model.LeaveTypes;

@Repository("leaveTypesDao")
@Transactional
public class LeaveTypesDaoImpl extends AbstractDao<Integer, LeaveTypes> implements LeaveTypesDao {

	@Override
	public List<LeaveTypes> findAllLeaveTypes() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("id"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		List<LeaveTypes> leaveTypes = (List<LeaveTypes>) criteria.list();
		return leaveTypes;
	}

	@Override
	public LeaveTypes findLeaveTypeById(int typeId) {
		return getByKey(typeId);
	}

	@Override
	public void saveLeaveType(LeaveTypes type) {
		persist(type);
	}

	@Override
	public void updateLeaveType(LeaveTypes type) {
		update(type);
	}

	@Override
	public void deleteLeaveType(LeaveTypes type) {
		delete(type);
	}
}