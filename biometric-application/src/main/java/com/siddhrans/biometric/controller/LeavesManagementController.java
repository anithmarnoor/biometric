package com.siddhrans.biometric.controller;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

import com.siddhrans.biometric.model.LeaveTypes;
import com.siddhrans.biometric.model.Leaves;
import com.siddhrans.biometric.model.LeavesAvailable;
import com.siddhrans.biometric.model.LeavesLimit;
import com.siddhrans.biometric.model.User;
import com.siddhrans.biometric.model.UserBiometricData;
import com.siddhrans.biometric.service.DesignationService;
import com.siddhrans.biometric.service.LeavesService;
import com.siddhrans.biometric.service.UserBiometricDataService;
import com.siddhrans.biometric.service.UserService;


@Controller
@RequestMapping("/")
@SessionAttributes("roles")
public class LeavesManagementController {

	static final Logger logger = LoggerFactory.getLogger(LeavesManagementController.class);

	@Autowired
	LeavesService leavesService;

	@Autowired
	DesignationService designationService;

	@Autowired
	UserService userService;

	@Autowired
	UserBiometricDataService userBiometricDataService;

	/**
	 * This method will provide the medium to add a Salary Division Details.
	 */
	@RequestMapping(value = { "/leaveTypes-list" }, method = RequestMethod.GET)
	public String viewLeaveTypes(ModelMap model) {
		model.addAttribute("leaveTypes", new LeaveTypes());

		List<LeaveTypes> leaveTypesList = leavesService.findAllLeaveTypes();

		model.addAttribute("leaveTypesList", leaveTypesList);

		User profile = userService.findByUserName(getPrincipal());

		model.addAttribute("profile", profile);
		model.addAttribute("loggedinuser", getPrincipal());
		return "addLeaveTypes";
	}

	/**
	 * This method will provide the medium to add a Salary Division Details.
	 */
	@RequestMapping(value = { "/leaveTypes-{action}-{leaveTypeId}" }, method = RequestMethod.POST)
	public String saveOrEditLeaveType(@PathVariable String action, @PathVariable String leaveTypeId, 
			@Valid LeaveTypes leaveType, BindingResult result,RedirectAttributes redirectAttributes,
			ModelMap model) {

		List<LeaveTypes> leaveTypesList = new ArrayList<LeaveTypes>();

		if (result.hasErrors()) {
			model.addAttribute("leaveTypes", leaveType);
			leaveTypesList = leavesService.findAllLeaveTypes();
			if(!action.equals("edit")){
				model.addAttribute("edit", false);
			} else {
				model.addAttribute("edit", true);
				LeaveTypes leavesType = leavesService.findLeaveTypeById(Integer.parseInt(leaveTypeId));
				model.addAttribute("leaveTypes", leavesType);
				model.addAttribute("leaveTypeId", leaveTypeId);
				leaveTypesList = leavesService.findAllLeaveTypes();
				model.addAttribute("leaveTypesList", leaveTypesList);
			}
			User profile = userService.findByUserName(getPrincipal());
			logger.debug(" Has Errors in LeaveTypes save "+result.getAllErrors());

			model.addAttribute("profile", profile);
			model.addAttribute("loggedinuser", getPrincipal());
			return "addLeaveTypes";
		}
		String message ="";
		if(!action.equals("edit")){
			logger.debug("Leave Types Id to SAVE is "+leaveType.getLeaveTypeId());
			leavesService.saveLeaveType(leaveType);
			message="Leave Type Added Successfully.";
		} else{
			logger.debug("Leave Types Id to EDIT is "+leaveTypeId);
			leaveType.setLeaveTypeId(Integer.parseInt(leaveTypeId));
			leavesService.updateLeaveType(leaveType);
			message="Leave Type Updated Successfully.";
		}
		logger.debug(" Leave Type Edit or Save message is  "+message);
		model.addAttribute("success", message);
		User profile = userService.findByUserName(getPrincipal());

		model.addAttribute("profile", profile);
		model.addAttribute("loggedinuser", getPrincipal());
		model.addAttribute("url", "leaveTypes-list");
		return "result";
	}

	/**
	 * This method will provide the medium to add a Salary Division Details.
	 */
	@RequestMapping(value = { "/leaveTypes-edit-{leaveTypeId}" }, method = RequestMethod.GET)
	public String editLeaveType(@PathVariable String leaveTypeId, ModelMap model) {
		LeaveTypes leaveTypes = leavesService.findLeaveTypeById(Integer.parseInt(leaveTypeId));
		model.addAttribute("leaveTypes", leaveTypes);
		model.addAttribute("leaveTypeId", leaveTypeId);
		model.addAttribute("edit", true);
		model.addAttribute("message", "Please edit below details.");
		User profile = userService.findByUserName(getPrincipal());
		List<LeaveTypes> leaveTypesList = leavesService.findAllLeaveTypes();
		model.addAttribute("leaveTypesList", leaveTypesList);
		model.addAttribute("profile", profile);
		model.addAttribute("loggedinuser", getPrincipal());
		return "addLeaveTypes";
	}

	/**
	 * This method will provide the medium to add a Salary Division Details.
	 */
	@RequestMapping(value = { "/leaveTypes-delete-{leaveTypeId}" }, method = RequestMethod.GET)
	public String deleteLeaveType(@PathVariable String leaveTypeId, ModelMap model) {
		LeaveTypes leaveTypes = leavesService.findLeaveTypeById(Integer.parseInt(leaveTypeId));
		leavesService.deleteLeaveType(leaveTypes);
		model.addAttribute("leaveTypes", new LeaveTypes());
		model.addAttribute("edit", true);
		model.addAttribute("message", "Leaves Type Deleted Successfully.");
		User profile = userService.findByUserName(getPrincipal());
		List<LeaveTypes> leaveTypesList = leavesService.findAllLeaveTypes();
		model.addAttribute("leaveTypesList", leaveTypesList);
		model.addAttribute("profile", profile);
		model.addAttribute("loggedinuser", getPrincipal());
		return "redirect:leaveTypes-list";
	}

	/**
	 * This method will provide the medium to add a Salary Division Details.
	 */
	@RequestMapping(value = { "/leavesLimit-list" }, method = RequestMethod.GET)
	public String viewLeavesLimit(ModelMap model) {
		model.addAttribute("leavesLimit", new LeavesLimit());

		List<LeavesLimit> leavesLimitList = leavesService.findAllLeavesLimit();

		List<LeaveTypes>  leaveTypesList = leavesService.findAllLeaveTypes();
		model.addAttribute("leaveTypesList", leaveTypesList);

		model.addAttribute("leavesLimitList", leavesLimitList);

		User profile = userService.findByUserName(getPrincipal());

		model.addAttribute("profile", profile);
		model.addAttribute("loggedinuser", getPrincipal());
		return "addLeavesLimit";
	}

	/**
	 * This method will provide the medium to add a Salary Division Details.
	 */
	@RequestMapping(value = { "/leavesLimit-{action}-{leavesLimitId}" }, method = RequestMethod.POST)
	public String saveOrEditLeaveType(@PathVariable String action, @PathVariable String leavesLimitId, 
			@Valid LeavesLimit leavesLimit, BindingResult result,RedirectAttributes redirectAttributes,
			ModelMap model) {

		List<LeavesLimit> leavesLimitList = new ArrayList<LeavesLimit>();

		if (result.hasErrors()) {
			model.addAttribute("leavesLimit", leavesLimit);
			leavesLimitList = leavesService.findAllLeavesLimit();
			if(!action.equals("edit")){
				model.addAttribute("edit", false);
			} else {
				model.addAttribute("edit", true);
				LeavesLimit leaveLimit = leavesService.findLeavesLimitById(Integer.parseInt(leavesLimitId));
				model.addAttribute("leavesLimit", leaveLimit);
				model.addAttribute("leavesLimitId", leavesLimitId);
				leavesLimitList = leavesService.findAllLeavesLimit();
				model.addAttribute("leavesLimitList", leavesLimitList);
			}
			User profile = userService.findByUserName(getPrincipal());
			logger.debug(" Has Errors in LeavesLimit save "+result.getAllErrors());

			model.addAttribute("profile", profile);
			model.addAttribute("loggedinuser", getPrincipal());
			return "addLeavesLimit";
		}
		String message ="";
		if(!action.equals("edit")){
			logger.debug("Leaves Limit Id to SAVE is "+leavesLimit.getLeavesLimitId());
			leavesService.saveLeavesLimit(leavesLimit);
			message="Leaves Limit Added Successfully.";
		} else{
			logger.debug("Leaves Limit Id to EDIT is "+leavesLimitId);
			leavesLimit.setLeavesLimitId(Integer.parseInt(leavesLimitId));
			leavesService.updateLeavesLimit(leavesLimit);
			message="Leaves Limit Updated Successfully.";
		}
		logger.debug(" Leaves Limit Edit or Save message is  "+message);
		model.addAttribute("success", message);
		User profile = userService.findByUserName(getPrincipal());

		model.addAttribute("profile", profile);
		model.addAttribute("loggedinuser", getPrincipal());
		model.addAttribute("url", "leavesLimit-list");
		return "result";
	}

	/**
	 * This method will provide the medium to add a Salary Division Details.
	 */
	@RequestMapping(value = { "/leavesLimit-edit-{leavesLimitId}" }, method = RequestMethod.GET)
	public String editLeavesLimit(@PathVariable String leavesLimitId, ModelMap model) {
		LeavesLimit leavesLimit = leavesService.findLeavesLimitById(Integer.parseInt(leavesLimitId));
		model.addAttribute("leavesLimit", leavesLimit);
		model.addAttribute("leavesLimitId", leavesLimitId);
		model.addAttribute("edit", true);
		model.addAttribute("message", "Please edit below details.");
		User profile = userService.findByUserName(getPrincipal());
		List<LeavesLimit> leavesLimitList = leavesService.findAllLeavesLimit();
		model.addAttribute("leavesLimitList", leavesLimitList);
		List<LeaveTypes>  leaveTypesList = leavesService.findAllLeaveTypes();
		model.addAttribute("leaveTypesList", leaveTypesList);
		model.addAttribute("profile", profile);
		model.addAttribute("loggedinuser", getPrincipal());
		return "addLeavesLimit";
	}

	/**
	 * This method will provide the medium to add a Salary Division Details.
	 */
	@RequestMapping(value = { "/leavesLimit-delete-{leavesLimitId}" }, method = RequestMethod.GET)
	public String deleteLeavesLimit(@PathVariable String leavesLimitId, ModelMap model) {
		LeavesLimit leavesLimit = leavesService.findLeavesLimitById(Integer.parseInt(leavesLimitId));
		leavesService.deleteLeavesLimit(leavesLimit);
		model.addAttribute("leavesLimit", new LeavesLimit());
		model.addAttribute("message", "Limit Deleted Successfully.");
		User profile = userService.findByUserName(getPrincipal());
		List<LeavesLimit> leavesLimitList = leavesService.findAllLeavesLimit();
		model.addAttribute("leavesLimitList", leavesLimitList);
		List<LeaveTypes>  leaveTypesList = leavesService.findAllLeaveTypes();
		model.addAttribute("leaveTypesList", leaveTypesList);
		model.addAttribute("profile", profile);
		model.addAttribute("loggedinuser", getPrincipal());
		return "redirect:leavesLimit-list";
	}

	/**
	 * This method will provide the medium to add a Salary Division Details.
	 */
	@RequestMapping(value = { "/apply-leave" }, method = RequestMethod.GET)
	public String applyLeave(ModelMap model) {
		model.addAttribute("leaves", new Leaves());
		User profile = userService.findByUserName(getPrincipal());
		List<LeaveTypes>  leaveTypesList = leavesService.findAllLeaveTypes();
		model.addAttribute("leaveTypesList", leaveTypesList);
		if(profile.getUserProfile().getType().equals("ADMIN")){
			List<User> usersList = userService.findAllUsers();
			model.addAttribute("usersList", usersList);
		}
		if(profile.getUserProfile().getType().equals("USER")){
			List<User> userList = new ArrayList<User>();
			userList.add(profile);
			model.addAttribute("userList", userList);
		}
		model.addAttribute("profile", profile);
		model.addAttribute("loggedinuser", getPrincipal());
		return "addLeaves";
	}


	/**
	 * This method will provide the medium to add a Salary Division Details.
	 * @throws ParseException 
	 */
	@RequestMapping(value = { "/apply-leave" }, method = RequestMethod.POST)
	public String applyLeave(@Valid Leaves leaves, BindingResult result,RedirectAttributes redirectAttributes,
			ModelMap model) throws ParseException {

		User profile = userService.findByUserName(getPrincipal());

		if (result.hasErrors()) {
			model.addAttribute("leaves", leaves);
			List<LeaveTypes>  leaveTypesList = leavesService.findAllLeaveTypes();
			model.addAttribute("leaveTypesList", leaveTypesList);
			if(profile.getUserProfile().getType().equals("ADMIN")){
				List<User> usersList = userService.findAllUsers();
				model.addAttribute("usersList", usersList);
			}
			if(profile.getUserProfile().getType().equals("USER")){
				List<User> userList = new ArrayList<User>();
				userList.add(profile);
				model.addAttribute("userList", userList);
			}
			model.addAttribute("profile", profile);
			model.addAttribute("loggedinuser", getPrincipal());
			return "addLeaves";
		}
		String message ="";

		LeaveTypes leaveType = leaves.getLeaveTypes();

		LeavesLimit limit = leavesService.findLeavesLimitByLeavesType(leaveType);
		LeavesAvailable leavesAvailable = leavesService.findAvailableLeavesByUserAndType(leaves.getUser(), leaveType);
		if(leavesAvailable == null){
			leavesAvailable = new LeavesAvailable();
			leavesAvailable.setAvailability(Float.parseFloat(limit.getLimit()));
			leavesAvailable.setLeaveType(leaveType);
			leavesAvailable.setUser(leaves.getUser());
			logger.debug("Saving not leaves Available 1 ");
		}

		Integer test = Integer.parseInt(limit.getLimit());
		logger.debug("Test Limit is "+test);
		if(Integer.parseInt(limit.getLimit()) < leavesAvailable.getAvailability()){
			message = "Leaves Limit for "+leaveType.getLeaveType()+" Leave Type is "+ limit.getLimit()
			+". Available number of leaves for type "+leaveType.getLeaveType()+" is "+leavesAvailable.getAvailability();
		} else {
			// write code to save leave, update available leaves for user. 

			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			String startDate = leaves.getStartDate();
			String endDate = leaves.getEndDate();
			Date start = format.parse(startDate);
			Date end = format.parse(endDate);
			Calendar startCal = Calendar.getInstance();
			Calendar endCal = Calendar.getInstance();
			startCal.setTime(start);
			endCal.setTime(end);
			float numberOfDays = 0.0f;

			if(start.after(end) || (startCal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)){
				message = "Selected dates is NOT valid";
				logger.debug(" Leaves applying Edit or Save message is  "+message);
				model.addAttribute("success", message);

				model.addAttribute("profile", profile);
				model.addAttribute("loggedinuser", getPrincipal());
				model.addAttribute("url", "apply-leave");
				return "result";
			}

			while(!startCal.after(endCal))
			{
				int day = startCal.get(Calendar.DAY_OF_WEEK);
				if (/*(day != Calendar.SATURDAY) && */(day != Calendar.SUNDAY))
					numberOfDays++;
				startCal.add(Calendar.DATE, 1);
			}
			logger.debug("1Number of days after difference between dates is  "+numberOfDays);
			if(leaves.getFullOrHalf() != null){
				if((leaves.getFullOrHalf()).equals("Half") ){
					numberOfDays = 1.0f/2.0f;
				}
			}
			logger.debug("Number of days afterchecking Full or Half is  "+numberOfDays);
			Float available = leavesAvailable.getAvailability();
			logger.debug(" ANITH Available Leaves ==>"+available);

			float diff1 = Float.parseFloat(available.toString()) - numberOfDays;
			logger.debug("Number of days after difference  is  "+numberOfDays);
			logger.debug("Number of diff Days is "+diff1);
			if(diff1 < 0 ){
				message = "Leaves Limit for Leave Type "+leaveType.getLeaveType()+" is "+ limit.getLimit()
				+". You have only "+leavesAvailable.getAvailability() +" leaves available.";
				logger.debug(" Leaves applying Edit or Save message is  "+message);
				model.addAttribute("success", message);

				model.addAttribute("profile", profile);
				model.addAttribute("loggedinuser", getPrincipal());
				model.addAttribute("url", "apply-leave");
				return "result";
			} else {
				leavesAvailable.setAvailability(diff1);
				leavesService.updateLeavesAvailable(leavesAvailable);
			}

			leaves.setNoOfDays(numberOfDays+"");
			leaves.setLeaveStatus("Pending for Approval");
			leavesService.saveLeave(leaves);
			message = numberOfDays+" days "+leaves.getLeaveTypes().getLeaveType()+" leave applied successfully. "
					+ "Available Number of "+leaves.getLeaveTypes().getLeaveType()+" leaves is "
					+ leavesAvailable.getAvailability();

			
		}

		logger.debug(" Leaves applying Edit or Save message is  "+message);
		model.addAttribute("success", message);

		model.addAttribute("profile", profile);
		model.addAttribute("loggedinuser", getPrincipal());
		model.addAttribute("url", "apply-leave");
		return "result";
	}



	/**
	 * This method will provide the medium to add a Salary Division Details.
	 */
	@RequestMapping(value = { "/user-leaves" }, method = RequestMethod.GET)
	public String userLeavesList(ModelMap model) {
		//model.addAttribute("leaves", new Leaves());
		User profile = userService.findByUserName(getPrincipal());
		List<Leaves>  leavesList = leavesService.findLeavesByUser(profile);
		model.addAttribute("leavesList", leavesList);

		model.addAttribute("profile", profile);
		model.addAttribute("loggedinuser", getPrincipal());
		return "viewLeaves";
	}

	/**
	 * This method will provide the medium to add a Salary Division Details.
	 */
	@RequestMapping(value = { "/applied-leaves" }, method = RequestMethod.GET)
	public String allAppliedLeavesList(ModelMap model) {
		//model.addAttribute("leaves", new Leaves());
		User profile = userService.findByUserName(getPrincipal());
		List<Leaves>  leavesList = leavesService.findAllLeaves();
		model.addAttribute("leavesList", leavesList);

		model.addAttribute("profile", profile);
		model.addAttribute("loggedinuser", getPrincipal());
		return "viewLeaves";
	}

	/**
	 * This method will provide the medium to add a Salary Division Details.
	 * @throws ParseException 
	 */
	@RequestMapping(value = { "/{action}-leaves-{leavesId}" }, method = RequestMethod.GET)
	public String approveOrCancelLeave(ModelMap model, @PathVariable String action, @PathVariable String leavesId) throws ParseException {
		//model.addAttribute("leaves", new Leaves());
		User profile = userService.findByUserName(getPrincipal());
		Leaves leave = leavesService.findLeaveById(Integer.parseInt(leavesId));

		if(action.equals("Cancel")){
			LeavesAvailable leavesAvailable = leavesService.findAvailableLeavesByUserAndType(leave.getUser(), leave.getLeaveTypes());
			Float available = leavesAvailable.getAvailability();
			leave.setLeaveStatus("Cancelled");
			leavesAvailable.setAvailability((Float)(available + Float.parseFloat(leave.getNoOfDays())));
			leavesService.updateLeavesAvailable(leavesAvailable);
			model.addAttribute("message", "Leave Cancelled.");
			leavesService.updateLeave(leave);
		} else if(action.equals("Approve")){
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			String startDate = leave.getStartDate();
			String endDate = leave.getEndDate();
			Date start = format.parse(startDate);
			Date end = format.parse(endDate);
			Calendar startCal = Calendar.getInstance();
			Calendar endCal = Calendar.getInstance();
			startCal.setTime(start);
			endCal.setTime(end);
			startCal = null;
			endCal = null;
			startCal = Calendar.getInstance();
			endCal = Calendar.getInstance();
			startCal.setTime(start);
			endCal.setTime(end);
			while(!startCal.after(endCal))
			{
				int date = startCal.get(Calendar.DATE);
				int year = startCal.get(Calendar.YEAR);
				int month = startCal.get(Calendar.MONTH)+1;
				
				int day = startCal.get(Calendar.DAY_OF_WEEK);
				
				logger.debug("ANith:: Day==>"+day +" Year==>"+year+" Month==>"+month);

				if (/*(day != Calendar.SATURDAY) && */(day != Calendar.SUNDAY)){
					UserBiometricData userBiometricDataLS = new UserBiometricData();
					userBiometricDataLS.setDate(date);
					userBiometricDataLS.setMonth(month);
					userBiometricDataLS.setYear(year);
					if(leave.getFullOrHalf() != null){
						if((leave.getFullOrHalf()).equals("Half") ){
							userBiometricDataLS.setNoOfHours("4");
						} else {
							userBiometricDataLS.setNoOfHours("8");
						}
					} else {
						userBiometricDataLS.setNoOfHours("8");
					}
					
					userBiometricDataLS.setNoOfMins("0");
					userBiometricDataLS.setLoginTime("");
					userBiometricDataLS.setLogoutTime("");
					userBiometricDataLS.setStatus("Time Off");
					userBiometricDataLS.setUserId(leave.getUser().getId());
					//listOfAttendance.add(userBiometricDataLS);
					userBiometricDataService.save(userBiometricDataLS);
				}
				startCal.add(Calendar.DATE, 1);
			}
			
			leave.setLeaveStatus("Approved");
			model.addAttribute("message", "Leave Successfully Approved.");
			leavesService.updateLeave(leave);
		}
		model.addAttribute("profile", profile);
		model.addAttribute("loggedinuser", getPrincipal());
		if(profile.getUserProfile().getType().equals("ADMIN")){
			List<Leaves>  leavesList = leavesService.findAllLeaves();
			model.addAttribute("leavesList", leavesList);
			return "redirect:/applied-leaves";
		}
		if(profile.getUserProfile().getType().equals("USER")){
			List<Leaves>  leavesList = leavesService.findAllLeaves();
			model.addAttribute("leavesList", leavesList);
			return "redirect:/user-leaves";
		}
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

}
