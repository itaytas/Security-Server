package com.itaytas.securityServer.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itaytas.securityServer.dal.UserDao;
import com.itaytas.securityServer.logic.user.UserEntity;

/**
 * Loads a user’s data given its username.
 * 
 * The first method loadUserByUsername() is used by Spring security.
 * This allows users to log in using either username or email.
 * 
 * The second method loadUserById() will be used by JWTAuthenticationFilter.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserDao userDao;
    
    @Autowired
    public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
    
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String usernameOrEmail)
            throws UsernameNotFoundException {
        // Let people login with either username or email
        UserEntity user = userDao.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() -> 
                        new UsernameNotFoundException(
                        		"User not found with username or email : " + usernameOrEmail));

        return UserPrincipal.create(user);
    }

    // This method is used by JWTAuthenticationFilter
    @Transactional
    public UserDetails loadUserById(String id) {
        UserEntity user = userDao.findById(id).orElseThrow(
            () -> new UsernameNotFoundException("User not found with id : " + id));

        return UserPrincipal.create(user);
    }
}
