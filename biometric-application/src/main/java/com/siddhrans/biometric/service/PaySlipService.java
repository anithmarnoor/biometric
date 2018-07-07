package com.siddhrans.biometric.service;

import java.util.List;

import com.siddhrans.biometric.model.Designation;
import com.siddhrans.biometric.model.PaySlip;
import com.siddhrans.biometric.model.SalaryComponentFormula;
import com.siddhrans.biometric.model.SalaryComponent;
import com.siddhrans.biometric.model.User;
import com.siddhrans.biometric.model.Wages;


public interface PaySlipService {

	void saveSalaryDivision(SalaryComponent salaryComponent);

	void updateSalaryDivision(SalaryComponent salaryComponent);

	List<SalaryComponent> findAllSalaryDivisions();

	SalaryComponent findSalaryDivisionByName(String name);

	SalaryComponent findSalaryDivisionById(Integer id);

	void deleteSalaryDivision(SalaryComponent division);

	void saveWages(Wages wages);

	void updateWages(Wages wages);

	List<Wages> findWages();

	Wages findWageById(Integer wagesId);

	Wages findWagesByUser(User user);

	List<PaySlip> getPayDetails(User user, Integer month, Integer year, String componentName);

	public boolean savePayDetails(PaySlip paySlip);

	public boolean deletePayDetails(User user, Integer month, Integer year, String componentName);

	List<SalaryComponentFormula> findSalaryDivPercentages();

	SalaryComponentFormula findDivPercentageById(Integer percentagesId);

	/*SalaryDivPercentages findSalDivPercentagesByDesignation(Designation designation);*/

	void saveSalaryDivPercentages(SalaryComponentFormula percentages);

	public void updateSalaryDivPercentages(SalaryComponentFormula percentages);

}
