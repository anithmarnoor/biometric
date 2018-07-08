package com.siddhrans.biometric.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.siddhrans.biometric.dao.impl.HibernateTokenRepositoryImpl;
import com.siddhrans.biometric.model.Designation;
import com.siddhrans.biometric.model.User;
import com.siddhrans.biometric.model.UserProfile;
import com.siddhrans.biometric.service.DesignationService;
import com.siddhrans.biometric.service.UserProfileService;
import com.siddhrans.biometric.service.UserService;
import com.siddhrans.biometric.validator.FileValidator;

@Controller
@RequestMapping("/")
@SessionAttributes("roles")
public class AppController {

	static final Logger logger = LoggerFactory.getLogger(HibernateTokenRepositoryImpl.class);

	@Autowired
	HttpServletRequest request;

	@Autowired
	UserService userService;

	@Autowired
	UserProfileService userProfileService;

	@Autowired
	private DesignationService designationService;

	@Autowired
	MessageSource messageSource;

	@Autowired
	PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;

	@Autowired
	AuthenticationTrustResolver authenticationTrustResolver;

	@Autowired
	FileValidator fileValidator;

	@InitBinder("biometricData")
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(fileValidator);
	}

	/**
	 * This method will list all existing users.
	 */
	@RequestMapping(value = { "/list" }, method = RequestMethod.GET)
	public String listUsers(ModelMap model) {

		List<User> users = userService.findAllUsers();
		model.addAttribute("users", users);
		User profile = userService.findByUserName(getPrincipal());

		model.addAttribute("profile", profile);
		model.addAttribute("loggedinuser", getPrincipal());
		return "userslist";
	}

	/**
	 * This method will list all existing users.
	 */
	@RequestMapping(value = { "/inactiveUsersList" }, method = RequestMethod.GET)
	public String listOfInactiveUsers(ModelMap model) {

		List<User> users = userService.findAllInactiveUsers();
		model.addAttribute("users", users);
		User profile = userService.findByUserName(getPrincipal());

		model.addAttribute("profile", profile);
		model.addAttribute("inactiveUsers", "Yes");
		model.addAttribute("loggedinuser", getPrincipal());
		return "userslist";
	}

	/**
	 * This method will list all existing users.
	 */
	@RequestMapping(value = { "/", "/myProfile" }, method = RequestMethod.GET)
	public String myProfile(ModelMap model) {

		User profile = userService.findByUserName(getPrincipal());

		model.addAttribute("profile", profile);
		model.addAttribute("loggedinuser", getPrincipal());
		return "myProfile";
	}

	/**
	 * This method will list all existing users.
	 */
	@RequestMapping(value = { "/getCompleteUserProfile" }, method = RequestMethod.POST)
	public String myCompleteUserProfile(ModelMap model) {
		String userId = request.getParameter("userId");
		User profile = userService.findById(Integer.parseInt(userId));

		model.addAttribute("profile", profile);
		model.addAttribute("loggedinuser", getPrincipal());
		return "myProfile";
	}

	/**
	 * This method will provide the medium to add a new user.
	 */
	@RequestMapping(value = { "/newuser" }, method = RequestMethod.GET)
	public String newUser(ModelMap model) {
		User user = new User();
		List<Designation> designations = designationService.findAllDesignations();
		logger.debug("designations size is "+designations.size());
		model.addAttribute("designations", designations);
		model.addAttribute("user", user);
		model.addAttribute("edit", false);
		model.addAttribute("roleChange", true);
		User profile = userService.findByUserName(getPrincipal());

		model.addAttribute("profile", profile);
		model.addAttribute("loggedinuser", getPrincipal());
		return "registration";
	}

	/**
	 * This method will be called on form submission, handling POST request for
	 * saving user in database. It also validates the user input
	 */
	@RequestMapping(value = { "/newUser" }, method = RequestMethod.POST)
	public String saveUser(@Valid User user, BindingResult result,
			ModelMap model) {

		List<Designation> designations = designationService.findAllDesignations();
		logger.debug("designations size is "+designations.size());
		model.addAttribute("designations", designations);
		if (result.hasErrors()) {
			model.addAttribute("user", user);
			model.addAttribute("edit", false);
			model.addAttribute("roleChange", true);

			User profile = userService.findByUserName(getPrincipal());

			model.addAttribute("profile", profile);
			model.addAttribute("loggedinuser", getPrincipal());
			return "registration";
		}

		/*
		 * Preferred way to achieve uniqueness of field [userName] should be implementing custom @Unique annotation 
		 * and applying it on field [userName] of Model class [User].
		 * 
		 * Below mentioned peace of code [if block] is to demonstrate that you can fill custom errors outside the validation
		 * framework as well while still using internationalized messages.
		 * 
		 */
		if(!userService.isUserNameUnique(user.getId(), user.getUserName())){
			FieldError userNameError =new FieldError("user","userName",messageSource.getMessage("non.unique.userName", new String[]{user.getUserName()}, Locale.getDefault()));
			result.addError(userNameError);
			return "registration";
		}
		if(!userService.isPhoneNoUnique(user.getId(), user.getPhoneNo())){
			FieldError phoneNoError =new FieldError("user","phoneNo",messageSource.getMessage("non.unique.phoneNo", new String[]{user.getPhoneNo()}, Locale.getDefault()));
			result.addError(phoneNoError);
			return "registration";
		}
		/*if(!userService.isDlNoUnique(user.getId(), user.getDlNo())){
			FieldError dlNoError =new FieldError("user","dlNo",messageSource.getMessage("non.unique.dlNo", new String[]{user.getDlNo()}, Locale.getDefault()));
			result.addError(dlNoError);
			return "registration";
		}*/
		user.setStatus("Active");
		userService.saveUser(user);

		model.addAttribute("success", "User " + user.getFirstName() + " "+ user.getLastName() + " registered successfully");
		User profile = userService.findByUserName(getPrincipal());

		model.addAttribute("profile", profile);
		model.addAttribute("loggedinuser", getPrincipal());
		model.addAttribute("url", "list");
		return "result";
	}


	/**
	 * This method will provide the medium to update an existing user.
	 */
	@RequestMapping(value = { "/editUserView" }, method = RequestMethod.POST)
	public String editUserView( ModelMap model) {
		String userId = request.getParameter("userId");
		User user = userService.findById(Integer.parseInt(userId));
		List<Designation> designations = designationService.findAllDesignations();
		logger.debug("designations size is in editUserView===>"+designations.size());
		model.addAttribute("designations", designations);
		model.addAttribute("editUser", user);
		model.addAttribute("edit", true);
		model.addAttribute("roleChange", false);
		User profile = userService.findByUserName(getPrincipal());

		model.addAttribute("profile", profile);
		model.addAttribute("loggedinuser", getPrincipal());
		return "editUser";
	}

	/**
	 * This method will be called on form submission, handling POST request for
	 * updating user in database. It also validates the user input
	 */
	@RequestMapping(value = { "/editUser" }, method = RequestMethod.POST)
	public String updateUser(@Valid User editUser, BindingResult result,
			ModelMap model) {
		logger.debug("Anith New Error in edit is "+result.toString()+" \n\n Error message END");
		if (result.hasErrors()) {
			List<Designation> designations = designationService.findAllDesignations();
			logger.debug("designations size is "+designations.size());
			model.addAttribute("designations", designations);
			model.addAttribute("user", editUser);
			model.addAttribute("edit", true);
			model.addAttribute("roleChange", false);
			User profile = userService.findByUserName(getPrincipal());

			model.addAttribute("profile", profile);
			model.addAttribute("loggedinuser", getPrincipal());
			return "editUser";
		}

		/*//Uncomment below 'if block' if you WANT TO ALLOW UPDATING USER_NAME in UI which is a unique key to a User.
        if(!userService.isUserUserNameUnique(user.getId(), user.getUserName())){
            FieldError ssoError =new FieldError("user","userName",messageSource.getMessage("non.unique.userName", new String[]{user.getUserName()}, Locale.getDefault()));
            result.addError(ssoError);
            return "registration";
        }*/


		userService.updateUser(editUser);

		model.addAttribute("success", "User " + editUser.getFirstName() + " "+ editUser.getLastName() + " updated successfully");
		User profile = userService.findByUserName(getPrincipal());

		model.addAttribute("profile", profile);
		model.addAttribute("loggedinuser", getPrincipal());
		model.addAttribute("url", "list");
		return "result";
	}


	/**
	 * This method will delete an user by it's USER_NAME value.
	 */
	@RequestMapping(value = { "/deleteUser" }, method = RequestMethod.POST)
	public String deleteUser(ModelMap model) {
		String userId = request.getParameter("userId");
		userService.deleteUserById(Integer.parseInt(userId));
		model.addAttribute("success", "User is Deleted successfully.");
		return "redirect:/list";
	}


	/**
	 * This method will provide UserProfile list to views
	 */
	@ModelAttribute("roles")
	public List<UserProfile> initializeProfiles() {
		return userProfileService.findAll();
	}

	/**
	 * This method handles Access-Denied redirect.
	 */
	@RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
	public String accessDeniedPage(ModelMap model) {
		model.addAttribute("loggedinuser", getPrincipal());
		return "accessDenied";
	}

	/**
	 * This method handles login GET requests.
	 * If users is already logged-in and tries to goto login page again, will be redirected to list page.
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage(ModelMap model) {
		model.addAttribute("loggedinuser", "Guest");
		if (isCurrentAuthenticationAnonymous()) {
			return "login";
		} else {
			return "redirect:/myProfile";  
		}
	}

	/**
	 * This method handles logout requests.
	 * Toggle the handlers if you are RememberMe functionality is useless in your app.
	 */
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null){    
			//new SecurityContextLogoutHandler().logout(request, response, auth);
			persistentTokenBasedRememberMeServices.logout(request, response, auth);
			SecurityContextHolder.getContext().setAuthentication(null);
		}
		return "redirect:/login?logout";
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
	 * This method returns true if users is already authenticated [logged-in], else false.
	 */
	private boolean isCurrentAuthenticationAnonymous() {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authenticationTrustResolver.isAnonymous(authentication);
	}


}