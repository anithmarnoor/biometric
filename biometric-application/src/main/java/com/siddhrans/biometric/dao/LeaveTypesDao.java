package com.siddhrans.biometric.dao;

import java.util.List;

import com.siddhrans.biometric.model.LeaveTypes;

public interface LeaveTypesDao {
	      
    List<LeaveTypes> findAllLeaveTypes();
    
    LeaveTypes findLeaveTypeById(int typeId);
    
    void saveLeaveType(LeaveTypes type);
     
    void updateLeaveType(LeaveTypes type);
     
    void deleteLeaveType(LeaveTypes type);
}