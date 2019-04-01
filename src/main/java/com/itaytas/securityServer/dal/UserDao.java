package com.itaytas.securityServer.dal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.itaytas.securityServer.logic.user.Role;
import com.itaytas.securityServer.logic.user.UserEntity;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface UserDao extends PagingAndSortingRepository<UserEntity, String> {
	
	Optional<UserEntity> findByEmail(String email);

	Optional<UserEntity> findByUsernameOrEmail(String username, String email);

	List<UserEntity> findByIdIn(List<String> userIds);

	Optional<UserEntity> findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
	
	List<UserEntity> findByRoles(Set<Role> roles);

	Page<UserEntity> findAllByRolesContains(Role userRole, PageRequest of);

	List<UserEntity> findAllByRolesContains(Role userRole);
}