package com.itaytas.securityServer.api;

import javax.validation.constraints.Email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itaytas.securityServer.aop.MyLog;
import com.itaytas.securityServer.api.user.UserIdentityAvailability;
import com.itaytas.securityServer.api.user.UserProfile;
import com.itaytas.securityServer.api.user.UserSummary;
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
    @PreAuthorize("hasRole('USER')")
    public UserSummary getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        UserSummary userSummary = 
        		new UserSummary(currentUser.getId(), currentUser.getUsername(), currentUser.getName());
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
    public UserProfile getUserProfile(@PathVariable(value = "username") String username) {
        UserEntity user = this.userUtilService.getUserProfile(username);

        UserProfile userProfile = 
        		new UserProfile(user.getId(), user.getUsername(), user.getName(), user.getCreatedAt());

        return userProfile;
    }

}
