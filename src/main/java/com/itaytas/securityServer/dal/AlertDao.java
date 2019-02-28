package com.itaytas.securityServer.dal;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.itaytas.securityServer.logic.alert.AlertEntity;

public interface AlertDao extends PagingAndSortingRepository<AlertEntity, String> {
	
	List<AlertEntity> findByUserId(@Param("userId") String userId);
}
