package com.itaytas.securityServer.api;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itaytas.securityServer.aop.MyLog;
import com.itaytas.securityServer.api.response.PagedResponse;
import com.itaytas.securityServer.api.user.UserIdentityAvailability;
import com.itaytas.securityServer.api.user.UserProfile;
import com.itaytas.securityServer.api.user.UserSummary;
import com.itaytas.securityServer.config.AppUtilsAndConstants;
import com.itaytas.securityServer.logic.user.UserEntity;
import com.itaytas.securityServer.logic.user.UserUtilService;
import com.itaytas.securityServer.security.CurrentUser;
import com.itaytas.securityServer.security.UserPrincipal;

@RestController
@RequestMapping("/api")
public class UserUtilRestConroller {
	
	private UserUtilService userUtilService;
	
	@Autowired
	public UserUtilRestConroller(UserUtilService userUtilService) {
		this.userUtilService = userUtilService;
	}
	
	@MyLog
	@GetMapping("/user/me")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public UserSummary getCurrentUser(@CurrentUser UserPrincipal currentUser) {
		UserEntity user = this.userUtilService.getUserProfile(currentUser.getUsername());

		Set<String> currentUserRoles = new HashSet<>();
		user.getRoles().stream().forEach((role) -> currentUserRoles.add(role.getName().toString()));
		
		UserSummary userSummary = new UserSummary(
				user.getId(),
				user.getUsername(),
				user.getName(),
				user.getEmail(),
				currentUserRoles,
				user.getCreatedAt());
		
        return userSummary;
    }

	@MyLog
    @GetMapping("/user/checkUsernameAvailability/{username}")
    public UserIdentityAvailability checkUsernameAvailability(@PathVariable(value = "username") String username) {
        Boolean isAvailable = this.userUtilService.checkUsernameAvailability(username);
        return new UserIdentityAvailability(isAvailable);
    }
	
	@MyLog
    @GetMapping("/user/checkEmailAvailability/{email}")
    public UserIdentityAvailability checkEmailAvailability(@Email @PathVariable(value = "email") String email) {
        Boolean isAvailable = this.userUtilService.checkEmailAvailability(email);
        return new UserIdentityAvailability(isAvailable);
    }

    @MyLog
    @GetMapping("/users/{username}")
    @PreAuthorize("hasRole('ADMIN')")
    public UserProfile getUserProfile(
    		@CurrentUser UserPrincipal currentUser,
    		@PathVariable(value = "username") String username) {
        UserEntity user = this.userUtilService.getUserProfile(username);

        UserProfile userProfile = 
        		new UserProfile(user.getId(), user.getUsername(), user.getName(), user.getCreatedAt());

        return userProfile;
    }
    
    @MyLog
    @GetMapping("/users/all")
    @PreAuthorize("hasRole('ADMIN')")
    public PagedResponse<?> getAllUser(@CurrentUser UserPrincipal currentUser,
    		@RequestParam(name = "size", required = false, defaultValue = AppUtilsAndConstants.DEFAULT_PAGE_SIZE) int size,
			@RequestParam(name = "page", required = false, defaultValue = AppUtilsAndConstants.DEFAULT_PAGE_NUMBER) int page) {
        return this.userUtilService.adminRequestToGetAllRoleUsers(page, size);
    }
}
