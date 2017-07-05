package com.siddhrans.biometric.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.siddhrans.biometric.model.BiometricData;

@Component
public class FileValidator implements Validator {
         
    public boolean supports(Class<?> clazz) {
        return BiometricData.class.isAssignableFrom(clazz);
    }
 
    public void validate(Object obj, Errors errors) {
    	BiometricData data = (BiometricData) obj;
        if(data.getFile()!=null){
            if (data.getFile().getSize() == 0) {
                errors.rejectValue("file", "missing.file");
            }
        }
    }
}