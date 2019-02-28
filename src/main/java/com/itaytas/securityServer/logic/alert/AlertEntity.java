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

}
