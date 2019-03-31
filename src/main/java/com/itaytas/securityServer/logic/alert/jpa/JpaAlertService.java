package com.itaytas.securityServer.logic.alert.jpa;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itaytas.securityServer.aop.MyLog;
import com.itaytas.securityServer.api.response.PagedResponse;
import com.itaytas.securityServer.config.AppUtilsAndConstants;
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
	
	private static final Logger LOG = Logger.getLogger(JpaAlertService.class.getName());
	
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
	@Transactional
	@MyLog
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
			if (alertEntity.equals(alert)) {
				foundSimilar = true;
			}
		}
		if (foundSimilar) {
			LOG.info("Alert already exists with the same details: " + alertEntity.toString());
			throw new AlertAlreadyExistsException("Alert already exists with the same details: " + alertEntity.toString());
		}
		
		return this.alertDao.save(alertEntity);
	}

	@Override
	@Transactional(readOnly = true)
	@MyLog
	public PagedResponse<AlertEntity> getAllAlerts(int page, int size) {
		AppUtilsAndConstants.validatePageNumberAndSize(page, size);

		Page<AlertEntity> alertPage = this.alertDao
				.findAll(PageRequest.of(page, size, Sort.Direction.DESC, "createdAt"));

		return new PagedResponse<>(alertPage.getContent(), alertPage.getNumber(), alertPage.getSize(),
				alertPage.getTotalElements(), alertPage.getTotalPages(), alertPage.isLast());
	}
	
	@Override
	@Transactional
	@MyLog
	public void cleanup() {
		this.alertDao.deleteAll();
	}

}
