package com.example.loginregister.registration.token;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.example.loginregister.appuser.AppUser;

public class ConfirmationToken {
	
	
	@SequenceGenerator(
			name = "confirmation_token_sequence",
			sequenceName = "confirmation_token_sequence",
			allocationSize = 1
	)
	@Id
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "confirmation_token_sequence"
			
	)
	private Long id;
	
	@Column(nullable = false)
	private String token;
	
	@Column(nullable = false)
	private LocalDateTime createdAt;
	
	@Column(nullable = false)
	private LocalDateTime expiresAt;
	
	private LocalDateTime confirmedAt;
	
	@ManyToOne
	@JoinColumn(
			nullable = false,
			name = "app_user_id"
	)
	private AppUser appUser; //Un usuario puede tener varios mensajes de confirmacion.
	
	public ConfirmationToken() {
	}
	
	public ConfirmationToken(String token, LocalDateTime createdAt, LocalDateTime expiresAt,
			LocalDateTime confirmedAt, AppUser appUser) {
		this.token = token;
		this.createdAt = createdAt;
		this.expiresAt = expiresAt;
		this.confirmedAt = confirmedAt;
		this.appUser = appUser;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getToken() {
		return token;
	}
	
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	
	public LocalDateTime getExpiresAt() {
		return expiresAt;
	}
	
	public LocalDateTime getConfirmedAt() {
		return confirmedAt;
	}
	
	
}
