package com.nyk.MonitoringDashboard.service;

import java.util.List;

import com.nyk.MonitoringDashboard.bean.Monitoring;

public interface MonitoringService {
	
	List<Monitoring> getTable();
	
	
	List<Monitoring> getJobsList();


	List<Monitoring> getJobStatus(String job);
	
	

}
