package com.siddhrans.biometric.controller;

import java.text.ParseException;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.siddhrans.biometric.model.Holidays;
import com.siddhrans.biometric.model.User;
import com.siddhrans.biometric.model.UserBiometricData;
import com.siddhrans.biometric.service.HolidaysService;
import com.siddhrans.biometric.service.UserBiometricDataService;
import com.siddhrans.biometric.service.UserService;

@Controller
@RequestMapping("/")
@SessionAttributes("roles")
public class HolidaysManagementController {

	static final Logger logger = LoggerFactory.getLogger(HolidaysManagementController.class);

	@Autowired
	HolidaysService holidaysService;

	@Autowired
	UserService userService;
	
	@Autowired
	UserBiometricDataService userBiometricDataService;

	/**
	 * This method will provide the medium to add a Salary Division Details.
	 */
	@RequestMapping(value = { "/holidays-list" }, method = RequestMethod.GET)
	public String viewHolidays(ModelMap model) {
		model.addAttribute("holidays", new Holidays());

		List<Holidays> holidaysList = holidaysService.findAllHolidays();

		model.addAttribute("holidaysList", holidaysList);

		User profile = userService.findByUserName(getPrincipal());

		model.addAttribute("profile", profile);
		model.addAttribute("loggedinuser", getPrincipal());
		return "addHolidays";
	}

	/**
	 * This method will provide the medium to add a Salary Division Details.
	 * @throws ParseException 
	 */
	@RequestMapping(value = { "/holidays-save" }, method = RequestMethod.POST)
	public String saveHolidays(
			@Valid Holidays holidays, BindingResult result,RedirectAttributes redirectAttributes,
			ModelMap model) throws ParseException {

		if (result.hasErrors()) {
			List<Holidays> holidaysList = holidaysService.findAllHolidays();
			model.addAttribute("holidaysList", holidaysList);
			model.addAttribute("holidays", holidays);
			User profile = userService.findByUserName(getPrincipal());
			logger.debug(" Has Errors in Holidays save "+result.getAllErrors());
			model.addAttribute("message", "Error occurred.");
			model.addAttribute("profile", profile);
			model.addAttribute("loggedinuser", getPrincipal());
			return "addHolidays";
		}
		String message ="";
		String[] dateS = holidays.getHoliday().split("/");
		Integer year = Integer.parseInt(dateS[2]);
		Integer month = Integer.parseInt(dateS[1]);
		Integer date = Integer.parseInt(dateS[0]);
		List<Holidays> existingHolidays = holidaysService.findHolidaysByMonthAndYearAndDate(year,month,date);
		if(existingHolidays != null && existingHolidays.size()>0){
			List<Holidays> holidaysList = holidaysService.findAllHolidays();
			model.addAttribute("holidaysList", holidaysList);
			model.addAttribute("holidays", holidays);
			User profile = userService.findByUserName(getPrincipal());
			logger.debug(" Has Errors in Holidays s	ave "+result.getAllErrors());
			model.addAttribute("message", "Holiday with selected Year Present already.");
			model.addAttribute("profile", profile);
			model.addAttribute("loggedinuser", getPrincipal());
			return "addHolidays";
		}
		
		holidays.setHolidayMonth(month);
		holidays.setHolidayYear(year);
		holidays.setHolidayDate(date);


		holidaysService.saveHolidays(holidays);
		message="Holidays Added Successfully.";
		List<User> usersList = userService.findAllUsers();
		for(int i=0; i<usersList.size();i++){
			UserBiometricData userBiometricDataHS = new UserBiometricData();

			userBiometricDataHS.setDate(date);
			userBiometricDataHS.setMonth(month);
			userBiometricDataHS.setYear(year);
			userBiometricDataHS.setNoOfHours("8");
			userBiometricDataHS.setNoOfMins("0");
			userBiometricDataHS.setLoginTime("");
			userBiometricDataHS.setLogoutTime("");
			userBiometricDataHS.setStatus("Holiday");
			userBiometricDataHS.setUserId(usersList.get(i).getId());

			//listOfAttendance.add(userBiometricDataHS);
			userBiometricDataService.save(userBiometricDataHS);
		}

		logger.debug("Holidays Save message is  "+message);
		model.addAttribute("message", message);
		model.addAttribute("success", message);
		User profile = userService.findByUserName(getPrincipal());

		model.addAttribute("profile", profile);
		model.addAttribute("loggedinuser", getPrincipal());
		model.addAttribute("url", "holidays-list");
		return "result";
	}

	/**
	 * This method will provide the medium to add a Salary Division Details.
	 */
	@RequestMapping(value = { "/holidays-delete-{holidaysId}" }, method = RequestMethod.GET)
	public String deleteLeaveType(@PathVariable String holidaysId, ModelMap model) {
		Holidays holidays = holidaysService.findHolidaysById(Integer.parseInt(holidaysId));
		holidaysService.deleteHolidays(holidays);
		model.addAttribute("edit", false);
		model.addAttribute("message", "Holidays Deleted Successfully.");
		User profile = userService.findByUserName(getPrincipal());
		model.addAttribute("profile", profile);
		model.addAttribute("loggedinuser", getPrincipal());
		return "redirect:holidays-list";
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
