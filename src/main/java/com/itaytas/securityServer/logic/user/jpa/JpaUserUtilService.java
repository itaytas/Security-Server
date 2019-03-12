package com.itaytas.securityServer.logic.user.jpa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itaytas.securityServer.aop.MyLog;
import com.itaytas.securityServer.dal.RoleDao;
import com.itaytas.securityServer.dal.UserDao;
import com.itaytas.securityServer.exception.AppException;
import com.itaytas.securityServer.exception.ResourceNotFoundException;
import com.itaytas.securityServer.logic.user.Role;
import com.itaytas.securityServer.logic.user.RoleName;
import com.itaytas.securityServer.logic.user.UserEntity;
import com.itaytas.securityServer.logic.user.UserUtilService;

@Service
public class JpaUserUtilService implements UserUtilService {
	
    private UserDao userDao;
    private RoleDao roleDao;
	
    @Autowired
    public JpaUserUtilService(UserDao userDao, RoleDao roleDao) {
		this.userDao = userDao;
		this.roleDao = roleDao;
	}

	@Override
	@Transactional(readOnly=true)
	@MyLog
	public boolean checkUsernameAvailability(String username) {
		return !userDao.existsByUsername(username);
	}

	@Override
	@Transactional(readOnly=true)
	@MyLog
	public boolean checkEmailAvailability(String email) {
		return !userDao.existsByEmail(email);
	}

	@Override
	@Transactional(readOnly=true)
	@MyLog
	public UserEntity getUserProfile(String username) {
		Optional<UserEntity> op = this.userDao.findByUsername(username);
		if (op.isPresent()) {
			return op.get();
		} else {
			throw new ResourceNotFoundException("User", "username", username);
		}
	}

	@Override
	@Transactional(readOnly=true)
	@MyLog
	public List<UserEntity> getAllUsersWithUserRole() {
		Role userRole = this.roleDao
        		.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new AppException("User Role not set."));

		Set<Role> roles = Collections.singleton(userRole);
		
		return this.userDao.findByRoles(roles);
	}
    
	@Override
	@Transactional(readOnly=true)
	@MyLog
	public List<UserEntity> getAllRoleUsers() {
		List<UserEntity> allUsers = (List<UserEntity>) this.userDao.findAll();
		List<UserEntity> usersWithUserRole = new ArrayList<>();
		Role userRole = this.roleDao
        		.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new AppException("User Role not set."));
		
		allUsers.stream().forEach((user)-> {
			if (user.getRoles().contains(userRole)) {
				usersWithUserRole.add(user);
			}
		});
		
		return usersWithUserRole;
	}
    
}
