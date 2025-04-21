package com.nyk.MonitoringDashboard.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.nyk.MonitoringDashboard.bean.Monitoring;
import com.nyk.MonitoringDashboard.bean.Regression;
import com.nyk.MonitoringDashboard.service.MonitoringService;
import com.nyk.MonitoringDashboard.service.RegressionService;

@Controller()
public class MonitoringDashboardController {

	@Autowired
	MonitoringService service1;

	@Autowired
	RegressionService service2;


	@GetMapping(value = "/index")
	public String getHomepage() {
		return "index";
	}

	@GetMapping(value = "/getalldata")
	public String getAllData(ModelMap model) {
		List <Monitoring> tableData = service1.getTable();
		model.put("tableData", tableData);		
		return "getalldata";
	}

	@GetMapping("/getstatus")
	public String showCharts(Model model) {
		List<Monitoring> jobsList = service1.getJobsList();
		List<Monitoring> dataObjects = null;
		Map<String , Map> barMap = new LinkedHashMap<String, Map>();
		Map<String , ArrayList<String>> pieMap = new LinkedHashMap<String, ArrayList<String>>();


		for(Monitoring data:jobsList) {

			Map<String , Integer> m1 = new LinkedHashMap<String, Integer>();
			ArrayList<String> m2 = new ArrayList<String>();

			dataObjects= service1.getJobStatus(data.getJobName());
			for(Monitoring t1:dataObjects) {
				m1.put(t1.getCreatedTime().substring(5, 16), t1.getStatus());
				m2.add(t1.getResult());
			}
			barMap.put(data.getJobName(), m1);
			pieMap.put(data.getJobName(), m2);
		}
		model.addAttribute("barMap", barMap);
		model.addAttribute("pieMap", pieMap);

		return "job-status";
	}

	@GetMapping(value = "/RegressionStats")
	public String getRegressionData(ModelMap model) {
		List<Regression> tableData = service2.getTable();

		Map <String , ArrayList<Map>> m1 = new LinkedHashMap<String, ArrayList<Map>>();
		for(Regression td:tableData) {
			if(td.getJenkinsJobName().equalsIgnoreCase("CD_React_Automation") || td.getJenkinsJobName().equalsIgnoreCase("CD_App_Automation") && !td.getChannel().equalsIgnoreCase("nykaaFashionIOS") && !td.getChannel().equalsIgnoreCase("nykaaFashionAndroid")) {
				String key = td.getJenkinsJobName()+"-"+td.getBuildNo()+" : "+td.getEnv().toUpperCase(); //m1 key name

				ArrayList <Map>l2;
				if(m1.get(key)==null) {
					l2 = new ArrayList<Map>();
				}
				else {
					l2 = m1.get(key);
				}			
				Map <String , List<Integer>> m2 = new LinkedHashMap<String, List<Integer>>();
				String key2 = td.getChannel(); //m2 key name

				ArrayList <Integer>l1 = new ArrayList<Integer>();
				l1.add(td.getTotalTests());
				l1.add(td.getPassedTests());
				l1.add(td.getFailedTests());

				m2.put(key2, l1);
				l2.add(m2);
				m1.put(key, l2);			
			}}

		List<Regression> tableData1 = service2.getMonthlyStats();

		Map <String , Map> reactProd = new LinkedHashMap<String, Map>();
		Map <String , Map> reactSmoke = new LinkedHashMap<String, Map>();
		Map <String , Map> appLiveiOS = new LinkedHashMap<String, Map>();
		Map <String , Map> appLiveAndroid = new LinkedHashMap<String, Map>();


		Map <String , ArrayList<Integer>> prodPassFailData = new LinkedHashMap<String, ArrayList<Integer>>();
		Map <String , ArrayList<Integer>> smokePassFailData = new LinkedHashMap<String, ArrayList<Integer>>();
		Map <String , ArrayList<Integer>> iosPassFailData = new LinkedHashMap<String, ArrayList<Integer>>();
		Map <String , ArrayList<Integer>> andriodPassFailData = new LinkedHashMap<String, ArrayList<Integer>>();

		for(Regression td1 : tableData1) {
			if(td1.getJenkinsJobName().equalsIgnoreCase("CD_React_Automation") && td1.getEnv().equalsIgnoreCase("prod")){
				ArrayList  <Integer> Values = new ArrayList<Integer>();
				Values.add(td1.getTotalTests());
				Values.add(td1.getPassedTests());
				Values.add(td1.getFailedTests());
				// String keyName = "("+td1.getBuildNo()+") "+td1.getCreatedTime().substring(5, 10);
				String keyName = td1.getBuildNo();
				if(prodPassFailData.get(td1.getBuildNo()+"%") == null) {
					prodPassFailData.put(keyName, Values);
				}else {

					ArrayList dummy = prodPassFailData.get(keyName);
					ArrayList  <Integer> mergedlist = new ArrayList<Integer>();

					for (int i = 0; i < dummy.size(); i++) {
						int sum = (int)dummy.get(i) + Values.get(i);
						mergedlist.add(sum);
					}
					prodPassFailData.replace(keyName, mergedlist);
				}							
			}else if(td1.getJenkinsJobName().equalsIgnoreCase("CD_React_Automation") && td1.getEnv().equalsIgnoreCase("smoke")){
				ArrayList  <Integer> Values = new ArrayList<Integer>();
				Values.add(td1.getTotalTests());
				Values.add(td1.getPassedTests());
				Values.add(td1.getFailedTests());
				// String keyName = "("+td1.getBuildNo()+") "+td1.getCreatedTime().substring(5, 10);
				String keyName = td1.getBuildNo();
				if(smokePassFailData.get(keyName) == null) {
					smokePassFailData.put(keyName, Values);
				}else {

					ArrayList dummy = smokePassFailData.get(keyName);
					ArrayList  <Integer> mergedlist = new ArrayList<Integer>();

					for (int i = 0; i < dummy.size(); i++) {
						int sum = (int)dummy.get(i) + Values.get(i);
						mergedlist.add(sum);
					}
					smokePassFailData.replace(keyName, mergedlist);
				}				

			}else if(td1.getJenkinsJobName().equalsIgnoreCase("CD_App_Automation") && td1.getChannel().contains("IOS")){
				ArrayList  <Integer> Values = new ArrayList<Integer>();
				Values.add(td1.getTotalTests());
				Values.add(td1.getPassedTests());
				Values.add(td1.getFailedTests());
				// String keyName = "("+td1.getBuildNo()+") "+td1.getCreatedTime().substring(5, 10);
				String keyName = td1.getBuildNo();
				if(iosPassFailData.get(keyName) == null) {
					iosPassFailData.put(keyName, Values);
				}else {

					ArrayList dummy = iosPassFailData.get(keyName);
					ArrayList  <Integer> mergedlist = new ArrayList<Integer>();

					for (int i = 0; i < dummy.size(); i++) {
						int sum = (int)dummy.get(i) + Values.get(i);
						mergedlist.add(sum);
					}
					iosPassFailData.replace(keyName, mergedlist);
				}
			}else if(td1.getJenkinsJobName().equalsIgnoreCase("CD_App_Automation") && td1.getChannel().contains("Android")){
				ArrayList  <Integer> Values = new ArrayList<Integer>();
				Values.add(td1.getTotalTests());
				Values.add(td1.getPassedTests());
				Values.add(td1.getFailedTests());
				// String keyName = "("+td1.getBuildNo()+") "+td1.getCreatedTime().substring(5, 10);
				String keyName = td1.getBuildNo();
				if(andriodPassFailData.get(keyName) == null) {
					andriodPassFailData.put(keyName, Values);
				}else {

					ArrayList dummy = andriodPassFailData.get(keyName);
					ArrayList  <Integer> mergedlist = new ArrayList<Integer>();

					for (int i = 0; i < dummy.size(); i++) {
						int sum = (int)dummy.get(i) + Values.get(i);
						mergedlist.add(sum);
					}
					andriodPassFailData.replace(keyName, mergedlist);
				}
			}		
		}
		reactProd.put("CD_React_Automation(Production)", prodPassFailData);
		reactSmoke.put("CD_React_Automation(smoke)", smokePassFailData);
		appLiveiOS.put("CD_App_Automation(Android)", iosPassFailData);
		appLiveAndroid.put("CD_App_Automation(iOS)", andriodPassFailData);

		ArrayList<Map> data = new ArrayList<Map>();
		data.add(reactProd);
		data.add(reactSmoke);
		data.add(appLiveiOS);
		data.add(appLiveAndroid);

		model.put("regressionData", m1);
		model.put("reactProd", reactProd);
		model.put("reactSmoke", reactSmoke);
		model.put("appLiveiOS", appLiveiOS);
		model.put("appLiveAndroid", appLiveAndroid);
		return "RegressionStats";

	}

}
