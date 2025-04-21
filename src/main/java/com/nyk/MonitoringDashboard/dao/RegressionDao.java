package com.nyk.MonitoringDashboard.dao;

import java.util.List;

import com.nyk.MonitoringDashboard.bean.Regression;

public interface RegressionDao {

	List <Regression> getTable();
	
	List <Regression> getMonthlyStats();

}
