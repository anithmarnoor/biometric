package com.siddhrans.biometric.dao;

import java.util.List;

import com.siddhrans.biometric.model.UserProfile;

 
 
public interface UserProfileDao {
 
    List<UserProfile> findAll();
     
    UserProfile findByType(String type);
     
    UserProfile findById(int id);
}