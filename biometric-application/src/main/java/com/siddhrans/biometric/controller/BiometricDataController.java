package com.siddhrans.biometric.controller;

import java.io.IOException;
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
import org.springframework.web.multipart.MultipartFile;

import com.siddhrans.biometric.model.BiometricData;
import com.siddhrans.biometric.model.FileBucket;
import com.siddhrans.biometric.service.BiometricDataService;

@Controller
@RequestMapping("/")
@SessionAttributes("roles")
public class BiometricDataController {


	static final Logger logger = LoggerFactory.getLogger(PaySlipController.class);

	@Autowired
	BiometricDataService biometricDataService;

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
		return "addBiometricData";
	}
	
	/**
	 * This method will provide the medium to add a Salary Division Details.
	 * @throws IOException 
	 */
	@RequestMapping(value = { "/view-BiometricData" }, method = RequestMethod.POST)
	public String saveBiometricData(@Valid BiometricData biometricData, BindingResult result,
			ModelMap model) throws IOException {
		if (result.hasErrors()) {
			model.addAttribute("biometricData", biometricData);
			model.addAttribute("edit", false);
			model.addAttribute("loggedinuser", getPrincipal());
			return "addBiometricData";
		}
		MultipartFile multipartFile = biometricData.getFile();
		
		biometricData.setName(multipartFile.getOriginalFilename());
		biometricData.setType(multipartFile.getContentType());
		biometricData.setContent(multipartFile.getBytes());
		
		biometricDataService.saveDocument(biometricData);
		model.addAttribute("success", "Biometric Data saves Successfully..");
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
