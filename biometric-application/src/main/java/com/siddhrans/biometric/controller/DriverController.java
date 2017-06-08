package com.siddhrans.biometric.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.siddhrans.biometric.model.Driver;
import com.siddhrans.biometric.service.DriverService;

@Controller
public class DriverController {

private DriverService driverService;
	
	@Autowired(required=true)
	@Qualifier(value="driverService")
	public void setDriverService(DriverService ps){
		this.driverService = ps;
	}
	
	@RequestMapping(value = "/drivers", method = RequestMethod.GET)
	public String listDrivers(Model model) {
		model.addAttribute("driver", new Driver());
		model.addAttribute("listDrivers", this.driverService.listDrivers());
		return "driver";
	}
	
	//For add and update driver both
	@RequestMapping(value= "/driver/add", method = RequestMethod.POST)
	public String addDriver(@ModelAttribute("driver") Driver p){
		
		if(p.getId() == 0){
			//new driver, add it
			this.driverService.addDriver(p);
		}else{
			//existing driver, call update
			this.driverService.updateDriver(p);
		}
		
		return "redirect:/drivers";
		
	}
	
	@RequestMapping("/driver/remove/{id}")
    public String removeDriver(@PathVariable("id") int id){
		
        this.driverService.removeDriver(id);
        return "redirect:/drivers";
    }
 
    @RequestMapping("/driver/edit/{id}")
    public String editDriver(@PathVariable("id") int id, Model model){
        model.addAttribute("driver", this.driverService.getDriverById(id));
        model.addAttribute("listDrivers", this.driverService.listDrivers());
        return "driver";
    }
	
}
