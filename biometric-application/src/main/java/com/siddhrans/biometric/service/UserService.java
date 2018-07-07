package com.siddhrans.biometric.service;

import java.util.List;

import com.siddhrans.biometric.model.User;

public interface UserService {
     
    User findById(int id);
     
    User findByUserName(String userName);
    
    User findByPhoneNo(String phoneNo);
    
    User findByDlNo(String dlNo);
     
    void saveUser(User user);
     
    void updateUser(User user);
     
    void deleteUserByUserName(String userName);
    
    void deleteUserById(Integer userId);
 
    List<User> findAllUsers();
    
    List<User> findAllInactiveUsers(); 
     
    boolean isUserNameUnique(Integer id, String userName);
    
    boolean isPhoneNoUnique(Integer id, String phoneNo);
    
    boolean isDlNoUnique(Integer id, String dlNo);
 
}