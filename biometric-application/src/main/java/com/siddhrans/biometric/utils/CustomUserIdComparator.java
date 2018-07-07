package com.siddhrans.biometric.utils;

import java.util.ArrayList;
import java.util.Comparator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomUserIdComparator implements Comparator<ArrayList<String>> {
	static final Logger logger = LoggerFactory.getLogger(CustomUserIdComparator.class);
	@Override
	public int compare(ArrayList<String> o1, ArrayList<String> o2) {
		logger.debug("Sorting Start");
		logger.debug(Integer.parseInt(o1.get(0))+" > "+Integer.parseInt(o2.get(0)) +" = "+(Integer.parseInt(o1.get(0)) > Integer.parseInt(o2.get(0))));
		if(Integer.parseInt(o1.get(0)) > Integer.parseInt(o2.get(0))){
			logger.debug("Less than...");
			return 1;
		} else if(Integer.parseInt(o1.get(0)) == Integer.parseInt(o2.get(0))){
			return 0;
		} else {
			logger.debug("Greater than...");
			return -1;
		}
	}
}
