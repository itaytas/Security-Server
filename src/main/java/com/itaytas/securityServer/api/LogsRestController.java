package com.itaytas.securityServer.api;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itaytas.securityServer.aop.MyLog;
import com.itaytas.securityServer.api.logs.LogRequest;
import com.itaytas.securityServer.logic.log.LogEntity;
import com.itaytas.securityServer.logic.log.LogService;
import com.itaytas.securityServer.security.CurrentUser;
import com.itaytas.securityServer.security.UserPrincipal;

@RestController
@RequestMapping("/api/logs")
public class LogsRestController {

	private LogService logService;

	@Autowired
	public LogsRestController(LogService logService) {
		this.logService = logService;
	}

	@MyLog
	@PostMapping("/add")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> addNewLogs(
			@CurrentUser UserPrincipal currentUser,
			@Valid @RequestBody LogRequest[] logRequests) {
		// convert LogRequest_v2[] -> List<LogEntity_v2>
		Stream.of(logRequests).forEach((log) -> System.err.println(log));
		List<LogEntity> logs = new ArrayList<>();
		Stream.of(logRequests).map(o -> o.toEntity(currentUser.getId())).forEach(o -> logs.add(o));
		System.err.println(logs);
		return this.logService.addNewLogs(currentUser.getUsername(), logs);
	}
	
}
