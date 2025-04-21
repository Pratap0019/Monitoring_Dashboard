package com.nyk.MonitoringDashboard.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nyk.MonitoringDashboard.bean.Monitoring;
import com.nyk.MonitoringDashboard.dao.MonitoringDao;
import com.nyk.MonitoringDashboard.service.MonitoringService;

@Service
public class MonitoringServiceImpl implements MonitoringService{

	@Autowired
	MonitoringDao dao1;
	
	
	@Override
	public List<Monitoring> getTable() {
		return dao1.getTable();
	}


	@Override
	public List<Monitoring> getJobsList() {
		return dao1.getJobsList();
	}


	@Override
	public List<Monitoring> getJobStatus(String job) {
		return dao1.getJobsStatus(job);
	}

}
