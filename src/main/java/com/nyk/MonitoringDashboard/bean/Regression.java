package com.nyk.MonitoringDashboard.bean;

public class Regression {
	
	String jenkinsJobName = "";
	String buildNo = "";
	String channel = "";
	String env = "";
	String failureJson = "";
	String createdTime = "";
	int totalTests = 0;
	int failedTests = 0;
	int passedTests = 0;
	
	public String getJenkinsJobName() {
		return jenkinsJobName;
	}
	public void setJenkinsJobName(String jenkinsJobName) {
		this.jenkinsJobName = jenkinsJobName;
	}
	public String getBuildNo() {
		return buildNo;
	}
	public void setBuildNo(String buildNo) {
		this.buildNo = buildNo;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getEnv() {
		return env;
	}
	public void setEnv(String env) {
		this.env = env;
	}
	public String getFailureJson() {
		return failureJson;
	}
	public void setFailureJson(String failureJson) {
		this.failureJson = failureJson;
	}
	public String getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}
	public int getTotalTests() {
		return totalTests;
	}
	public void setTotalTests(String totalTests) {
		this.totalTests = Integer.parseInt(totalTests);
	}
	public int getFailedTests() {
		return failedTests;
	}
	public void setFailedTests(int failedTests) {
		this.failedTests = failedTests;
	}
	public int getPassedTests() {
		return passedTests;
	}
	public void setPassedTests(int passedTests) {
		this.passedTests = passedTests;
	}
	

}
