package com.itaytas.securityServer.dal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.itaytas.securityServer.logic.log.LogEntity;

@Repository
public interface LogDao extends PagingAndSortingRepository<LogEntity, String> {

	Page<LogEntity> findByUserId(String userId, Pageable pageable);

}
