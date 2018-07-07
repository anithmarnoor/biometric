package com.siddhrans.biometric.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.View;

import com.itextpdf.text.DocumentException;
import com.siddhrans.biometric.model.OverTime;
import com.siddhrans.biometric.model.PaySlip;
import com.siddhrans.biometric.model.SalaryComponentFormula;
import com.siddhrans.biometric.model.SalaryComponent;
import com.siddhrans.biometric.model.User;
import com.siddhrans.biometric.model.UserBiometricData;
import com.siddhrans.biometric.model.UserPayRoll;
import com.siddhrans.biometric.model.Wages;
import com.siddhrans.biometric.service.OvertimeService;
import com.siddhrans.biometric.service.PaySlipService;
import com.siddhrans.biometric.service.UserBiometricDataService;
import com.siddhrans.biometric.service.UserPayRollService;
import com.siddhrans.biometric.service.UserProfileService;
import com.siddhrans.biometric.service.UserService;

@Controller
@RequestMapping("/")
@SessionAttributes("roles")
public class PaySlipController {
	static final Logger logger = LoggerFactory.getLogger(PaySlipController.class);

	@Autowired
	UserService userService;

	@Autowired
	HttpServletRequest request;

	@Autowired
	PaySlipService paySlipService;

	@Autowired
	UserPayRollService userPayRollService;

	@Autowired
	UserProfileService userProfileService;

	@Autowired
	UserBiometricDataService userBiometricDataService;

	@Autowired
	OvertimeService overtimeService;

	@RequestMapping(value = { "/view-PaySlip" }, method = RequestMethod.GET)
	public String viewPaySlip(ModelMap model) {
		PaySlip paySlip = new PaySlip();
		model.addAttribute("paySlip", paySlip);
		User profile = userService.findByUserName(getPrincipal());
		List<User> users = new ArrayList<User>();
		if((profile.getUserProfile().getType()).equals("ADMIN")) {
			users = userService.findAllUsers();
		} else {
			users.add(profile);
		}
		model.addAttribute("users", userService.findAllUsers());
		model.addAttribute("profile", profile);
		model.addAttribute("loggedinuser", getPrincipal());
		logger.debug(" in View PaySlip Get Method");
		return "viewPaySlip";
	}

	@RequestMapping(value = { "/view-PaySlip" }, method = RequestMethod.POST)
	public String generateSelfPaySlip(@Valid PaySlip paySlip, BindingResult result,
			ModelMap model) {
		String id = request.getParameter("userId");
		User profile = userService.findByUserName(getPrincipal());
		User user = userService.findById(Integer.parseInt(id));
		List<PaySlip> existingPaySlip = paySlipService.getPayDetails(user, paySlip.getMonth(), paySlip.getYear(), null);
		logger.debug("Existing pay Slip size is and its data is ==>"+existingPaySlip.size()+" "+existingPaySlip);

		if(existingPaySlip != null && existingPaySlip.size()>0){
			model.addAttribute("paySlips", existingPaySlip);
			model.addAttribute("profile", profile);
			model.addAttribute("loggedinuser", getPrincipal());
			return "paySlip";
		} else {
			model.addAttribute("error", "PaySlip for the specified month and year is"
					+ " not generated yet. If you are an Admin, please generate and try "
					+ "again Or if you are user, please contact administrator.");
			List<PaySlip> paySlips = new ArrayList<PaySlip>();
			paySlips.add(paySlip);
			model.addAttribute("paySlips", paySlips);
			model.addAttribute("profile", profile);
			model.addAttribute("loggedinuser", getPrincipal());
			return "viewPaySlip";
		}
	}

	@RequestMapping(value = { "/getPayRollAddPage" }, method = RequestMethod.POST)
	public String getPayRollPage(ModelMap model) {
		String userId = request.getParameter("userId");
		PaySlip paySlip = new PaySlip();
		model.addAttribute("paySlip", paySlip);
		User user = userService.findById(Integer.parseInt(userId));
		List<UserPayRoll> payRolls = userPayRollService.findUserPayRollDetails(user);

		List<SalaryComponentFormula> salDivPercentList = paySlipService.findSalaryDivPercentages();
		List<SalaryComponent> salaryComponents = paySlipService.findAllSalaryDivisions();
		OUTER : for(int i=0;i<salaryComponents.size();i++) {
			SalaryComponent division = salaryComponents.get(i);
			String divisionName = division.getComponentName();
			logger.debug(" OUTSIDE I Value===>\n "+i +"\n");
			for(SalaryComponentFormula precentage : salDivPercentList) {
				if((precentage.getComponent().getComponentName()).equals(divisionName)) {
					salaryComponents.remove(i);
					i = i-1;
					continue OUTER;
				}
			}
			for(UserPayRoll payRoll: payRolls) {
				if((payRoll.getComponent().getComponentName()).equals(divisionName)) {
					salaryComponents.remove(i);
					i = i-1;
					continue OUTER;
				}
			}
		}
		model.addAttribute("userId", userId);
		if(salaryComponents.size()<=0) {
			model.addAttribute("isComponentsPresent", false);
		} else {
			model.addAttribute("salaryComponents", salaryComponents);
		}

		logger.debug(" salaryComponents List to add Payroll ===>\n\n "+salaryComponents +"\n\n");

		User profile = userService.findByUserName(getPrincipal());
		model.addAttribute("profile", profile);
		model.addAttribute("user", userId);
		model.addAttribute("loggedinuser", getPrincipal());
		logger.debug(" in View PaySlip Get Method");
		return "addPayroll";
	}

	@RequestMapping(value = { "/payRollDetails-{action}" }, method = RequestMethod.POST)
	public String viewPayRollDetails(ModelMap model, @PathVariable String action) {
		String userId = request.getParameter("userId");
		PaySlip paySlip = new PaySlip();
		model.addAttribute("paySlip", paySlip);
		User user = userService.findById(Integer.parseInt(userId));
		List<UserPayRoll> payRolls = userPayRollService.findUserPayRollDetails(user);
		model.addAttribute("payRolls", payRolls);

		User profile = userService.findByUserName(getPrincipal());
		model.addAttribute("users", userService.findAllUsers());
		model.addAttribute("userId", userId);
		model.addAttribute("profile", profile);
		model.addAttribute("loggedinuser", getPrincipal());
		logger.debug("viewPayRollDetails Action is View");
		if(action.equals("view")) {
			return "payRollDetails";
		} else if(action.equals("edit")){
			return "payRollDetailsEdit";
		}
		return "result";
	}

	@RequestMapping(value = { "/addPayRoll" }, method = RequestMethod.POST)
	public String savePayRoll(ModelMap model) {
		String userId = request.getParameter("userId");
		Map<String, String[]> parameters = request.getParameterMap();
		User user = userService.findById(Integer.parseInt(userId));
		Set<String> keys = parameters.keySet();
		List<SalaryComponentFormula> salaryComponentFormula = paySlipService.findSalaryDivPercentages();
		List<UserPayRoll> payRollDetailsList = new ArrayList<UserPayRoll>();

		for(int i=0;i< salaryComponentFormula.size(); i++) {
			SalaryComponentFormula formula = salaryComponentFormula.get(i);
			UserPayRoll payRoll = new UserPayRoll();
			payRoll.setComponent(formula.getComponent());
			payRoll.setUser(user);
			payRoll.setComponentValue(formula.getFormula());
			payRoll.setWayOfDefiningValue("FORMULA");
			payRollDetailsList.add(payRoll);
			payRoll = null;
		}

		Iterator<String> keysIterator = keys.iterator();
		while(keysIterator.hasNext()) {
			UserPayRoll payRoll = new UserPayRoll();
			String componentName = keysIterator.next().toString();
			SalaryComponent salaryComponent = paySlipService.findSalaryDivisionByName(componentName);
			if(salaryComponent != null) {
				payRoll.setComponent(salaryComponent);
				payRoll.setUser(user);
				payRoll.setComponentValue(parameters.get(componentName)[0]);//values will be in array. So fetch first item int he array
				payRoll.setWayOfDefiningValue("MANUAL");
				payRollDetailsList.add(payRoll);
			}
			payRoll = null;
		}

		logger.debug(" payRollDetailsList New ===>\n\n "+payRollDetailsList +"\n\n");

		if(payRollDetailsList.size() >0) {
			userPayRollService.saveOrUpdateAllUserPayRollDetails(payRollDetailsList);
			model.addAttribute("success", "PayRoll Data Added successfully.");
		} else {
			model.addAttribute("success", "Nothing added/updated.");
		}
		PaySlip paySlip = new PaySlip();
		model.addAttribute("paySlip", paySlip);
		User profile = userService.findByUserName(getPrincipal());
		model.addAttribute("profile", profile);
		model.addAttribute("userId", userId);
		model.addAttribute("loggedinuser", getPrincipal());
		logger.debug(" in View PaySlip Get Method");
		return "addPayroll";
	}

	@RequestMapping(value = { "/updatePayRoll" }, method = RequestMethod.POST)
	public String updatePayRoll(ModelMap model) {
		String userId = request.getParameter("userId");
		User user = userService.findById(Integer.parseInt(userId));
		String[] payRollIds = request.getParameterValues("payRollIds");
		List<UserPayRoll> payRollList = userPayRollService.findUserPayRollDetails(user);
		List<UserPayRoll> updatedPayRollList = new ArrayList<UserPayRoll>();
		OUTER: for(int i=0;i<payRollIds.length;i++) {
			Integer payRollId = Integer.parseInt(payRollIds[i]);
			for(int j=0;j<payRollList.size();j++) {
				UserPayRoll payRoll = payRollList.get(j);
				if((payRoll.getPayRollId()).equals(payRollId)) {
					payRoll.setComponentValue(request.getParameter("payRoll-"+payRollId));
					updatedPayRollList.add(payRoll);
					break OUTER;
				}
			}
		}
		logger.debug("updatedPayRollList===>"+updatedPayRollList);
		if(updatedPayRollList.size() >0) {
			userPayRollService.saveOrUpdateAllUserPayRollDetails(updatedPayRollList);
			model.addAttribute("success", "PayRoll Data Updated successfully.");
		} else {
			model.addAttribute("success", "Nothing updated.");
		}
		PaySlip paySlip = new PaySlip();
		model.addAttribute("paySlip", paySlip);
		User profile = userService.findByUserName(getPrincipal());
		model.addAttribute("profile", profile);
		model.addAttribute("userId", userId);
		model.addAttribute("loggedinuser", getPrincipal());
		logger.debug(" in View PaySlip Get Method");
		request.setAttribute(View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.TEMPORARY_REDIRECT);
		return "redirect:payRollDetails-view";
	}

	/**
	 * This method will provide the medium to add a Salary Division Details.
	 */
	@RequestMapping(value = { "/generate-PaySlip" }, method = RequestMethod.GET)
	public String viewOvertimes(ModelMap model) {
		User profile = userService.findByUserName(getPrincipal());
		List<PaySlip> paySlips = new ArrayList<PaySlip>();
		paySlips.add(new PaySlip());
		if(profile.getUserProfile().getType().equals("ADMIN")){
			List<User> usersList = userService.findAllUsers();
			model.addAttribute("usersList", usersList);
		}
		if(profile.getUserProfile().getType().equals("USER")){
			List<User> userList = new ArrayList<User>();
			userList.add(profile);
			model.addAttribute("userList", userList);
		}
		model.addAttribute("paySlips", paySlips);
		model.addAttribute("paySlip", new PaySlip());
		model.addAttribute("profile", profile);
		model.addAttribute("loggedinuser", getPrincipal());
		return "generatePaySlip";
	}

	@RequestMapping(value = { "/generate-PaySlip" }, method = RequestMethod.POST)
	public String generateUserPaySlip(@Valid PaySlip paySlip, BindingResult result,
			ModelMap model) throws InvalidFormatException, IOException, DocumentException {

		User profile = userService.findByUserName(getPrincipal());
		List<User> users = userService.findAllUsers();
		List<Integer> userPaySlipExists = new ArrayList<Integer>();
		List<Integer> userDataNotExists = new ArrayList<Integer>();
		List<PaySlip> payslips = new ArrayList<PaySlip>();
		for(int i=0;i<users.size(); i++) {
			User user = users.get(i);
			List<PaySlip> existingPaySlip = paySlipService.getPayDetails(user, paySlip.getMonth(), paySlip.getYear(),null);
			logger.debug("Anith:: Existing Pay Slips count is===>"+existingPaySlip.size());
			if(existingPaySlip != null && existingPaySlip.size() >0){
				userPaySlipExists.add(user.getId());
				continue;
			}
			List<Wages> wages = paySlipService.findWages();
			List<SalaryComponent> salaryComponents = paySlipService.findAllSalaryDivisions();

			if(wages.size()<=0 /*|| salaryDivision.size() <=0*/){
				model.addAttribute("error", "Either Wages or Salary Division Information is not present.<br>"
						+ " Please Add Wages or Salary Division Details before Generating PaySlip");
				model.addAttribute("paySlip", new PaySlip());
				model.addAttribute("profile", profile);
				model.addAttribute("loggedinuser", getPrincipal());
				return "generatePaySlip";
			}
			OverTime ot = overtimeService.findOTByDesignation(user.getDesignation());
			Wages wage = paySlipService.findWagesByUser(user);
			String otRule = user.getOverTime();
			//List<User> users = userService.findAllUsers();
			//String usersNotFound = new String();
			logger.debug("Anith : UserID====>"+user.getId());
			List<UserBiometricData> biometricData = userBiometricDataService.findByYearAndMonth(paySlip.getYear(), paySlip.getMonth(), user.getId());
			if(biometricData.size()<=0 ) {
				userDataNotExists.add(user.getId());
				continue;
			}
			HashMap<String, Float> workingHoursAndOT = calculateNoOfWorkingHours(biometricData);
			HashMap<String, Float> totalSalaryDetails = calculateTotalSalary(workingHoursAndOT, wage, ot.getOvertimeAmount(), otRule);
			paySlip.setAttendance(workingHoursAndOT.get("workingHours"));
			paySlip.setOverTimeHours(workingHoursAndOT.get("overTimeHours"));
			paySlip.setUser(user);
			generateAndSaveSalaryData(paySlip, totalSalaryDetails, salaryComponents, workingHoursAndOT, user);
			payslips.add(paySlip);
		}
		model.addAttribute("profile", profile);
		if(payslips.size() == users.size()) {
			model.addAttribute("success", "Pay Slips for all users generated successfully.");
		} else {
			String message = new String();
			message = message +"PaySlips Generated successfully.<br>";
			message = message + "PaySlips for below users already exists.<br>";
			message = message + userPaySlipExists.toString();
			message = message +" <br>Biometric data not available for below users. Hence no paySLips generated.<br>";
			message = message + userDataNotExists.toString();			
			model.addAttribute("success", message);
		}



		model.addAttribute("loggedinuser", getPrincipal());
		model.addAttribute("url", "generate-PaySlip");
		return "result";
	}


	private HashMap<String, Float> calculateNoOfWorkingHours(List<UserBiometricData> biometricData){
		Float totalWorkingHours = 0.0f;
		Float totalOverTimeWorkingHours = 0.0f;
		for(UserBiometricData data: biometricData){
			Float mins = 0.0f;
			if(Float.parseFloat(data.getNoOfMins()) >15.00f) {
				mins = Float.parseFloat(data.getNoOfMins()) /60;
			}
			Float workingHours = Float.parseFloat(data.getNoOfHours()) + mins;
			if(workingHours > 8.0f){
				totalOverTimeWorkingHours = totalOverTimeWorkingHours + (workingHours - 8.0f)+mins;
				totalWorkingHours = totalWorkingHours + 8.0f;
			} else {
				totalWorkingHours = totalWorkingHours + workingHours + mins;
			}
		}
		HashMap<String, Float> workingHourDetails = new HashMap<String, Float>();
		workingHourDetails.put("workingHours", totalWorkingHours);
		workingHourDetails.put("overTimeHours", totalOverTimeWorkingHours);
		return workingHourDetails;
	}

	private HashMap<String, Float> calculateTotalSalary(HashMap<String, Float> workingHoursAndOT, Wages wage, String otAmount, String otRule){
		logger.debug("ANITH: calculateTotalSalary==> workingHoursAndOT==> "+workingHoursAndOT+""
				+ "\n otAmount==>"+otAmount+"\n otRule==>"+otRule+"\n Wage==> "+wage.getNormalShift());
		Float wholeDaySalary = 0.0f;
		Float overTimeAmt = 0.0f;
		logger.debug("ANITH:: workingHoursAndOT is===>"+workingHoursAndOT);
		wholeDaySalary = (workingHoursAndOT.get("workingHours")) * (Float.parseFloat(wage.getNormalShift())/8.0f);
		if(otRule.equals("Full")){
			overTimeAmt = workingHoursAndOT.get("overTimeHours") * (Float.parseFloat(otAmount));
		} else if(otRule.equals("Half")){
			overTimeAmt = workingHoursAndOT.get("overTimeHours") * (Float.parseFloat(otAmount)/2.0f);
		} else if(otRule.equals("One And Half")){
			Float otAmt = (Float.parseFloat(otAmount)/2.0f)+Float.parseFloat(otAmount);
			overTimeAmt = workingHoursAndOT.get("overTimeHours") * otAmt;
		}

		HashMap<String, Float> totalSalaryDetails = new HashMap<String, Float>();
		totalSalaryDetails.put("totalSalary", wholeDaySalary);
		totalSalaryDetails.put("overTimeAmt", overTimeAmt);

		return totalSalaryDetails;
	}

	private void generateAndSaveSalaryData(PaySlip paySlip, HashMap<String, Float> totalAmount, List<SalaryComponent> salaryComponent, 
			HashMap<String, Float> workingHoursAndOT, User user) throws InvalidFormatException, IOException, DocumentException{

		File file = new File("C:/BMS/PaySlipTemplate.xlsx");
		FileInputStream inputStream = new FileInputStream(file);
		BufferedInputStream bis = new BufferedInputStream(inputStream);
		Workbook workbook =  new XSSFWorkbook(bis);
		workbook.setForceFormulaRecalculation(true);
		Sheet sheet = workbook.getSheetAt(0);
		Row row = sheet.getRow(1);

		Cell cell = row.createCell(7);//total Salary Column
		cell.setCellValue(totalAmount.get("totalSalary"));
		cell = row.createCell(6);//OT Salary Column
		cell.setCellValue(totalAmount.get("overTimeAmt"));

		inputStream.close();
		bis.close();
		FileOutputStream outputStream = new FileOutputStream("C:/BMS/"+user.getUserName()+"_"+paySlip.getYear()+"_"+
				paySlip.getMonth()+".xlsx");
		workbook.write(outputStream);
		cell = row.createCell(8);//total Salary Column
		cell.setCellValue(0.0);
		cell = row.createCell(7);//OT Salary Column
		cell.setCellValue(0.0);
		workbook.close();
		outputStream.close();

		saveExcelDatatoDB("C:/BMS/"+user.getUserName()+"_"+paySlip.getYear()+"_"+
				paySlip.getMonth()+".xlsx", paySlip, user);
	}

	public void saveExcelDatatoDB(String xlsFilePath, PaySlip paySlip, User user) throws DocumentException, IOException{
		//First we read the Excel file in binary format into FileInputStream
		File file = new File(xlsFilePath);
		FileInputStream input_document = new FileInputStream(file);
		BufferedInputStream bis = new BufferedInputStream(input_document);
		// Read workbook into HSSFWorkbook
		Workbook my_xls_workbook = new XSSFWorkbook(bis); 
		try {
			//my_xls_workbook.setForceFormulaRecalculation(true);
			my_xls_workbook.getForceFormulaRecalculation();

			// Read worksheet into HSSFSheet
			Sheet my_worksheet = my_xls_workbook.getSheetAt(0); 
			//Loop through rows.
			Row row1 = my_worksheet.getRow(1); 
			Row row0 = my_worksheet.getRow(0);

			for(int j=0; j<row1.getLastCellNum();j++){
				String componentName = row0.getCell(j).getStringCellValue();

				PaySlip paySlipCopy = new PaySlip();
				paySlipCopy.setMonth(paySlip.getMonth());
				paySlipCopy.setYear(paySlip.getYear());
				paySlipCopy.setUser(paySlip.getUser());
				paySlipCopy.setAttendance(paySlip.getAttendance());
				paySlipCopy.setOverTimeHours(paySlip.getOverTimeHours());

				paySlipCopy.setComponentName(componentName);
				Cell cell = row1.getCell(j); //Fetch CELL

				FormulaEvaluator evaluator = my_xls_workbook.getCreationHelper().createFormulaEvaluator();

				switch(cell.getCellType()) {
				case Cell.CELL_TYPE_STRING:
					paySlipCopy.setValue(Float.parseFloat(cell.getStringCellValue()));			
					break;
				case Cell.CELL_TYPE_NUMERIC:
					paySlipCopy.setValue(Float.parseFloat(""+cell.getNumericCellValue()));
					break;
				case Cell.CELL_TYPE_FORMULA:
					paySlipCopy.setValue(Float.parseFloat(""+evaluator.evaluate(cell).getNumberValue()));
					break;
				case Cell.CELL_TYPE_BLANK:
					paySlipCopy.setValue(0.0f);
					break;
				case Cell.CELL_TYPE_ERROR:
					//TODO: need a logic if cell has error
					break;
				}
				//next line
				paySlipService.savePayDetails(paySlipCopy);
				paySlipCopy = null;
			}
		}
		catch(Exception e) {
			logger.debug("Exception occurred--->"+e.getMessage());
			throw e;
		}
		finally {
			my_xls_workbook.close();
			input_document.close(); //close xls
			bis.close();
			file.delete();//Delete Excel file as the data is stored in database.
		}
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
