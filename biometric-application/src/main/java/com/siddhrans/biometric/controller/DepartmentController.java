package com.siddhrans.biometric.controller;

import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationTrustResolver;
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

import com.siddhrans.biometric.model.Department;
import com.siddhrans.biometric.model.Designation;
import com.siddhrans.biometric.model.User;
import com.siddhrans.biometric.service.DepartmentService;
import com.siddhrans.biometric.service.DesignationService;
import com.siddhrans.biometric.service.UserService;

@Controller
@RequestMapping("/")
@SessionAttributes("roles")
public class DepartmentController {
	static final Logger logger = LoggerFactory.getLogger(DepartmentController.class);
	
	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private DesignationService designationService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	MessageSource messageSource;

	@Autowired
	PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;

	@Autowired
	AuthenticationTrustResolver authenticationTrustResolver;
	
	/**
	 * This method will list all existing departments.
	 */
	@RequestMapping(value = { "/listDesignations" }, method = RequestMethod.GET)
	public String listDepartments(ModelMap model) {
		
		List<Designation> designations = designationService.findAllDesignations();
		logger.debug("designations size is "+designations.size());
		model.addAttribute("designations", designations);
		
		User profile = userService.findByUserName(getPrincipal());

		model.addAttribute("profile", profile);
		model.addAttribute("loggedinuser", getPrincipal());
		return "viewDesignations";
	}
	
	/**
	 * This method will list all existing departments.
	 */
	@RequestMapping(value = { "/addDepartment" }, method = RequestMethod.GET)
	public String addDepartment(ModelMap model) {
		model.addAttribute("department", new Department());
		User profile = userService.findByUserName(getPrincipal());

		model.addAttribute("profile", profile);
		model.addAttribute("loggedinuser", getPrincipal());
		return "adddepartment";
	}
	
	/**
	 * This method will list all existing departments.
	 */
	@RequestMapping(value = { "/addDepartment" }, method = RequestMethod.POST)
	public String saveDepartment(@Valid Department department, BindingResult result,
			ModelMap model) {
		User profile = userService.findByUserName(getPrincipal());
		if (result.hasErrors() || department == null || department.getDepartmentName() == "") {
			logger.debug("ERROR IS : "+result.getAllErrors()+" error count is "+result.getErrorCount());
			if(department == null || department.getDepartmentName() == "") {
				model.addAttribute("success", "Department Name cannot be empty..");
			}
			model.addAttribute("department", department);
			model.addAttribute("edit", false);
			model.addAttribute("roleChange", true);
			model.addAttribute("profile", profile);
			model.addAttribute("loggedinuser", getPrincipal());
			return "adddepartment";
		}
		logger.debug("Saving department==>" +department.getDepartmentName());
		departmentService.saveDepartment(department);
		
		model.addAttribute("department", new Department());
		
		model.addAttribute("success", "Department added successfully");
		model.addAttribute("profile", profile);
		model.addAttribute("loggedinuser", getPrincipal());
		return "adddepartment";
	}
	
	/**
	 * This method will send the view for adding designation.
	 */
	@RequestMapping(value = { "/addDesignation" }, method = RequestMethod.GET)
	public String addDesignation(ModelMap model) {
		
		List<Department> departments = departmentService.findAllDepartments();
		logger.debug("departments size is "+departments.size());
		model.addAttribute("departments", departments);
		model.addAttribute("designation", new Designation());
		User profile = userService.findByUserName(getPrincipal());

		model.addAttribute("profile", profile);
		model.addAttribute("loggedinuser", getPrincipal());
		return "adddesignation";
	}
	
	/**
	 * This method will list all existing departments.
	 */
	@RequestMapping(value = { "/addDesignation" }, method = RequestMethod.POST)
	public String saveDesignation(@Valid Designation designation, BindingResult result,
			ModelMap model) {
		
		if (result.hasErrors() || designation == null || designation.getDesignationName() == "") {
			logger.debug("ERROR IS : "+result.getAllErrors()+" error count is "+result.getErrorCount());
			if( designation == null || designation.getDesignationName() == "") {
				model.addAttribute("success", "Designation name cannot be empty..");
			}
			model.addAttribute("designation", designation);
			model.addAttribute("edit", false);
			model.addAttribute("roleChange", true);
			User profile = userService.findByUserName(getPrincipal());
			List<Department> departments = departmentService.findAllDepartments();
			logger.debug("departments size is "+departments.size());
			model.addAttribute("departments", departments);

			model.addAttribute("profile", profile);
			model.addAttribute("loggedinuser", getPrincipal());
			return "adddesignation";
		}
		designationService.saveDesignation(designation);
		model.addAttribute("designation", new Designation());
		List<Department> departments = departmentService.findAllDepartments();
		logger.debug("departments size is "+departments.size());
		model.addAttribute("departments", departments);
		
		User profile = userService.findByUserName(getPrincipal());

		model.addAttribute("profile", profile);
		model.addAttribute("success", "Designation added successfully..");
		model.addAttribute("loggedinuser", getPrincipal());
		return "adddesignation";
	}
	
	
	/**
	 * This method will send the view for adding designation.
	 */
	@RequestMapping(value = { "/delete-designation-{designationId}" }, method = RequestMethod.GET)
	public String deleteDesignation(@PathVariable String designationId) {
		
		designationService.deleteDesignation(Integer.parseInt(designationId));
		
		return "redirect:/listDesignations";
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