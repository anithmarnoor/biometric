package com.siddhrans.biometric.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.siddhrans.biometric.dao.AbstractDao;
import com.siddhrans.biometric.dao.PaySlipDao;
import com.siddhrans.biometric.model.PaySlip;
import com.siddhrans.biometric.model.User;



@Repository("paySlipDao")
public class PaySlipDaoImpl extends AbstractDao<Integer, PaySlip> implements PaySlipDao {


	public List<PaySlip> getPayDetails(User user, Integer month, Integer year, String componentName) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("user", user));
		crit.add(Restrictions.eq("year", year));
		crit.add(Restrictions.eq("month", month));
		List<PaySlip> list = (List<PaySlip>)crit.list();
		PaySlip dataList = null;
		if(list != null && list.size()>0){
			for(int i=0; i<list.size();i++){
				dataList = list.get(i);
				Hibernate.initialize(dataList.getUser());
			}
			
		}
		return dataList;
	}


	public boolean savePayDetails(PaySlip paySlip) {
		/*PaySlip existingPaySlip = getPayDetails(paySlip.getUserId(), paySlip.getYear(), paySlip.getMonth());
		if(existingPaySlip == null)*/
		persist(paySlip);
		/*else 
		 	update(paySlip);*/
		return true;
	}


	public boolean deletePayDetails(User user, Integer month, Integer year, String componentName) {
		List<PaySlip> existingPaySlip = getPayDetails(user, month, year, componentName);
		for(PaySlip slip: existingPaySlip)
			delete(slip);
		return true;
	}
}