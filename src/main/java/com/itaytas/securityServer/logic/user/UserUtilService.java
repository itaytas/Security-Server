
package com.itaytas.securityServer.logic.user;

import java.util.List;

import com.itaytas.securityServer.api.response.PagedResponse;

public interface UserUtilService {
	
	boolean checkUsernameAvailability(String username);
	
	boolean checkEmailAvailability(String email);
	
	UserEntity getUserProfile(String username);
	
	List<UserEntity> getAllUsersWithUserRole();

	List<UserEntity> getAllRoleUsers();
	
	PagedResponse<UserEntity> adminRequestToGetAllRoleUsers(int page, int size);
}
