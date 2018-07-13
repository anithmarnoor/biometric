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

import com.siddhrans.biometric.model.Department;
import com.siddhrans.biometric.model.Exams;
import com.siddhrans.biometric.model.SalaryComponent;
import com.siddhrans.biometric.model.User;
import com.siddhrans.biometric.model.UserProfile;
import com.siddhrans.biometric.service.DesignationService;
import com.siddhrans.biometric.service.ExamService;
import com.siddhrans.biometric.service.PaySlipService;
import com.siddhrans.biometric.service.UserProfileService;
import com.siddhrans.biometric.service.UserService;

@Controller
@RequestMapping("/")
@SessionAttributes("roles")
public class ExamController {

	static final Logger logger = LoggerFactory.getLogger(SalaryDivisionController.class);

	@Autowired
	UserService userService;

	@Autowired
	UserProfileService userProfileService;
	
	@Autowired
	ExamService examService;

	/**
	 * This method will provide the medium to add a Salary Division Details.
	 */
	@RequestMapping(value = { "/view-exams" }, method = RequestMethod.GET)
	public String viewSalaryDivision(ModelMap model) {
		List<Exams> examsList = examService.findAllExams();
		model.addAttribute("examsList", examsList);
		User profile = userService.findByUserName(getPrincipal());

		model.addAttribute("profile", profile);
		model.addAttribute("exams", new Exams());
		
		model.addAttribute("loggedinuser", getPrincipal());
		return "viewExams";
	}

	
	/**
	 * This method will list all existing departments.
	 */
	@RequestMapping(value = { "/exam-save" }, method = RequestMethod.POST)
	public String saveExam(@Valid Exams exams, BindingResult result,
			ModelMap model) {
		User profile = userService.findByUserName(getPrincipal());
		List<Exams> examsList = examService.findAllExams();
		if (result.hasErrors() || exams == null || exams.getExamName() == "") {
			logger.debug("ERROR IS : "+result.getAllErrors()+" error count is "+result.getErrorCount());
			if(exams == null || exams.getExamName() == "") {
				model.addAttribute("success", "Exam Name cannot be empty..");
			}
			
			model.addAttribute("examsList", examsList);
			model.addAttribute("exams", exams);
			model.addAttribute("edit", false);
			model.addAttribute("profile", profile);
			model.addAttribute("loggedinuser", getPrincipal());
			return "viewExams";
		}
		logger.debug("Saving Exam,==>" +exams.getExamName() );
		examService.saveExam(exams);
		examsList= null;
		examsList = examService.findAllExams();
		model.addAttribute("examsList", examsList);
		model.addAttribute("exams", new Exams());
		
		model.addAttribute("success", "Exam added successfully");
		model.addAttribute("profile", profile);
		model.addAttribute("loggedinuser", getPrincipal());
		return "viewExams";
	}
	
	/**
	 * This method will list all existing departments.
	 */
	@RequestMapping(value = { "/exam-edit-{examId}" }, method = RequestMethod.GET)
	public String updateExamView(@PathVariable String examId,
			ModelMap model) {
		User profile = userService.findByUserName(getPrincipal());
		List<Exams> examsList = examService.findAllExams();
		model.addAttribute("edit", true);
		Exams existingExam = examService.findById(Integer.parseInt(examId));
		model.addAttribute("examsList", examsList);
		model.addAttribute("exams", existingExam);
		
		model.addAttribute("profile", profile);
		model.addAttribute("loggedinuser", getPrincipal());
		return "viewExams";
	}
	
	/**
	 * This method will list all existing departments.
	 */
	@RequestMapping(value = { "/exam-edit-{examId}" }, method = RequestMethod.POST)
	public String updateExam(@Valid Exams exams, @PathVariable String examId, BindingResult result,
			ModelMap model) {
		User profile = userService.findByUserName(getPrincipal());
		List<Exams> examsList = examService.findAllExams();
		if (result.hasErrors() || exams == null || exams.getExamName() == "") {
			logger.debug("ERROR IS : "+result.getAllErrors()+" error count is "+result.getErrorCount());
			if(exams == null || exams.getExamName() == "") {
				model.addAttribute("success", "Exam Name cannot be empty..");
			}
			
			model.addAttribute("examsList", examsList);
			model.addAttribute("exams", exams);
			model.addAttribute("edit", false);
			model.addAttribute("profile", profile);
			model.addAttribute("loggedinuser", getPrincipal());
			return "viewExams";
		}
		
		Exams existingExam = examService.findById(Integer.parseInt(examId));
		existingExam.setExamName(exams.getExamName());
		logger.debug("Updating Exam==>" +exams.getExamName() );
		examService.updateExam(existingExam);
		examsList= null;
		examsList = examService.findAllExams();
		model.addAttribute("examsList", examsList);
		model.addAttribute("exams", new Exams());
		
		model.addAttribute("success", "Exam Updated successfully");
		model.addAttribute("profile", profile);
		model.addAttribute("loggedinuser", getPrincipal());
		return "viewExams";
	}
	
	/**
	 * This method will list all existing departments.
	 */
	@RequestMapping(value = { "/exam-delete-{examId}" }, method = RequestMethod.GET)
	public String deleteExam(@PathVariable String examId,
			ModelMap model) {
		User profile = userService.findByUserName(getPrincipal());
		model.addAttribute("edit", false);
		examService.deleteExam(Integer.parseInt(examId));
		List<Exams> examsList = examService.findAllExams();
		model.addAttribute("examsList", examsList);
		model.addAttribute("exams", new Exams());
		model.addAttribute("success", "Exam Deleted successfully");
		model.addAttribute("profile", profile);
		model.addAttribute("loggedinuser", getPrincipal());
		return "viewExams";
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
