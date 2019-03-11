package com.itaytas.securityServer.logic.alert;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.itaytas.securityServer.logic.audit.DateAudit;

@Entity
@Document(collection = "Alerts")
public class AlertEntity extends DateAudit {

	private static final long serialVersionUID = 6007135680406506452L;

	private String alertId;

	// User Identifiers
	private String userId;
	private String userFullName;
	private String username;
	private String userEmail;

	// Logs Identifiers
	private List<String> attacksNames;
	private List<String> logsId;

	// Scripts Identifiers
	private List<String> scriptsId;

	public AlertEntity() {
		super();
	}

	public AlertEntity(String userId, String userFullName, String username, String userEmail, List<String> attacksNames,
			List<String> logsId, List<String> scriptsId) {
		super();
		this.userId = userId;
		this.userFullName = userFullName;
		this.username = username;
		this.userEmail = userEmail;
		this.attacksNames = attacksNames;
		this.logsId = logsId;
		this.scriptsId = scriptsId;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public String getAlertId() {
		return alertId;
	}

	public String getUserId() {
		return userId;
	}

	public String getUserFullName() {
		return userFullName;
	}

	public String getUsername() {
		return username;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public List<String> getAttacksNames() {
		return attacksNames;
	}

	public List<String> getLogsId() {
		return logsId;
	}

	public List<String> getScriptsId() {
		return scriptsId;
	}

	public void setAlertId(String alertId) {
		this.alertId = alertId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setUserFullName(String userFullName) {
		this.userFullName = userFullName;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public void setAttacksNames(List<String> attacksNames) {
		this.attacksNames = attacksNames;
	}

	public void setLogsId(List<String> logsId) {
		this.logsId = logsId;
	}

	public void setScriptsId(List<String> scriptsId) {
		this.scriptsId = scriptsId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "AlertEntity [alertId=" + alertId + ", userId=" + userId + ", userFullName=" + userFullName
				+ ", username=" + username + ", userEmail=" + userEmail + ", attacksNames=" + attacksNames + ", logsId="
				+ logsId + ", scriptsId=" + scriptsId + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((attacksNames == null) ? 0 : attacksNames.hashCode());
		result = prime * result + ((logsId == null) ? 0 : logsId.hashCode());
		result = prime * result + ((scriptsId == null) ? 0 : scriptsId.hashCode());
		result = prime * result + ((userEmail == null) ? 0 : userEmail.hashCode());
		result = prime * result + ((userFullName == null) ? 0 : userFullName.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		AlertEntity other = (AlertEntity) obj;
		if (attacksNames == null) {
			if (other.attacksNames != null)
				return false;
		} else if (!attacksNames.equals(other.attacksNames))
			return false;
		if (logsId == null) {
			if (other.logsId != null)
				return false;
		} else if (!logsId.equals(other.logsId)) {
			return false;
		}
		if (scriptsId == null) {
			if (other.scriptsId != null)
				return false;
		} else if (!scriptsId.equals(other.scriptsId))
			return false;
		if (userEmail == null) {
			if (other.userEmail != null)
				return false;
		} else if (!userEmail.equals(other.userEmail))
			return false;
		if (userFullName == null) {
			if (other.userFullName != null)
				return false;
		} else if (!userFullName.equals(other.userFullName))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	
	
	
}
