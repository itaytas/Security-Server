package com.itaytas.securityServer.logic.script;

import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.itaytas.securityServer.logic.audit.DateAudit;

@Entity
@Document(collection = "scripts")
public class ScriptEntity extends DateAudit {

	private static final long serialVersionUID = 7399472036093751716L;

	private String scriptId;
	private String attackName;
	private boolean active;
	private Map<String, Object> details;

	public ScriptEntity() {
	}

	public ScriptEntity(String attackName, boolean active, Map<String, Object> details) {
		super();
		this.attackName = attackName;
		this.active = active;
		this.details = details;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public String getScriptId() {
		return scriptId;
	}

	@NotBlank
	public String getAttackName() {
		return attackName;
	}

	public boolean isActive() {
		return active;
	}

	public Map<String, Object> getDetails() {
		return details;
	}

	public void setScriptId(String scriptId) {
		this.scriptId = scriptId;
	}

	public void setAttackName(String attackName) {
		this.attackName = attackName;
	}

	public void setActive(boolean active) {
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
		return "ScriptEntity [scriptId=" + scriptId + ", attackName=" + attackName + ", active=" + active + ", details="
				+ details + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (active ? 1231 : 1237);
		result = prime * result + ((attackName == null) ? 0 : attackName.hashCode());
		result = prime * result + ((details == null) ? 0 : details.hashCode());
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
		if (active != other.active)
			return false;
		if (attackName == null) {
			if (other.attackName != null)
				return false;
		} else if (!attackName.equals(other.attackName))
			return false;
		if (details == null) {
			if (other.details != null)
				return false;
		} else if (!details.equals(other.details))
			return false;
		return true;
	}

}
