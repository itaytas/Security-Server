package com.itaytas.securityServer.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itaytas.securityServer.aop.MyLog;
import com.itaytas.securityServer.api.user.SignInRequest;
import com.itaytas.securityServer.api.user.SignUpRequest;
import com.itaytas.securityServer.logic.user.AuthService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthRestController {
	
	private AuthService authService;
	
	@Autowired
	public AuthRestController(AuthService authService) {
		super();
		this.authService = authService;
	}
	
	@MyLog
	@PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
		return this.authService.registerUser(
				signUpRequest.getName(),
				signUpRequest.getUsername(),
				signUpRequest.getEmail(),
				signUpRequest.getPassword());
	}

	@MyLog
	@PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody SignInRequest loginRequest) {
		return this.authService.authenticateUser(
				loginRequest.getUsernameOrEmail(), loginRequest.getPassword());
	}
}