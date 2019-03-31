package com.itaytas.securityServer.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itaytas.securityServer.aop.MyLog;
import com.itaytas.securityServer.api.response.PagedResponse;
import com.itaytas.securityServer.api.script.ScriptRequest;
import com.itaytas.securityServer.config.AppUtilsAndConstants;
import com.itaytas.securityServer.logic.script.ScriptEntity;
import com.itaytas.securityServer.logic.script.ScriptService;
import com.itaytas.securityServer.security.CurrentUser;
import com.itaytas.securityServer.security.UserPrincipal;

@RestController
@RequestMapping("/api/scripts")
public class ScriptsRestController {
	
	private ScriptService scriptService;
	
	@Autowired
	public ScriptsRestController(ScriptService scriptService) {
		this.scriptService = scriptService;
	}
	
	@MyLog
	@GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public PagedResponse<ScriptEntity> getAllScripts(
    		@CurrentUser UserPrincipal currentUser,
    		@RequestParam(name = "size", required = false, defaultValue = AppUtilsAndConstants.DEFAULT_PAGE_SIZE) int size,
			@RequestParam(name = "page", required = false, defaultValue = AppUtilsAndConstants.DEFAULT_PAGE_NUMBER) int page) {
		
		return this.scriptService.getAllScripts(page, size);
	}
	
	@MyLog
	@PostMapping("/add")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> addNewScript(
			@CurrentUser UserPrincipal currentUser,
			@Valid @RequestBody ScriptRequest scriptRequest) {
		
		return this.scriptService.addNewScript(scriptRequest.toEntity());
	}
	
/*	
	@MyLog
	@PutMapping("/update")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> updateScript(
			@CurrentUser UserPrincipal currentUser,
			@RequestParam(name = "id") String id, 
			@Valid @RequestBody ScriptRequest scriptRequest) {
		
		return this.scriptService.updateScript(id, scriptRequest.toEntity());
	}
*/	
}
