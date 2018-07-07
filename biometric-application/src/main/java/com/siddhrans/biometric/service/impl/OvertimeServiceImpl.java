package com.siddhrans.biometric.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.siddhrans.biometric.dao.OvertimeDao;
import com.siddhrans.biometric.model.Designation;
import com.siddhrans.biometric.model.OverTime;
import com.siddhrans.biometric.model.User;
import com.siddhrans.biometric.service.OvertimeService;

@Service("overtimeService")
@Transactional
public class OvertimeServiceImpl implements OvertimeService {

	@Autowired
    private OvertimeDao dao;
	
	@Override
	public OverTime findById(int id) {
		return dao.findById(id);
	}

	@Override
	public void saveOverTime(OverTime overTime) {
		dao.saveOverTime(overTime);
	}

	
	/*
     * Since the method is running with Transaction, No need to call hibernate update explicitly.
     * Just fetch the entity from db and update it with proper values within transaction.
     * It will be updated in db once transaction ends. 
     */
	@Override
	public void updateOverTime(OverTime overTime) {
		OverTime entity = findById(overTime.getOverTimeId());
		if(entity!=null){
			entity.setDesignation(overTime.getDesignation());
			entity.setOvertimeAmount(overTime.getOvertimeAmount());
			entity.setOverTimeId(overTime.getOverTimeId());
		}
	}
	
	@Override
	public void deleteOverTime(int id) {
		dao.deleteOverTime(id);
	}

	@Override
	public List<OverTime> findAllOverTimes() {
		return dao.findAllOverTimes();
	}

	@Override
	public OverTime findOTByDesignation(Designation designation) {
		return dao.findOTByDesignation(designation);
	}
}
