package com.siddhrans.biometric.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.siddhrans.biometric.dao.DriverDAO;
import com.siddhrans.biometric.model.Driver;

@Repository
public class DriverDAOImpl implements DriverDAO {

	
	private static final Logger logger = LoggerFactory.getLogger(DriverDAOImpl.class);

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	@Override
	public void addDriver(Driver p) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(p);
		logger.info("Driver saved successfully, Driver Details="+p);
	}

	@Override
	public void updateDriver(Driver p) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(p);
		logger.info("Driver updated successfully, Driver Details="+p);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Driver> listDrivers() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Driver> DriversList = session.createQuery("from Driver").list();
		for(Driver p : DriversList){
			logger.info("Driver List::"+p);
		}
		return DriversList;
	}

	@Override
	public Driver getDriverById(int id) {
		Session session = this.sessionFactory.getCurrentSession();		
		Driver p = (Driver) session.load(Driver.class, new Integer(id));
		logger.info("Driver loaded successfully, Driver details="+p);
		return p;
	}

	@Override
	public void removeDriver(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Driver p = (Driver) session.load(Driver.class, new Integer(id));
		if(null != p){
			session.delete(p);
		}
		logger.info("Driver deleted successfully, Driver details="+p);
	}
}
