package com.siddhrans.biometric.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomDateTimeComparator implements Comparator<LocalDateTime> {
	static final Logger logger = LoggerFactory.getLogger(CustomDateTimeComparator.class);
	@Override
	public int compare(LocalDateTime o1, LocalDateTime o2){

		logger.debug("o1.compareTo(o2)==>"+o1.compareTo(o2));
		if(o1.compareTo(o2) > 0){
			logger.debug("After...");
			return 1;
		} else if(o1.compareTo(o2) == 0){
			logger.debug("Equals...");
			return 0;
		} else {
			logger.debug("Before than...");
			return -1;
		}
	}
}
