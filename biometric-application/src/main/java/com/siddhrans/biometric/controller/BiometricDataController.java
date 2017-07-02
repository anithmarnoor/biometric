package com.siddhrans.biometric.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

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
import com.siddhrans.biometric.model.User;
import com.siddhrans.biometric.model.UserBiometricData;
import com.siddhrans.biometric.service.BiometricDataService;
import com.siddhrans.biometric.service.UserBiometricDataService;
import com.siddhrans.biometric.service.UserService;

@Controller
@RequestMapping("/")
@SessionAttributes("roles")
public class BiometricDataController {


	static final Logger logger = LoggerFactory.getLogger(PaySlipController.class);

	static final private  String LOCATION = System.getProperty("java.io.tmpdir");

	@Autowired
	BiometricDataService biometricDataService;
	
	@Autowired
	UserBiometricDataService userBiometricDataService;

	@Autowired
	UserService userService;

	@Autowired
	MessageSource messageSource;

	@Autowired
	PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;


	/**
	 * This method will provide the medium to add a Salary Division Details.
	 */
	@RequestMapping(value = { "/view-BiometricData" }, method = RequestMethod.GET)
	public String viewBiometricData(ModelMap model) {
		List<BiometricData> biometricDataList = biometricDataService.findAll();
		model.addAttribute("biometricDataList", biometricDataList);
		model.addAttribute("biometricData", new BiometricData());
		model.addAttribute("loggedinuser", getPrincipal());
		return "viewBiometricData";
	}

	/**
	 * This method will provide the medium to update an existing user.
	 */
	@RequestMapping(value = { "/delete-BiometricData-{id}" }, method = RequestMethod.GET)
	public String deleteBiometricData(@PathVariable String id, ModelMap model) {
		biometricDataService.deleteBiometricDataById(Integer.parseInt(id));
		model.addAttribute("success", "Data Deleted Successfully..");
		model.addAttribute("loggedinuser", getPrincipal());
		return "delBiometricSuccess";
	}

	/**
	 * This method will provide the medium to add a Salary Division Details.
	 */
	@RequestMapping(value = { "/save-biometricData" }, method = RequestMethod.GET)
	public String saveBiometricData(ModelMap model) {
		BiometricData biometricData = new BiometricData();
		model.addAttribute("biometricData", biometricData);
		model.addAttribute("edit", false);
		model.addAttribute("loggedinuser", getPrincipal());
		return "viewBiometricData";
	}

	/**
	 * This method will provide the medium to add a Salary Division Details.
	 * @throws IOException 
	 */
	@RequestMapping(value = { "/view-BiometricData" }, method = RequestMethod.POST)
	public String saveBiometricData(@Valid BiometricData biometricData, BindingResult result,
			ModelMap model) throws IOException {
		if (result.hasErrors()) {
			List<BiometricData> biometricDataList = biometricDataService.findAll();
			model.addAttribute("biometricDataList", biometricDataList);
			model.addAttribute("biometricData", biometricData);
			model.addAttribute("edit", false);
			model.addAttribute("loggedinuser", getPrincipal());
			return "viewBiometricData";
		}

		MultipartFile multipartFile = biometricData.getFile();
		String fileName = multipartFile.getOriginalFilename();
		String extension = fileName.substring(fileName.lastIndexOf(".")+1,fileName.length());
		logger.debug("ANITH: File Extension is "+extension);
		File csvFile = null;
		if(extension.equals("xls") || extension.equals("xlsx") || extension.equals("csv") || extension.equals("dat")){
			/*if(extension.equals("xls") || extension.equals("xlsx")){
			biometricData.setName(multipartFile.getOriginalFilename());
			biometricData.setType(multipartFile.getContentType());
			biometricData.setContent(multipartFile.getBytes());
		} else {*/
			File tmpFile = new File(System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") + 
					multipartFile.getOriginalFilename());
			multipartFile.transferTo(tmpFile);

			csvFile = datToCSVFile(tmpFile);
			byte[] fileBytes = readBytesFromFile(csvFile);
			biometricData.setName(csvFile.getName());
			biometricData.setType("text/csv");
			//biometricData.setContent(multipartFile.getBytes());
			biometricData.setContent(fileBytes);

			//START: Read the data in file and insert the values to list

			Scanner scanner1=new Scanner(csvFile);
			ArrayList< ArrayList<String>> bioDataList = new ArrayList< ArrayList<String>>();
			while(scanner1.hasNext()){
				String line=scanner1.nextLine();
				String[] lineArr = line.split(",");
				ArrayList<String> data = new ArrayList<String>(Arrays.asList(lineArr));
				bioDataList.add(data);
			}
			logger.debug("ANITH: bioDataList==>"+bioDataList);
			HashMap<String, ArrayList<ArrayList<String>>> userData = getMapFromList(bioDataList);

			//Block to read List and filter based on user Id's END
			//Calculate Login and Logout Time based on map generated above.START
			//Above map(userData) contains entries based on id's as keys. It contains all entries present in file as arrayList
			Set<Entry<String, ArrayList<ArrayList<String>>>> keySet = userData.entrySet();
			Iterator<Entry<String, ArrayList<ArrayList<String>>>> iterator = keySet.iterator();
			while(iterator.hasNext())
			{
				Entry<String, ArrayList<ArrayList<String>>> me = iterator.next();
				String userIdinMap = me.getKey();
				ArrayList<ArrayList<String>> data = me.getValue();
				User user = userService.findById(Integer.parseInt(userIdinMap));
				if(user == null){
					List<BiometricData> biometricDataList = biometricDataService.findAll();
					model.addAttribute("biometricDataList", biometricDataList);
					model.addAttribute("biometricData", biometricData);
					model.addAttribute("edit", false);
					model.addAttribute("error", "User with ID=>"+userIdinMap +" not found. Please add User details before uploading biometric data. ");
					model.addAttribute("loggedinuser", getPrincipal());
					//return "viewBiometricData";
				} else {
					if(biometricData.isMultipleShift()){
						getMultipleShiftData(data);
					} else {
						ArrayList<String> login = data.get(0);
						ArrayList<String> logout = data.get(data.size()-1);
						UserBiometricData biometricDataSS = getSingleShiftData(login, logout, user);
						userBiometricDataService.save(biometricDataSS);
					}
				}
			}
			//Calculate Login and Logout Time based on map generated above.END

			//END

			/*}*/
		} else {
			List<BiometricData> biometricDataList = biometricDataService.findAll();
			model.addAttribute("biometricDataList", biometricDataList);
			model.addAttribute("biometricData", biometricData);
			model.addAttribute("edit", false);
			model.addAttribute("error", "File Format is not supported. Please select correct file. Allowed file types are .csv OR .dat OR .xls OR .xlsx");
			model.addAttribute("loggedinuser", getPrincipal());
			return "viewBiometricData";
		}

		try{
			biometricDataService.saveDocument(biometricData);
		}catch(TransientObjectException e){
			List<BiometricData> biometricDataList = biometricDataService.findAll();
			model.addAttribute("biometricDataList", biometricDataList);
			model.addAttribute("biometricData", biometricData);
			model.addAttribute("edit", false);
			model.addAttribute("error", "Biometric Data with same month and year is present.. Please delete existing data" +
					" and upload again if you want to change.");
			model.addAttribute("loggedinuser", getPrincipal());
			return "viewBiometricData";
		}

		model.addAttribute("success", "Biometric Data saved Successfully..");
		model.addAttribute("loggedinuser", getPrincipal());
		return "addBiometricSuccess";
	}

	@RequestMapping(value = { "/download-document-{docId}" }, method = RequestMethod.GET)
	public String downloadDocument( @PathVariable int docId, HttpServletResponse response) throws IOException {
		BiometricData document = biometricDataService.findById(docId);
		response.setContentType(document.getType());
		response.setContentLength(document.getContent().length);
		response.setHeader("Content-Disposition","attachment; filename=\"" + document.getName() +"\"");

		FileCopyUtils.copy(document.getContent(), response.getOutputStream());

		return "viewBiometricData";
	}

	//Attendance Related START
	
	@RequestMapping(value = { "/view-searchAttendance" }, method = RequestMethod.GET)
	public String viewAttendance(ModelMap model) {
		List<UserBiometricData> userAttendanceLog = new ArrayList<UserBiometricData>();
		model.addAttribute("userAttendanceLog", userAttendanceLog);
		model.addAttribute("userBiometricData", new UserBiometricData());
		model.addAttribute("loggedinuser", getPrincipal());
		return "viewUserAttendanceData";
	}
	
	
	@RequestMapping(value = { "/view-searchAttendance" }, method = RequestMethod.POST)
	public String searchAttendance(@Valid UserBiometricData userBiometricData, BindingResult result,
			ModelMap model) throws IOException {
		List<UserBiometricData> userAttendanceLog = userBiometricDataService.findByYearAndMonth(userBiometricData.getYear(),userBiometricData.getMonth(),userBiometricData.getUserId());
		model.addAttribute("userAttendanceLog", userAttendanceLog);
		model.addAttribute("userBiometricData", new UserBiometricData());
		model.addAttribute("loggedinuser", getPrincipal());
		return "viewUserAttendanceData";
	}
	//Attendance Related END
	

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

	private HashMap<String, ArrayList<ArrayList<String>>> getMapFromList(ArrayList< ArrayList<String>> bioDataList){
		ArrayList<String> loginData= new ArrayList<String>();
		ArrayList<String> logoutData= new ArrayList<String>();
		ArrayList<ArrayList<String>> nextData = new ArrayList<ArrayList<String>>();
		HashMap<String, ArrayList<ArrayList<String>>> userData= new HashMap<String, ArrayList<ArrayList<String>>>();
		String userId=null;
		for (int i=0; i<bioDataList.size(); i++) {
			nextData = new ArrayList<ArrayList<String>>();
			loginData= bioDataList.get(i);
			logoutData= new ArrayList<String>();
			userId=loginData.get(0);
			for(int j=i+1 ;j<bioDataList.size();j++){
				logoutData= bioDataList.get(j);
				String userId1=logoutData.get(0);
				if(userId1.equals(userId)){
					nextData.add(logoutData);
				}
			}
			if(nextData.size()>0){
				nextData.add(loginData);
				userData.put(userId, nextData);
				nextData = null;
			}
		}
		return userData;
	}

	private UserBiometricData getSingleShiftData(ArrayList<String> login, ArrayList<String> logout, User user){
		String loginDate = new String(login.get(1));
		String loginTime = new String(login.get(2));
		String logoutTime = new String(logout.get(2));

		String year = loginDate.substring(0,loginDate.indexOf("-"));
		String subStr = loginDate.substring(loginDate.indexOf("-")+1);
		String month = subStr.substring(0,subStr.indexOf("-"));
		String date = subStr.substring(subStr.indexOf("-")+1);

		String inHour = loginTime.substring(0,loginTime.indexOf(":"));
		subStr=loginTime.substring(loginTime.indexOf(":")+1); 
		String inMin = subStr.substring(0,subStr.indexOf(":"));

		String outHour = logoutTime.substring(0,logoutTime.indexOf(":"));
		subStr=logoutTime.substring(logoutTime.indexOf(":")+1); 
		String outMin = subStr.substring(0,subStr.indexOf(":"));

		GregorianCalendar calendar= new GregorianCalendar(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(date),
				Integer.parseInt(inHour), Integer.parseInt(inMin));

		GregorianCalendar calendar1= new GregorianCalendar(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(date),
				Integer.parseInt(outHour), Integer.parseInt(outMin));
		
		long startTime=calendar.getTimeInMillis();
		long endTime=calendar1.getTimeInMillis();
		
		long diff=startTime-endTime;
		
		long diffinMins= (diff/1000)/60;
		long workHours= diffinMins/60;
		long workMins= diffinMins%60;
		
		UserBiometricData userBiometricData = new UserBiometricData();
		userBiometricData.setDate(Integer.parseInt(date));
		userBiometricData.setMonth(Integer.parseInt(month));
		userBiometricData.setYear(Integer.parseInt(year));
		userBiometricData.setNoOfHours(workHours+"");
		userBiometricData.setNoOfMins(workMins+"");
		userBiometricData.setLoginTime(loginTime);
		userBiometricData.setLogoutTime(logoutTime);
		userBiometricData.setUserId(Integer.parseInt(login.get(0)));
		return userBiometricData;
	}

	private UserBiometricData getMultipleShiftData(ArrayList<ArrayList<String>> data){

		return null;
	}
}
