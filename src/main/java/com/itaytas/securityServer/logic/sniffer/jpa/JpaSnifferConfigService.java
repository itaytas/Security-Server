package com.itaytas.securityServer.logic.sniffer.jpa;

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
import com.itaytas.securityServer.dal.SnifferConfigDao;
import com.itaytas.securityServer.dal.UserDao;
import com.itaytas.securityServer.logic.sniffer.SnifferConfigEntity;
import com.itaytas.securityServer.logic.sniffer.SnifferConfigService;

@Service
public class JpaSnifferConfigService implements SnifferConfigService{

	private SnifferConfigDao snifferConfigDao;
	private UserDao userDao; 
	
	@Autowired
	public JpaSnifferConfigService(SnifferConfigDao snifferConfigDao, UserDao userDao) {
		this.snifferConfigDao = snifferConfigDao;
		this.userDao = userDao;
	}

	@Override
	@Transactional
	@MyLog
	public SnifferConfigEntity createInitialSnifferConfigFileForNewUser(String userId) {
		if (this.snifferConfigDao.existsByUserId(userId) || !this.userDao.existsById(userId)) {
			return null;
		}
		
		SnifferConfigEntity entity = new SnifferConfigEntity(userId, AppUtilsAndConstants.DEFAULT_SNIFFER_CONFIG_APPS);
		return this.snifferConfigDao.save(entity);
	}

	@Override
	@Transactional(readOnly = true)
	@MyLog
	public ResponseEntity<?> getSnifferConfigFileByUserId(String userId) {
		if (!this.userDao.existsById(userId)) {
			return new ResponseEntity<>(
        			new ApiResponse(false, "There is no user with id: " + userId),
        			HttpStatus.BAD_REQUEST);
		}
		
		if(!this.snifferConfigDao.existsByUserId(userId)) {
            return new ResponseEntity<>(
            			new ApiResponse(false, "ERROR: No sniffer config file was found for userId: " + userId),
            			HttpStatus.INTERNAL_SERVER_ERROR);
        }
		
		return new ResponseEntity<>(
    			this.snifferConfigDao.findByUserId(userId),
    			HttpStatus.OK);
	}

	@Override
	@Transactional
	@MyLog
	public ResponseEntity<?> updateSnifferConfigFileByUserId(String entityId, SnifferConfigEntity entityUpdates) {
		if (!this.snifferConfigDao.existsById(entityId)) {
			return new ResponseEntity<>(
        			new ApiResponse(false, "There is no sniffer config file with id: " + entityId),
        			HttpStatus.BAD_REQUEST);
		}
		
		String userId = entityUpdates.getUserId();
		if (!this.userDao.existsById(userId)) {
			return new ResponseEntity<>(
        			new ApiResponse(false, "There is no user with id: " + userId),
        			HttpStatus.BAD_REQUEST);
		}
		
		if(!this.snifferConfigDao.existsByUserId(userId)) {
            return new ResponseEntity<>(
        			new ApiResponse(false, "ERROR: No sniffer config file was found for userId: " + userId),
        			HttpStatus.INTERNAL_SERVER_ERROR);
        }
		
		SnifferConfigEntity existing = this.snifferConfigDao.findById(entityId).get();
		
		if (!existing.getUserApps().equals(entityUpdates.getUserApps())) {
			existing.setUserApps(entityUpdates.getUserApps());
		}
		
		SnifferConfigEntity updatedEntity = this.snifferConfigDao.save(existing);
		return new ResponseEntity<>(
				new ApiResponse(true, "Sniffer config file for userId: " + updatedEntity.getUserId() + " was updated!"),
				HttpStatus.CREATED);
	}

	@Override
	@Transactional(readOnly = true)
	@MyLog
	public PagedResponse<SnifferConfigEntity> getAllSnifferConfigFiles(int page, int size) {
		AppUtilsAndConstants.validatePageNumberAndSize(page, size);

		Page<SnifferConfigEntity> snifferConfigPage = this.snifferConfigDao
				.findAll(PageRequest.of(page, size, Sort.Direction.DESC, "createdAt"));

		return new PagedResponse<>(snifferConfigPage.getContent(), snifferConfigPage.getNumber(), snifferConfigPage.getSize(),
				snifferConfigPage.getTotalElements(), snifferConfigPage.getTotalPages(), snifferConfigPage.isLast());
	}

	@Override
	@Transactional
	@MyLog
	public void cleanup() {
		this.snifferConfigDao.deleteAll();
	}
	
}
