package com.itaytas.securityServer.plugins;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itaytas.securityServer.aop.MyLog;
import com.itaytas.securityServer.logic.alert.AlertEntity;
import com.itaytas.securityServer.logic.alert.AlertService;
import com.itaytas.securityServer.logic.log.LogEntity;
import com.itaytas.securityServer.logic.log.LogService;
import com.itaytas.securityServer.logic.script.ScriptEntity;
import com.itaytas.securityServer.logic.user.UserEntity;
import com.itaytas.securityServer.logic.user.UserUtilService;
import com.itaytas.securityServer.schedule.ScheduledTasks;

@Component
public class NumLogsPlugin implements SystemPlugin{

	private static final Logger LOG = Logger.getLogger(ScheduledTasks.class.getName());
    
	private UserUtilService userUtilService;
	private LogService logService;
	private AlertService alertService;
	private ObjectMapper jackson;
	
	@Autowired
	public NumLogsPlugin(UserUtilService userUtilService, LogService logService, AlertService alertService) {
		this.userUtilService = userUtilService;
		this.logService = logService;
		this.alertService = alertService;
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

	@MyLog
	@Override
	public List<AlertEntity> invokeOperation(ScriptEntity scriptEntity) throws Exception {
		List<AlertEntity> alerts = new ArrayList<>();
		
		NumLogs numLogsObj = this.jackson.readValue(
				this.jackson.writeValueAsString(
						scriptEntity.getDetails()), NumLogs.class);
		
		int numLogsToCheck = numLogsObj.getNumLogs();
		Date fromDateToCheck = 
				Date.from(Instant.now().minus(Duration.ofDays(numLogsObj.getNumDaysAgo())));
		
		List<UserEntity> users = this.userUtilService.getAllRoleUsers();
		System.err.println(users);
		for (UserEntity user : users) {
			List<LogEntity> logsByUserId = 
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
				
				try {
					alerts.add(this.alertService.addNewAlert(alertToAdd));
				} catch (Exception e) {
					LOG.info("No Alert found for : " + alertToAdd.getUserFullName());
				}
			}
		}
		return alerts;
	}

}
