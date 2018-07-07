package com.siddhrans.biometric.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.siddhrans.biometric.dao.HolidaysDao;
import com.siddhrans.biometric.model.Holidays;
import com.siddhrans.biometric.service.HolidaysService;

@Service("holidaysService")
@Transactional
public class HolidaysServiceImpl implements HolidaysService {

	@Autowired
    private HolidaysDao holidaysDao;

	@Override
	public List<Holidays> findAllHolidays() {
		return holidaysDao.findAllHolidays();
	}

	@Override
	public Holidays findHolidaysById(int holidaysId) {
		return holidaysDao.findHolidaysById(holidaysId);
	}

	@Override
	public void saveHolidays(Holidays holidays) {
		holidaysDao.saveHolidays(holidays);
	}

	@Override
	public void updateHolidays(Holidays holidays) {
		holidaysDao.updateHolidays(holidays);
	}

	@Override
	public void deleteHolidays(Holidays holidays) {
		holidaysDao.deleteHolidays(holidays);
	}

	@Override
	public List<Holidays> findHolidaysByYear(int year) {
		return holidaysDao.findHolidaysByYear(year);
	}

	@Override
	public List<Holidays> findHolidaysByMonth(int month) {
		return holidaysDao.findHolidaysByMonth(month);
	}

	@Override
	public List<Holidays> findHolidaysByMonthAndYear(int month, int year) {
		return holidaysDao.findHolidaysByMonthAndYear(month, year);
	}

	@Override
	public List<Holidays> findHolidaysByDate(String date) {
		return holidaysDao.findHolidaysByDate(date);
	}

	@Override
	public List<Holidays> findHolidaysByMonthAndYearAndDate(int month, int year, int date) {
		return holidaysDao.findHolidaysByMonthAndYearAndDate(month, year, date);
	}
	
}