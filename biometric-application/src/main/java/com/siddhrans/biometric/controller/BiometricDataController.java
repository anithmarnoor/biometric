package com.siddhrans.biometric.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.hibernate.TransientObjectException;
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
import org.springframework.web.multipart.MultipartFile;

import com.siddhrans.biometric.model.BiometricData;
import com.siddhrans.biometric.model.BiometricMachine;
import com.siddhrans.biometric.model.Holidays;
import com.siddhrans.biometric.model.User;
import com.siddhrans.biometric.model.UserBiometricData;
import com.siddhrans.biometric.service.BiometricDataService;
import com.siddhrans.biometric.service.HolidaysService;
import com.siddhrans.biometric.service.LeavesService;
import com.siddhrans.biometric.service.UserBiometricDataService;
import com.siddhrans.biometric.service.UserService;
import com.siddhrans.biometric.utils.BiometricConstants;
import com.siddhrans.biometric.utils.CustomDateTimeComparator;
import com.siddhrans.biometric.utils.CustomUserIdComparator;

@Controller
@RequestMapping("/")
@SessionAttributes("roles")
public class BiometricDataController {


	static final Logger logger = LoggerFactory.getLogger(BiometricDataController.class);

	@Autowired
	BiometricDataService biometricDataService;

	@Autowired
	UserBiometricDataService userBiometricDataService;

	@Autowired
	UserService userService;

	@Autowired
	LeavesService leavesService;

	@Autowired
	HolidaysService holidaysService;

	@Autowired
	MessageSource messageSource;

	@Autowired
	PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;


	/**
	 * This method will provide the medium to add a Biometric Machine.
	 */
	@RequestMapping(value = { "/addMachine" }, method = RequestMethod.GET)
	public String addMachine(ModelMap model) {
		List<BiometricMachine> machineList = biometricDataService.findAllMachines();
		model.addAttribute("machineList", machineList);
		model.addAttribute("bioMachine", new BiometricMachine());
		User profile = userService.findByUserName(getPrincipal());

		model.addAttribute("profile", profile);
		model.addAttribute("loggedinuser", getPrincipal());
		return "addBiometricMachine";
	}

	/**
	 * This method will save/edit/delete the Biometric Machine Details to database
	 */
	@RequestMapping(value = { "/machine-{action}-{id}" }, method = RequestMethod.POST)
	public String addMachineData(@Valid BiometricMachine bioMachine, @PathVariable String action, @PathVariable String id, BindingResult bindingResult, ModelMap model) {
		if(action.equals("save")){
			biometricDataService.saveMachine(bioMachine);
			model.addAttribute("message", "Saved Successfully.");
		} else if(action.equals("delete")){
			biometricDataService.deleteMachineById(Integer.parseInt(id));
			model.addAttribute("message", "Deleted Successfully.");
		} else if(action.equals("edit")){
			biometricDataService.editMachine(bioMachine);
			model.addAttribute("message", "Edited Successfully.");
		}		

		List<BiometricMachine> machineList = biometricDataService.findAllMachines();
		model.addAttribute("machineList", machineList);
		model.addAttribute("bioMachine", new BiometricMachine());
		User profile = userService.findByUserName(getPrincipal());

		model.addAttribute("profile", profile);
		model.addAttribute("loggedinuser", getPrincipal());
		return "addBiometricMachine";
	}

	/**
	 * This method will provide the medium to add a Salary Division Details.
	 */
	@RequestMapping(value = { "/attendanceLog" }, method = RequestMethod.GET)
	public String saveBiometricData(ModelMap model) {
		BiometricData biometricData = new BiometricData();
		model.addAttribute("biometricData", biometricData);
		List<BiometricMachine> machineList = biometricDataService.findAllMachines();
		model.addAttribute("machineList", machineList);
		model.addAttribute("edit", false);
		User profile = userService.findByUserName(getPrincipal());

		model.addAttribute("profile", profile);
		model.addAttribute("loggedinuser", getPrincipal());
		return "viewBiometricData";
	}

	/**
	 * This method will provide the medium to add a Salary Division Details.
	 * @throws IOException 
	 * @throws ParseException 
	 */
	@RequestMapping(value = { "/attendanceLog-save" }, method = RequestMethod.POST)
	public String saveBiometricData(@Valid BiometricData biometricData, BindingResult result,
			ModelMap model) throws IOException, ParseException {
		logger.debug("ANITH: biometricData is "+biometricData);
		try{
			if (result.hasErrors()) {
				List<BiometricData> biometricDataList = biometricDataService.findAll();
				model.addAttribute("biometricDataList", biometricDataList);
				model.addAttribute("biometricData", biometricData);
				List<BiometricMachine> machineList = biometricDataService.findAllMachines();
				model.addAttribute("machineList", machineList);
				model.addAttribute("edit", false);
				User profile = userService.findByUserName(getPrincipal());

				model.addAttribute("profile", profile);
				model.addAttribute("loggedinuser", getPrincipal());
				return "viewBiometricData";
			}

			MultipartFile multipartFile = biometricData.getFile();
			String fileName = multipartFile.getOriginalFilename();
			String fileNameWithoutExt = fileName.substring(fileName.lastIndexOf(".")+1,fileName.length());
			String extension = fileNameWithoutExt.substring(fileNameWithoutExt.lastIndexOf(".")+1,fileNameWithoutExt.length());
			logger.debug("ANITH: File Name is "+fileNameWithoutExt+ "\n File Extension is "+extension);

			boolean isDuplicatesFound = false;
			if(extension.equals("xls") || extension.equals("xlsx") || extension.equals("csv") || extension.equals("dat")){
				biometricData.setFileName(fileNameWithoutExt);
				biometricData.setFileType("text/csv");
				//biometricData.setContent(multipartFile.getBytes());
				File tmpFile = File.createTempFile(fileNameWithoutExt, extension);
				multipartFile.transferTo(tmpFile);

				File csvFile = datToCSVFile(tmpFile);
				byte[] fileBytes = readBytesFromFile(csvFile);
				biometricData.setContent(fileBytes);

				//Reads data from dat file and reads only user ID,data and time column
				ArrayList<ArrayList<String>> bioDataList = readFromFile(fileNameWithoutExt, extension,  csvFile);
				//Divides the user data according to User IDs and then sorts it according to data and time
				HashMap<Integer, ArrayList<LocalDateTime>> userAttDataMap = this.sortAndDivideArrayList(bioDataList);
				//Finds login and logout time and Calculate the number of hours worked per day. 
				HashMap<Integer, ArrayList<HashMap<String,String>>> userAttDetails = calculateWorkingHours(userAttDataMap);
				ArrayList<UserBiometricData> userBiometricObjsList = convertToUserBiometricObject(userAttDetails, biometricData.getYear(), biometricData.getMonth());
				Integer machineId = biometricData.getMachine().getMachineId();
				for(UserBiometricData biometricDataObj: userBiometricObjsList){
					biometricDataObj.setMachineId(machineId);
					userBiometricDataService.save(biometricDataObj);
				}
				biometricDataService.saveDocument(biometricData);
			}
			return "result";

		}catch(TransientObjectException e){
			List<BiometricData> biometricDataList = biometricDataService.findAll();
			model.addAttribute("biometricDataList", biometricDataList);
			model.addAttribute("biometricData", biometricData);
			model.addAttribute("edit", false);
			User profile = userService.findByUserName(getPrincipal());
			logger.debug("TransientObjectException Occurred... Exception message is "+e.getMessage());
			model.addAttribute("profile", profile);
			model.addAttribute("error", "Biometric Data with same month and year is present.. Please delete existing data" +
					" and upload again if you want to change.");
			model.addAttribute("loggedinuser", getPrincipal());
			return "viewBiometricData";
		}catch(Exception e){
			List<BiometricData> biometricDataList = biometricDataService.findAll();
			model.addAttribute("biometricDataList", biometricDataList);
			model.addAttribute("biometricData", biometricData);
			model.addAttribute("edit", false);
			User profile = userService.findByUserName(getPrincipal());
			logger.debug("Exception StackTrace is ");
			e.printStackTrace();
			model.addAttribute("profile", profile);
			model.addAttribute("error", "Exception reading data. Exception message is "+e.getMessage() +"StackTrace\n "+e.getStackTrace().toString());
			model.addAttribute("loggedinuser", getPrincipal());
			return "viewBiometricData";

		}
	}

	/**
	 * This method will provide the medium to add a Salary Division Details.
	 */
	@RequestMapping(value = { "/view-BiometricData" }, method = RequestMethod.GET)
	public String viewBiometricData(ModelMap model) {
		List<BiometricData> biometricDataList = biometricDataService.findAll();
		model.addAttribute("biometricDataList", biometricDataList);
		model.addAttribute("biometricData", new BiometricData());
		User profile = userService.findByUserName(getPrincipal());

		model.addAttribute("profile", profile);
		model.addAttribute("loggedinuser", getPrincipal());
		return "viewBiometricData";
	}

	/**
	 * This method will provide the medium to update an existing user.
	 */
	@RequestMapping(value = { "/delete-BiometricData-{id}" }, method = RequestMethod.GET)
	public String deleteBiometricData(@PathVariable String id, ModelMap model) {
		biometricDataService.deleteBiometricDataById(Integer.parseInt(id));
		model.addAttribute("success", "Biometric Data Deleted Successfully..");
		User profile = userService.findByUserName(getPrincipal());

		model.addAttribute("profile", profile);
		model.addAttribute("loggedinuser", getPrincipal());
		model.addAttribute("url", "view-BiometricData");
		return "result";
	}


	@RequestMapping(value = { "/view-Attendance" }, method = RequestMethod.GET)
	public String getUserAttendance(ModelMap model) {
		/*List<UserBiometricData> userAttendanceLog = userBiometricDataService.findByUserId(userId);
		model.addAttribute("userAttendanceLog", userAttendanceLog);*/
		model.addAttribute("userBiometricData", new UserBiometricData());
		User profile = userService.findByUserName(getPrincipal());

		if(profile.getUserProfile().getType().equals(BiometricConstants.ADMIN_ROLE)){
			List<User> usersList = userService.findAllUsers();
			model.addAttribute("usersList", usersList);
		} else {
			List<User> usersList = new ArrayList<User>();
			usersList.add(profile);
			model.addAttribute("usersList", usersList);
		}

		model.addAttribute("profile", profile);
		model.addAttribute("loggedinuser", getPrincipal());
		return "viewUserAttendanceData";
	}

	@RequestMapping(value = { "/view-Attendance" }, method = RequestMethod.POST)
	public String searchSelfAttendance(@Valid UserBiometricData userBiometricData, BindingResult result,
			ModelMap model) throws IOException {
		Integer userId = userBiometricData.getUserId();
		List<UserBiometricData> userAttendanceLog = userBiometricDataService.findByYearAndMonth(userBiometricData.getYear(),userBiometricData.getMonth(),userId);
		model.addAttribute("userAttendanceLog", userAttendanceLog);
		model.addAttribute("userBiometricData", new UserBiometricData());
		User profile = userService.findByUserName(getPrincipal());
		if(profile.getUserProfile().getType().equals(BiometricConstants.ADMIN_ROLE)){
			List<User> usersList = userService.findAllUsers();
			model.addAttribute("usersList", usersList);
		} else {
			List<User> usersList = new ArrayList<User>();
			usersList.add(profile);
			model.addAttribute("usersList", usersList);
		}
		model.addAttribute("profile", profile);
		model.addAttribute("loggedinuser", getPrincipal());
		return "viewUserAttendanceData";
	}

	@RequestMapping(value = { "/download-document-{docId}" }, method = RequestMethod.GET)
	public String downloadDocument( @PathVariable int docId, HttpServletResponse response) throws IOException {
		BiometricData document = biometricDataService.findById(docId);
		response.setContentType(document.getFileType());
		response.setContentLength(document.getContent().length);
		response.setHeader("Content-Disposition","attachment; filename=\"" + document.getMachine().getMachineName() +"\"");

		FileCopyUtils.copy(document.getContent(), response.getOutputStream());

		return "viewBiometricData";
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

	private File datToCSVFile(File file) throws IOException{
		Scanner scanner=new Scanner(file);
		File csvFile=new File("BiometricDatacvs.csv");
		FileWriter writer=new FileWriter(csvFile);

		while(scanner.hasNext()){
			String line=scanner.nextLine();
			line = (line.trim()).replaceAll("\\s",",");
			writer.write(line);
			writer.write("\n");
		}
		writer.close();
		scanner.close();
		return csvFile;
	}

	private static byte[] readBytesFromFile(File csvFile) {
		FileInputStream fileInputStream = null;
		byte[] bytesArray = null;
		try {
			bytesArray = new byte[(int) csvFile.length()];

			//read file into bytes[]
			fileInputStream = new FileInputStream(csvFile);
			fileInputStream.read(bytesArray);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fileInputStream != null) {
				try {
					fileInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
		return bytesArray;
	}

	public HashMap<Integer, ArrayList<HashMap<String,String>>> calculateWorkingHours(HashMap<Integer, ArrayList<LocalDateTime>> userAttDataMap){

		HashMap<Integer, ArrayList<HashMap<String,String>>> userAttDetails = new HashMap<Integer, ArrayList<HashMap<String,String>>>();
		for (Map.Entry<Integer, ArrayList<LocalDateTime>> entry : userAttDataMap.entrySet()) {
			logger.debug("calculateWorkingHours : Inside Date Calculation => ");
			Integer key = entry.getKey();
			logger.debug("calculateWorkingHours : Inside Date Calculation => Key is "+key);

			ArrayList<LocalDateTime> dateTimeList = entry.getValue();
			logger.debug("calculateWorkingHours : Inside Date Calculation => Value is "+dateTimeList);

			HashMap<String,String> attDetails = new HashMap<String,String>();
			ArrayList<HashMap<String,String>> userAttDetailsList = new ArrayList<HashMap<String,String>>();
			for(int i=0,j=1;i<dateTimeList.size();){
				attDetails = new HashMap<String,String>();
				LocalDateTime loginDateTime = dateTimeList.get(i);
				LocalDateTime logoutDateTime = null;
				LocalDate logDate = loginDateTime.toLocalDate();
				if(j < dateTimeList.size() && logDate.isEqual(dateTimeList.get(j).toLocalDate())){
					j = j+1;//increments pointer to next data in a list
					continue;
				} else if(j > i){//If above block is entered, that means
					logoutDateTime = dateTimeList.get(j-1);
					if((j-1) != i && logoutDateTime.toLocalDate().equals(logDate)){
						i = j ;//points to the last index() where match was found.(j value starts from 1.)
						LocalTime loginTime = loginDateTime.toLocalTime();
						LocalTime logoutTime = logoutDateTime.toLocalTime();

						long diffMilliSecs = ChronoUnit.MILLIS.between(loginTime, logoutTime );
						long diffinMins= (diffMilliSecs/1000)/60;
						long workHours= diffinMins/60;
						long workMins= diffinMins%60;
						attDetails.put(BiometricConstants.LOGIN_DATE, logDate.toString());
						attDetails.put(BiometricConstants.LOGIN_TIME, loginTime.toString());
						attDetails.put(BiometricConstants.LOGOUT_TIME, logoutTime.toString());
						attDetails.put(BiometricConstants.WORK_HOURS, ""+workHours);
						attDetails.put(BiometricConstants.WORK_MINS, workMins+"");
					}else {//No logout found
						LocalTime loginTime = loginDateTime.toLocalTime();
						attDetails.put(BiometricConstants.LOGIN_DATE, logDate.toString());
						attDetails.put(BiometricConstants.LOGIN_TIME, loginTime.toString());
						attDetails.put(BiometricConstants.LOGOUT_TIME, "---");
						attDetails.put(BiometricConstants.WORK_HOURS, "0");
						attDetails.put(BiometricConstants.WORK_MINS, "0");
						i = j;
					}
				} 

				userAttDetailsList.add(attDetails);
				attDetails = null;
				loginDateTime = null;
			}
			userAttDetails.put(key, userAttDetailsList);
			userAttDetailsList = null;
			attDetails = null;
		}
		logger.debug("calculateWorkingHours :Final for all Users. After calculating time difference userAttDetails ==>" + userAttDetails);
		return userAttDetails;

	}

	public ArrayList<ArrayList<String>> readFromFile(String fileNameWithoutExt, String extension, File csvFile) throws Exception{
		//START: Read the data in file and insert the values to list
		ArrayList< ArrayList<String>> bioDataList = new ArrayList< ArrayList<String>>();
		try{
			Scanner scanner1=new Scanner(csvFile);

			while(scanner1.hasNext()){
				String line=scanner1.nextLine();
				String[] lineArr = line.split(",");
				ArrayList<String> data = new ArrayList<String>();
				data.add(lineArr[0]); //User ID
				data.add(lineArr[1]); //Log Date
				data.add(lineArr[2]); //Log Time

				bioDataList.add(data);
			}
			scanner1.close();
			logger.debug("readFromFile: bioDataList==>"+bioDataList);
		} catch(Exception e){
			throw e;
		}
		return bioDataList;
	}

	public HashMap<Integer, ArrayList<LocalDateTime>> sortAndDivideArrayList(ArrayList< ArrayList<String>> bioDataList) throws Exception{
		Collections.sort(bioDataList, new CustomUserIdComparator());// Sorts Based on IDs-Considers First Column as ID
		HashMap<Integer, List<ArrayList<String>>> userAttDataMap = new HashMap<Integer, List<ArrayList<String>>>();
		Integer index=0,startIndex=0, endIndex=0;
		//Below loop is used to separate the data based on User IDs and put it in a HashMap with User ID as Key and ArrayList of login and logout data as value
		for(int i=0; i < bioDataList.size(); i++){
			logger.debug(bioDataList.get(i).toString());
			if(i < bioDataList.size()-1){
				if(Integer.parseInt(bioDataList.get(i).get(0)) == Integer.parseInt(bioDataList.get(i+1).get(0))){
					continue;
				}
			}
			endIndex = i+1;

			logger.debug("sortAndDivideArrayList Index=> "+startIndex +" End Index => "+endIndex);
			List<ArrayList<String>> subList = bioDataList.subList(startIndex, endIndex);
			logger.debug("sortAndDivideArrayList=> "+subList.toString());
			userAttDataMap.put(Integer.parseInt(subList.get(0).get(0)), subList);
			startIndex = endIndex;
		}
		logger.debug("sortAndDivideArrayList: 1Sorted bioDataList==>END");
		logger.debug("sortAndDivideArrayList: 1Sorted bioDataList in a Map==>"+userAttDataMap);
		//Below loop is to sort the attendance data based on time(if it is not sorted already)
		HashMap<Integer, ArrayList<LocalDateTime>> dataWithDate = new HashMap<Integer, ArrayList<LocalDateTime>>();
		try{
			for (Map.Entry<Integer, List<ArrayList<String>>> entry : userAttDataMap.entrySet()) {

				Integer key = entry.getKey();
				List<ArrayList<String>> loginLogout = entry.getValue();
				ArrayList<LocalDateTime> loginLogoutDates = new ArrayList<LocalDateTime>();
				logger.debug("sortAndDivideArrayList: 1loginLogout is ==>"+loginLogout);

				for(int j=0; j< loginLogout.size(); j++){
					logger.debug("sortAndDivideArrayList: 2loginLogout at J is  ==>"+loginLogout.get(j));
					/*DateTimeFormatter sdf = DateTimeFormatter.ofPattern("yyyy-MM-dd");*/

					String now = (loginLogout.get(j).get(1)) +" "+(loginLogout.get(j).get(2)) ;

					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

					LocalDateTime formatDateTime = LocalDateTime.parse(now, formatter);

					//Date dateTime = dateTimeFormat.parse(input);
					logger.debug("sortAndDivideArrayList: 39DateTime after parse is ==>"+formatDateTime);
					loginLogoutDates.add(formatDateTime);
				}
				logger.debug("sortAndDivideArrayList: 1loginLogoutDates Before Sorting==>"+loginLogoutDates);
				Collections.sort(loginLogoutDates, new CustomDateTimeComparator());
				logger.debug("sortAndDivideArrayList: 1loginLogoutDates After Sorting==>"+loginLogoutDates);
				dataWithDate.put(key, loginLogoutDates);
			}
			logger.debug("sortAndDivideArrayList: 1Map after DateTime and User ID Sorting==>"+dataWithDate);
		} catch(Exception e){
			throw e;
		}
		return dataWithDate;
	}

	public ArrayList<UserBiometricData> convertToUserBiometricObject(HashMap<Integer, ArrayList<HashMap<String,String>>> userAttDetails, String logYear, String logMonth) throws Exception{
		logger.debug("convertToUserBiometricObject START userAttDetails ==> "+userAttDetails);
		int[] days = {
				0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
		};
		ArrayList<UserBiometricData> userBiometricDataObjsList = new ArrayList<UserBiometricData>(); 
		try{
			
			List<Holidays> holidaysList = holidaysService.findHolidaysByMonthAndYear(Integer.parseInt(logMonth), Integer.parseInt(logYear));
			for (Map.Entry<Integer, ArrayList<HashMap<String,String>>> entry : userAttDetails.entrySet()) {
				Integer userId = entry.getKey();
				List<UserBiometricData> userBioList = userBiometricDataService.findLeavesByUserInMonth(userId, Integer.parseInt(logMonth), Integer.parseInt(logYear));
				UserBiometricData boimetricData = null;
				UserBiometricData absentData = null;
				ArrayList<HashMap<String,String>> attendanceDataList = entry.getValue();
				int nextDay = 1;
				for(int i=0;i< attendanceDataList.size() ;i++){
					HashMap<String,String> attendanceDataMap = attendanceDataList.get(i);
					String loginDate 	= attendanceDataMap.get(BiometricConstants.LOGIN_DATE);
					String loginTime 	= attendanceDataMap.get(BiometricConstants.LOGIN_TIME);
					String logoutTime 	= attendanceDataMap.get(BiometricConstants.LOGOUT_TIME);
					String workHours 	= attendanceDataMap.get(BiometricConstants.WORK_HOURS);
					String workMins 	= attendanceDataMap.get(BiometricConstants.WORK_MINS);
					boimetricData = new UserBiometricData();
					boimetricData.setUserId(userId);
					boimetricData.setNoOfHours(workHours);
					boimetricData.setNoOfMins(workMins);
					boimetricData.setLoginTime(loginTime);
					boimetricData.setLogoutTime(logoutTime);
					String dateSplit[] = loginDate.split("-");
					Integer year = Integer.parseInt(dateSplit[0]);
					Integer month = Integer.parseInt(dateSplit[1]);
					Integer date = Integer.parseInt(dateSplit[2]);//current date in a  map
					
					if(logoutTime != "---" ){
						boimetricData.setStatus(BiometricConstants.PRESENT);
						/*//TODO: need to consider working weekends as OT
						 * LocalDate localDate = LocalDate.of(year, month, nextDay);
						DayOfWeek dayOfWeek = localDate.getDayOfWeek();
						if(dayOfWeek.toString().equals(BiometricConstants.WEEKEND_SUNDAY)){
							boimetricData.setStatus(BiometricConstants.OVERTIME);
						} else { */ 
							if(holidaysList != null && nextDay == date ){
								for(int k=0;k<holidaysList.size();k++){
									Holidays holiday = holidaysList.get(k);
									if(holiday.getHolidayDate().equals(nextDay)){
										logger.debug("convertToUserBiometricObject --> Holiday OT nextDay=>"+nextDay+" Current Date in Map==>"+date+" holiday Date==> "+holiday.getHolidayDate());
										boimetricData.setStatus(BiometricConstants.OVERTIME);
										break;
									}
								}
							}
						/*}*/
					} else {
						boimetricData.setStatus(BiometricConstants.DEFECTIVE_STATUS);
					}
					
					int numberOfDays = days[month];
					logger.debug("convertToUserBiometricObject --> nextDay=>"+nextDay+" Current Date in Map==>"+date);
					if(i!=0 && date != nextDay){//If some date is missing in middle
						int missingNoOfDays =  date - nextDay;
						logger.debug("convertToUserBiometricObject --> Some Date is missing. Number of days missing is "+ missingNoOfDays);
						
						while(missingNoOfDays != 0){//loop till nextDay reaches the current date in Map.
							//Absent
							absentData = new UserBiometricData();
							LocalDate localDate = LocalDate.of(year, month, nextDay);
							DayOfWeek dayOfWeek = localDate.getDayOfWeek();
							absentData.setUserId(userId);
							if(dayOfWeek.toString().equals(BiometricConstants.WEEKEND_SUNDAY)){//Sunday
								absentData.setStatus(BiometricConstants.WEEKEND_STATUS);
								absentData.setLoginTime("---");//empty
								absentData.setLogoutTime("---");//empty
								absentData.setNoOfHours("0");
								absentData.setNoOfMins("0");
								absentData.setDate(nextDay);
								absentData.setYear(year);
								absentData.setMonth(month);
								userBiometricDataObjsList.add(absentData);
								nextDay+=1;
								missingNoOfDays -= 1;
								logger.debug("convertToUserBiometricObject missingNoOfDays and nextDay after Sunday is "+missingNoOfDays+"->"+nextDay);
								absentData = null;
								continue;
							} else {
								absentData = new UserBiometricData();
								absentData.setUserId(userId);
								logger.debug("convertToUserBiometricObject nextDay is "+nextDay);
								logger.debug("convertToUserBiometricObject month is "+month);
								logger.debug("convertToUserBiometricObject year is "+year);
								
								if(holidaysList != null){
									for(int k=0;k<holidaysList.size();k++){
										Holidays holiday = holidaysList.get(k);
										if(holiday.getHolidayDate().equals(nextDay)){
											nextDay+=1;
											missingNoOfDays -= 1;
											logger.debug("convertToUserBiometricObject missingNoOfDays and nextDay after Holiday is "+missingNoOfDays+"->"+nextDay);
											continue;
										}
									}
								}
								if(userBioList != null){
									for(int k=0;k<userBioList.size();k++){
										UserBiometricData data = userBioList.get(k);
										logger.debug("convertToUserBiometricObject Leaves Date and Next Date is "+data.getDate() +" -> "+nextDay);
										logger.debug("convertToUserBiometricObject Condition on Date is "+data.getDate().equals(nextDay));
										if(data.getDate().equals(nextDay) && data.getYear().equals(year)){
											nextDay+=1;
											missingNoOfDays -= 1;
											logger.debug("convertToUserBiometricObject missingNoOfDays and nextDay after leave is "+missingNoOfDays+"->"+nextDay);
											continue;
										}
									}
								}
								if(missingNoOfDays == 0 ){
									break;
								}
								// Mark Absent only if some date is either holiday or taken leave.
								absentData.setStatus(BiometricConstants.ABSENT);
								absentData.setLoginTime("---");//empty
								absentData.setLogoutTime("---");//empty
								absentData.setNoOfHours("0");
								absentData.setNoOfMins("0");

								absentData.setDate(nextDay);
								absentData.setYear(year);
								absentData.setMonth(month);
								userBiometricDataObjsList.add(absentData);
								nextDay+=1;
								missingNoOfDays -= 1;
								logger.debug("convertToUserBiometricObject missingNoOfDays and nextDay after ABSENT is "+missingNoOfDays+"->"+nextDay);
								absentData = null;
							}
						}
					}
					boimetricData.setDate(date);
					boimetricData.setYear(year);
					boimetricData.setMonth(month);
					userBiometricDataObjsList.add(boimetricData);	
					boimetricData = null;
					nextDay+=1;
					logger.debug("convertToUserBiometricObject nextDay after PRESENT is "+nextDay);
				}

			}
		} catch(Exception e){
			throw e;
		}
		logger.debug("convertToUserBiometricObject END--> userBiometricDataObjsList==>\n "+userBiometricDataObjsList+"\n\n");
		return userBiometricDataObjsList;
	}

	private UserBiometricData getMultipleShiftData(ArrayList<ArrayList<String>> data){

		return null;
	}
}
