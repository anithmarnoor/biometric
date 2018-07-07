package com.siddhrans.biometric.dao;

import java.util.List;

import com.siddhrans.biometric.model.Leaves;
import com.siddhrans.biometric.model.User;

public interface LeavesDao {

    List<Leaves> findAllLeaves();
    
    Leaves findLeaveById(int leaveId);
    
    List<Leaves> findLeavesByUser(User user);
    
    void saveLeave(Leaves leave);
     
    void updateLeave(Leaves leave);
     
    void deleteLeave(Leaves leave);
}