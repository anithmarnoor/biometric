package com.siddhrans.biometric.service;

import java.util.List;

import com.siddhrans.biometric.model.UserProfile;

 
 
public interface UserProfileService {
 
    UserProfile findById(int id);
 
    UserProfile findByType(String type);
     
    List<UserProfile> findAll();
     
}
