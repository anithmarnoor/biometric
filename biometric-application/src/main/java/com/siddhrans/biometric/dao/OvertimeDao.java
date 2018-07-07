package com.siddhrans.biometric.dao;

import java.util.List;

import com.siddhrans.biometric.model.Designation;
import com.siddhrans.biometric.model.OverTime;

public interface OvertimeDao {
	
    OverTime findById(int id);

    void saveOverTime(OverTime overTime);
     
    void updateOverTime(OverTime overTime);
     
    void deleteOverTime(int id);
 
    List<OverTime> findAllOverTimes();
    
    OverTime findOTByDesignation(Designation designation);
}