package com.example.loginregister.registration;

import java.util.Objects;

public class RegistrationRequest {
	
	private String name = "";
	private String surname = "";
	private String email = "";
	private String password = "";
	
	public RegistrationRequest(String name, String surname, String email, String password) {
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, name, password, surname);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RegistrationRequest other = (RegistrationRequest) obj;
		return Objects.equals(email, other.email) && Objects.equals(name, other.name)
				&& Objects.equals(password, other.password) && Objects.equals(surname, other.surname);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RegistrationRequest [name=");
		builder.append(name);
		builder.append(", surname=");
		builder.append(surname);
		builder.append(", email=");
		builder.append(email);
		builder.append(", password=");
		builder.append(password);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
