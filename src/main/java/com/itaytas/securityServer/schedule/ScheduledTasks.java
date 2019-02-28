package com.itaytas.securityServer.schedule;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.itaytas.securityServer.dal.LogDao;
import com.itaytas.securityServer.dal.ScriptDao;
import com.itaytas.securityServer.dal.UserDao;

@Component
public class ScheduledTasks {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    
    private LogDao logDao;
    private UserDao userDao;
    private ScriptDao scriptDao;
    
    public ScheduledTasks() {
	}
    
	@Autowired
    public ScheduledTasks(LogDao logDao, UserDao userDao, ScriptDao scriptDao) {
		super();
		this.logDao = logDao;
		this.userDao = userDao;
		this.scriptDao = scriptDao;
	}

	//    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        System.err.println("The time is now " + dateFormat.format(new Date()));
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

	public void setLogDao(LogDao logDao) {
		this.logDao = logDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public void setScriptDao(ScriptDao scriptDao) {
		this.scriptDao = scriptDao;
	}
    
    
}