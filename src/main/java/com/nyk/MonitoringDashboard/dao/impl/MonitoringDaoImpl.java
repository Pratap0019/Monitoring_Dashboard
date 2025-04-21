package com.nyk.MonitoringDashboard.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.nyk.MonitoringDashboard.bean.Monitoring;
import com.nyk.MonitoringDashboard.dao.MonitoringDao;

@Repository
public class MonitoringDaoImpl extends JdbcDaoSupport implements MonitoringDao{

	
	@Autowired
	DataSource dataSource;

	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}
	
	@Override
	public List<Monitoring> getTable() {

		String sql = "select * from sale_monitoring_stats where jenkins_job_name LIke \"Sale%\"and updated_at >= NOW() - INTERVAL 12 HOUR order by updated_at desc";
		return getJdbcTemplate().query(sql,  new RowMapper<Monitoring>(){
			@Override
			public Monitoring mapRow(ResultSet rs, int rowNum) throws SQLException {

				Monitoring nyk = new Monitoring();
				nyk.setJobName(rs.getString(2));
				nyk.setDurationTime(rs.getString(7));
				nyk.setCreatedTime(rs.getString(9));
				nyk.setStatus(Integer.parseInt(rs.getString(4)));
				if(rs.getString(4).equals("1")) {
					nyk.setResult("pass");	
				}else if(rs.getString(4).equals("0")){
					nyk.setResult("fail");
				}else {
					nyk.setResult("skip");
				}
				return nyk;
			}
		});
	}

	@Override
	public List<Monitoring> getJobsList() {
		String sql = "select distinct jenkins_job_name from sale_monitoring_stats where jenkins_job_name LIke \"Sale%\"and updated_at >= NOW() - INTERVAL 12 HOUR";
		return getJdbcTemplate().query(sql,  new RowMapper<Monitoring>(){
			@Override
			public Monitoring mapRow(ResultSet rs, int rowNum) throws SQLException {

				Monitoring nyk = new Monitoring();
				nyk.setJobName(rs.getString(1));
				return nyk;
			}
		});
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<Monitoring> getJobsStatus(String jenkins_job_name) {
		String sql = "select result,updated_at from sale_monitoring_stats where jenkins_job_name=? and updated_at >= NOW() - INTERVAL 12 HOUR order by updated_at asc";

		return getJdbcTemplate().query(sql,new Object[] {jenkins_job_name },  new RowMapper<Monitoring>(){
			@Override
			public Monitoring mapRow(ResultSet rs, int rowNum) throws SQLException {

				Monitoring nyk = new Monitoring();
				nyk.setStatus(Integer.parseInt(rs.getString(1)));
				if(rs.getString(1).equals("1")) {
					nyk.setResult("pass");	
				}else if(rs.getString(1).equals("0")){
					nyk.setResult("fail");
				}else {
					nyk.setResult("skip");
				}				
				nyk.setCreatedTime(rs.getString(2));
				return nyk;
			}
		});
	}

}
