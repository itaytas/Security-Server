package com.itaytas.securityServer.init;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itaytas.securityServer.aop.MyLog;
import com.itaytas.securityServer.dal.RoleDao;
import com.itaytas.securityServer.dal.ScriptDao;
import com.itaytas.securityServer.logic.script.ScriptEntity;
import com.itaytas.securityServer.logic.user.Role;
import com.itaytas.securityServer.logic.user.RoleName;

@Component
public class Initializer {

	private RoleDao roleDao;
	private ScriptDao scriptDao;

	@Autowired
	public Initializer(RoleDao roleDao, ScriptDao scriptDao) {
		super();
		this.roleDao = roleDao;
		this.scriptDao = scriptDao;
	}

	@PostConstruct
	public void init() throws Exception {
		createDefaultRoles();
		createDefaultScripts();
	}

	@MyLog
	private void createDefaultScripts() {
		createSqlInjectionScript("sqlInjection", true, 2);
	}

	private void createSqlInjectionScript(String attackName, boolean active, int numLogs) {
		Map<String, Object> sqlInjection1Details = new HashMap<>();
		sqlInjection1Details.put("numLogs", numLogs);
		
		ScriptEntity sqlInjection1 = new ScriptEntity(attackName, active, sqlInjection1Details);
		
		if (this.scriptDao.findByAttackNameAndDetails(
				sqlInjection1.getAttackName(), sqlInjection1Details) == null) {
			this.scriptDao.save(sqlInjection1);
		}
	}

	@MyLog
	private void createDefaultRoles() {
		Role admimRole = new Role(RoleName.ROLE_ADMIN);
		Role userRole = new Role(RoleName.ROLE_USER);

		if (!roleDao.existsByName(RoleName.ROLE_ADMIN)) {
			roleDao.save(admimRole);
		}

		if (!roleDao.existsByName(RoleName.ROLE_USER)) {
			roleDao.save(userRole);
		}
	}
	
	
	
}
