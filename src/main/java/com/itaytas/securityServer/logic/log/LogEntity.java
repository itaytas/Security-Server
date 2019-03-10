package com.itaytas.securityServer.logic.log;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.itaytas.securityServer.logic.audit.DateAudit;

@Entity
@Document(collection = "Logs")
public class LogEntity extends DateAudit {

	private static final long serialVersionUID = -2799328143852341429L;

	private String logId;
	private String userId;

	// Logs Identifiers
	private String protocol;
	private String source;
	private String destination;
	private String sourcePort;
	private String destinationPort;
	private String httpData;
	private Boolean malicious;
	private List<String> attacksNames;

	public LogEntity(String userId, String protocol, String source, String destination, String sourcePort,
			String destinationPort, String httpData, Boolean malicious, List<String> attacksNames) {
		super();
		this.userId = userId;
		this.protocol = protocol;
		this.source = source;
		this.destination = destination;
		this.sourcePort = sourcePort;
		this.destinationPort = destinationPort;
		this.httpData = httpData;
		this.malicious = malicious;
		this.attacksNames = attacksNames;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public String getLogId() {
		return logId;
	}

	public void setLogId(String logId) {
		this.logId = logId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getSourcePort() {
		return sourcePort;
	}

	public void setSourcePort(String sourcePort) {
		this.sourcePort = sourcePort;
	}

	public String getDestinationPort() {
		return destinationPort;
	}

	public void setDestinationPort(String destinationPort) {
		this.destinationPort = destinationPort;
	}

	public String getHttpData() {
		return httpData;
	}

	public void setHttpData(String httpData) {
		this.httpData = httpData;
	}

	public Boolean isMalicious() {
		return malicious;
	}

	public void setMalicious(Boolean isMalicious) {
		this.malicious = isMalicious;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<String> getAttacksNames() {
		return attacksNames;
	}

	public void setAttacksNames(List<String> attacksNames) {
		this.attacksNames = attacksNames;
	}

	@Override
	public String toString() {
		return "LogEntity_v2 [logId=" + logId + ", userId=" + userId + ", protocol=" + protocol + ", source=" + source
				+ ", destination=" + destination + ", sourcePort=" + sourcePort + ", destinationPort=" + destinationPort
				+ ", httpData=" + httpData + ", isMalicious=" + malicious + ", attacksNames=" + attacksNames + "]";
	}

}
