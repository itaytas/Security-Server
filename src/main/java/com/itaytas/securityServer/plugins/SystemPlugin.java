package com.itaytas.securityServer.plugins;

import java.util.List;

import com.itaytas.securityServer.logic.alert.AlertEntity;
import com.itaytas.securityServer.logic.script.ScriptEntity;

public interface SystemPlugin {

	public List<AlertEntity> invokeOperation(ScriptEntity scriptEntity) throws Exception;

}
