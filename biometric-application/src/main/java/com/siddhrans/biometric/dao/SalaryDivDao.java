package com.siddhrans.biometric.dao;

import java.util.List;

import com.siddhrans.biometric.model.SalaryDivision;


public interface SalaryDivDao {

	List<SalaryDivision> findSalaryDivision();
	
	void save(SalaryDivision division);
	
	public void update(SalaryDivision division);
}
