package com.itaytas.securityServer.plugins;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
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
import com.itaytas.securityServer.logic.alert.AlertEntity;
import com.itaytas.securityServer.logic.log.LogEntity_v2;
import com.itaytas.securityServer.logic.log.LogService;
import com.itaytas.securityServer.logic.script.ScriptEntity;
import com.itaytas.securityServer.logic.user.Role;
import com.itaytas.securityServer.logic.user.RoleName;
import com.itaytas.securityServer.logic.user.UserEntity;
import com.itaytas.securityServer.logic.user.UserUtilService;

public class NumLogsPlugin implements SystemPlugin{

	private RoleDao roleDao;
	private UserUtilService userUtilService;
	private LogDao logDao;
	private LogService logService;
	private ObjectMapper jackson;
	
	@Autowired
	public NumLogsPlugin(RoleDao roleDao, UserUtilService userUtilService, LogDao logDao, LogService logService) {
		this.roleDao = roleDao;
		this.userUtilService = userUtilService;
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
		List<AlertEntity> alerts = new ArrayList<>();
		NumLogs numLogsObj = this.jackson.readValue(
				this.jackson.writeValueAsString(
						scriptEntity.getDetails()), NumLogs.class);
		int numLogsToCheck = numLogsObj.getNumLogs();
				
//		Date currentDate = new Date();
		Date fromDateToCheck = Date.from(Instant.now().minus(Duration.ofDays(numLogsObj.getNumDaysAgo())));
		
		List<UserEntity> users = this.userUtilService.getAllUsersWithUserRole();
		for (UserEntity user : users) {
			List<LogEntity_v2> logsByUserId = 
					this.logService.getUserMaliciousLogsByAttacksNamesAfterDate(
							user.getId(), scriptEntity.getAttackName(), fromDateToCheck);
			
			if (logsByUserId.size() >= numLogsToCheck) {
				List<String> scriptsId = new ArrayList<>();
				scriptsId.add(scriptEntity.getScriptId());
				List<String> logsId = new ArrayList<>();
				logsByUserId.stream().forEach((log) -> logsId.add(log.getLogId()));
				AlertEntity alertToAdd = 
						new AlertEntity(
								user.getId(), user.getName(), user.getUsername(), user.getEmail(),
								scriptEntity.getAttackName(), logsId, scriptsId);
			}
		}
		
		
		
		
		return null;
	}

}
