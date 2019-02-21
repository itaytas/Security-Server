package com.itaytas.securityServer.user;

import static org.junit.Assert.assertTrue;

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
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itaytas.securityServer.api.response.ApiResponse;
import com.itaytas.securityServer.api.response.JwtAuthenticationResponse;
import com.itaytas.securityServer.api.user.SignInRequest;
import com.itaytas.securityServer.api.user.SignUpRequest;
import com.itaytas.securityServer.logic.user.AuthService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class AuthRestControllerTests {
	
	private final String SIGNIN_URL = "/signin";
	private final String SIGNUP_URL = "/signup";

	@LocalServerPort
	private int port;
	
	private String url;
	private RestTemplate restTemplate;
	private ObjectMapper jsonMapper;
	
	@Autowired
	private AuthService authService;
	
	private SignUpRequest signUpRequest;
	private SignInRequest signInRequest;

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
		this.signUpRequest = new SignUpRequest(name, username, email, password);
		this.signInRequest = new SignInRequest(username, password);
	}

	@After
	public void teardown() {
		this.authService.cleanup();
	}
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Test
	public void testServerIsBootingCorrectly() throws Exception {
	}
	
	@Test
	public void testSignInSuccussfully() throws Exception {
		HttpEntity<?> signUpRequest = new HttpEntity<>(this.signUpRequest);
		ResponseEntity<?> signUpRes = this.restTemplate.exchange(
				this.url + SIGNUP_URL,
				HttpMethod.POST,
				signUpRequest,
				ApiResponse.class);
		
		HttpEntity<?> signInRequest = new HttpEntity<>(this.signInRequest);
		ResponseEntity<?> signInRes = this.restTemplate.exchange(
				this.url + SIGNIN_URL,
				HttpMethod.POST,
				signInRequest,
				JwtAuthenticationResponse.class);

		// Another way is: res.getStatusCode().is2xxSuccessful() [boolean]
		assertTrue(signInRes.getStatusCode().value() == 200);

	}
	
	@Test
	public void testSignInWithBlankUsernameOrEmailField() throws Exception {
		HttpEntity<?> signUpRequest = new HttpEntity<>(this.signUpRequest);
		ResponseEntity<?> signUpRes = this.restTemplate.exchange(
				this.url + SIGNUP_URL,
				HttpMethod.POST,
				signUpRequest,
				ApiResponse.class);
			
		String username = "UniqueUsername";
		String email = "username@Afeka.ac.il";
		String password = "password";
		
		HttpEntity<?> signInRequest = new HttpEntity<>(this.signInRequest);
		ResponseEntity<?> signInRes = this.restTemplate.exchange(
				this.url + SIGNIN_URL,
				HttpMethod.POST,
				signInRequest,
				JwtAuthenticationResponse.class);

		// Another way is: res.getStatusCode().is2xxSuccessful() [boolean]
		assertTrue(signInRes.getStatusCode().value() == 200);

	}
	
/*	
	@Test
	public void testSignUpSuccussfully() throws Exception {
		HttpEntity<?> request = new HttpEntity<>(this.signUpRequest);
		ResponseEntity<?> res = this.restTemplate.exchange(
				this.url + SIGNUP_URL,
				HttpMethod.POST,
				request,
				ApiResponse.class);
		// Another way is: res.getStatusCode().is2xxSuccessful() [boolean]
		assertTrue(res.getStatusCode().value() == 201);
		ApiResponse apiResponse = (ApiResponse) res.getBody();
		assertTrue(apiResponse.getSuccess());
	}
	
	@Test
	public void testSignUpWithBlankNameField() throws Exception {
		String username = "UniqueUsername";
		String email = "username@Afeka.ac.il";
		String password = "password";
		SignUpRequest blankSignUpRequest = new SignUpRequest("", username, email, password);
		HttpEntity<?> request = new HttpEntity<>(blankSignUpRequest);
		
		this.exception.expect(HttpClientErrorException.class);
		this.exception.expectMessage("400"); // Bad Request
		
		ResponseEntity<?> res = this.restTemplate.exchange(
				this.url + SIGNUP_URL,
				HttpMethod.POST,
				request,
				ApiResponse.class);
	}
	
	@Test
	public void testSignUpWithBlankUsernameField() throws Exception {
		String name = "UniqueName";
		String email = "username@Afeka.ac.il";
		String password = "password";
		SignUpRequest blankSignUpRequest = new SignUpRequest(name, "", email, password);
		HttpEntity<?> request = new HttpEntity<>(blankSignUpRequest);
		
		this.exception.expect(HttpClientErrorException.class);
		this.exception.expectMessage("400"); // Bad Request
		
		ResponseEntity<?> res = this.restTemplate.exchange(
				this.url + SIGNUP_URL,
				HttpMethod.POST,
				request,
				ApiResponse.class);
	}
	
	@Test
	public void testSignUpWithBlankEmailField() throws Exception {
		String name = "UniqueName";
		String username = "UniqueUsername";
		String password = "password";
		SignUpRequest blankSignUpRequest = new SignUpRequest(name, username, "", password);
		HttpEntity<?> request = new HttpEntity<>(blankSignUpRequest);
		
		this.exception.expect(HttpClientErrorException.class);
		this.exception.expectMessage("400"); // Bad Request
		
		ResponseEntity<?> res = this.restTemplate.exchange(
				this.url + SIGNUP_URL,
				HttpMethod.POST,
				request,
				ApiResponse.class);
	}
	
	@Test
	public void testSignUpWithBlankPasswordField() throws Exception {
		String name = "UniqueName";
		String username = "UniqueUsername";
		String email = "username@Afeka.ac.il";
		SignUpRequest blankSignUpRequest = new SignUpRequest(name, username, email, "");
		HttpEntity<?> request = new HttpEntity<>(blankSignUpRequest);
		
		this.exception.expect(HttpClientErrorException.class);
		this.exception.expectMessage("400"); // Bad Request
		
		ResponseEntity<?> res = this.restTemplate.exchange(
				this.url + SIGNUP_URL,
				HttpMethod.POST,
				request,
				ApiResponse.class);
	}
	
	@Test
	public void testSignUpWithInvalidEmailFormat() throws Exception {
		String name = "UniqueName";
		String username = "UniqueUsername";
		String email = "username";
		String password = "password";
		SignUpRequest blankSignUpRequest = new SignUpRequest(name, username, email, password);
		HttpEntity<?> request = new HttpEntity<>(blankSignUpRequest);
		
		this.exception.expect(HttpClientErrorException.class);
		this.exception.expectMessage("400"); // Bad Request
		
		ResponseEntity<?> res = this.restTemplate.exchange(
				this.url + SIGNUP_URL,
				HttpMethod.POST,
				request,
				ApiResponse.class);
	}
*/	
}
