package com.siddhrans.biometric.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.siddhrans.biometric.dao.PaySlipDao;
import com.siddhrans.biometric.dao.SalaryDivDao;
import com.siddhrans.biometric.dao.WagesDao;
import com.siddhrans.biometric.model.PaySlip;
import com.siddhrans.biometric.model.SalaryDivision;
import com.siddhrans.biometric.model.Wages;
import com.siddhrans.biometric.service.PaySlipService;
 

 
@Service("payslipService")
@Transactional
public class PaySlipServiceImpl implements PaySlipService{
 
    @Autowired
    private SalaryDivDao salaryDivDao;

    @Autowired
    private PaySlipDao paySlipDao;
    
    @Autowired
    private WagesDao wagesDao;

	@Override
	public void saveSalaryDivision(SalaryDivision salaryDivision) {
		salaryDivDao.save(salaryDivision);
	}

	@Override
	public void updateSalaryDivision(SalaryDivision salaryDivision) {
		salaryDivDao.update(salaryDivision);
		
	}

	@Override
	public List<SalaryDivision> findSalaryDivision() {
		return salaryDivDao.findSalaryDivision();
	}

	@Override
	public void saveWages(Wages wages) {
		wagesDao.save(wages);		
	}

	@Override
	public void updateWages(Wages wages) {
		wagesDao.update(wages);
	}

	@Override
	public List<Wages> findWages() {
		return wagesDao.findWages();
	}

	@Override
	public PaySlip getPayDetails(Integer id, Integer month, Integer year) {
		return paySlipDao.getPayDetails(id, month, year);
	}

	@Override
	public boolean savePayDetails(PaySlip paySlip) {
		return paySlipDao.savePayDetails(paySlip);
	}

	@Override
	public boolean deletePayDetails(Integer id, Integer month, Integer year) {
		return paySlipDao.deletePayDetails(id, month, year);
	}
}
