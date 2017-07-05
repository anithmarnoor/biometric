package com.siddhrans.biometric.dao;

import java.util.List;

import com.siddhrans.biometric.model.Wages;


public interface WagesDao {

	List<Wages> findWages();
	
	void save(Wages wages);
	
	public void update(Wages wages);
}
