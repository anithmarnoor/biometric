package com.siddhrans.biometric.controller;


import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.siddhrans.biometric.model.Designation;
import com.siddhrans.biometric.model.OverTime;
import com.siddhrans.biometric.model.User;
import com.siddhrans.biometric.service.DesignationService;
import com.siddhrans.biometric.service.OvertimeService;
import com.siddhrans.biometric.service.UserService;


@Controller
@RequestMapping("/")
@SessionAttributes("roles")
public class OvertimeController {

	static final Logger logger = LoggerFactory.getLogger(OvertimeController.class);

	@Autowired
	OvertimeService overtimeService;

	@Autowired
	DesignationService designationService;

	@Autowired
	UserService userService;

	@Autowired
	MessageSource messageSource;

	/**
	 * This method will provide the medium to add a Salary Division Details.
	 */
	@RequestMapping(value = { "/overtimes-list" }, method = RequestMethod.GET)
	public String viewOvertimes(ModelMap model) {
		model.addAttribute("overtime", new OverTime());

		List<Designation> designations = designationService.findAllDesignations();
		List<OverTime> overtimesList = overtimeService.findAllOverTimes();
		List<Designation> newDesignations = new ArrayList<Designation>();
		//logic to display only designations whose OT is not set. If it is set, no need to display in select menu.
		outer:for(Designation designation:designations){
			for(OverTime ot: overtimesList){
				if((ot.getDesignation().getDesignationId()).equals(designation.getDesignationId())){
					//designations.remove(designation);
					continue outer;
				}
			}
			newDesignations.add(designation);
		}
		logger.debug("designations size after removing existing is "+designations.size());
		model.addAttribute("overtimesList", overtimesList);
		model.addAttribute("designations", newDesignations);

		User profile = userService.findByUserName(getPrincipal());

		model.addAttribute("profile", profile);
		model.addAttribute("loggedinuser", getPrincipal());
		return "addOvertime";
	}

	/**
	 * This method will provide the medium to add a Salary Division Details.
	 */
	/*@RequestMapping(value = { "/overtimes-save" }, method = RequestMethod.GET)
	public String saveOvertimes(ModelMap model) {
		OverTime overTime = new OverTime();
		model.addAttribute("overtime", overTime);
		model.addAttribute("edit", false);
		User profile = userService.findByUserName(getPrincipal());

		model.addAttribute("profile", profile);
		model.addAttribute("loggedinuser", getPrincipal());
		return "addOvertime";
	}*/

	/**
	 * This method will provide the medium to add a Salary Division Details.
	 */
	@RequestMapping(value = { "/overtimes-{action}-{overtimeId}" }, method = RequestMethod.POST)
	public String saveOvertime(@PathVariable String action, @PathVariable String overtimeId, @Valid OverTime overtime, BindingResult result,RedirectAttributes redirectAttributes,
			ModelMap model) {

		List<OverTime> overtimesList = new ArrayList<OverTime>();
		
		if (result.hasErrors()) {
			model.addAttribute("overtime", overtime);
			overtimesList = overtimeService.findAllOverTimes();
			if(!action.equals("edit")){
				model.addAttribute("edit", false);

				List<Designation> designations = designationService.findAllDesignations();
				List<Designation> newDesignations = new ArrayList<Designation>();
				//logic to display only designations whose OT is not set. If it is set, no need to display in select menu.
				outer:for(Designation designation:designations){
					for(OverTime ot: overtimesList){
						if((ot.getDesignation().getDesignationId()).equals(designation.getDesignationId())){
							//designations.remove(designation);
							continue outer;
						}
					}
					newDesignations.add(designation);
				}
				model.addAttribute("designations", newDesignations);
			} else {
				model.addAttribute("edit", true);
				OverTime overTime = overtimeService.findById(Integer.parseInt(overtimeId));
				model.addAttribute("overtime", overTime);
				model.addAttribute("overtimeId", overtimeId);
				model.addAttribute("designation", overTime.getDesignation());
				overtimesList = overtimeService.findAllOverTimes();
				List<Designation> designations = new ArrayList<Designation>();
				//to display only assigned designation..
				designations.add(overTime.getDesignation());
				
				model.addAttribute("overtimesList", overtimesList);
				model.addAttribute("designations", designations);
			}
			User profile = userService.findByUserName(getPrincipal());
			logger.debug(" Has Errors in OT save "+result.getAllErrors());
			
			model.addAttribute("profile", profile);
			model.addAttribute("loggedinuser", getPrincipal());
			return "addOvertime";
		}
		String message ="";
		if(!action.equals("edit")){
			logger.debug(" OT Id to SAVE is "+overtime.getOverTimeId());
			overtimeService.saveOverTime(overtime);
			message="Overtime added Successfully.";
		} else{
			logger.debug(" OT Id to EDIT ANITH is "+overtimeId);
			overtime.setOverTimeId(Integer.parseInt(overtimeId));
			overtimeService.updateOverTime(overtime);
			message="Overtime Updated Successfully.";
		}
		logger.debug(" OT Edit or Save message is  "+message);
		model.addAttribute("success", message);
		User profile = userService.findByUserName(getPrincipal());

		model.addAttribute("profile", profile);
		model.addAttribute("loggedinuser", getPrincipal());
		model.addAttribute("url", "overtimes-list");
		return "result";
	}

	/**
	 * This method will provide the medium to add a Salary Division Details.
	 */
	@RequestMapping(value = { "/overtimes-edit-{overtimeId}" }, method = RequestMethod.GET)
	public String editOvertime(@PathVariable String overtimeId, ModelMap model) {
		OverTime overTime = overtimeService.findById(Integer.parseInt(overtimeId));
		model.addAttribute("overtime", overTime);
		model.addAttribute("overtimeId", overtimeId);
		model.addAttribute("designation", overTime.getDesignation());
		model.addAttribute("edit", true);
		User profile = userService.findByUserName(getPrincipal());
		List<OverTime> overtimesList = overtimeService.findAllOverTimes();
		List<Designation> designations = new ArrayList<Designation>();
		//to display only assigned designation..
		designations.add(overTime.getDesignation());
		
		model.addAttribute("overtimesList", overtimesList);
		model.addAttribute("designations", designations);

		model.addAttribute("profile", profile);
		model.addAttribute("loggedinuser", getPrincipal());
		return "addOvertime";
	}

	/**
	 * This method returns the principal[user-name] of logged-in user.
	 */
	private String getPrincipal(){
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails)principal).getUsername();
		} else {
			userName = principal.toString();
		}
		return userName;
	}

}
