package com.siddhrans.biometric.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.siddhrans.biometric.dao.DepartmentDao;
import com.siddhrans.biometric.model.Department;
import com.siddhrans.biometric.service.DepartmentService;

@Service("departmentService")
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

	static final Logger logger = LoggerFactory.getLogger(DepartmentServiceImpl.class);
	
	@Autowired
    private DepartmentDao dao;
	
	@Override
	public Department findById(int id) {
		return dao.findById(id);
	}

	@Override
	public void saveDepartment(Department department) {
		logger.debug("DepartmentServiceImpl saving department==> "+department.getDepartmentName());
		dao.saveDepartment(department);
	}

	@Override
	public void updateDepartment(Department department) {
		dao.updateDepartment(department);
	}

	@Override
	public void deleteDepartment(int id) {
		dao.deleteDepartment(id);
	}

	@Override
	public List<Department> findAllDepartments() {
		return dao.findAllDepartments();
	}

	@Override
	public boolean isDepartmentNameUnique(Integer id, String departmentName) {
		// TODO Auto-generated method stub
		return false;
	}

}
