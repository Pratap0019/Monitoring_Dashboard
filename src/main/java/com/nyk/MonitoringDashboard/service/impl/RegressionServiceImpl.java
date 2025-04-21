package com.nyk.MonitoringDashboard.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nyk.MonitoringDashboard.bean.Regression;
import com.nyk.MonitoringDashboard.dao.RegressionDao;
import com.nyk.MonitoringDashboard.service.RegressionService;

@Service
public class RegressionServiceImpl implements RegressionService{
	
	@Autowired
	RegressionDao dao2;

	@Override
	public List<Regression> getTable() {		
		return dao2.getTable();
	}

	@Override
	public List<Regression> getMonthlyStats() {
		return dao2.getMonthlyStats();
	}

}
