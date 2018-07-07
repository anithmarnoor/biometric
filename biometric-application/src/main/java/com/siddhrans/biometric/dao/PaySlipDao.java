package com.siddhrans.biometric.dao;

import com.siddhrans.biometric.model.PaySlip;
import com.siddhrans.biometric.model.User;


public interface PaySlipDao {
	public List<PaySlip> getPayDetails(User user, Integer month, Integer year, String componentName);
	public boolean savePayDetails(PaySlip paySlip);
	public boolean deletePayDetails(User user, Integer month, Integer year, String componentName);
}
