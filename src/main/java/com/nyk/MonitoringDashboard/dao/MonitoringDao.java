package com.nyk.MonitoringDashboard.dao;

import java.util.List;

import com.nyk.MonitoringDashboard.bean.Monitoring;

public interface MonitoringDao {
	
	List<Monitoring> getTable();

	List<Monitoring> getJobsList();

	List<Monitoring> getJobsStatus(String job);

}
