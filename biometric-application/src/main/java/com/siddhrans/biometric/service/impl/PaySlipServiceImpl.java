package com.siddhrans.biometric.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.siddhrans.biometric.dao.PaySlipDao;
import com.siddhrans.biometric.dao.SalaryComponentFormulaDao;
import com.siddhrans.biometric.dao.SalaryComponentDao;
import com.siddhrans.biometric.dao.WagesDao;
import com.siddhrans.biometric.model.PaySlip;
import com.siddhrans.biometric.model.SalaryComponentFormula;
import com.siddhrans.biometric.model.SalaryComponent;
import com.siddhrans.biometric.model.User;
import com.siddhrans.biometric.model.Wages;
import com.siddhrans.biometric.service.PaySlipService;


@Service("payslipService")
@Transactional
public class PaySlipServiceImpl implements PaySlipService{

	@Autowired
	private SalaryComponentDao salaryComponentDao;

	@Autowired
	private SalaryComponentFormulaDao salaryComponentFormulaDao;

	@Autowired
	private PaySlipDao paySlipDao;

	@Autowired
	private WagesDao wagesDao;

	public void saveSalaryDivision(SalaryComponent salaryComponent) {
		salaryComponentDao.save(salaryComponent);
	}

	public void updateSalaryDivision(SalaryComponent salaryComponent) {
		salaryComponentDao.update(salaryComponent);
	}

	public void deleteSalaryDivision(SalaryComponent salaryComponent) {
		salaryComponentDao.deleteSalaryDivision(salaryComponent);
	}

	/*public List<SalaryComponent> findSalaryDivision() {
		return salaryComponentDao.findSalaryDivision();
	}*/

	public List<SalaryComponent> findAllSalaryDivisions() {
		return salaryComponentDao.findAllSalaryDivisions();
	}	

	public SalaryComponent findSalaryDivisionByName(String name) {
		return salaryComponentDao.findSalaryDivisionByName(name);
	}

	public SalaryComponent findSalaryDivisionById(Integer id) {
		return salaryComponentDao.findSalaryDivisionById(id);
	}

	public void saveWages(Wages wages) {
		wagesDao.save(wages);		
	}

	public void updateWages(Wages wages) {
		wagesDao.update(wages);
	}

	public List<Wages> findWages() {
		return wagesDao.findWages();
	}

	public Wages findWageById(Integer wagesId) {
		return wagesDao.findWageById(wagesId);
	}

	public Wages findWagesByUser(User user) {
		return wagesDao.findWagesByUser(user);
	}

	public List<PaySlip> getPayDetails(User user, Integer month, Integer year, String componentName) {
		return paySlipDao.getPayDetails(user, month, year, componentName);
	}

	public boolean savePayDetails(PaySlip paySlip) {
		return paySlipDao.savePayDetails(paySlip);
	}

	public boolean deletePayDetails(User user, Integer month, Integer year, String componentName) {
		return paySlipDao.deletePayDetails(user, month, year, componentName);
	}

	@Override
	public List<SalaryComponentFormula> findSalaryDivPercentages() {
		return salaryComponentFormulaDao.findSalaryDivPercentages();
	}

	@Override
	public SalaryComponentFormula findDivPercentageById(Integer percentagesId) {
		return salaryComponentFormulaDao.findDivPercentageById(percentagesId);
	}

	/*@Override
	public SalaryDivPercentages findSalDivPercentagesByDesignation(Designation designation) {
		return salaryComponentFormulaDao.findSalDivPercentagesByDesignation(designation);
	}*/

	@Override
	public void saveSalaryDivPercentages(SalaryComponentFormula percentages) {
		salaryComponentFormulaDao.save(percentages);
	}

	@Override
	public void updateSalaryDivPercentages(SalaryComponentFormula percentages) {
		salaryComponentFormulaDao.update(percentages);
	}
}
