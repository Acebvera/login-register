package com.example.loginregister.registration;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {
	//When the client sends a request capture
	
	private final String firstName = "";
	private final String lastName = "";
	private final String email = "";
	private final String password = "";

}
