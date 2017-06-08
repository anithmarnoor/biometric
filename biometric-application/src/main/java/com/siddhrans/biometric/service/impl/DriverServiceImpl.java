package com.siddhrans.biometric.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.siddhrans.biometric.dao.DriverDAO;
import com.siddhrans.biometric.model.Driver;
import com.siddhrans.biometric.service.DriverService;

@Service
public class DriverServiceImpl implements DriverService {

	private DriverDAO driverDAO;

	public void setDriverDAO(DriverDAO driverDAO) {
		this.driverDAO = driverDAO;
	}

	@Override
	@Transactional
	public void addDriver(Driver p) {
		this.driverDAO.addDriver(p);
	}

	@Override
	@Transactional
	public void updateDriver(Driver p) {
		this.driverDAO.updateDriver(p);
	}

	@Override
	@Transactional
	public List<Driver> listDrivers() {
		return this.driverDAO.listDrivers();
	}

	@Override
	@Transactional
	public Driver getDriverById(int id) {
		return this.driverDAO.getDriverById(id);
	}

	@Override
	@Transactional
	public void removeDriver(int id) {
		this.driverDAO.removeDriver(id);
	}
}
