package com.itaytas.securityServer.logic.script;

import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.itaytas.securityServer.logic.audit.DateAudit;

@Entity
@Document(collection = "Scripts")
public class ScriptEntity extends DateAudit {

	private static final long serialVersionUID = 7399472036093751716L;

	private String scriptId;
	private String type;
	private List<String> attacksNames;
	private Boolean active;
	private Map<String, Object> details;

	public ScriptEntity() {
	}

	public ScriptEntity(String type, List<String> attacksNames, Boolean active, Map<String, Object> details) {
		super();
		this.type = type;
		this.attacksNames = attacksNames;
		this.active = active;
		this.details = details;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public String getScriptId() {
		return scriptId;
	}
	
	@NotBlank
	public String getType() {
		return type;
	}

	@NotBlank
	public List<String> getAttackName() {
		return attacksNames;
	}

	public Boolean isActive() {
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

	public void setAttackName(List<String> attacksNames) {
		this.attacksNames = attacksNames;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public void setDetails(Map<String, Object> details) {
		this.details = details;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "ScriptEntity [scriptId=" + scriptId + ", type=" + type + ", attacksNames=" + attacksNames + ", active="
				+ active + ", details=" + details + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((active == null) ? 0 : active.hashCode());
		result = prime * result + ((attacksNames == null) ? 0 : attacksNames.hashCode());
		result = prime * result + ((details == null) ? 0 : details.hashCode());
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
		ScriptEntity other = (ScriptEntity) obj;
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
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}



}
