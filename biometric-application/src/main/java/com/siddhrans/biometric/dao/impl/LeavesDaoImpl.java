package com.siddhrans.biometric.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.siddhrans.biometric.dao.AbstractDao;
import com.siddhrans.biometric.dao.LeavesDao;
import com.siddhrans.biometric.model.Leaves;
import com.siddhrans.biometric.model.User;

@Repository("leavesDao")
@Transactional
public class LeavesDaoImpl extends AbstractDao<Integer, Leaves> implements LeavesDao {

	@Override
	public List<Leaves> findAllLeaves() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("id"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		List<Leaves> leaves = (List<Leaves>) criteria.list();
		return leaves;
	}

	@Override
	public Leaves findLeaveById(int leaveId) {
		return getByKey(leaveId);
	}

	@Override
	public List<Leaves> findLeavesByUser(User user) {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("id"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		criteria.add(Restrictions.eq("user", user));
		List<Leaves> leaves = (List<Leaves>) criteria.list();
		if(leaves.size() >0){
			return leaves;
		}
		return null;
	}

	@Override
	public void saveLeave(Leaves leave) {
		persist(leave);
	}

	@Override
	public void updateLeave(Leaves leave) {
		update(leave);
	}

	@Override
	public void deleteLeave(Leaves leave) {
		delete(leave);
	}	
}
