package com.itaytas.securityServer.api;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itaytas.securityServer.aop.MyLog;
import com.itaytas.securityServer.api.logs.LogRequest;

@RestController
@RequestMapping("/api/logs")
public class LogsRestController {
	
	@MyLog
	@PostMapping("/Single")
    public ResponseEntity<?> addNewSingleLog(@Valid @RequestBody LogRequest logRequest) {
		return null;
	}
	
	/*@MyLog
	@PostMapping("/multiple")
    public ResponseEntity<?> addNewMultipleLogs(@Valid @RequestBody LogRequest logRequest) {
		return null;
	}*/
	
}
