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
import com.itaytas.securityServer.api.logs.LogRequest_v1;
import com.itaytas.securityServer.api.logs.LogRequest_v2;
import com.itaytas.securityServer.logic.log.LogEntity_v1;
import com.itaytas.securityServer.logic.log.LogEntity_v2;
import com.itaytas.securityServer.logic.log.LogService;
import com.itaytas.securityServer.security.CurrentUser;
import com.itaytas.securityServer.security.UserPrincipal;

@RestController
@RequestMapping("/api/logs")
public class LogsRestController {

	private LogService logService;

	@Autowired
	public void setLogService(LogService logService) {
		this.logService = logService;
	}

	@MyLog
	@PostMapping("/add")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> addNewLogs(
			@CurrentUser UserPrincipal currentUser,
			@Valid @RequestBody LogRequest_v2[] logRequests) {
		// convert LogRequest_v2[] -> List<LogEntity_v2>
		List<LogEntity_v2> logs = new ArrayList<>();
		Stream.of(logRequests).map(o -> o.toEntity(currentUser.getId())).forEach(o -> logs.add(o));
		
		return this.logService.addNewLogs(currentUser.getUsername(), logs);
	}

}
