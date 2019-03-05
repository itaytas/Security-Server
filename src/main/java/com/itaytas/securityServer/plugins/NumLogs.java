package com.itaytas.securityServer.plugins;

public class NumLogs {
	
	private int numLogs;
	private int numDaysAgo;

	public NumLogs() {
	}

	public NumLogs(int numLogs, int numDaysAgo) {
		this.numLogs = numLogs;
		this.setNumDaysAgo(numDaysAgo);
	}

	public int getNumLogs() {
		return numLogs;
	}

	public void setNumLogs(int numLogs) {
		this.numLogs = numLogs;
	}

	public int getNumDaysAgo() {
		return numDaysAgo;
	}

	public void setNumDaysAgo(int numDaysAgo) {
		this.numDaysAgo = numDaysAgo;
	}

	@Override
	public String toString() {
		return "NumLogs [numLogs=" + numLogs + ", numDaysAgo=" + numDaysAgo + "]";
	}
	
	
}
