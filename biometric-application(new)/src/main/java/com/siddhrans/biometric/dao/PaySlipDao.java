package com.siddhrans.biometric.dao;

import com.siddhrans.biometric.model.PaySlip;


public interface PaySlipDao {
	public PaySlip getPayDetails(Integer id, Integer month, Integer year);
	public boolean savePayDetails(PaySlip paySlip);
	public boolean deletePayDetails(Integer id, Integer month, Integer year);
}
