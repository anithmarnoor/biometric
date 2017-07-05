package com.siddhrans.biometric.service;

import java.util.List;

import com.siddhrans.biometric.model.PaySlip;
import com.siddhrans.biometric.model.SalaryDivision;
import com.siddhrans.biometric.model.Wages;


public interface PaySlipService {
	
	void saveSalaryDivision(SalaryDivision salaryDivision);

	void updateSalaryDivision(SalaryDivision salaryDivision);
	
	List<SalaryDivision> findSalaryDivision();
	
	void saveWages(Wages wages);

	void updateWages(Wages wages);
	
	List<Wages> findWages();
	
	PaySlip getPayDetails(Integer id, Integer month, Integer year);
	
	public boolean savePayDetails(PaySlip paySlip);
	
	public boolean deletePayDetails(Integer id, Integer month, Integer year);
}
