package com.itaytas.securityServer.logic.log;

import java.util.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.itaytas.securityServer.api.response.PagedResponse;

public interface LogService {

	ResponseEntity<?> addNewLogs(String userIdentifier, List<LogEntity> logs);

	PagedResponse<LogEntity> getAllLogsByUserId(String userId, int size, int page);

	LogEntity getLogById(String logId) throws Exception;

	void cleanup();

	List<LogEntity> getUserMaliciousLogsAfterDate(String userId, Date fromDateToCheck);
	
	List<LogEntity> getUserMaliciousLogsByAttacksNamesAfterDate(String userId, List<String> attacksNames, Date fromDateToCheck);

}
