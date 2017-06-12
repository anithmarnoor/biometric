package com.siddhrans.biometric.dao;

import java.util.List;

import com.siddhrans.biometric.model.User;

 
public interface UserDao {
 
    User findById(int id);
     
    User findByUserName(String userName);
     
    void save(User user);
     
    void deleteByUserName(String userName);
     
    List<User> findAllUsers();
 
}