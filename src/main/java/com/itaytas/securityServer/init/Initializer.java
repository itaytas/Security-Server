package com.itaytas.securityServer.init;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itaytas.securityServer.aop.MyLog;
import com.itaytas.securityServer.dal.RoleDao;
import com.itaytas.securityServer.logic.user.Role;
import com.itaytas.securityServer.logic.user.RoleName;

@Component
public class Initializer {

	private Role admimRole;
	private Role userRole;

	private RoleDao roleDao;

	@Autowired
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	@PostConstruct
	public void init() throws Exception {
		createRole();
	}

	@MyLog
	private void createRole() {
		this.admimRole = new Role(RoleName.ROLE_ADMIN);
		this.userRole = new Role(RoleName.ROLE_USER);

		if (!roleDao.existsByName(RoleName.ROLE_ADMIN)) {
			roleDao.save(this.admimRole);
		}

		if (!roleDao.existsByName(RoleName.ROLE_USER)) {
			roleDao.save(this.userRole);
		}

	}

}
