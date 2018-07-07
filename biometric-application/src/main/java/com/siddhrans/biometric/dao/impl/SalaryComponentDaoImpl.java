package com.siddhrans.biometric.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.siddhrans.biometric.controller.PaySlipController;
import com.siddhrans.biometric.dao.AbstractDao;
import com.siddhrans.biometric.dao.SalaryComponentDao;
import com.siddhrans.biometric.model.SalaryComponent;

@Repository("salaryComponentDao")
public class SalaryComponentDaoImpl extends AbstractDao<Integer, SalaryComponent> implements SalaryComponentDao {
	static final Logger logger = LoggerFactory.getLogger(PaySlipController.class);
	
	/*public List<SalaryComponent> findSalaryDivision() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("id"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		criteria.add(Restrictions.ne("componentName", "OTHER"));
		List<SalaryComponent> divisions = (List<SalaryComponent>) criteria.list();
		return divisions;
	}
	*/
	public List<SalaryComponent> findAllSalaryDivisions() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("id"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		List<SalaryComponent> divisions = (List<SalaryComponent>) criteria.list();
		return divisions;
	}
	
	public SalaryComponent findSalaryDivisionByName(String name) {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("id"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		criteria.add(Restrictions.eq("componentName", name));
		List<SalaryComponent> divisions = (List<SalaryComponent>) criteria.list();
		
		if(divisions !=null && divisions.size()>0)
			return divisions.get(0);
		return null;
	}
	
	public SalaryComponent findSalaryDivisionById(Integer id) {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("id"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		criteria.add(Restrictions.eq("componentId", id));
		List<SalaryComponent> divisions = (List<SalaryComponent>) criteria.list();
		
		if(divisions !=null && divisions.size()>0)
			return divisions.get(0);
		return null;
	}


	public void save(SalaryComponent division) {
		/*division.setId(1);*///Only one row should be present. So setting it to 1
		division.setComponentName(division.getComponentName().toUpperCase());
		super.saveOrUpdate(division);
		/*other.setIsDeductible("No");
		super.saveOrUpdate(other);*/
	}

	public void update(SalaryComponent division) {
		/*division.setId(1);*///Only one row should be present. Always update same. So setting it to 1
		super.update(division);
		/*other.setIsDeductible("No");
		super.update(other);*/
	}
	
	public void deleteSalaryDivision(SalaryComponent division) {
		/*division.setId(1);*///Only one row should be present. Always update same. So setting it to 1
		super.delete(division);
		/*other.setIsDeductible("No");
		super.update(other);*/
	}
}