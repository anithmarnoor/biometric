package com.siddhrans.biometric.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
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
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.siddhrans.biometric.model.PaySlip;
import com.siddhrans.biometric.model.SalaryDivision;
import com.siddhrans.biometric.model.User;
import com.siddhrans.biometric.model.UserBiometricData;
import com.siddhrans.biometric.model.Wages;
import com.siddhrans.biometric.service.PaySlipService;
import com.siddhrans.biometric.service.UserBiometricDataService;
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
	UserBiometricDataService userBiometricDataService;

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



	@RequestMapping(value = { "/view-PaySlip" }, method = RequestMethod.GET)
	public String viewPaySlip(ModelMap model) {
		PaySlip paySlip = new PaySlip();
		model.addAttribute("paySlip", paySlip);
		User profile = userService.findByUserName(getPrincipal());
		model.addAttribute("profile", profile);
		model.addAttribute("loggedinuser", getPrincipal());
		return "viewPaySlip";
	}


	@RequestMapping(value = { "/generate-PaySlip{id}" }, method = RequestMethod.POST)
	public String generateSelfPaySlip(@Valid PaySlip paySlip, BindingResult result,
			ModelMap model, @PathVariable String id) {

		User profile = userService.findByUserName(getPrincipal());
		PaySlip existingPaySlip = paySlipService.getPayDetails(paySlip.getUserId(), paySlip.getMonth(), paySlip.getYear());
		if(existingPaySlip != null){
			model.addAttribute("paySlip", existingPaySlip);
			model.addAttribute("profile", profile);
			model.addAttribute("loggedinuser", getPrincipal());
			return "paySlip";
		} else {
			model.addAttribute("error", "PaySlip for the month and year specified is not generated yet. Please contact administrator.");
			model.addAttribute("paySlip", paySlip);
			model.addAttribute("profile", profile);
			model.addAttribute("loggedinuser", getPrincipal());
			return "paySlip";
		}
	}

	@RequestMapping(value = { "/generate-PaySlip" }, method = RequestMethod.POST)
	public String generateUserPaySlip(@Valid PaySlip paySlip, BindingResult result,
			ModelMap model) {

		User profile = userService.findByUserName(getPrincipal());
		PaySlip existingPaySlip = paySlipService.getPayDetails(paySlip.getUserId(), paySlip.getMonth(), paySlip.getYear());
		if(existingPaySlip != null){
			model.addAttribute("paySlip", existingPaySlip);
			model.addAttribute("profile", profile);
			model.addAttribute("loggedinuser", getPrincipal());
			return "paySlipError";
		} else {


			List<UserBiometricData> biometricData = userBiometricDataService.findByYearAndMonth(paySlip.getYear(), paySlip.getMonth(), paySlip.getUserId());
			HashMap<String, Float> workingHoursAndOT = calculateNoOfWorkingHours(biometricData);

			List<Wages> wages = paySlipService.findWages();
			List<SalaryDivision> salaryDivision = paySlipService.findSalaryDivision();

			if(wages.size()<=0 || salaryDivision.size() <=0){
				model.addAttribute("error", "Either Wages or Salary Division Information is not present.<br>"
						+ " Please Add Wages and Salary Details before Generating PaySlip");
				model.addAttribute("profile", profile);
				model.addAttribute("loggedinuser", getPrincipal());
				return "paySlipError";
			}
			Wages wage = wages.get(0);
			Float workingHours = workingHoursAndOT.get("workingHours") + 
					workingHoursAndOT.get("totalHalfWorkingHrs") + 
						workingHoursAndOT.get("remainingWorkingHrs");
			logger.debug("Anith : workingHours====>"+workingHours);
			paySlip.setAttendance(workingHours / 8.0f);
			
			paySlip.setOverTimeHours(workingHoursAndOT.get("overTime"));
			
			
			HashMap<String, Float> totalSalaryDetails = calculateTotalSalary(workingHoursAndOT, wage);
			logger.debug("Anith : totalSalaryDetails====>"+totalSalaryDetails);
			generateSalaryData(paySlip, totalSalaryDetails, salaryDivision.get(0));

			paySlip.setUserId(profile.getId());
		}
		
		paySlipService.savePayDetails(paySlip);
		
		model.addAttribute("paySlip", paySlip);
		model.addAttribute("profile", profile);
		model.addAttribute("loggedinuser", getPrincipal());
		return "paySlip";
	}
	
	@RequestMapping(value = { "/download-paySlip-{userId}-{month}-{year}" },  method = RequestMethod.GET)
	public String downloadPaySlip( @PathVariable int userId, @PathVariable int month, @PathVariable int year, HttpServletResponse response) throws IOException {
		PaySlip paySlip = paySlipService.getPayDetails(userId, month, year);
		response.setContentType("application/pdf");
		/*response.setContentLength(document.getContent().length);*/
		response.setHeader("Content-Disposition","attachment; filename=\"" + ""+userId+"-"+month+"-"+year
				+"\"");
		byte[] b = "Test Data in PDF ".getBytes();
		FileCopyUtils.copy(b, response.getOutputStream());

		return "paySlip";
	}

	private HashMap<String, Float> calculateNoOfWorkingHours(List<UserBiometricData> biometricData){
		Float totalWorkingHours = 0.0f;
		Float totalOverTimeWorkingHours = 0.0f;
		Float totalHalfWorkingHrs = 0.0f;
		Float remainingWorkingHrs = 0.0f;
		for(UserBiometricData data: biometricData){
			Float workingHours = Float.parseFloat(data.getNoOfHours()) + (Float.parseFloat(data.getNoOfMins())/60);
			if(workingHours > 8.0f){
				totalOverTimeWorkingHours = totalOverTimeWorkingHours + (workingHours - 8.0f);
				totalWorkingHours = totalOverTimeWorkingHours + (workingHours - totalOverTimeWorkingHours);
			} else {
				if(workingHours > 4){
					totalHalfWorkingHrs = totalHalfWorkingHrs + 4;
					remainingWorkingHrs = workingHours - 4;
				} else {
					remainingWorkingHrs = remainingWorkingHrs+ workingHours;
				}
				totalOverTimeWorkingHours = totalOverTimeWorkingHours + 0.0f;
			}
		}

		HashMap<String, Float> workingHourDetails = new HashMap<String, Float>();
		workingHourDetails.put("workingHours", totalWorkingHours);
		workingHourDetails.put("overTime", totalOverTimeWorkingHours);
		workingHourDetails.put("totalHalfWorkingHrs", totalHalfWorkingHrs);
		workingHourDetails.put("remainingWorkingHrs", remainingWorkingHrs);
		return workingHourDetails;
	}

	private HashMap<String, Float> calculateTotalSalary(HashMap<String, Float> workingHoursAndOT, Wages wage){
		Float totalSalary = 0.0f;
		Float wholeDaySalary = 0.0f;
		Float overTimeAmt = 0.0f;
		Float halfDayAmt = 0.0f;
		Float remainingAmt = 0.0f;

		wholeDaySalary = (workingHoursAndOT.get("workingHours")/8) * Float.parseFloat(wage.getNormalShift());
		overTimeAmt = workingHoursAndOT.get("overTime") * Float.parseFloat(wage.getOvertime());
		halfDayAmt = (workingHoursAndOT.get("totalHalfWorkingHrs") / 4) * (Float.parseFloat(wage.getNormalShift()) / 2.0f);
		remainingAmt = workingHoursAndOT.get("remainingWorkingHrs") * (Float.parseFloat(wage.getNormalShift())/8);

		totalSalary = wholeDaySalary + halfDayAmt + remainingAmt;

		HashMap<String, Float> totalSalaryDetails = new HashMap<String, Float>();
		totalSalaryDetails.put("totalSalary", totalSalary);
		totalSalaryDetails.put("overTimeAmt", overTimeAmt);

		return totalSalaryDetails;
	}

	private PaySlip generateSalaryData(PaySlip paySlip, HashMap<String, Float> totalAmount, SalaryDivision salaryDivision){
		Float totalSalary = totalAmount.get("totalSalary");

		Float basic = salaryDivision.getBasicPercentage() * (totalSalary/100);
		Float conveyance = salaryDivision.getConveyancePercentage() * (totalSalary/100);
		Float esi = salaryDivision.getEsiPercentage() * (totalSalary/100);
		Float hra = salaryDivision.getHraPercentage() * (totalSalary/100);
		Float incomeTax = salaryDivision.getIncomeTaxPercentage() * (totalSalary/100);
		Float leaveTravelAllowance = salaryDivision.getLtaPercentage() * (totalSalary/100);
		Float medicalReimbursement = salaryDivision.getMrPercentage() * (totalSalary/100);
		Float providentFund = salaryDivision.getPfPercentage() * (totalSalary/100);
		Float professionTax = salaryDivision.getPtPercentage() * (totalSalary/100);
		Float specialAllowance = salaryDivision.getSaPercentage() * (totalSalary/100);

		paySlip.setBasic(basic);
		paySlip.setConveyance(conveyance);
		paySlip.setEsi(esi);
		paySlip.setHra(hra);
		paySlip.setIncomeTax(incomeTax);
		paySlip.setLta(leaveTravelAllowance);
		paySlip.setMr(medicalReimbursement);
		paySlip.setPf(providentFund);
		paySlip.setPt(professionTax);
		paySlip.setSa(specialAllowance);
		paySlip.setOverTimeAmount(totalAmount.get("overTimeAmt"));
		paySlip.setTotalSalary(totalSalary);

		return paySlip;
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
		Float total= division.getBasicPercentage() +
				division.getConveyancePercentage()+
				division.getEsiPercentage()+
				division.getHraPercentage()+
				division.getIncomeTaxPercentage()+
				division.getLtaPercentage()+
				division.getMrPercentage()+
				division.getPfPercentage()+
				division.getPtPercentage()+
				division.getSaPercentage();

		return (total == 100.0f);
	}

}
