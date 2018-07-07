package com.siddhrans.biometric.controller;

import java.util.List;
import java.util.ArrayList;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.siddhrans.biometric.model.User;
import com.siddhrans.biometric.model.UserProfile;
import com.siddhrans.biometric.model.Wages;
import com.siddhrans.biometric.service.DesignationService;
import com.siddhrans.biometric.service.PaySlipService;
import com.siddhrans.biometric.service.UserProfileService;
import com.siddhrans.biometric.service.UserService;
import com.siddhrans.biometric.utils.CommonOperations;

@Controller
@RequestMapping("/")
@SessionAttributes("roles")
public class WagesController {

	static final Logger logger = LoggerFactory.getLogger(WagesController.class);

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
	@RequestMapping(value = { "/view-wages" }, method = RequestMethod.GET)
	public String viewWages(ModelMap model) {
		List<Wages> wagesList = paySlipService.findWages();
		
		model.addAttribute("wagesList", wagesList);
		User profile = userService.findByUserName(getPrincipal());

		model.addAttribute("profile", profile);
		model.addAttribute("loggedinuser", getPrincipal());
		return "viewWages";
	}

	/**
	 * This method will provide the medium to add a Salary Division Details.
	 */
	@RequestMapping(value = { "/save-wages" }, method = RequestMethod.GET)
	public String saveWages(ModelMap model) {
		Wages wages = new Wages();
		//List<Wages> wagesList = paySlipService.findWages();
		//List<Designation> designations = designationService.findAllDesignations();
		/*List<Designation> newDesignations = new ArrayList<Designation>();
		//logic to display only designations whose OT is not set. If it is set, no need to display in select menu.
		outer:for(Designation designation:designations){
			for(Wages wage: wagesList){
				if((wage.getDesignation().getDesignationId()).equals(designation.getDesignationId())){
					//designations.remove(designation);
					continue outer;
				}
			}
			newDesignations.add(designation);
		}*/
		//logger.debug("designations size after removing existing is "+designations.size());
		//model.addAttribute("designations", newDesignations);
		
		List<User> usersList = userService.findAllUsers();
		model.addAttribute("usersList", usersList);
		
		model.addAttribute("wages", wages);
		model.addAttribute("edit", false);
		User profile = userService.findByUserName(getPrincipal());

		model.addAttribute("profile", profile);
		model.addAttribute("loggedinuser", getPrincipal());
		return "addwages";
	}

	/**
	 * This method will provide the medium to add a Salary Division Details.
	 */
	@RequestMapping(value = { "/save-wages" }, method = RequestMethod.POST)
	public String saveWages(@Valid Wages wages, BindingResult result,RedirectAttributes redirectAttributes,
			ModelMap model) {
		if (result.hasErrors()) {
			//List<Wages> wagesList = paySlipService.findWages();
			/*List<Designation> designations = designationService.findAllDesignations();
			List<Designation> newDesignations = new ArrayList<Designation>();
			//logic to display only designations whose OT is not set. If it is set, no need to display in select menu.
			outer:for(Designation designation:designations){
				for(Wages wage: wagesList){
					if((wage.getDesignation().getDesignationId()).equals(designation.getDesignationId())){
						//designations.remove(designation);
						continue outer;
					}
				}
				newDesignations.add(designation);
			}
			logger.debug("designations size after removing existing is "+designations.size());
			model.addAttribute("designations", newDesignations);*/
			
			List<User> usersList = userService.findAllUsers();
			model.addAttribute("usersList", usersList);
			
			model.addAttribute("wages", wages);
			model.addAttribute("edit", false);
			User profile = userService.findByUserName(getPrincipal());

			model.addAttribute("profile", profile);
			model.addAttribute("loggedinuser", getPrincipal());
			return "addwages";
		}
		User user = wages.getUser();
		Wages wage = paySlipService.findWagesByUser(user);
		if(wage != null){
			List<User> usersList = userService.findAllUsers();
			model.addAttribute("usersList", usersList);
			
			model.addAttribute("wages", wages);
			model.addAttribute("edit", false);
			User profile = userService.findByUserName(getPrincipal());
			model.addAttribute("error", "Already Wages details present for the selected User."
					+ " Please edit or use select different user.");
			model.addAttribute("profile", profile);
			model.addAttribute("loggedinuser", getPrincipal());
			return "addwages";
		}
		if((wages.getNormalShift() == null || wages.getNormalShift() == "") && (wages.getCtc() == null || wages.getCtc() == "")){
			//List<Wages> wagesList = paySlipService.findWages();
		/*	List<Designation> designations = designationService.findAllDesignations();
			List<Designation> newDesignations = new ArrayList<Designation>();
			//logic to display only designations whose OT is not set. If it is set, no need to display in select menu.
			outer:for(Designation designation:designations){
				for(Wages wage: wagesList){
					if((wage.getDesignation().getDesignationId()).equals(designation.getDesignationId())){
						//designations.remove(designation);
						continue outer;
					}
				}
				newDesignations.add(designation);
			}
			logger.debug("designations size after removing existing is "+designations.size());
			model.addAttribute("designations", newDesignations);*/
			
			List<User> usersList = userService.findAllUsers();
			model.addAttribute("usersList", usersList);
			
			model.addAttribute("wages", wages);
			model.addAttribute("edit", false);
			User profile = userService.findByUserName(getPrincipal());
			model.addAttribute("error", "Either Normal Shift Amount or CTC should be specified");
			model.addAttribute("profile", profile);
			model.addAttribute("loggedinuser", getPrincipal());
			return "addwages";
		}

		int noOfDays = 0;
		if(CommonOperations.isLeapYear()){
			noOfDays= 366;
		} else{
			noOfDays= 365;
		}

		if((wages.getNormalShift() != null && wages.getNormalShift() != "") ){
			wages.setCtc(((Integer.parseInt(wages.getNormalShift()) * noOfDays))+"");
		} else if( (wages.getCtc() != null && wages.getCtc() != "")){
			wages.setNormalShift(((Integer.parseInt(wages.getCtc()) / noOfDays))+"");
		}

		paySlipService.saveWages(wages);
		model.addAttribute("success", "Daily Wages updated successfully. Now Total salary will be calculated based on these amounts.");
		User profile = userService.findByUserName(getPrincipal());
		
		model.addAttribute("profile", profile);
		model.addAttribute("loggedinuser", getPrincipal());
		model.addAttribute("url", "view-wages");
		return "result";
	}

	/**
	 * This method will provide the medium to update an existing user.
	 */
	@RequestMapping(value = { "/edit-wages-{id}" }, method = RequestMethod.GET)
	public String editWages(@PathVariable String id, ModelMap model) {
		Wages existingWage = paySlipService.findWageById(Integer.parseInt(id));
		/*List<Designation> designationsList = new ArrayList<Designation>();
		
		designationsList.add(existingWage.getDesignation());
		model.addAttribute("designations", designationsList);*/
		
		//List<User> usersList = userService.findAllUsers();
		List<User> usersList = new ArrayList();
		usersList.add(existingWage.getUser());
		model.addAttribute("usersList", usersList);
		model.addAttribute("wages", existingWage);
		model.addAttribute("edit", true);
		User profile = userService.findByUserName(getPrincipal());

		model.addAttribute("profile", profile);
		model.addAttribute("loggedinuser", getPrincipal());
		return "addwages";
	}

	/**
	 * This method will provide the medium to add a Salary Division Details.
	 */
	@RequestMapping(value = { "/edit-wages-{id}" }, method = RequestMethod.POST)
	public String updateWages(@Valid Wages wages, BindingResult result,
			ModelMap model, @PathVariable String id) {
		//List<Designation> designationsList = new ArrayList<Designation>();
		if (result.hasErrors()) {
			model.addAttribute("wages", wages);
			/*designationsList.add(wages.getDesignation());
			model.addAttribute("designations", designationsList)*/;
			List<User> usersList = new ArrayList();
			usersList.add(wages.getUser());
			model.addAttribute("usersList", wages.getUser());
			model.addAttribute("edit", true);
			User profile = userService.findByUserName(getPrincipal());

			model.addAttribute("profile", profile);
			model.addAttribute("loggedinuser", getPrincipal());
			return "addwages";
		}

		int noOfDays = 0;
		if(CommonOperations.isLeapYear()){
			noOfDays= 366;
		} else{
			noOfDays= 365;
		}

		if((wages.getNormalShift() != null && wages.getNormalShift() != "") ){
			wages.setCtc(((Integer.parseInt(wages.getNormalShift()) * noOfDays))+"");
		} else if( (wages.getCtc() != null && wages.getCtc() != "")){
			wages.setNormalShift(((Integer.parseInt(wages.getNormalShift()) / noOfDays))+"");
		}

		paySlipService.updateWages(wages);

		model.addAttribute("success", "Wage details updated successfully. Total salary will calculated based on this.");
		User profile = userService.findByUserName(getPrincipal());

		model.addAttribute("profile", profile);
		model.addAttribute("loggedinuser", getPrincipal());
		model.addAttribute("url", "view-wages");
		return "result";
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
