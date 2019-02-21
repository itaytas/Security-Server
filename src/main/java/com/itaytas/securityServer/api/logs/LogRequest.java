package com.itaytas.securityServer.api.logs;

public class LogRequest {

	// User Identifiers
	private String username;

	// Logs Identifiers
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
	private String isMalicious;

	public LogRequest() {
	}

	public LogRequest(String username, String protocol, String source, String destination, String sourcePort,
			String destinationPort, String httpMethod, String resource, String httpProtocol, String userAgent,
			String referer, String contentType, String contentLength, String data, String isMalicious) {
		this.username = username;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getIsMalicious() {
		return isMalicious;
	}

	public void setIsMalicious(String isMalicious) {
		this.isMalicious = isMalicious;
	}

}
