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

import com.nyk.MonitoringDashboard.bean.Regression;
import com.nyk.MonitoringDashboard.dao.RegressionDao;

@Repository
public class RegressionDaoImpl extends JdbcDaoSupport implements RegressionDao{

	@Autowired
	DataSource dataSource;

	@PostConstruct
	private void initialize() {
		setDataSource(dataSource);
	}


	@Override
	public List<Regression> getTable() {

		String sql = "select * from Execution_failure_json_data where created_at >= NOW() - INTERVAL 24 HOUR order by created_at asc";
		return getJdbcTemplate().query(sql,  new RowMapper<Regression>(){
			@Override
			public Regression mapRow(ResultSet rs, int rowNum) throws SQLException {

				Regression nyk = new Regression();
				nyk.setJenkinsJobName(rs.getString(1));
				nyk.setBuildNo(rs.getString(2));
				nyk.setChannel(rs.getString(3));
				nyk.setEnv(rs.getString(4));
				nyk.setFailureJson(rs.getString(5));
				nyk.setCreatedTime(rs.getString(6));
				nyk.setTotalTests(rs.getString(7));
				nyk.setFailedTests(rs.getInt(8));
				nyk.setPassedTests(rs.getInt(9));

				return nyk;
			}
		});
	}

	@Override
	public List<Regression> getMonthlyStats() {

		String sql = " select * from Execution_failure_json_data where (jenkins_job_name = 'CD_React_Automation' or jenkins_job_name = 'CD_App_Automation') and created_at >= NOW() - INTERVAL 160 HOUR order by created_at asc";
		return getJdbcTemplate().query(sql,  new RowMapper<Regression>(){
			@Override
			public Regression mapRow(ResultSet rs, int rowNum) throws SQLException {

				Regression nyk = new Regression();
				nyk.setJenkinsJobName(rs.getString(1));
				nyk.setBuildNo(rs.getString(2));
				nyk.setChannel(rs.getString(3));
				nyk.setEnv(rs.getString(4));
				nyk.setFailureJson(rs.getString(5));
				nyk.setCreatedTime(rs.getString(6));
				nyk.setTotalTests(rs.getString(7));
				nyk.setFailedTests(rs.getInt(8));
				nyk.setPassedTests(rs.getInt(9));

				return nyk;
			}
		});

	}
}
