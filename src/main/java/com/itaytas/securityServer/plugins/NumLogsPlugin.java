package com.itaytas.securityServer.plugins;

import java.time.Duration;
import java.time.Instant;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itaytas.securityServer.dal.LogDao;
import com.itaytas.securityServer.dal.RoleDao;
import com.itaytas.securityServer.dal.UserDao;
import com.itaytas.securityServer.exception.AppException;
import com.itaytas.securityServer.logic.log.LogEntity_v2;
import com.itaytas.securityServer.logic.log.LogService;
import com.itaytas.securityServer.logic.script.ScriptEntity;
import com.itaytas.securityServer.logic.user.Role;
import com.itaytas.securityServer.logic.user.RoleName;
import com.itaytas.securityServer.logic.user.UserEntity;

public class NumLogsPlugin implements SystemPlugin{

	private RoleDao roleDao;
	private UserDao userDao;
	private LogDao logDao;
	private LogService logService;
	private ObjectMapper jackson;
	
	@Autowired
	public NumLogsPlugin(RoleDao roleDao, UserDao userDao, LogDao logDao, LogService logService) {
		this.roleDao = roleDao;
		this.userDao = userDao;
		this.logDao = logDao;
		this.logService = logService;
	}
	
	@PostConstruct
	public void init() {
		this.jackson = new ObjectMapper();
	}
	
	/**
     * Step 1: Convert scriptEntity details map to NumLogs object.
     * Step 2: for each script invoke operation of plug-in using script's type [String]
     * Step 3: Get return value (Null / Alert).
     * Step 4: Check if the return value is not Null and then try to save it.
     * Step 5: I'll try to figure it out later.
     * */

	@Override
	public Object[] invokeOperation(ScriptEntity scriptEntity) throws Exception {
		Role userRole = this.roleDao
        		.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new AppException("User Role not set."));

		Set<Role> roles = Collections.singleton(userRole);
		
		NumLogs numLogsObj = this.jackson.readValue(
				this.jackson.writeValueAsString(
						scriptEntity.getDetails()), NumLogs.class);
		
		List<UserEntity> users = this.userDao.findByRoles(roles);
		Date currentDate = new Date();
		Date fromDateToCheck = Date.from(Instant.now().minus(Duration.ofDays(numLogsObj.getNumDaysAgo())));
		
		for (UserEntity user : users) {
			List<LogEntity_v2> logsByUserId = 
					this.logService.getUserMaliciousLogsBetweenDates(user.getId(), true, fromDateToCheck, currentDate);
			
		}
		
		
		
		
		return null;
	}

}
