package com.siddhrans.biometric.convertor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.siddhrans.biometric.model.LeaveTypes;
import com.siddhrans.biometric.service.LeavesService;

 
/**
 * A converter class used in views to map id's to actual userProfile objects.
 */
@Component
public class LeaveTypeStrToLeaveType implements Converter<Object, LeaveTypes>{
 
    static final Logger logger = LoggerFactory.getLogger(LeaveTypeStrToLeaveType.class);
     
    @Autowired
    LeavesService leavesService;
 
    /**
     * Gets UserProfile by Id
     * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
     */
    public LeaveTypes convert(Object element) {
    	if(element instanceof String){
    		Integer id = Integer.parseInt((String)element);
            logger.info("Department ID is: {}",id);
            LeaveTypes leaveTypes = leavesService.findLeaveTypeById(id);
            logger.info("Leave Types : {}",leaveTypes);
            return leaveTypes;
    	} else {
    		return (LeaveTypes)element;
    	}
    }    
}