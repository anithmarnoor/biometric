package com.siddhrans.biometric.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CommonOperations {



	public static boolean isLeapYear() {
		
		Date date = new Date();
		Calendar cal = Calendar.getInstance();
	    cal.setTime(date);
	    int year = cal.get(Calendar.YEAR);

		/*if ((year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0))) {
			return true;
		} else {
			return false;
		}*/
		
		GregorianCalendar gregorianCalendar = (
				GregorianCalendar) GregorianCalendar.getInstance();
		return gregorianCalendar.isLeapYear(year);
	}

}
