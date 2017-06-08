package com.siddhrans.biometric.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.siddhrans.biometric.model.BiometricData;
import com.siddhrans.biometric.service.BiometricService;

@Controller
public class BiometricAppController {
	private BiometricService biometricService;
	
	
	public BiometricService getBiometricService() {
		return biometricService;
	}
	@Autowired(required=true)
	@Qualifier(value="biometricService")
	public void setBiometricService(BiometricService biometricService) {
		this.biometricService = biometricService;
	}

	
	@RequestMapping(value = "/persons", method = RequestMethod.GET)
	public String listPersons(Model model) {
		model.addAttribute("biometricData", new BiometricData());
		return "person";
	}
	
	//For add and update person both
	@RequestMapping(value= "/biometric/add", method = RequestMethod.POST)
	public String addPerson(@ModelAttribute("biometricData") BiometricData p){
		
		if(p.getId() == 0){
			//new person, add it
			this.biometricService.saveBiometricData(p);
		}else{
			//existing person, call update
			this.biometricService.updateBiometricData(p);
		}
		
		return "redirect:/biometric";
		
	}
	
	@RequestMapping("/remove/{id}")
    public String removePerson(@PathVariable("id") int id){
		
        this.biometricService.deleteBiometricData(id);
        return "redirect:/biometric";
    }
 
    @RequestMapping("/edit/{id}")
    public String editPerson(@PathVariable("id") int id, Model model){
        model.addAttribute("person", this.biometricService.fetchBiometricDataById(id));
        model.addAttribute("listPersons", this.biometricService.fetchBiometricData(new BiometricData()));
        return "person";
    }
}
