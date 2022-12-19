package com.example.loginregister.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.loginregister.appuser.AppUserService;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;


import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig{
	private final AppUserService appUserService = new AppUserService();
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeHttpRequests().requestMatchers("/api/v*/registration").permitAll().anyRequest().authenticated().and().formLogin();
		return null;
	}
	
}
