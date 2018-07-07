package com.siddhrans.biometric.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
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

import com.siddhrans.biometric.model.SalaryComponentFormula;
import com.siddhrans.biometric.model.SalaryComponent;
import com.siddhrans.biometric.model.User;
import com.siddhrans.biometric.model.UserProfile;
import com.siddhrans.biometric.service.DesignationService;
import com.siddhrans.biometric.service.PaySlipService;
import com.siddhrans.biometric.service.UserProfileService;
import com.siddhrans.biometric.service.UserService;
import com.siddhrans.biometric.utils.ExpressionValidator;

@Controller
@RequestMapping("/")
@SessionAttributes("roles")
public class SalDivPercentagesController {

	static final Logger logger = LoggerFactory.getLogger(SalDivPercentagesController.class);

	@Autowired
	PaySlipService paySlipService;

	@Autowired
	UserService userService;

	@Autowired
	HttpServletRequest request;

	@Autowired
	UserProfileService userProfileService;

	@Autowired
	DesignationService designationService;

	/**
	 * This method will provide the medium to add a Salary Division Details.
	 */
	@RequestMapping(value = { "/list-formula" }, method = RequestMethod.GET)
	public String viewFormula(ModelMap model) {
		User profile = userService.findByUserName(getPrincipal());

		model.addAttribute("percentages", new SalaryComponentFormula());
		List<SalaryComponent> salaryComponents = paySlipService.findAllSalaryDivisions();
		List<SalaryComponent> salaryComponentsAll = paySlipService.findAllSalaryDivisions();
		List<SalaryComponentFormula> formulae = paySlipService.findSalaryDivPercentages();
		for(int i=0;i<salaryComponents.size();i++) {
			SalaryComponent	component = salaryComponents.get(i);
			for(SalaryComponentFormula formula: formulae) {
				if((formula.getComponent().getComponentId()).equals(component.getComponentId())) {
					salaryComponents.remove(i--);
				}
			}
		}
		List<SalaryComponentFormula> salDivPercentList = paySlipService.findSalaryDivPercentages();

		model.addAttribute("salDivPercentList", salDivPercentList);
		model.addAttribute("salaryComponents", salaryComponents);
		model.addAttribute("salaryComponentsAll", salaryComponentsAll);
		model.addAttribute("profile", profile);
		model.addAttribute("loggedinuser", getPrincipal());
		return "viewComponentFormula";
	}

	/**
	 * This method will provide the medium to add a Salary Division Details.
	 */
	@RequestMapping(value = { "/saveComponentFormula" }, method = RequestMethod.POST)
	public String saveWages(@Valid SalaryComponentFormula percentage, BindingResult result,RedirectAttributes redirectAttributes,
			ModelMap model) {

		ExpressionValidator validator = new ExpressionValidator();
		validator.setExpression(percentage.getFormula());
		String validationResult = validator.isExpressionValid();
		logger.debug("Percentage adding has some error==>"+validationResult +" Anith after");
		if (result.hasErrors()) {
			logger.debug("Percentage adding has some error==>"+result.getFieldErrors());
			List<SalaryComponentFormula> salDivPercentList = paySlipService.findSalaryDivPercentages();

			model.addAttribute("salDivPercentList", salDivPercentList);
			List<SalaryComponent> salaryComponents = paySlipService.findAllSalaryDivisions();
			model.addAttribute("salaryComponents", salaryComponents);

			model.addAttribute("percentages", percentage);
			model.addAttribute("edit", false);
			User profile = userService.findByUserName(getPrincipal());
			model.addAttribute("success", validationResult);
			model.addAttribute("profile", profile);
			model.addAttribute("loggedinuser", getPrincipal());
			return "viewComponentFormula";
		}
		if(validationResult.indexOf("Expression is valid") < 0)
		{
			model.addAttribute("success", validationResult);
			model.addAttribute("percentages", percentage);
		} else {
			paySlipService.saveSalaryDivPercentages(percentage);
			model.addAttribute("success", "Formula Added Successfully.");
			model.addAttribute("percentages", new SalaryComponentFormula());
		}

		List<SalaryComponentFormula> salDivPercentList = paySlipService.findSalaryDivPercentages();

		model.addAttribute("salDivPercentList", salDivPercentList);
		List<SalaryComponentFormula> formulae = paySlipService.findSalaryDivPercentages();
		List<SalaryComponent> salaryComponents = paySlipService.findAllSalaryDivisions();
		for(int i=0;i<salaryComponents.size();i++) {
			SalaryComponent	component = salaryComponents.get(i);
			for(SalaryComponentFormula formula: formulae) {
				if((formula.getComponent().getComponentId()).equals(component.getComponentId())) {
					salaryComponents.remove(i--);
				}
			}
		}

		List<SalaryComponent> salaryComponentsAll = paySlipService.findAllSalaryDivisions();
		model.addAttribute("salaryComponents", salaryComponents);
		model.addAttribute("salaryComponentsAll", salaryComponentsAll);

		model.addAttribute("edit", false);

		User profile = userService.findByUserName(getPrincipal());

		model.addAttribute("profile", profile);
		model.addAttribute("loggedinuser", getPrincipal());
		return "viewComponentFormula";
	}

	/**
	 * This method will provide the medium to update an existing user.
	 */
	@RequestMapping(value = { "/componentFormula-edit-{id}" }, method = RequestMethod.GET)
	public String updateComponentFormulaJsp(@PathVariable String id, ModelMap model) {
		SalaryComponentFormula existingPercentages = paySlipService.findDivPercentageById(Integer.parseInt(id));
		List<SalaryComponent> salaryComponents = new ArrayList<SalaryComponent>();
		salaryComponents.add(existingPercentages.getComponent());
		model.addAttribute("salaryComponents", salaryComponents);

		List<SalaryComponent> salaryComponentsAll = paySlipService.findAllSalaryDivisions();
		model.addAttribute("salaryComponentsAll", salaryComponentsAll);

		List<SalaryComponentFormula> salDivPercentList = paySlipService.findSalaryDivPercentages();
		model.addAttribute("salDivPercentList", salDivPercentList);

		model.addAttribute("percentages", existingPercentages);
		model.addAttribute("edit", true);
		User profile = userService.findByUserName(getPrincipal());

		model.addAttribute("profile", profile);
		model.addAttribute("loggedinuser", getPrincipal());
		return "viewComponentFormula";
	}

	/**
	 * This method will provide the medium to add a Salary Division Details.
	 */
	@RequestMapping(value = { "/componentFormula-edit-{id}" }, method = RequestMethod.POST)
	public String updateComponentFormula(@Valid SalaryComponentFormula formula, BindingResult result,
			ModelMap model, @PathVariable String id) {
		ExpressionValidator validator = new ExpressionValidator();
		validator.setExpression(formula.getFormula());
		String validationResult = validator.isExpressionValid();
		if (result.hasErrors()) {
			SalaryComponentFormula existingPercentages = paySlipService.findDivPercentageById(Integer.parseInt(id));

			List<SalaryComponent> salaryComponents = new ArrayList<SalaryComponent>();
			salaryComponents.add(existingPercentages.getComponent());
			model.addAttribute("salaryComponents", salaryComponents);

			List<SalaryComponent> salaryComponentsAll = paySlipService.findAllSalaryDivisions();
			model.addAttribute("salaryComponentsAll", salaryComponentsAll);

			List<SalaryComponentFormula> salDivPercentList = paySlipService.findSalaryDivPercentages();
			model.addAttribute("salDivPercentList", salDivPercentList);

			model.addAttribute("percentages", formula);
			model.addAttribute("edit", true);
			User profile = userService.findByUserName(getPrincipal());

			model.addAttribute("profile", profile);
			model.addAttribute("loggedinuser", getPrincipal());
			return "viewComponentFormula";
		}
		if(validationResult.indexOf("Expression is valid") < 0)
		{
			model.addAttribute("success", validationResult);
			model.addAttribute("percentages", formula);
		} else {
			paySlipService.updateSalaryDivPercentages(formula);
			model.addAttribute("success", "Formula Updated Successfully.");
			model.addAttribute("percentages", new SalaryComponentFormula());
		}

		List<SalaryComponentFormula> salDivPercentList = paySlipService.findSalaryDivPercentages();

		model.addAttribute("salDivPercentList", salDivPercentList);

		User profile = userService.findByUserName(getPrincipal());

		model.addAttribute("profile", profile);
		model.addAttribute("loggedinuser", getPrincipal());
		return "viewComponentFormula";
	}

	/**
	 * This method will provide the medium to add a Salary Division Details.
	 */
	/*@RequestMapping(value = { "/salDivPercent-delete-{percentageId}" }, method = RequestMethod.GET)
	public String deleteSalDivPercentage(ModelMap model,@PathVariable String percentageId) {
		SalaryDivPercentages salaryDivPercentages = paySlipService.findDivPercentageById((Integer.parseInt(percentageId));
		model.addAttribute("percentages", salaryDivPercentages);
		model.addAttribute("edit", false);
		SalaryComponent division = paySlipService.findSalaryDivisionById(Integer.parseInt(divisionId));
		paySlipService.deleteSalaryDivision(division);

		List<SalaryComponent> salaryDivisionList = paySlipService.findAllSalaryDivisions();
		model.addAttribute("salaryDivisionList", salaryDivisionList);

		model.addAttribute("success", "Salary component deleted successfully.");
		User profile = userService.findByUserName(getPrincipal());

		model.addAttribute("profile", profile);
		model.addAttribute("loggedinuser", getPrincipal());
		return "viewSalaryDiv";
	}*/


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
