package com.siddhrans.biometric.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.siddhrans.biometric.dao.DesignationDao;
import com.siddhrans.biometric.model.Designation;
import com.siddhrans.biometric.service.DesignationService;

@Service("designationService")
@Transactional
public class DesignationServiceImpl implements DesignationService {

	@Autowired
    private DesignationDao dao;
	
	@Override
	public Designation findById(int id) {
		return dao.findById(id);
	}

	@Override
	public void saveDesignation(Designation designation) {
		dao.saveDesignation(designation);
	}

	@Override
	public void updateDesignation(Designation designation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteDesignation(int id) {
		dao.deleteDesignation(id);
	}

	@Override
	public List<Designation> findAllDesignations() {
		return dao.findAllDesignations();
	}

	@Override
	public boolean isDesignationNameUnique(Integer id, String designationName) {
		// TODO Auto-generated method stub
		return false;
	}

}
