package com.siddhrans.biometric.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.siddhrans.biometric.dao.AbstractDao;
import com.siddhrans.biometric.dao.DepartmentDao;
import com.siddhrans.biometric.model.Department;

@Repository("departmentDao")
@Transactional
public class DepartmentDaoImpl extends AbstractDao<Integer, Department> implements DepartmentDao {

	@Override
	public Department findById(int id) {
		Department department = getByKey(id);
		/*if(user!=null){
			Hibernate.initialize(user.getUserProfile());
		}*/
		return department;
	}

	@Override
	public void saveDepartment(Department department) {
		persist(department);
	}

	@Override
	public void updateDepartment(Department department) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteDepartment(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Department> findAllDepartments() {
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		List<Department> departments = (List<Department>) criteria.list();
		/*for(Department department:departments)
		{
			Hibernate.initialize(department.getDesignations());
		}*/
		return departments;
	}

	@Override
	public boolean isDepartmentNameUnique(Integer id, String departmentName) {
		// TODO Auto-generated method stub
		return false;
	}

}
