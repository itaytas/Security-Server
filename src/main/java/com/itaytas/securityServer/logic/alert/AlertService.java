package com.itaytas.securityServer.logic.alert;

import com.itaytas.securityServer.api.response.PagedResponse;

public interface AlertService {
	
	AlertEntity addNewAlert(AlertEntity alertEntity) throws Exception;
	
	PagedResponse<AlertEntity> getAllAlerts(int page, int size);

	void cleanup();
}
