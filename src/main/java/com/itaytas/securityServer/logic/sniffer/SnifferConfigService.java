package com.itaytas.securityServer.logic.sniffer;

import org.springframework.http.ResponseEntity;

import com.itaytas.securityServer.api.response.PagedResponse;

public interface SnifferConfigService {

	SnifferConfigEntity createInitialSnifferConfigFileForNewUser(String userId);
	
	ResponseEntity<?> getSnifferConfigFileByUserId(String userId);
	
	ResponseEntity<?> updateSnifferConfigFileByUserId(String entityId, SnifferConfigEntity entity);
	
	PagedResponse<SnifferConfigEntity> getAllSnifferConfigFiles(int page, int size);
	
	void cleanup();

}
