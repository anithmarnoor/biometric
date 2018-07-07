package com.siddhrans.biometric.dao.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.siddhrans.biometric.dao.AbstractDao;
import com.siddhrans.biometric.dao.UserDao;
import com.siddhrans.biometric.model.Department;
import com.siddhrans.biometric.model.Designation;
import com.siddhrans.biometric.model.User;
@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {
	static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

	public User findById(int id) {
		User user = getByKey(id);
		if(user!=null){
			Hibernate.initialize(user.getUserProfile());
		}
		return user;
	}

	public User findByUserName(String userName) {
		logger.info("userName : {}", userName);
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("userName", userName));
		User user = (User)crit.uniqueResult();
		if(user!=null){
			Hibernate.initialize(user.getUserProfile());
		}
		return user;
	}

	@SuppressWarnings("unchecked")
	public List<User> findAllUsers() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("id"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		criteria.add(Restrictions.eq("status", "Active"));
		List<User> users = (List<User>) criteria.list();
		// No need to fetch userProfiles since we are not showing them on list page. Let them lazy load. 
		// Uncomment below lines for eagerly fetching of userProfiles if you want.
		/*
        for(User user : users){
            Hibernate.initialize(user.getUserProfiles());
        }*/

		for(User user : users){
			Designation designation = user.getDesignation();
			Hibernate.initialize(user.getDesignation());
			if(designation != null)
				Hibernate.initialize(designation.getDepartment());
		}
		return users;
	}

	@SuppressWarnings("unchecked")
	public List<User> findAllInactiveUsers() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("id"));
		criteria.add(Restrictions.eq("status", "Left"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		List<User> users = (List<User>) criteria.list();
		// No need to fetch userProfiles since we are not showing them on list page. Let them lazy load. 
		// Uncomment below lines for eagerly fetching of userProfiles if you want.
		/*
        for(User user : users){
            Hibernate.initialize(user.getUserProfiles());
        }*/
		for(User user : users){
			Designation designation = user.getDesignation();
			Hibernate.initialize(user.getDesignation());
			if(designation != null)
				Hibernate.initialize(designation.getDepartment());
		}
		return users;
	}

	public void save(User user) {
		persist(user);
	}

	public void deleteByUserName(String userName) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("userName", userName));
		User user = (User)crit.uniqueResult();
		DateFormat formatter = new SimpleDateFormat("dd/M/yyyy");
		Date today = new Date();
		String todayWithZeroTime = null;
		todayWithZeroTime = formatter.format(today);
		user.setStatus("Left");
		user.setLeftDate(todayWithZeroTime);
		user.setUserProfile(null);
		//instead of deleting from Database, just set the status to Left. so that User data will be available for future reference.
		update(user);
	}

	public void deleteUserById(Integer userId) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("id", userId));
		User user = (User)crit.uniqueResult();
		DateFormat formatter = new SimpleDateFormat("dd/M/yyyy");
		Date today = new Date();
		String todayWithZeroTime = null;
		todayWithZeroTime = formatter.format(today);
		user.setStatus("Left");
		user.setLeftDate(todayWithZeroTime);
		user.setUserProfile(null);
		//instead of deleting from Database, just set the status to Left. so that User data will be available for future reference.
		update(user);
	}

	public User findByPhoneNo(String phoneNo) {
		logger.info("PhoneNo : {}", phoneNo);
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("phoneNo", phoneNo));
		User user = (User)crit.uniqueResult();
		if(user!=null){
			logger.info("user Found for Phone Number criteria : {}", user.getId());
			Hibernate.initialize(user.getUserProfile());
		}
		return user;
	}


	public User findByDlNo(String dlNo) {
		logger.info("dlNo : {}", dlNo);
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("dlNo", dlNo));
		User user = (User)crit.uniqueResult();
		if(user!=null){
			logger.info("user Found for DL No criteria : {}", user.getId());
			Hibernate.initialize(user.getUserProfile());
		}
		return user;
	}

	public User findByDesignationName(String designationName) {
		logger.info("in USER DAO-->designationName : {}", designationName);
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("designationName", designationName));
		User user = (User)crit.uniqueResult();
		if(user!=null){
			logger.info("user Found for DL No criteria : {}", user.getId());
			Hibernate.initialize(user.getUserProfile());
			Hibernate.initialize(user.getDesignation().getDepartment());
		}
		return user;
	}

	public User findByDesignationid(String designationId) {
		logger.info("in USER DAO-->designationName : {}", designationId);
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("designationName", designationId));
		User user = (User)crit.uniqueResult();
		if(user!=null){
			logger.info("user Found for DL No criteria : {}", user.getId());
			Hibernate.initialize(user.getUserProfile());
			Hibernate.initialize(user.getDesignation().getDepartment());
		}
		return user;
	}
}