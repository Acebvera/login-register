package com.example.loginregister.registration;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.loginregister.appuser.AppUser;
import com.example.loginregister.appuser.AppUserRole;
import com.example.loginregister.appuser.AppUserService;
import com.example.loginregister.registration.token.ConfirmationToken;
import com.example.loginregister.registration.token.ConfirmationTokenService;

@Service
public class RegistrationService {
	
	private AppUserService appUserService;
	private EmailValidator emailValidator;
	private ConfirmationTokenService confirmationTokenService; //
	
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
	
	@Transactional
	public String confirmToken(String token) {
		
		ConfirmationToken confirmationToken = confirmationTokenService.getToken(token).orElseThrow(() -> new IllegalStateException("Token not found."));
		
		if (confirmationToken.getConfirmedAt() != null) {
			throw new IllegalStateException("Email already confirmed.");
		}
		
		LocalDateTime expiredAt = confirmationToken.getExpiresAt();
		
		if (expiredAt.isBefore(LocalDateTime.now())) {
			throw new IllegalStateException("Token expired.");
		}
		
		confirmationTokenService.setConfirmedAt(token);
		appUserService.enableAppUser(confirmationToken.getAppUser().getEmail());
		
		return "Confirmed.";
	}
}
