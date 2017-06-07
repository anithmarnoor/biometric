package com.siddhrans.biometric.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.siddhrans.biometric.dao.BiometricDAO;
import com.siddhrans.biometric.model.BiometricData;
import com.siddhrans.biometric.service.BiometricService;

public class BiometricDAOImpl implements BiometricDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(BiometricDAOImpl.class);

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}
	
	public void readBiometricData(BiometricData data) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
	}

	public void saveBiometricData(BiometricData data) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
	}

	public void updateBiometricData(BiometricData data) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
	}

	public List<BiometricData> fetchBiometricData(BiometricData data) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
		return null;
	}

	public void deleteBiometricData(int id) {
		// TODO Auto-generated method stub
		Session session = this.sessionFactory.getCurrentSession();
	}

}
