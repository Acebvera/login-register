package com.example.loginregister.registration;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.loginregister.appuser.AppUser;
import com.example.loginregister.appuser.AppUserRole;
import com.example.loginregister.appuser.AppUserService;
import com.example.loginregister.email.EmailSender;
import com.example.loginregister.registration.token.ConfirmationToken;
import com.example.loginregister.registration.token.ConfirmationTokenService;

@Service
public class RegistrationService {
	
	private AppUserService appUserService;
	private EmailValidator emailValidator;
	private ConfirmationTokenService confirmationTokenService;
	private EmailSender emailSender;
	
	public RegistrationService(AppUserService appUserService, EmailValidator emailValidator, EmailSender emailSender) {
		this.appUserService = appUserService;
		this.emailValidator = emailValidator;
		this.emailSender = emailSender;
	}
	
	public String register(RegistrationRequest request) {
		boolean isEmailValid = emailValidator.test(request.getEmail());
		
		if (!isEmailValid) {
			throw new IllegalStateException("The email " + request.getEmail() + " is not vaild.");
		}
		
		String token = appUserService.signUpUser(new AppUser(request.getName(), request.getSurname(), request.getEmail(), request.getPassword(), AppUserRole.USER));
		String link = "http://localhost:8080/api/v1/registration/confirm?token=" + token;
		
		emailSender.send(request.getEmail(), buildEmail(request.getName(), link));
		
		return token;
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
	
	private String buildEmail(String name, String link) {
		String message = "Message";
		
		return message;
	}
}
