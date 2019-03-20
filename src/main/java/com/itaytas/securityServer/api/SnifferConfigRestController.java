package com.itaytas.securityServer.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itaytas.securityServer.aop.MyLog;
import com.itaytas.securityServer.api.response.PagedResponse;
import com.itaytas.securityServer.api.sniffer.SnffierConfigRequest;
import com.itaytas.securityServer.config.AppConstants;
import com.itaytas.securityServer.logic.sniffer.SnifferConfigEntity;
import com.itaytas.securityServer.logic.sniffer.SnifferConfigService;
import com.itaytas.securityServer.security.CurrentUser;
import com.itaytas.securityServer.security.UserPrincipal;

@RestController
@RequestMapping("/api/sniffer")
public class SnifferConfigRestController {

	// @PreAuthorize("hasRole('ROLE_VIEWER') or hasRole('ROLE_EDITOR')")
	
	private SnifferConfigService snifferConfigService;
	
	@Autowired
	public SnifferConfigRestController(SnifferConfigService snifferConfigService) {
		this.snifferConfigService = snifferConfigService;
	}
	
	@MyLog
	@GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public PagedResponse<SnifferConfigEntity> getAllSnifferConfigFiles(
    		@CurrentUser UserPrincipal currentUser,
    		@RequestParam(name = "size", required = false, defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size,
			@RequestParam(name = "page", required = false, defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page) {
		
		return this.snifferConfigService.getAllSnifferConfigFiles(page, size);
	}
	
	@MyLog
	@GetMapping("/user")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    public ResponseEntity<?> getSnifferConfigFileByUserId(
    		@CurrentUser UserPrincipal currentUser,
    		@RequestParam(name = "id") String id) {
		return this.snifferConfigService.getSnifferConfigFileByUserId(id);
	}
	
	
	@MyLog
	@PutMapping("/update")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<?> updateSnifferConfigFile(
			@CurrentUser UserPrincipal currentUser,
			@RequestParam(name = "id") String id, 
			@Valid @RequestBody SnffierConfigRequest snffierConfigRequest) {
		
		return this.snifferConfigService.updateSnifferConfigFileByUserId(id, snffierConfigRequest.toEntity());
	}
	
}
