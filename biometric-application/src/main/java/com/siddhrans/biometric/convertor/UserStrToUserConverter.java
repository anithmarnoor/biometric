package com.siddhrans.biometric.convertor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.siddhrans.biometric.model.User;
import com.siddhrans.biometric.service.UserService;

 
/**
 * A converter class used in views to map id's to actual userProfile objects.
 */
@Component
public class UserStrToUserConverter implements Converter<Object, User>{
 
    static final Logger logger = LoggerFactory.getLogger(DeptStrToDeptConverter.class);
     
    @Autowired
    UserService userService;
 
    /**
     * Gets UserProfile by Id
     * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
     */
    public User convert(Object element) {
    	if(element instanceof String){
    		Integer id = Integer.parseInt((String)element);
            logger.info("User ID is: {}",id);
            User user= userService.findById(id);
            logger.info("User Name is : {}",user.getUserName());
            return user;
    	} else {
    		return (User)element;
    	}
    }    
}