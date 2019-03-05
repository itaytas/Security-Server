package com.itaytas.securityServer.dal;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.itaytas.securityServer.logic.log.LogEntity_v1;
import com.itaytas.securityServer.logic.log.LogEntity_v2;

@Repository
public interface LogDao extends PagingAndSortingRepository<LogEntity_v2, String> {

	Page<LogEntity_v2> findByUserId(String userId, Pageable pageable);

	List<LogEntity_v2> findByUserIdAndIsMaliciousAndCreateDateBetween(
			@Param("userId") String userId,
			@Param("isMalicious") Boolean isMalicious,
			Date from, Date to);
	

}
