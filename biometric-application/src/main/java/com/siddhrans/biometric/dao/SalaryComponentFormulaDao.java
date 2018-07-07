package com.siddhrans.biometric.dao;

import java.util.List;

import com.siddhrans.biometric.model.SalaryComponentFormula;


public interface SalaryComponentFormulaDao {

	List<SalaryComponentFormula> findSalaryDivPercentages();
	
	SalaryComponentFormula findDivPercentageById(Integer percentagesId);
	/*
	SalaryDivPercentages findSalDivPercentagesByDesignation(Designation designation);*/
	
	void save(SalaryComponentFormula percentages);
	
	public void update(SalaryComponentFormula percentages);
}
