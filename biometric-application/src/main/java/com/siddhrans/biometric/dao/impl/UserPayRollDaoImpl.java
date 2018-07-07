package com.siddhrans.biometric.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.siddhrans.biometric.dao.AbstractDao;
import com.siddhrans.biometric.dao.UserPayRollDao;
import com.siddhrans.biometric.model.User;
import com.siddhrans.biometric.model.UserPayRoll;

@Repository("userPayRollDao")
public class UserPayRollDaoImpl extends AbstractDao<Integer, UserPayRoll> implements UserPayRollDao {
	static final Logger logger = LoggerFactory.getLogger(UserPayRollDaoImpl.class);

	@Override
	public List<UserPayRoll> findUserPayRollDetails(User user) {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("id"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		criteria.add(Restrictions.eq("user", user));
		List<UserPayRoll> userPayRoll = (List<UserPayRoll>) criteria.list();
		logger.debug("In findUserPayRollDetails ==> "+userPayRoll);
		for(UserPayRoll payRoll:userPayRoll)
		{
			Hibernate.initialize(payRoll.getComponent());
			Hibernate.initialize(payRoll.getUser());
		}
		return userPayRoll;
	}

	@Override
	public void deleteUserPayRollDetails(User user) {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("id"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		criteria.add(Restrictions.eq("user", user));
		List<UserPayRoll> userPayRoll = (List<UserPayRoll>) criteria.list();
		delete(userPayRoll.get(0));
	}

	@Override
	public void saveOrUpdateUserPayRollDetails(UserPayRoll payRoll) {
		saveOrUpdate(payRoll);
	}
	
	@Override
	public void saveOrUpdateAllUserPayRollDetails(List<UserPayRoll> payRolls) {
		for(int i=0;i<payRolls.size();i++) {
			UserPayRoll payRoll =payRolls.get(i);
			saveOrUpdate(payRoll);
		}
	}
}