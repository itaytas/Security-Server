package com.itaytas.securityServer.logic.log;

import java.util.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;

public interface LogService {

	ResponseEntity<?> addNewLogs(String userIdentifier, List<LogEntity_v2> logs);

	List<LogEntity_v2> getAllLogsByUserId(String userId, int size, int page);

	LogEntity_v2 getLogById(String logId) throws Exception;

	void cleanup();

	List<LogEntity_v2> getUserMaliciousLogsBetweenDates(String userId, Date fromDateToCheck, Date currentDate);

}
