package com.siddhrans.biometric.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.siddhrans.biometric.dao.UserDao;
import com.siddhrans.biometric.model.User;
import com.siddhrans.biometric.service.UserService;
 

 
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService{
 
    @Autowired
    private UserDao dao;
 
    @Autowired
    private PasswordEncoder passwordEncoder;
     
    public User findById(int id) {
        return dao.findById(id);
    }
 
    public User findByUserName(String userName) {
        User user = dao.findByUserName(userName);
        return user;
    }
    
	@Override
	public User findByPhoneNo(String phoneNo) {
		 User user = dao.findByPhoneNo(phoneNo);
	        return user;
	}

	@Override
	public User findByDlNo(String dlNo) {
		 User user = dao.findByDlNo(dlNo);
	        return user;
	}
 
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        dao.save(user);
    }
 
    /*
     * Since the method is running with Transaction, No need to call hibernate update explicitly.
     * Just fetch the entity from db and update it with proper values within transaction.
     * It will be updated in db once transaction ends. 
     */
    public void updateUser(User user) {
        User entity = dao.findById(user.getId());
        if(entity!=null){
            entity.setUserName(user.getUserName());
            if(!user.getPassword().equals(entity.getPassword())){
                entity.setPassword(passwordEncoder.encode(user.getPassword()));
            }
            entity.setFirstName(user.getFirstName());
            entity.setLastName(user.getLastName());
            entity.setEmail(user.getEmail());
            entity.setAddress(user.getAddress());
            entity.setDlNo(user.getDlNo());
            entity.setDob(user.getDob());
            entity.setPhoneNo(user.getPhoneNo());
            entity.setDoj(user.getDoj());
            entity.setUserProfiles(user.getUserProfiles());
        }
    }
 
     
    public void deleteUserByUserName(String userName) {
        dao.deleteByUserName(userName);
    }
 
    public List<User> findAllUsers() {
        return dao.findAllUsers();
    }
 
    public boolean isUserNameUnique(Integer id, String userName) {
        User user = findByUserName(userName);
        return ( user == null || ((id != null) && (user.getId() == id)));
    }
    
    public boolean isPhoneNoUnique(Integer id, String phoneNo) {
        User user = findByPhoneNo(phoneNo);
        return ( user == null || ((id != null) && (user.getId() == id)));
    }
    
    public boolean isDlNoUnique(Integer id, String dlNo) {
        User user = findByDlNo(dlNo);
        return ( user == null || ((id != null) && (user.getId() == id)));
    }     
}
