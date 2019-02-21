package com.itaytas.securityServer.logic.user;


public interface UserUtilService {
	
	boolean checkUsernameAvailability(String username);
	
	boolean checkEmailAvailability(String email);
	
	UserEntity getUserProfile(String username);
}
