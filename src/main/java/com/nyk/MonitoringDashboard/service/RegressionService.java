package com.nyk.MonitoringDashboard.service;

import java.util.List;

import com.nyk.MonitoringDashboard.bean.Regression;

public interface RegressionService {
	
	List<Regression> getTable();
	
	List <Regression> getMonthlyStats();

}
