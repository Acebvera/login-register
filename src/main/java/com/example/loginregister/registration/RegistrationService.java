package com.example.loginregister.registration;

import org.springframework.stereotype.Service;

import com.example.loginregister.appuser.AppUser;
import com.example.loginregister.appuser.AppUserRole;
import com.example.loginregister.appuser.AppUserService;

@Service
public class RegistrationService {
	
	private AppUserService appUserService;
	private EmailValidator emailValidator;
	
	public RegistrationService(AppUserService appUserService, EmailValidator emailValidator) {
		this.appUserService = appUserService;
		this.emailValidator = emailValidator;
	}
	
	public String register(RegistrationRequest request) {
		boolean isEmailValid = emailValidator.test(request.getEmail());
		
		if (!isEmailValid) {
			throw new IllegalStateException("The email " + request.getEmail() + " is not vaild.");
		}
		
		return appUserService.signUpUser(new AppUser(request.getName(), request.getSurname(), request.getEmail(), request.getPassword(), AppUserRole.USER));
	}
}
