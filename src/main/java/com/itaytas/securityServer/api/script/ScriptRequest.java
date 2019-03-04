package com.itaytas.securityServer.api.script;

import java.util.List;
import java.util.Map;

import com.itaytas.securityServer.logic.script.ScriptEntity;

public class ScriptRequest {

	private String scriptId;
	private List<String> attackName;
	private Boolean active;
	private Map<String, Object> details;

	public ScriptRequest() {
	}

	public ScriptRequest(List<String> attackName, boolean active, Map<String, Object> details) {
		super();
		this.attackName = attackName;
		this.active = active;
		this.details = details;
	}
	
	public ScriptRequest(ScriptEntity entity) {
		this(entity.getAttackName(), entity.isActive(), entity.getDetails());
		this.scriptId = entity.getScriptId();
	}
	
	public ScriptEntity toEntity() {
		ScriptEntity rv = new ScriptEntity();
		
		if(this.scriptId != null || !this.scriptId.isEmpty()) {
			rv.setScriptId(this.scriptId);
		}
		rv.setAttackName(this.attackName);
		rv.setActive(this.active);
		rv.setDetails(this.details);
		return rv;
	}
	
	public String getScriptId() {
		return scriptId;
	}

	public void setScriptId(String scriptId) {
		this.scriptId = scriptId;
	}

	public List<String> getAttackName() {
		return attackName;
	}

	public void setAttackName(List<String> attackName) {
		this.attackName = attackName;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Map<String, Object> getDetails() {
		return details;
	}

	public void setDetails(Map<String, Object> details) {
		this.details = details;
	}

	@Override
	public String toString() {
		return "ScriptRequest [scriptId=" + scriptId + ", attackName=" + attackName + ", active=" + active
				+ ", details=" + details + "]";
	}
}
