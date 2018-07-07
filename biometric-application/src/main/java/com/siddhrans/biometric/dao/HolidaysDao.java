package com.siddhrans.biometric.dao;

import java.util.List;

import com.siddhrans.biometric.model.Holidays;

public interface HolidaysDao {

	List<Holidays> findAllHolidays();
    
    Holidays findHolidaysById(int holidaysId);
    
    void saveHolidays(Holidays holidays);
    
    void updateHolidays(Holidays holidays);
     
    void deleteHolidays(Holidays holidays);
   
    List<Holidays> findHolidaysByYear(int year);
    
    List<Holidays> findHolidaysByMonth(int month);
    
    List<Holidays> findHolidaysByDate(String date);
    
    List<Holidays> findHolidaysByMonthAndYear(int month, int year);
    
    List<Holidays> findHolidaysByMonthAndYearAndDate(int month, int year, int date);
}