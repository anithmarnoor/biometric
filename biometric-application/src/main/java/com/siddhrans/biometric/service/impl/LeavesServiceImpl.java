package com.siddhrans.biometric.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.siddhrans.biometric.dao.AvailableLeavesDao;
import com.siddhrans.biometric.dao.LeaveTypesDao;
import com.siddhrans.biometric.dao.LeavesDao;
import com.siddhrans.biometric.dao.LeavesLimitDao;
import com.siddhrans.biometric.model.LeaveTypes;
import com.siddhrans.biometric.model.Leaves;
import com.siddhrans.biometric.model.LeavesAvailable;
import com.siddhrans.biometric.model.LeavesLimit;
import com.siddhrans.biometric.model.User;
import com.siddhrans.biometric.service.LeavesService;

@Service("leavesService")
@Transactional
public class LeavesServiceImpl implements LeavesService {

	@Autowired
    private LeavesDao leavesDao;

	@Autowired
    private LeavesLimitDao leavesLimitDao;
	
	@Autowired
    private LeaveTypesDao leavetypesdao;
	
	@Autowired
    private AvailableLeavesDao availableLeavesDao;

	@Override
	public List<LeavesLimit> findAllLeavesLimit() {
		return leavesLimitDao.findAllLeavesLimit();
	}

	@Override
	public LeavesLimit findLeavesLimitById(int limitId) {
		return leavesLimitDao.findLeavesLimitById(limitId);
	}

	@Override
	public void saveLeavesLimit(LeavesLimit limit) {
		leavesLimitDao.saveLeavesLimit(limit);
	}

	@Override
	public void updateLeavesLimit(LeavesLimit limit) {
		leavesLimitDao.updateLeavesLimit(limit);
	}

	@Override
	public void deleteLeavesLimit(LeavesLimit limit) {
		leavesLimitDao.deleteLeavesLimit(limit);
	}
	
	@Override
	public LeavesLimit findLeavesLimitByLeavesType(LeaveTypes leavesType){
		return leavesLimitDao.findLeavesLimitByLeavesType(leavesType);
	}

	@Override
	public List<LeaveTypes> findAllLeaveTypes() {
		return leavetypesdao.findAllLeaveTypes();
	}

	@Override
	public LeaveTypes findLeaveTypeById(int typeId) {
		return leavetypesdao.findLeaveTypeById(typeId);
	}

	@Override
	public void saveLeaveType(LeaveTypes type) {
		leavetypesdao.saveLeaveType(type);
	}

	@Override
	public void updateLeaveType(LeaveTypes type) {
		leavetypesdao.updateLeaveType(type);
	}

	@Override
	public void deleteLeaveType(LeaveTypes type) {
		leavetypesdao.deleteLeaveType(type);
	}

	@Override
	public List<Leaves> findAllLeaves() {
		return leavesDao.findAllLeaves();
	}

	@Override
	public Leaves findLeaveById(int leaveId) {
		return leavesDao.findLeaveById(leaveId);
	}

	@Override
	public List<Leaves> findLeavesByUser(User user) {
		return leavesDao.findLeavesByUser(user);
	}

	@Override
	public void saveLeave(Leaves leave) {
		leavesDao.saveLeave(leave);
	}

	@Override
	public void updateLeave(Leaves leave) {
		leavesDao.updateLeave(leave);
	}

	@Override
	public void deleteLeave(Leaves leave) {
		leavesDao.deleteLeave(leave);
	}

	@Override
	public LeavesAvailable findAvailableLeavesByUserAndType(User user, LeaveTypes leaveTypes) {
		return availableLeavesDao.findAvailableLeavesByUserAndType(user, leaveTypes);
	}

	@Override
	public void saveLeavesAvailable(LeavesAvailable leavesAvailable) {
		availableLeavesDao.saveLeavesAvailable(leavesAvailable);
	}

	@Override
	public void updateLeavesAvailable(LeavesAvailable leavesAvailable) {
		availableLeavesDao.updateLeavesAvailable(leavesAvailable);
	}

	@Override
	public void deleteLeavesAvailable(LeavesAvailable leavesAvailable) {
		availableLeavesDao.deleteLeavesAvailable(leavesAvailable);
	}
}