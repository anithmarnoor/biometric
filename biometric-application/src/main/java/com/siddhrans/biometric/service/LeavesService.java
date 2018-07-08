package com.siddhrans.biometric.service;

import java.util.List;

import com.siddhrans.biometric.model.LeaveTypes;
import com.siddhrans.biometric.model.Leaves;
import com.siddhrans.biometric.model.LeavesAvailable;
import com.siddhrans.biometric.model.LeavesLimit;
import com.siddhrans.biometric.model.User;
 
public interface LeavesService {
    
	List<LeavesLimit> findAllLeavesLimit();
    
    LeavesLimit findLeavesLimitById(int limitId);
    
    void saveLeavesLimit(LeavesLimit limit);
    
    void updateLeavesLimit(LeavesLimit limit);
     
    void deleteLeavesLimit(LeavesLimit limit);  
    
    LeavesLimit findLeavesLimitByLeavesType(LeaveTypes leavesType);
    
    List<LeavesLimit> findLeavesLimitByUser(Integer userId);
    
    List<LeaveTypes> findAllLeaveTypes();
    
    LeaveTypes findLeaveTypeById(int typeId);
    
    void saveLeaveType(LeaveTypes type);
     
    void updateLeaveType(LeaveTypes type);
     
    void deleteLeaveType(LeaveTypes type);
    
    List<Leaves> findAllLeaves();
    
    Leaves findLeaveById(int leaveId);
    
    List<Leaves> findLeavesByUser(User user);
    
    void saveLeave(Leaves leave);
     
    void updateLeave(Leaves leave);
     
    void deleteLeave(Leaves leave);
    
    LeavesAvailable findAvailableLeavesByUserAndType(User user, LeaveTypes leaveTypes);
    
    List<LeavesAvailable> findAvailableLeavesByUser(User user);
    
    void saveLeavesAvailable(LeavesAvailable leavesAvailable);
    
    void updateLeavesAvailable(LeavesAvailable leavesAvailable);
     
    void deleteLeavesAvailable(LeavesAvailable leavesAvailable);
}