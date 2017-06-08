package com.siddhrans.biometric.service;

import java.util.List;

import com.siddhrans.biometric.model.Driver;

public interface DriverService {
	
	public void addDriver(Driver p);
	public void updateDriver(Driver p);
	public List<Driver> listDrivers();
	public Driver getDriverById(int id);
	public void removeDriver(int id);

}
