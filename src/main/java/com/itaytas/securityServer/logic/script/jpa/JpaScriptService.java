package com.itaytas.securityServer.logic.script.jpa;

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
import com.itaytas.securityServer.dal.ScriptDao;
import com.itaytas.securityServer.exception.BadRequestException;
import com.itaytas.securityServer.logic.script.ScriptEntity;
import com.itaytas.securityServer.logic.script.ScriptService;

@Service
public class JpaScriptService implements ScriptService {

	private ScriptDao scriptDao;

	@Autowired
	public void setScriptDao(ScriptDao scriptDao) {
		this.scriptDao = scriptDao;
	}

	@Override
	@Transactional
	@MyLog
	public ResponseEntity<?> addNewScript(ScriptEntity scriptEntity) {
		ScriptEntity entity = this.scriptDao.save(scriptEntity);

		return new ResponseEntity(
				new ApiResponse(true, "Script for " + entity.getAttackName() + "was saved!"),
				HttpStatus.CREATED);
	}

	@Override
	@Transactional(readOnly = true)
	@MyLog
	public PagedResponse<ScriptEntity> getAllScripts(int page, int size) {
		validatePageNumberAndSize(page, size);

		Page<ScriptEntity> scriptPage = this.scriptDao
				.findAll(PageRequest.of(page, size, Sort.Direction.DESC, "createdAt"));

		return new PagedResponse<>(scriptPage.getContent(), scriptPage.getNumber(), scriptPage.getSize(),
				scriptPage.getTotalElements(), scriptPage.getTotalPages(), scriptPage.isLast());

	}

	@Override
	@Transactional
	@MyLog
	public void cleanup() {
		this.scriptDao.deleteAll();

	}

	private void validatePageNumberAndSize(int page, int size) {
		if (page < 0) {
			throw new BadRequestException("Page number cannot be less than zero.");
		}

		if (size > AppConstants.MAX_PAGE_SIZE) {
			throw new BadRequestException("Page size must not be greater than " + AppConstants.MAX_PAGE_SIZE);
		}
	}
}