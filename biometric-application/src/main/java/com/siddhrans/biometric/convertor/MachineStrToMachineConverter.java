package com.siddhrans.biometric.convertor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.siddhrans.biometric.service.BiometricDataService;
import com.siddhrans.biometric.model.BiometricMachine;

 
/**
 * A converter class used in views to map id's to actual userProfile objects.
 */
@Component
public class MachineStrToMachineConverter implements Converter<Object, BiometricMachine>{
 
    static final Logger logger = LoggerFactory.getLogger(MachineStrToMachineConverter.class);
     
    @Autowired
    BiometricDataService biometricDataService;
 
    /**
     * Gets UserProfile by Id
     * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
     */
    public BiometricMachine convert(Object element) {
    	if(element instanceof String){
    		Integer id = Integer.parseInt((String)element);
            logger.info("Department ID is: {}",id);
            BiometricMachine department= biometricDataService.findMachineById(id);
            logger.info("Department : {}",department);
            return department;
    	} else {
    		return (BiometricMachine)element;
    	}
    }    
}