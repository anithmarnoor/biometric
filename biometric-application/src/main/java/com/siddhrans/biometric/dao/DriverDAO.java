package com.siddhrans.biometric.dao;

import java.util.List;

import com.siddhrans.biometric.model.Driver;

public interface DriverDAO {
	public void addDriver(Driver p);
	public void updateDriver(Driver p);
	public List<Driver> listDrivers();
	public Driver getDriverById(int id);
	public void removeDriver(int id);

}
