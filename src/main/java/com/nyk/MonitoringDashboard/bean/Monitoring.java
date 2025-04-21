package com.nyk.MonitoringDashboard.bean;

public class Monitoring {
	
	private String jobName = "";
	private int status = 0;
	private String result = "";
	private String durationTime = "";
	private String createdTime = "";
	
	public Monitoring() {
		super();
	}


	public int getStatus() {
		return status;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getDurationTime() {
		return durationTime;
	}

	public void setDurationTime(String durationTime) {
		this.durationTime = durationTime;
	}

	public void setStatus(int i) {
		this.status = i;
		
	}

	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

}
