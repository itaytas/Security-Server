package com.itaytas.securityServer.dal;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.itaytas.securityServer.logic.sniffer.SnifferConfigEntity;

public interface SnifferConfigDao extends PagingAndSortingRepository<SnifferConfigEntity, String> {

	SnifferConfigEntity findByUserId(@Param("userId") String userId);
	
	Boolean existsByUserId(@Param("userId") String userId);
}
