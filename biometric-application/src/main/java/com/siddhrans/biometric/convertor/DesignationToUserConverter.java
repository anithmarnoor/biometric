package com.siddhrans.biometric.convertor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.siddhrans.biometric.model.Department;
import com.siddhrans.biometric.model.Designation;
import com.siddhrans.biometric.model.UserProfile;
import com.siddhrans.biometric.service.DepartmentService;
import com.siddhrans.biometric.service.DesignationService;
import com.siddhrans.biometric.service.UserProfileService;

 
/**
 * A converter class used in views to map id's to actual userProfile objects.
 */
@Component
public class DesignationToUserConverter implements Converter<Object, Designation>{
 
    static final Logger logger = LoggerFactory.getLogger(DesignationToUserConverter.class);
     
    @Autowired
    DepartmentService departmentService;
    
    @Autowired
    DesignationService designationService;
 
    /**
     * Gets UserProfile by Id
     * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
     */
    public Designation convert(Object element) {
    	if(element instanceof String){
    		Integer id = Integer.parseInt((String)element);
            logger.info("Designation ID is: {}",id);
            Designation designation= designationService.findById(id);
            logger.info("designation : {}",designation);
            return designation;
    	} else {
    		return (Designation)element;
    	}
    }    
}