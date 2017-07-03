package com.siddhrans.biometric.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.siddhrans.biometric.dao.impl.HibernateTokenRepositoryImpl;
import com.siddhrans.biometric.model.SalaryDivision;
import com.siddhrans.biometric.model.User;
import com.siddhrans.biometric.model.Wages;
import com.siddhrans.biometric.service.PaySlipService;
import com.siddhrans.biometric.service.UserService;


@Controller
@RequestMapping("/")
@SessionAttributes("roles")
public class PaySlipController {


	static final Logger logger = LoggerFactory.getLogger(PaySlipController.class);

	@Autowired
	PaySlipService paySlipService;
	
	@Autowired
	UserService userService;

	@Autowired
	MessageSource messageSource;

	@Autowired
	PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;


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
	public String saveWages(@Valid Wages wages, BindingResult result,
			ModelMap model) {
		if (result.hasErrors()) {
			model.addAttribute("wages", wages);
			model.addAttribute("edit", false);
			User profile = userService.findByUserName(getPrincipal());

			model.addAttribute("profile", profile);
			model.addAttribute("loggedinuser", getPrincipal());
			return "addwages";
		}
		
		paySlipService.saveWages(wages);
		model.addAttribute("success", "Daily Wages updated successfully. Now Total salary will be calculated based on these amounts.");
		User profile = userService.findByUserName(getPrincipal());

		model.addAttribute("profile", profile);
		model.addAttribute("loggedinuser", getPrincipal());
		return "addwagessuccess";
	}
	
	/**
	 * This method will provide the medium to update an existing user.
	 */
	@RequestMapping(value = { "/edit-wages{id}" }, method = RequestMethod.GET)
	public String editWages(@PathVariable String id, ModelMap model) {
		List<Wages> wages = paySlipService.findWages();
		model.addAttribute("wages", wages.get(0));
		model.addAttribute("edit", true);
		User profile = userService.findByUserName(getPrincipal());

		model.addAttribute("profile", profile);
		model.addAttribute("loggedinuser", getPrincipal());
		return "addwages";
	}
	
	/**
	 * This method will provide the medium to add a Salary Division Details.
	 */
	@RequestMapping(value = { "/edit-wages{id}" }, method = RequestMethod.POST)
	public String updateWages(@Valid Wages wages, BindingResult result,
			ModelMap model, @PathVariable String id) {
		
		if (result.hasErrors()) {
			model.addAttribute("wages", wages);
			model.addAttribute("edit", true);
			User profile = userService.findByUserName(getPrincipal());

			model.addAttribute("profile", profile);
			model.addAttribute("loggedinuser", getPrincipal());
			return "addwages";
		}
		paySlipService.updateWages(wages);
	
		model.addAttribute("success", "Daily Wages updated successfully. Now Total salary will calculated based on this amount per day.");
		User profile = userService.findByUserName(getPrincipal());

		model.addAttribute("profile", profile);
		model.addAttribute("loggedinuser", getPrincipal());
		return "addwagessuccess";
	}
	
	/**
	 * This method will provide the medium to add a Salary Division Details.
	 */
	@RequestMapping(value = { "/view-salaryDivision" }, method = RequestMethod.GET)
	public String viewSalaryDivision(ModelMap model) {
		List<SalaryDivision> salaryDivisionList = paySlipService.findSalaryDivision();
		model.addAttribute("salaryDivision", salaryDivisionList);
		User profile = userService.findByUserName(getPrincipal());

		model.addAttribute("profile", profile);
		model.addAttribute("loggedinuser", getPrincipal());
		return "viewSalaryDiv";
	}

	/**
	 * This method will provide the medium to add a Salary Division Details.
	 */
	@RequestMapping(value = { "/save-salaryDivision" }, method = RequestMethod.GET)
	public String saveSalaryDivision(ModelMap model) {
		SalaryDivision salaryDivision = new SalaryDivision();
		model.addAttribute("salaryDivision", salaryDivision);
		model.addAttribute("edit", false);
		User profile = userService.findByUserName(getPrincipal());

		model.addAttribute("profile", profile);
		model.addAttribute("loggedinuser", getPrincipal());
		return "addsalarydivision";
	}

	/**
	 * This method will provide the medium to add a Salary Division Details.
	 */
	@RequestMapping(value = { "/save-salaryDivision" }, method = RequestMethod.POST)
	public String saveSalaryDivision(@Valid SalaryDivision division, BindingResult result,
			ModelMap model) {
		if (result.hasErrors()) {
			model.addAttribute("salaryDivision", division);
			model.addAttribute("edit", false);
			User profile = userService.findByUserName(getPrincipal());

			model.addAttribute("profile", profile);
			model.addAttribute("loggedinuser", getPrincipal());
			return "addsalarydivision";
		}
		if(isBreakDownCorrect(division)){
			paySlipService.saveSalaryDivision(division);
		}else{
			model.addAttribute("salaryDivision", division);
			model.addAttribute("edit", false);
			model.addAttribute("loggedinuser", getPrincipal());
			User profile = userService.findByUserName(getPrincipal());

			model.addAttribute("profile", profile);
			model.addAttribute("error","Total % is not equal to 100. Please fill in correct percentage values.Make sure total is equal to 100%");
			return "addsalarydivision";
		}
		model.addAttribute("success", "Salary divisions updated successfully. Now Total salary will broken down based on this information");
		model.addAttribute("loggedinuser", getPrincipal());
		User profile = userService.findByUserName(getPrincipal());

		model.addAttribute("profile", profile);
		return "savesalarydivsuccess";
	}

	/**
	 * This method will provide the medium to update an existing user.
	 */
	@RequestMapping(value = { "/edit-salaryDivision{id}" }, method = RequestMethod.GET)
	public String editSalaryDiv(@PathVariable String id, ModelMap model) {
		List<SalaryDivision> salaryDivisionList = paySlipService.findSalaryDivision();
		model.addAttribute("salaryDivision", salaryDivisionList.get(0));
		model.addAttribute("edit", true);
		User profile = userService.findByUserName(getPrincipal());

		model.addAttribute("profile", profile);
		model.addAttribute("loggedinuser", getPrincipal());
		return "addsalarydivision";
	}

	/**
	 * This method will provide the medium to add a Salary Division Details.
	 */
	@RequestMapping(value = { "/edit-salaryDivision{id}" }, method = RequestMethod.POST)
	public String updateSalaryDiv(@Valid SalaryDivision salaryDivision, BindingResult result,
			ModelMap model, @PathVariable String id) {
		
		if (result.hasErrors()) {
			model.addAttribute("salaryDivision", salaryDivision);
			model.addAttribute("edit", true);
			model.addAttribute("loggedinuser", getPrincipal());
			User profile = userService.findByUserName(getPrincipal());

			model.addAttribute("profile", profile);
			return "addsalarydivision";
		}
		if(isBreakDownCorrect(salaryDivision)){
			paySlipService.updateSalaryDivision(salaryDivision);
		}else{
			model.addAttribute("salaryDivision", salaryDivision);
			model.addAttribute("edit", false);
			model.addAttribute("loggedinuser", getPrincipal());
			User profile = userService.findByUserName(getPrincipal());

			model.addAttribute("profile", profile);
			model.addAttribute("error","Total % is not equal to 100. Please fill in correct percentage values.Make sure total is equal to 100%");
			return "addsalarydivision";
		}
		
		model.addAttribute("success", "Salary divisions updated successfully. Now Total salary will broken down during payslip generation based on this information");
		User profile = userService.findByUserName(getPrincipal());

		model.addAttribute("profile", profile);
		model.addAttribute("loggedinuser", getPrincipal());
		return "savesalarydivsuccess";
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
	
	private boolean isBreakDownCorrect(SalaryDivision division){
		int total= division.getBasicPercentage() +
					division.getConveyancePercentage()+
					division.getEsiPercentage()+
					division.getHraPercentage()+
					division.getIncomeTaxPercentage()+
					division.getLtaPercentage()+
					division.getMrPercentage()+
					division.getPfPercentage()+
					division.getPtPercentage()+
					division.getSaPercentage();
		
		return (total==100);
	}

}
