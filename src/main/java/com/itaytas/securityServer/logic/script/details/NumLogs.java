package com.itaytas.securityServer.logic.script.details;

public class NumLogs {
	
	private int numLogs;

	public NumLogs() {
	}

	public NumLogs(int numLogs) {
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
		return "NumLogs [numLogs=" + numLogs + "]";
	}
	
}
