package com.itaytas.securityServer.logic.script;

import org.springframework.http.ResponseEntity;

import com.itaytas.securityServer.api.response.PagedResponse;

public interface ScriptService {
	
	ResponseEntity<?> addNewScript(ScriptEntity scriptEntity);
	
	ResponseEntity<?> updateScript(String entityId, ScriptEntity entityUpdates);
	
	PagedResponse<ScriptEntity> getAllScripts(int page, int size);
	
	void cleanup();
	
}
