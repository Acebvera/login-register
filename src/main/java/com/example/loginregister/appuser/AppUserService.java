package com.example.loginregister.appuser;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.loginregister.registration.token.ConfirmationToken;
import com.example.loginregister.registration.token.ConfirmationTokenService;


//Spring security
@Service
public class AppUserService implements UserDetailsService {
	
	private final static String USER_NOT_FOUND = "User with email %s not found";
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private AppUserRepository appUserRepository;
	private ConfirmationTokenService confirmationTokenService;

	
	public AppUserService (AppUserRepository appUserRepository, ConfirmationTokenService confirmationTokenService) {
		this.appUserRepository = appUserRepository;
		this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
		this.confirmationTokenService = confirmationTokenService;
	}
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return appUserRepository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException(String.format(USER_NOT_FOUND, email)));
	}
	
	public String signUpUser (AppUser appUser) {
		boolean userExists = appUserRepository.findByEmail(appUser.getEmail()).isPresent();
		
		if (userExists) {
			throw new IllegalStateException("The email " + appUser.getEmail() + " is already taken." );
		}
		
		String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());
		
		appUser.setPassword(encodedPassword);
		//Guarda nuevo usuario en la base de datos
		appUserRepository.save(appUser);
		
		String token = UUID.randomUUID().toString();
		ConfirmationToken confirmationToken = new ConfirmationToken(token, LocalDateTime.now(), LocalDateTime.now().plusMinutes(15), appUser);
		confirmationTokenService.saveConfirmationToken(confirmationToken);
		
		//TODO: enviar email
		
		return token;
	}

}
