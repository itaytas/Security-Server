package com.itaytas.securityServer.logic.log;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface LogService {

	public ResponseEntity<?> addNewLogs(String userIdentifier, List<LogEntity_v2> logs);

	public List<LogEntity_v2> getAllLogsByUserId(String userId, int size, int page);

	public LogEntity_v2 getLogById(String logId) throws Exception;

	public void cleanup();

}
