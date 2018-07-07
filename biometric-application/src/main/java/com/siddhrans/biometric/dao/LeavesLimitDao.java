package com.siddhrans.biometric.dao;

import java.util.List;

import com.siddhrans.biometric.model.LeaveTypes;
import com.siddhrans.biometric.model.LeavesLimit;

public interface LeavesLimitDao {
	
	List<LeavesLimit> findAllLeavesLimit();
    
    LeavesLimit findLeavesLimitById(int limitId);
    
    void saveLeavesLimit(LeavesLimit limit);
    
    void updateLeavesLimit(LeavesLimit limit);
     
    void deleteLeavesLimit(LeavesLimit limit);
    
    public LeavesLimit findLeavesLimitByLeavesType(LeaveTypes leavesType);
}