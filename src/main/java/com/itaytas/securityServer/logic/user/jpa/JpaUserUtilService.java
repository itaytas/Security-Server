package com.itaytas.securityServer.logic.user.jpa;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.itaytas.securityServer.dal.RoleDao;
import com.itaytas.securityServer.dal.UserDao;
import com.itaytas.securityServer.exception.ResourceNotFoundException;
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
	public boolean checkUsernameAvailability(String username) {
		return !userDao.existsByUsername(username);
	}

	@Override
	public boolean checkEmailAvailability(String email) {
		return !userDao.existsByEmail(email);
	}

	@Override
	public UserEntity getUserProfile(String username) {
		Optional<UserEntity> op = this.userDao.findByUsername(username);
		if (op.isPresent()) {
			return op.get();
		} else {
			throw new ResourceNotFoundException("User", "username", username);
		}
	}
    
    
}
