package com.siddhrans.biometric.dao;

import java.util.List;

import com.siddhrans.biometric.model.Designation;
 
public interface DesignationDao {
	
	Designation findById(int id);

    void saveDesignation(Designation designation);
     
    void updateDesignation(Designation designation);
     
    void deleteDesignation(int id);
 
    List<Designation> findAllDesignations(); 
     
    boolean isDesignationNameUnique(Integer id, String designationName);
}