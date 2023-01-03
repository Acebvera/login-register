package com.example.loginregister.registration.token;

import org.springframework.stereotype.Service;
import java.util.Optional;
import java.time.LocalDateTime;

@Service
public class ConfirmationTokenService {
	
	private ConfirmationTokenRepository confirmationTokenRepository;
	
	public ConfirmationTokenService(ConfirmationTokenRepository confirmationTokenRepository) {
		this.confirmationTokenRepository = confirmationTokenRepository;
	}


	public void saveConfirmationToken(ConfirmationToken token) {
		confirmationTokenRepository.save(token);
	}

	public Optional<ConfirmationToken> getToken(String token) {
        return confirmationTokenRepository.findByToken(token);
    }
	
	public int setConfirmedAt(String token) {
		return confirmationTokenRepository.updateConfirmedAt(token, LocalDateTime.now());
	}
	
	
}
