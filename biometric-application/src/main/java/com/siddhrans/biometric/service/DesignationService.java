package com.siddhrans.biometric.service;

import java.util.List;

import com.siddhrans.biometric.model.Designation;
 
public interface DesignationService {
     
    Designation findById(int id);

    void saveDesignation(Designation designation);
     
    void updateDesignation(Designation designation);
     
    void deleteDesignation(int id);
 
    List<Designation> findAllDesignations(); 
     
    boolean isDesignationNameUnique(Integer id, String designationName);
}