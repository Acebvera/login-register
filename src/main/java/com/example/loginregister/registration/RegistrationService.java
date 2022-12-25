package com.example.loginregister.registration;

import org.springframework.stereotype.Service;

@Service
public class RegistrationService {
	
	private EmailValidator emailValidator;
	
	public String register(RegistrationRequest request) {
		boolean isEmailValid = emailValidator.test(request.getEmail());
		return "Funciona";
	}

	public RegistrationService(EmailValidator emailValidator) {
		this.emailValidator = emailValidator;
	}
	
	

}
