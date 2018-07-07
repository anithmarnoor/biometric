package com.siddhrans.biometric.dao;

import java.util.List;

import com.siddhrans.biometric.model.User;
import com.siddhrans.biometric.model.Wages;


public interface WagesDao {

	List<Wages> findWages();
	
	Wages findWageById(Integer wagesId);
	
	Wages findWagesByUser(User user);
	
	void save(Wages wages);
	
	public void update(Wages wages);
}
