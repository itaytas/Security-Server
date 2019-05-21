package com.itaytas.securityServer.init;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itaytas.securityServer.aop.MyLog;
import com.itaytas.securityServer.dal.RoleDao;
import com.itaytas.securityServer.logic.script.ScriptEntity;
import com.itaytas.securityServer.logic.script.ScriptService;
import com.itaytas.securityServer.logic.user.AuthService;
import com.itaytas.securityServer.logic.user.Role;
import com.itaytas.securityServer.logic.user.RoleName;

@Component
public class Initializer {

	private RoleDao roleDao;
	private ScriptService scriptService;
	private AuthService authService;

	@Autowired
	public Initializer(RoleDao roleDao, ScriptService scriptService, AuthService authService) {
		super();
		this.roleDao = roleDao;
		this.scriptService = scriptService;
		this.authService = authService;
	}

	@PostConstruct
	public void init() throws Exception {
		createDefaultRoles();
		createDefaultScripts();
		createDefaulAdmin();
	}

	@MyLog
	private void createDefaulAdmin() {
		this.authService.addAdmin("Admin", "admin", "itayfinalprojectafeka@gmail.com", "Admin4FinalProject");
	}

	@MyLog
	private void createDefaultScripts() {
		createNumLogsScript();
	}

	@MyLog
	private void createNumLogsScript() {
		List<String> attacksNames = new ArrayList<>();
		attacksNames.add("SqlInjection");

		Map<String, Object> numLogsDetails = new HashMap<>();
		numLogsDetails.put("numLogs", 3);
		numLogsDetails.put("numDaysAgo", 1);

		ScriptEntity scriptEntity = new ScriptEntity("NumLogs", attacksNames, true, numLogsDetails);
		this.scriptService.addNewScript(scriptEntity);
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
