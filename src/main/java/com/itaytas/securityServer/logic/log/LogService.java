package com.itaytas.securityServer.logic.log;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.http.ResponseEntity;

public interface LogService {

	ResponseEntity<?> addNewLogs(String userIdentifier, List<LogEntity> logs);

	List<LogEntity> getAllLogsByUserId(String userId, int size, int page);

	LogEntity getLogById(String logId) throws Exception;

	void cleanup();

	List<LogEntity> getUserMaliciousLogsAfterDate(String userId, Date fromDateToCheck);
	
	Set<LogEntity> getUserMaliciousLogsByAttacksNamesAfterDate(String userId, List<String> attacksNames, Date fromDateToCheck);

}
