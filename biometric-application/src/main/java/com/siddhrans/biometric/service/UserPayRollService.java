package com.siddhrans.biometric.service;

import java.util.List;

import com.siddhrans.biometric.model.User;
import com.siddhrans.biometric.model.UserPayRoll;

public interface UserPayRollService {
	List<UserPayRoll> findUserPayRollDetails(User user);

	void deleteUserPayRollDetails(User user);

	void saveOrUpdateUserPayRollDetails(UserPayRoll payRoll);
	
	void saveOrUpdateAllUserPayRollDetails(List<UserPayRoll> payRolls);
}
