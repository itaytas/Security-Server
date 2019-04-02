package com.itaytas.securityServer.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itaytas.securityServer.aop.MyLog;
import com.itaytas.securityServer.api.response.ApiResponse;
import com.itaytas.securityServer.config.AppUtilsAndConstants;

@RestController
public class IndexRestController {

	public IndexRestController() {
	}
	
	@MyLog
	@GetMapping("/")
	public ApiResponse getIndex() {
		return new ApiResponse(false, AppUtilsAndConstants.INDEX_HELLO_WORLD);
	}
	
	@MyLog
	@PostMapping("/")
	public ApiResponse postIndex() {
		return new ApiResponse(false, AppUtilsAndConstants.INDEX_HELLO_WORLD);
	}
	
	@MyLog
	@GetMapping("/api")
	public ApiResponse getApiIndex() {
		return new ApiResponse(false, AppUtilsAndConstants.INDEX_HELLO_WORLD);
	}
	
	@MyLog
	@PostMapping("/api")
	public ApiResponse postApiIndex() {
		return new ApiResponse(false, AppUtilsAndConstants.INDEX_HELLO_WORLD);
	}
	
}
