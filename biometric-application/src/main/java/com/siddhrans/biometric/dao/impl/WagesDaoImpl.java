package com.siddhrans.biometric.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import com.siddhrans.biometric.dao.AbstractDao;
import com.siddhrans.biometric.dao.WagesDao;
import com.siddhrans.biometric.model.Designation;
import com.siddhrans.biometric.model.User;
import com.siddhrans.biometric.model.Wages;

@Repository("wagesDao")
public class WagesDaoImpl extends AbstractDao<Integer, Wages> implements WagesDao {

	public void save(Wages wages) {
		wages.setId(1);//Only one row should be present. So setting it to 1
		super.persist(wages);
	}

	public void update(Wages wages) {
		wages.setId(1);//Only one row should be present. Always update same. So setting it to 1
		super.update(wages);
	}

	public List<Wages> findWages() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("id"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		List<Wages> divisions = (List<Wages>) criteria.list();
		for(Wages wage:divisions)
		{
			Hibernate.initialize(wage.getUser());
		}
		return divisions;
	}

	@Override
	public Wages findWageById(Integer id) {
		Wages wage = getByKey(id);
		if(wage !=null){
			Hibernate.initialize(wage.getUser());
			return wage;
		}
		return null;
	}

	public Wages findWagesByUser(User user) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("user", user));
		List<Wages> dataList = (List<Wages>)crit.list();
		if(dataList.size()>0){
			Wages wage = dataList.get(0);
			Hibernate.initialize(wage.getUser());
			return wage;
		}
		return null;
	}
}