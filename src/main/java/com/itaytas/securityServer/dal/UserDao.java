package com.itaytas.securityServer.dal;

//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.itaytas.securityServer.logic.user.UserEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDao extends PagingAndSortingRepository<UserEntity, String> {
	
	Optional<UserEntity> findByEmail(String email);

	Optional<UserEntity> findByUsernameOrEmail(String username, String email);

	List<UserEntity> findByIdIn(List<String> userIds);

	Optional<UserEntity> findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
}