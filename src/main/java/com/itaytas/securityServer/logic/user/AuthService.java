package com.itaytas.securityServer.logic.user;

import org.springframework.http.ResponseEntity;


public interface AuthService {
	
	public ResponseEntity<?> authenticateUser(String usernameOrEmail, String password);
	
	public ResponseEntity<?> registerUser(String name, String username, String email, String password);

	ResponseEntity<?> addAdmin(String name, String username, String email, String password);
	
	public void cleanup();

}
