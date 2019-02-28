package com.itaytas.securityServer.logic.script.details;

public class SqlInjectionDetails {
	
	private int numLogs;

	public SqlInjectionDetails() {
	}

	public SqlInjectionDetails(int numLogs) {
		this.numLogs = numLogs;
	}

	public int getNumLogs() {
		return numLogs;
	}

	public void setNumLogs(int numLogs) {
		this.numLogs = numLogs;
	}

	@Override
	public String toString() {
		return "SqlInjectionDetails [numLogs=" + numLogs + "]";
	}
	
	
}
