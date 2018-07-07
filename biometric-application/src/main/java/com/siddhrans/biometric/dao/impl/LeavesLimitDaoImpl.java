package com.siddhrans.biometric.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.siddhrans.biometric.dao.AbstractDao;
import com.siddhrans.biometric.dao.LeavesLimitDao;
import com.siddhrans.biometric.model.LeaveTypes;
import com.siddhrans.biometric.model.LeavesLimit;

@Repository("leavesLimitDao")
@Transactional
public class LeavesLimitDaoImpl extends AbstractDao<Integer, LeavesLimit> implements LeavesLimitDao {

	@Override
	public List<LeavesLimit> findAllLeavesLimit() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("id"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		List<LeavesLimit> leavesLimit = (List<LeavesLimit>) criteria.list();
		return leavesLimit;
	}

	@Override
	public LeavesLimit findLeavesLimitById(int limitId) {
		return getByKey(limitId);
	}
	
	@Override
	public LeavesLimit findLeavesLimitByLeavesType(LeaveTypes leavesType) {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("id"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		criteria.add(Restrictions.eq("leaveType", leavesType));
		List<LeavesLimit> leavesLimit = (List<LeavesLimit>) criteria.list();
		if(leavesLimit.size() >0){
			return leavesLimit.get(0);
		}
		return null;
	}

	@Override
	public void saveLeavesLimit(LeavesLimit limit) {
		persist(limit);
	}

	@Override
	public void updateLeavesLimit(LeavesLimit limit) {
		update(limit);
	}

	@Override
	public void deleteLeavesLimit(LeavesLimit limit) {
		delete(limit);
	}	
}
