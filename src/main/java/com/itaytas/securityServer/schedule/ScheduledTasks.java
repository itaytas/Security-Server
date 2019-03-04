package com.itaytas.securityServer.schedule;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.itaytas.securityServer.dal.LogDao;
import com.itaytas.securityServer.dal.ScriptDao;
import com.itaytas.securityServer.dal.UserDao;
import com.itaytas.securityServer.logic.alert.AlertService;
import com.itaytas.securityServer.logic.script.ScriptEntity;

@Component
public class ScheduledTasks {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    
    private LogDao logDao;
    private UserDao userDao;
    private ScriptDao scriptDao;
    private AlertService alertService;
    
    public ScheduledTasks() {
	}
    
	@Autowired
    public ScheduledTasks(LogDao logDao, UserDao userDao, ScriptDao scriptDao, AlertService alertService) {
		super();
		this.logDao = logDao;
		this.userDao = userDao;
		this.scriptDao = scriptDao;
		this.alertService = alertService;
	}

	//    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        System.err.println("The time is now " + dateFormat.format(new Date()));
    }
    
    /**
     * Step 1: Get all scripts from DB.
     * Step 2: for each script invoke operation of plug-in using script's type [String]
     * Step 3: Get return value (Null / Alert).
     * Step 4: Check if the return value is not Null and then try to save it.
     * Step 5: I'll try to figure it out later.
     * */
    
	@Scheduled(fixedRate = 5000)
	public void findScriptsEvents() {
		System.err.println("The time is now " + dateFormat.format(new Date()));
		List<ScriptEntity> scripts = (List<ScriptEntity>) this.scriptDao.findAll();
		
	}
    

	public static SimpleDateFormat getDateformat() {
		return dateFormat;
	}

	public LogDao getLogDao() {
		return logDao;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public ScriptDao getScriptDao() {
		return scriptDao;
	}
	
	public AlertService getAlertService() {
		return alertService;
	}
	
	public void setLogDao(LogDao logDao) {
		this.logDao = logDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void setScriptDao(ScriptDao scriptDao) {
		this.scriptDao = scriptDao;
	}

	public void setAlertService(AlertService alertService) {
		this.alertService = alertService;
	}
    
    
}