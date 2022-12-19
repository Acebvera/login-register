package com.example.loginregister.appuser;

import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface AppUserRepository {
	//Find user by email
	Optional<AppUser> findByEmail(String email);
}