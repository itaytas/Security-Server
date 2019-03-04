package com.itaytas.securityServer.plugins;

import com.itaytas.securityServer.logic.script.ScriptEntity;

public interface SystemPlugin {

	public Object[] invokeOperation(ScriptEntity scriptEntity) throws Exception;

}
