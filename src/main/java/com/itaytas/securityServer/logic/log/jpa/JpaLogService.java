package com.itaytas.securityServer.logic.log.jpa;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itaytas.securityServer.aop.MyLog;
import com.itaytas.securityServer.api.response.ApiResponse;
import com.itaytas.securityServer.api.response.PagedResponse;
import com.itaytas.securityServer.config.AppConstants;
import com.itaytas.securityServer.dal.LogDao;
import com.itaytas.securityServer.exception.BadRequestException;
import com.itaytas.securityServer.exception.LogNotFoundException;
import com.itaytas.securityServer.logic.log.LogEntity;
import com.itaytas.securityServer.logic.log.LogService;

@Service
public class JpaLogService implements LogService {

	private LogDao logDao;

	@Autowired
	public JpaLogService(LogDao logDao) {
		this.logDao = logDao;
	}

	@Override
	@Transactional
	@MyLog
	public ResponseEntity<?> addNewLogs(String userIdentifier, List<LogEntity> logs) {
		int numLogs = logs.size();
		if (numLogs == 0) {
			return new ResponseEntity<>(
        			new ApiResponse(false, "No logs were found for: " + userIdentifier),
        			HttpStatus.NO_CONTENT);
		}
		
		logs.stream().forEach(o -> this.logDao.save(o));
		
		return new ResponseEntity<>(
    			new ApiResponse(true, numLogs + " logs were found and saved for: " + userIdentifier),
    			HttpStatus.CREATED);
	}

	@Override
	@Transactional(readOnly = true)
	@MyLog
	public PagedResponse<LogEntity> getAllLogsByUserId(String userId, int size, int page) {
		validatePageNumberAndSize(page, size);
		
		Page<LogEntity> logPage = this.logDao
				.findAll(PageRequest.of(page, size, Sort.Direction.DESC, "createdAt"));

		return new PagedResponse<>(logPage.getContent(), logPage.getNumber(), logPage.getSize(),
				logPage.getTotalElements(), logPage.getTotalPages(), logPage.isLast());

	}

	@Override
	@Transactional(readOnly = true)
	@MyLog
	public LogEntity getLogById(String logId) throws Exception {
		Optional<LogEntity> op = this.logDao.findById(logId);
		if (op.isPresent()) {
			return op.get();
		} else {
			throw new LogNotFoundException("Log with id: " + logId + "not found!");
		}
	}

	@Override
	@Transactional
	@MyLog
	public void cleanup() {
		this.logDao.deleteAll();
	}

	@Override
	@Transactional(readOnly = true)
	@MyLog
	public List<LogEntity> getUserMaliciousLogsAfterDate(
			String userId, Date fromDateToCheck) {
		
		List<LogEntity> allUserLogs = this.logDao.findByUserIdAndMalicious(userId, true);
		
		List<LogEntity> relevantLogs = new ArrayList<>();
		
		allUserLogs.stream().forEach((log) -> {
			if (log.getCreatedAt().after(fromDateToCheck)) {
				relevantLogs.add(log);
			}
		});
		return relevantLogs;
	}

	@Override
	@Transactional(readOnly = true)
	@MyLog
	public List<LogEntity> getUserMaliciousLogsByAttacksNamesAfterDate(String userId, List<String> attacksNames,
			Date fromDateToCheck) {
		List<LogEntity> UnfilteredLogs = getUserMaliciousLogsAfterDate(userId, fromDateToCheck);
		List<LogEntity> relevantLogs = new ArrayList<>();
			
		UnfilteredLogs.stream().forEach((log)-> {
			log.getAttacksNames().stream().forEach((attackName)-> {
				if (attacksNames.contains(attackName) && !relevantLogs.contains(log)) {
					relevantLogs.add(log);
					return;
				}
			}); 
		});
		return relevantLogs;
	}

	private void validatePageNumberAndSize(int page, int size) {
		if (page < 0) {
			throw new BadRequestException("Page number cannot be less than zero.");
		}

		if (size > AppConstants.MAX_PAGE_SIZE || size < 0) {
			throw new BadRequestException("Page size must be between 0 and " + AppConstants.MAX_PAGE_SIZE);
		}
	}
	
}
