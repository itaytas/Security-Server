package com.itaytas.securityServer.logic.script;

import org.springframework.http.ResponseEntity;

import com.itaytas.securityServer.api.response.PagedResponse;

public interface ScriptService {
	
	ResponseEntity<?> addNewScript(ScriptEntity scriptEntity);
	
	PagedResponse<ScriptEntity> getAllScripts(int page, int size);
	
	void cleanup();
}
