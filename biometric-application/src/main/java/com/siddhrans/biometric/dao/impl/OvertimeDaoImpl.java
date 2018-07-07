package com.siddhrans.biometric.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.siddhrans.biometric.dao.AbstractDao;
import com.siddhrans.biometric.dao.OvertimeDao;
import com.siddhrans.biometric.model.Designation;
import com.siddhrans.biometric.model.OverTime;

@Repository("overTimeDao")
@Transactional
public class OvertimeDaoImpl extends AbstractDao<Integer, OverTime> implements OvertimeDao {

	@Override
	public OverTime findById(int id) {
		OverTime overTime = getByKey(id);
		if(overTime !=null){
			Hibernate.initialize(overTime.getDesignation());
		}
		return overTime;
	}

	@Override
	public void saveOverTime(OverTime overTime) {
		persist(overTime);
	}

	@Override
	public void updateOverTime(OverTime overTime) {
		update(overTime);
	}
		
	@Override
	public void deleteOverTime(int id) {
		OverTime overTime = findById(id);
		delete(overTime);
	}

	@Override
	public List<OverTime> findAllOverTimes() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("id"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		List<OverTime> overTimes = (List<OverTime>) criteria.list();
		for(OverTime overTime:overTimes)
		{
			Hibernate.initialize(overTime.getDesignation());
		}
		return overTimes;
	}
	
	@Override
	public OverTime findOTByDesignation(Designation designation) {
		Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("designation", designation));
        return (OverTime) crit.uniqueResult();
	}
	
}
