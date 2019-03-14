package com.itaytas.securityServer.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itaytas.securityServer.aop.MyLog;
import com.itaytas.securityServer.api.response.PagedResponse;
import com.itaytas.securityServer.logic.alert.AlertEntity;
import com.itaytas.securityServer.logic.alert.AlertService;
import com.itaytas.securityServer.security.CurrentUser;
import com.itaytas.securityServer.security.UserPrincipal;

@RestController
@RequestMapping("/api/alerts")
public class AlertsRestController {
	
	private AlertService alertService;
	
	@Autowired
	public AlertsRestController(AlertService alertService) {
		this.alertService = alertService;
	}

	
	@MyLog
	@GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public PagedResponse<AlertEntity> getAllAlerts(
    		@CurrentUser UserPrincipal currentUser,
    		@RequestParam(name = "size", required = false, defaultValue = "10") int size,
			@RequestParam(name = "page", required = false, defaultValue = "0") int page) {
		
		return this.alertService.getAllAlerts(page, size);
	}
	
}
