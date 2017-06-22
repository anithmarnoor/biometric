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
import com.siddhrans.biometric.dao.PaySlipDao;
import com.siddhrans.biometric.dao.UserDao;
import com.siddhrans.biometric.model.PaySlip;
import com.siddhrans.biometric.model.User;
 
 
 
@Repository("paySlipDao")
public class PaySlipDaoImpl extends AbstractDao<Integer, PaySlip> implements PaySlipDao {
 
   
}