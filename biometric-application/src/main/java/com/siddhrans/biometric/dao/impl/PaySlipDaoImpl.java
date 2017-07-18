package com.siddhrans.biometric.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.siddhrans.biometric.dao.AbstractDao;
import com.siddhrans.biometric.dao.PaySlipDao;
import com.siddhrans.biometric.model.PaySlip;



@Repository("paySlipDao")
public class PaySlipDaoImpl extends AbstractDao<Integer, PaySlip> implements PaySlipDao {

	@Override
	public PaySlip getPayDetails(Integer id, Integer month, Integer year) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("userId", id));
		crit.add(Restrictions.eq("year", year));
		crit.add(Restrictions.eq("month", month));
		List<PaySlip> list = (List<PaySlip>)crit.list();
		PaySlip dataList = null;
		if(list != null && list.size()>0)
			dataList = list.get(0);
		return dataList;
	}

	@Override
	public boolean savePayDetails(PaySlip paySlip) {
		/*PaySlip existingPaySlip = getPayDetails(paySlip.getUserId(), paySlip.getYear(), paySlip.getMonth());
		if(existingPaySlip == null)*/
			persist(paySlip);
		/*else 
		 	update(paySlip);*/
		return true;
	}

	@Override
	public boolean deletePayDetails(Integer id, Integer month, Integer year) {
		PaySlip existingPaySlip = getPayDetails(id, month, year);
		delete(existingPaySlip);
		return true;
	}
}