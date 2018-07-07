package com.siddhrans.biometric.dao;

import java.util.List;

import com.siddhrans.biometric.model.SalaryComponent;


public interface SalaryComponentDao {

	/*List<SalaryComponent> findSalaryDivision();*/
	
	List<SalaryComponent> findAllSalaryDivisions();
	
	SalaryComponent findSalaryDivisionByName(String name);
	
	SalaryComponent findSalaryDivisionById(Integer id);
	
	void deleteSalaryDivision(SalaryComponent division);
	
	void save(SalaryComponent division);
	
	public void update(SalaryComponent division);
}
