package com.itaytas.securityServer.dal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.itaytas.securityServer.logic.log.LogEntity_v1;
import com.itaytas.securityServer.logic.log.LogEntity_v2;

@Repository
public interface LogDao extends PagingAndSortingRepository<LogEntity_v2, String> {

	Page<LogEntity_v2> findByUserId(String userId, Pageable pageable);

}
