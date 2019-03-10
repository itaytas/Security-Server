package com.itaytas.securityServer.logic.alert.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.itaytas.securityServer.api.response.PagedResponse;
import com.itaytas.securityServer.dal.AlertDao;
import com.itaytas.securityServer.dal.UserDao;
import com.itaytas.securityServer.exception.AlertAlreadyExistsException;
import com.itaytas.securityServer.exception.UserNotFoundException;
import com.itaytas.securityServer.exception.WorngUserDetailsException;
import com.itaytas.securityServer.logic.alert.AlertEntity;
import com.itaytas.securityServer.logic.alert.AlertService;
import com.itaytas.securityServer.logic.user.UserEntity;

@Service
public class JpaAlertService implements AlertService{
	
	private AlertDao alertDao;
	private UserDao userDao; 

	@Autowired
	public JpaAlertService(AlertDao alertDao, UserDao userDao) {
		super();
		this.alertDao = alertDao;
		this.userDao = userDao;
	}

	/*
	 * Check if there is no alert with all the same attributes together:
	 * - userId [String] 
	 * - attacksNames [List<String>]
	 * - logsId [List<String>] 
	 * - scriptsId [List<String>] {***OPTIONAL***}
	 */
	
	@Override
	public AlertEntity addNewAlert(AlertEntity alertEntity) throws Exception {
		UserEntity user = this.userDao.findById(alertEntity.getUserId())
						.orElseThrow(()-> new UserNotFoundException(
								"There is no user with id: " + alertEntity.getUserId()));
		Boolean assertUserDetails = 
				user.getName().equalsIgnoreCase(alertEntity.getUserFullName()) &&
				user.getEmail().equalsIgnoreCase(alertEntity.getUserEmail()) &&
				user.getUsername().equalsIgnoreCase(alertEntity.getUsername());
		if (!assertUserDetails) {
			String found = String.format(
					"Found: Name = %s, Email = %s, Username = %s",
					alertEntity.getUserFullName(), alertEntity.getUserEmail(), alertEntity.getUsername());
			String expected = String.format(
					"Found: Name = %s, Email = %s, Username = %s",
					user.getName(),	user.getEmail(), user.getUsername());
			throw new WorngUserDetailsException(found + "\n" + expected);
		}
		
		Boolean foundSimilar = false;
		List<AlertEntity> userAlerts = this.alertDao.findByUserId(alertEntity.getUserId());
		for (AlertEntity alert : userAlerts) {
			/*Boolean sameAttacksNames = alert.getAttacksNames().equals(alertEntity.getAttacksNames());
			Boolean sameLogsId = alert.getLogsId().equals(alertEntity.getLogsId());			
			Boolean sameScriptsId = alert.getScriptsId().equals(alertEntity.getScriptsId());
			if (sameAttacksNames && sameLogsId && sameScriptsId ) {
				foundSimilar = true;
			}*/
			
			if (alertEntity.equals(alert)) {
				foundSimilar = true;
			}
		}
		if (foundSimilar) {
			throw new AlertAlreadyExistsException("Alert already exists with the same details: " + alertEntity.toString());
		}
		
		return this.alertDao.save(alertEntity);
	}

	@Override
	public PagedResponse<AlertEntity> getAllAlertByUserId(String userId, int page, int size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void cleanup() {
		this.alertDao.deleteAll();
	}
	
}
