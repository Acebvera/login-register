package com.example.loginregister.security.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import com.example.loginregister.appuser.AppUserService;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;


import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	private final AppUserService appUserService = new AppUserService();
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
}
