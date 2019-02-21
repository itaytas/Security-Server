package com.itaytas.securityServer.logic.user.jpa;

import java.net.URI;
import java.util.Collections;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.itaytas.securityServer.aop.MyLog;
import com.itaytas.securityServer.api.response.ApiResponse;
import com.itaytas.securityServer.api.response.JwtAuthenticationResponse;
import com.itaytas.securityServer.dal.RoleDao;
import com.itaytas.securityServer.dal.UserDao;
import com.itaytas.securityServer.exception.AppException;
import com.itaytas.securityServer.logic.user.AuthService;
import com.itaytas.securityServer.logic.user.Role;
import com.itaytas.securityServer.logic.user.RoleName;
import com.itaytas.securityServer.logic.user.UserEntity;
import com.itaytas.securityServer.security.JwtTokenProvider;

@Service
public class JpaAuthService implements AuthService{
	
    private AuthenticationManager authenticationManager;
    private UserDao userDao;
    private RoleDao roleDao;
    private PasswordEncoder passwordEncoder;
    private JwtTokenProvider tokenProvider;
	
    @Autowired
	public JpaAuthService(AuthenticationManager authenticationManager, UserDao userDao, RoleDao roleDao,
			PasswordEncoder passwordEncoder, JwtTokenProvider tokenProvider) {
		this.authenticationManager = authenticationManager;
		this.userDao = userDao;
		this.roleDao = roleDao;
		this.passwordEncoder = passwordEncoder;
		this.tokenProvider = tokenProvider;
	}
    
	@Override
	@Transactional
	@MyLog
	public ResponseEntity<?> authenticateUser(String usernameOrEmail, String password) {
		Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(usernameOrEmail, password));

		SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
	}

	@Override
	@Transactional
	@MyLog
	public ResponseEntity<?> registerUser(String name, String username, String email, String password) {
		if(userDao.existsByUsername(username)) {
            return new ResponseEntity(
            			new ApiResponse(false, "Username is already taken!"),
            			HttpStatus.BAD_REQUEST);
        }

        if(userDao.existsByEmail(email)) {
            return new ResponseEntity(
            			new ApiResponse(false, "Email Address already in use!"),
            			HttpStatus.BAD_REQUEST);
        }
        
     // Creating user's account
        UserEntity user = new UserEntity(name, username, email, password);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role userRole = roleDao
			        		.findByName(RoleName.ROLE_USER)
			                .orElseThrow(() -> new AppException("User Role not set."));

        user.setRoles(Collections.singleton(userRole));

        UserEntity result = userDao.save(user);
        
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/api/users/{username}")
                .buildAndExpand(result.getUsername())
                .toUri();

        return ResponseEntity
        		.created(location)
        		.body(new ApiResponse(true, "User registered successfully"));

	}

	@Override
	public void cleanup() {
		this.userDao.deleteAll();
	}

}
