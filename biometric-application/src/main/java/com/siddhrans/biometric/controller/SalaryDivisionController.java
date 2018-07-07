package com.siddhrans.biometric.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.siddhrans.biometric.model.SalaryComponent;
import com.siddhrans.biometric.model.User;
import com.siddhrans.biometric.model.UserProfile;
import com.siddhrans.biometric.service.DesignationService;
import com.siddhrans.biometric.service.PaySlipService;
import com.siddhrans.biometric.service.UserProfileService;
import com.siddhrans.biometric.service.UserService;

@Controller
@RequestMapping("/")
@SessionAttributes("roles")
public class SalaryDivisionController {

	static final Logger logger = LoggerFactory.getLogger(SalaryDivisionController.class);

	@Autowired
	PaySlipService paySlipService;

	@Autowired
	UserService userService;

	@Autowired
	UserProfileService userProfileService;

	@Autowired
	DesignationService designationService;

	/**
	 * This method will provide the medium to add a Salary Division Details.
	 */
	@RequestMapping(value = { "/view-salaryDivision" }, method = RequestMethod.GET)
	public String viewSalaryDivision(ModelMap model) {
		List<SalaryComponent> salaryDivisionList = paySlipService.findAllSalaryDivisions();

		model.addAttribute("salaryDivisionList", salaryDivisionList);
		model.addAttribute("salaryDivision", new SalaryComponent());
		User profile = userService.findByUserName(getPrincipal());

		model.addAttribute("profile", profile);
		model.addAttribute("loggedinuser", getPrincipal());
		return "viewSalaryDiv";
	}

	/**
	 * This method will provide the medium to add a Salary Division Details.
	 */
	@RequestMapping(value = { "/salaryDivision-edit-{divisionId}" }, method = RequestMethod.GET)
	public String editSalaryDivision(ModelMap model,@PathVariable String divisionId) {
		SalaryComponent salaryComponent = paySlipService.findSalaryDivisionById(Integer.parseInt(divisionId));
		model.addAttribute("salaryDivision", salaryComponent);
		model.addAttribute("edit", true);
		List<SalaryComponent> salaryDivisionList = paySlipService.findAllSalaryDivisions();
		model.addAttribute("salaryDivisionList", salaryDivisionList);

		User profile = userService.findByUserName(getPrincipal());

		model.addAttribute("profile", profile);
		model.addAttribute("loggedinuser", getPrincipal());
		return "viewSalaryDiv";
	}

	/**
	 * This method will provide the medium to add a Salary Division Details.
	 */
	@RequestMapping(value = { "/salaryDivision-delete-{divisionId}" }, method = RequestMethod.GET)
	public String deleteSalaryDivision(ModelMap model,@PathVariable String divisionId) {
		SalaryComponent salaryComponent = paySlipService.findSalaryDivisionById(Integer.parseInt(divisionId));
		model.addAttribute("salaryDivision", salaryComponent);
		model.addAttribute("edit", true);
		SalaryComponent division = paySlipService.findSalaryDivisionById(Integer.parseInt(divisionId));
		paySlipService.deleteSalaryDivision(division);

		List<SalaryComponent> salaryDivisionList = paySlipService.findAllSalaryDivisions();
		model.addAttribute("salaryDivisionList", salaryDivisionList);

		model.addAttribute("success", "Salary component deleted successfully.");
		User profile = userService.findByUserName(getPrincipal());

		model.addAttribute("profile", profile);
		model.addAttribute("loggedinuser", getPrincipal());
		return "viewSalaryDiv";
	}

	/**
	 * This method will provide the medium to add a Salary Division Details.
	 */
	@RequestMapping(value = { "/salaryDivision-{action}-{componentId}" }, method = RequestMethod.POST)
	public String saveSalaryDivision(@PathVariable String action, @PathVariable String componentId, @Valid SalaryComponent division, BindingResult result,
			ModelMap model) {

		if (result.hasErrors()) {
			List<SalaryComponent> salaryDivisionList = paySlipService.findAllSalaryDivisions();
			model.addAttribute("salaryDivisionList", salaryDivisionList);
			model.addAttribute("salaryDivision", division);
			model.addAttribute("edit", false);
			User profile = userService.findByUserName(getPrincipal());

			model.addAttribute("profile", profile);
			model.addAttribute("loggedinuser", getPrincipal());
			return "viewSalaryDiv";
		}
		if(action.equals("edit")){//if edit is true, then update the persisted state instead of adding new row
			SalaryComponent  persistedDivision = paySlipService.findSalaryDivisionById(Integer.parseInt(componentId));
			logger.debug("ANITH: edit Salary DIvision");
			division.setComponentId(persistedDivision.getComponentId());
			paySlipService.saveSalaryDivision(division);
			model.addAttribute("success", "Salary component updated successfully.");
		} else if(action.equals("save")){
			SalaryComponent  persistedDivision = paySlipService.findSalaryDivisionByName(division.getComponentName().toUpperCase());
			if(persistedDivision != null){
				List<SalaryComponent> salaryDivisionList = paySlipService.findAllSalaryDivisions();
				model.addAttribute("salaryDivisionList", salaryDivisionList);
				model.addAttribute("salaryDivision", division);
				model.addAttribute("edit", false);
				User profile = userService.findByUserName(getPrincipal());
				model.addAttribute("success", "Salary component With same name already exists.");
				model.addAttribute("profile", profile);
				model.addAttribute("loggedinuser", getPrincipal());
				return "viewSalaryDiv";
			} 
			logger.debug("ANITH: Save Salary DIvision");
			division.setComponentName(division.getComponentName().toUpperCase());
			paySlipService.saveSalaryDivision(division);
			model.addAttribute("success", "Salary component added successfully.");

		}
		model.addAttribute("salaryDivision", new SalaryComponent());
		List<SalaryComponent> salaryDivisionList = paySlipService.findAllSalaryDivisions();
		model.addAttribute("salaryDivisionList", salaryDivisionList);

		model.addAttribute("loggedinuser", getPrincipal());
		User profile = userService.findByUserName(getPrincipal());

		model.addAttribute("profile", profile);
		return "viewSalaryDiv";
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

	/**
	 * This method will provide UserProfile list to views
	 */
	@ModelAttribute("roles")
	public List<UserProfile> initializeProfiles() {
		return userProfileService.findAll();
	}
}
