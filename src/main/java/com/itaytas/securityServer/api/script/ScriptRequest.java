package com.itaytas.securityServer.api.script;

import java.util.List;
import java.util.Map;

import com.itaytas.securityServer.logic.script.ScriptEntity;

public class ScriptRequest {

	private String scriptId;
	private String type;
	private List<String> attacksNames;
	private Boolean active;
	private Map<String, Object> details;

	public ScriptRequest() {
	}
	
	public ScriptRequest(String type, List<String> attacksNames, Boolean active, Map<String, Object> details) {
		super();
		this.type = type;
		this.attacksNames = attacksNames;
		this.active = active;
		this.details = details;
	}


	public ScriptRequest(ScriptEntity entity) {
		this(entity.getType(), entity.getAttackName(), entity.isActive(), entity.getDetails());
		this.scriptId = entity.getScriptId();
	}
	
	public ScriptEntity toEntity() {
		ScriptEntity rv = new ScriptEntity();
		
		if(this.scriptId != null || this.scriptId != "") {
			rv.setScriptId(this.scriptId);
		}
		rv.setType(this.type);
		rv.setAttackName(this.attacksNames);
		rv.setActive(this.active);
		rv.setDetails(this.details);
		return rv;
	}

	public String getScriptId() {
		return scriptId;
	}

	public String getType() {
		return type;
	}

	public List<String> getAttacksNames() {
		return attacksNames;
	}

	public Boolean getActive() {
		return active;
	}

	public Map<String, Object> getDetails() {
		return details;
	}

	public void setScriptId(String scriptId) {
		this.scriptId = scriptId;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setAttacksNames(List<String> attacksNames) {
		this.attacksNames = attacksNames;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public void setDetails(Map<String, Object> details) {
		this.details = details;
	}

	@Override
	public String toString() {
		return "ScriptRequest [scriptId=" + scriptId + ", type=" + type + ", attacksNames=" + attacksNames + ", active="
				+ active + ", details=" + details + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((active == null) ? 0 : active.hashCode());
		result = prime * result + ((attacksNames == null) ? 0 : attacksNames.hashCode());
		result = prime * result + ((details == null) ? 0 : details.hashCode());
		result = prime * result + ((scriptId == null) ? 0 : scriptId.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ScriptRequest other = (ScriptRequest) obj;
		if (active == null) {
			if (other.active != null)
				return false;
		} else if (!active.equals(other.active))
			return false;
		if (attacksNames == null) {
			if (other.attacksNames != null)
				return false;
		} else if (!attacksNames.equals(other.attacksNames))
			return false;
		if (details == null) {
			if (other.details != null)
				return false;
		} else if (!details.equals(other.details))
			return false;
		if (scriptId == null) {
			if (other.scriptId != null)
				return false;
		} else if (!scriptId.equals(other.scriptId))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
	
}
