package com.itaytas.securityServer.logic.log;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface LogService {

	public ResponseEntity<?> addNewLogs(String userIdentifier, List<LogEntity> logs);

	public List<LogEntity> getAllLogsByUserId(String userId, int size, int page);

	public LogEntity getLogById(String logId) throws Exception;

	public void cleanup();

}
