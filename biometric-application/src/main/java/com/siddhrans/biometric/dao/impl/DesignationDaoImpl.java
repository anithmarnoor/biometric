package com.siddhrans.biometric.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.siddhrans.biometric.dao.AbstractDao;
import com.siddhrans.biometric.dao.DesignationDao;
import com.siddhrans.biometric.model.Designation;

@Repository("designationDao")
@Transactional
public class DesignationDaoImpl extends AbstractDao<Integer, Designation> implements DesignationDao {

	@Override
	public Designation findById(int id) {
		Designation designation = getByKey(id);
		if(designation !=null){
			Hibernate.initialize(designation.getDepartment());
		}
		return designation;
	}

	@Override
	public void saveDesignation(Designation designation) {
		persist(designation);
	}

	@Override
	public void updateDesignation(Designation designation) {
		update(designation);
	}
	
	@Override
	public void deleteDesignation(int id) {
		Designation designation = findById(id);
		delete(designation);
	}

	@Override
	public List<Designation> findAllDesignations() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("id"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		List<Designation> designations = (List<Designation>) criteria.list();
		for(Designation designation:designations)
		{
			Hibernate.initialize(designation.getDepartment());
		}
		return designations;
	}

	@Override
	public boolean isDesignationNameUnique(Integer id, String designationName) {
		// TODO Auto-generated method stub
		return false;
	}

}
