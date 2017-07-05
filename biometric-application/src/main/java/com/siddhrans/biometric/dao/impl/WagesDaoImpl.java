package com.siddhrans.biometric.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import com.siddhrans.biometric.dao.AbstractDao;
import com.siddhrans.biometric.dao.WagesDao;
import com.siddhrans.biometric.model.Wages;




@Repository("wagesDao")
public class WagesDaoImpl extends AbstractDao<Integer, Wages> implements WagesDao {

	@Override
	public List<Wages> findWages() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("id"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		List<Wages> divisions = (List<Wages>) criteria.list();
		return divisions;
	}

	public void save(Wages wages) {
		wages.setId(1);//Only one row should be present. So setting it to 1
		super.persist(wages);
	}

	public void update(Wages wages) {
		wages.setId(1);//Only one row should be present. Always update same. So setting it to 1
		super.update(wages);
	}
}