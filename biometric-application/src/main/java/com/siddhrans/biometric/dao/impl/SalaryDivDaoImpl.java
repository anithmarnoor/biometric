package com.siddhrans.biometric.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import com.siddhrans.biometric.dao.AbstractDao;
import com.siddhrans.biometric.dao.SalaryDivDao;
import com.siddhrans.biometric.model.SalaryDivision;




@Repository("salaryDivDao")
public class SalaryDivDaoImpl extends AbstractDao<Integer, SalaryDivision> implements SalaryDivDao {

	@Override
	public List<SalaryDivision> findSalaryDivision() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("id"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		List<SalaryDivision> divisions = (List<SalaryDivision>) criteria.list();
		return divisions;
	}

	public void save(SalaryDivision division) {
		division.setId(1);//Only one row should be present. So setting it to 1
		super.persist(division);
	}

	public void update(SalaryDivision division) {
		division.setId(1);//Only one row should be present. Always update same. So setting it to 1
		super.update(division);
	}
}