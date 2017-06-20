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
import com.siddhrans.biometric.dao.UserDao;
import com.siddhrans.biometric.model.User;
 
 
 
@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {
 
    static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
     
    public User findById(int id) {
        User user = getByKey(id);
        if(user!=null){
            Hibernate.initialize(user.getUserProfiles());
        }
        return user;
    }
 
    public User findByUserName(String userName) {
        logger.info("userName : {}", userName);
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("userName", userName));
        User user = (User)crit.uniqueResult();
        if(user!=null){
            Hibernate.initialize(user.getUserProfiles());
        }
        return user;
    }
 
    @SuppressWarnings("unchecked")
    public List<User> findAllUsers() {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("firstName"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
        List<User> users = (List<User>) criteria.list();
         
        // No need to fetch userProfiles since we are not showing them on list page. Let them lazy load. 
        // Uncomment below lines for eagerly fetching of userProfiles if you want.
        /*
        for(User user : users){
            Hibernate.initialize(user.getUserProfiles());
        }*/
        return users;
    }
 
    public void save(User user) {
        persist(user);
    }
 
    public void deleteByUserName(String userName) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("userName", userName));
        User user = (User)crit.uniqueResult();
        delete(user);
    }

	@Override
	public User findByPhoneNo(String phoneNo) {
		// TODO Auto-generated method stub
        logger.info("PhoneNo : {}", phoneNo);
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("phoneNo", phoneNo));
        User user = (User)crit.uniqueResult();
        if(user!=null){
        	logger.info("user Found for Phone Number criteria : {}", user.getId());
            Hibernate.initialize(user.getUserProfiles());
        }
        return user;
	}

	@Override
	public User findByDlNo(String dlNo) {
		// TODO Auto-generated method stub
        logger.info("dlNo : {}", dlNo);
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("dlNo", dlNo));
        User user = (User)crit.uniqueResult();
        
        if(user!=null){
        	logger.info("user Found for DL No criteria : {}", user.getId());
            Hibernate.initialize(user.getUserProfiles());
        }
        return user;
	}
 
}