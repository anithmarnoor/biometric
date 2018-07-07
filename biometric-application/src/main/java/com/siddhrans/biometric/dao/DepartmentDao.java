package com.siddhrans.biometric.dao;

import java.util.List;

import com.siddhrans.biometric.model.Department;
 
public interface DepartmentDao {
	
	Department findById(int id);

    void saveDepartment(Department department);
     
    void updateDepartment(Department department);
     
    void deleteDepartment(int id);
 
    List<Department> findAllDepartments(); 
     
    boolean isDepartmentNameUnique(Integer id, String departmentName);
}