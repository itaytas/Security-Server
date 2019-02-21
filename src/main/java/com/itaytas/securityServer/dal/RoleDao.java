package com.itaytas.securityServer.dal;

//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.itaytas.securityServer.logic.user.Role;
import com.itaytas.securityServer.logic.user.RoleName;

import java.util.Optional;

@Repository
public interface RoleDao extends PagingAndSortingRepository<Role, String> {
    
	Optional<Role> findByName(RoleName roleName);
	
	Boolean existsByName(RoleName roleName);
}
