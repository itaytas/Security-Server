package com.itaytas.securityServer.logic.log;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.itaytas.securityServer.logic.audit.DateAudit;

@Entity
@Document(collection="logs")
public class LogEntity_v1 extends DateAudit {

	private static final long serialVersionUID = -4630112483209068719L;

	private String logId;
	private String userId;

	// LogRequest Attributes
	private String protocol;
	private String source;
	private String destination;
	private String sourcePort;
	private String destinationPort;
	private String httpMethod;
	private String resource;
	private String httpProtocol;
	private String userAgent;
	private String referer;
	private String contentType;
	private String contentLength;
	private String data;
	private boolean isMalicious;

	public LogEntity_v1() {
	}

	public LogEntity_v1(String userId, String protocol, String source, String destination, String sourcePort,
			String destinationPort, String httpMethod, String resource, String httpProtocol, String userAgent,
			String referer, String contentType, String contentLength, String data, boolean isMalicious) {
		this.userId = userId;
		this.protocol = protocol;
		this.source = source;
		this.destination = destination;
		this.sourcePort = sourcePort;
		this.destinationPort = destinationPort;
		this.httpMethod = httpMethod;
		this.resource = resource;
		this.httpProtocol = httpProtocol;
		this.userAgent = userAgent;
		this.referer = referer;
		this.contentType = contentType;
		this.contentLength = contentLength;
		this.data = data;
		this.isMalicious = isMalicious;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public String getLogId() {
		return logId;
	}

	public void setLogId(String logId) {
		this.logId = logId;
	}

	@NotBlank
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

	public String getHttpMethod() {
		return httpMethod;
	}

	public void setHttpMethod(String httpMethod) {
		this.httpMethod = httpMethod;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	public String getHttpProtocol() {
		return httpProtocol;
	}

	public void setHttpProtocol(String httpProtocol) {
		this.httpProtocol = httpProtocol;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public String getReferer() {
		return referer;
	}

	public void setReferer(String referer) {
		this.referer = referer;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getContentLength() {
		return contentLength;
	}

	public void setContentLength(String contentLength) {
		this.contentLength = contentLength;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public boolean isMalicious() {
		return isMalicious;
	}

	public void setMalicious(boolean isMalicious) {
		this.isMalicious = isMalicious;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "LogEntity [logId=" + logId + ", userId=" + userId + ", protocol=" + protocol + ", source=" + source
				+ ", destination=" + destination + ", sourcePort=" + sourcePort + ", destinationPort=" + destinationPort
				+ ", httpMethod=" + httpMethod + ", resource=" + resource + ", httpProtocol=" + httpProtocol
				+ ", userAgent=" + userAgent + ", referer=" + referer + ", contentType=" + contentType
				+ ", contentLength=" + contentLength + ", data=" + data + ", isMalicious=" + isMalicious + "]";
	}
	
}
