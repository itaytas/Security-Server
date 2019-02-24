package com.itaytas.securityServer.log;

import javax.annotation.PostConstruct;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itaytas.securityServer.api.user.SignInRequest;
import com.itaytas.securityServer.api.user.SignUpRequest;
import com.itaytas.securityServer.logic.log.LogService;
import com.itaytas.securityServer.logic.user.AuthService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class LogRestControllerTests {
	
	private final String ADD_URL = "/add"; 
	
	@LocalServerPort
	private int port;
	
	private String url;
	private RestTemplate restTemplate;
	private ObjectMapper jsonMapper;
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private LogService logService;
	
	@PostConstruct
	public void init() {
		this.jsonMapper = new ObjectMapper();
		this.restTemplate = new RestTemplate();
		this.url = "http://localhost:" + port + "/api/auth";		
		System.err.println(this.url);
	}
	
	@Before
	public void setup() {
		String name = "Fname Lname";
		String username = "UniqueUsername";
		String email = "username@Afeka.ac.il";
		String password = "password";
		//SignUpRequest signUpRequest = new SignUpRequest(name, username, email, password);
		SignInRequest signInRequest = new SignInRequest(username, password);
		this.authService.registerUser(name, username, email, password);
		this.authService.authenticateUser(username, password);
	}

	@After
	public void teardown() {
	}
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Test
	public void testServerIsBootingCorrectly() throws Exception {
	}
	
}
