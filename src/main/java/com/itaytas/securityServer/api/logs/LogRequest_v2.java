package com.itaytas.securityServer.api.logs;

import java.util.List;

import com.itaytas.securityServer.logic.log.LogEntity_v2;

public class LogRequest_v2 {

	// Logs Identifiers

	private String protocol;
	private String source;
	private String destination;
	private String sourcePort;
	private String destinationPort;
	private String httpData;
	private boolean isMalicious;
	private List<String> attacksNames;

	public LogRequest_v2() {
	}

	public LogRequest_v2(String protocol, String source, String destination, String sourcePort, String destinationPort,
			String httpData, boolean isMalicious, List<String> attacksNames) {
		super();
		this.protocol = protocol;
		this.source = source;
		this.destination = destination;
		this.sourcePort = sourcePort;
		this.destinationPort = destinationPort;
		this.httpData = httpData;
		this.isMalicious = isMalicious;
		this.attacksNames = attacksNames;
	}

	public LogEntity_v2 toEntity(String userId) {
		LogEntity_v2 entity = new LogEntity_v2(userId, this.protocol, this.source, this.destination,
				this.sourcePort, this.destinationPort, this.httpData, this.isMalicious, this.attacksNames);
		return entity;
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

	public boolean isMalicious() {
		return isMalicious;
	}

	public void setMalicious(boolean isMalicious) {
		this.isMalicious = isMalicious;
	}

	public List<String> getAttacksNames() {
		return attacksNames;
	}

	public void setAttacksNames(List<String> attacksNames) {
		this.attacksNames = attacksNames;
	}

	@Override
	public String toString() {
		return "LogRequest_v2 [protocol=" + protocol + ", source=" + source + ", destination=" + destination
				+ ", sourcePort=" + sourcePort + ", destinationPort=" + destinationPort + ", httpData=" + httpData
				+ ", isMalicious=" + isMalicious + ", attacksNames=" + attacksNames + "]";
	}

}
