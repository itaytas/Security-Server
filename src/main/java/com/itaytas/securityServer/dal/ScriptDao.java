package com.itaytas.securityServer.dal;

import java.util.Map;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.itaytas.securityServer.logic.script.ScriptEntity;

@Repository
public interface ScriptDao extends PagingAndSortingRepository<ScriptEntity, String> {
	
	ScriptEntity findByAttackNameAndDetails(
			@Param("attackName") String attackName,
    		@Param("details") Map<String, Object> details);
}
