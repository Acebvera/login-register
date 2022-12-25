package com.example.loginregister.appuser;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


//Spring security
@Service
public class AppUserService implements UserDetailsService {
	
	private final static String USER_NOT_FOUND = "User with email %s not found";
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private AppUserRepository appUserRepository;

	
	public AppUserService (AppUserRepository appUserRepository) {
		this.appUserRepository = appUserRepository;
		this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
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
		
		//TODO: Send confirmation token
		
		return "Funciona";
	}

}
