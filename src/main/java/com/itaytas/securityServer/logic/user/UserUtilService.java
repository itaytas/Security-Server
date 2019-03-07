package com.itaytas.securityServer.logic.user;

import java.util.List;

public interface UserUtilService {
	
	boolean checkUsernameAvailability(String username);
	
	boolean checkEmailAvailability(String email);
	
	UserEntity getUserProfile(String username);
	
	List<UserEntity> getAllUsersWithUserRole();
}
