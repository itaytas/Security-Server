package com.itaytas.securityServer.logic.script.jpa;

import java.util.List;

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
import com.itaytas.securityServer.config.AppUtilsAndConstants;
import com.itaytas.securityServer.dal.ScriptDao;
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
		
		List<ScriptEntity> allActiveScripts = this.scriptDao.findByActive(true);
		for (ScriptEntity activeScript : allActiveScripts) {
			if (scriptEntity.equals(activeScript)) {
				return new ResponseEntity<>(
						new ApiResponse(true, "Script Already found!"),
						HttpStatus.BAD_REQUEST);
			}
		}
		
		ScriptEntity entity = this.scriptDao.save(scriptEntity);
		
		return new ResponseEntity<>(
				new ApiResponse(true, "Script for " + entity.getAttackName() + "was saved!"),
				HttpStatus.CREATED);
	}
	
	@Override
	@Transactional
	@MyLog
	public ResponseEntity<?> updateScript(String entityId, ScriptEntity entityUpdates) {
		if(!this.scriptDao.existsById(entityId)) {
            return new ResponseEntity<>(
            			new ApiResponse(false, "There is no script with id: " + entityId),
            			HttpStatus.BAD_REQUEST);
        }
		
		ScriptEntity existing = this.scriptDao.findById(entityId).get();
		
		// Compare between existing object and entityUpdates object
		if (!existing.getType().equals(entityUpdates.getType()) && entityUpdates.getType() != null && !entityUpdates.getType().isEmpty()) {
			existing.setType(entityUpdates.getType());
		}
		if (!existing.getAttackName().equals(entityUpdates.getAttackName()) && entityUpdates.getAttackName() != null && !entityUpdates.getAttackName().isEmpty()) {
			existing.setAttackName(entityUpdates.getAttackName());
		}
		if (!existing.isActive().equals(entityUpdates.isActive()) && entityUpdates.isActive() != null) {
			existing.setActive(entityUpdates.isActive());
		}
		if (!existing.getDetails().equals(entityUpdates.getDetails()) && entityUpdates.getDetails() != null && !entityUpdates.getDetails().isEmpty()) {
			existing.setDetails(entityUpdates.getDetails());
		}
		
		ScriptEntity savedUpdated = this.scriptDao.save(existing);
		
		return new ResponseEntity<>(
				new ApiResponse(true, "Script with id: " + savedUpdated.getScriptId() + " was updated!"),
				HttpStatus.CREATED);
	}
	
	

	@Override
	@Transactional(readOnly = true)
	@MyLog
	public PagedResponse<ScriptEntity> getAllScripts(int page, int size) {
		AppUtilsAndConstants.validatePageNumberAndSize(page, size);

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
}
