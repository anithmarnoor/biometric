package com.siddhrans.biometric.convertor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.siddhrans.biometric.model.SalaryComponent;
import com.siddhrans.biometric.service.PaySlipService;
 
/**
 * A converter class used in views to map id's to actual userProfile objects.
 */
@Component
public class SalDivStrToSalDivConverter implements Converter<Object, SalaryComponent>{
 
    static final Logger logger = LoggerFactory.getLogger(SalDivStrToSalDivConverter.class);
     
    @Autowired
    PaySlipService paySlipService;
 
    /**
     * Gets UserProfile by Id
     * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
     */
    public SalaryComponent convert(Object element) {
    	if(element instanceof String){
    		Integer id = Integer.parseInt((String)element);
            logger.info("Salary Division ID is: {}",id);
            SalaryComponent division = paySlipService.findSalaryDivisionById(id);
            logger.info("Division is : {}",division);
            return division;
    	} else {
    		return (SalaryComponent)element;
    	}
    }    
}