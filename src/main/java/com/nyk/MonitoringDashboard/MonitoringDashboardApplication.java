package com.nyk.MonitoringDashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.nyk.MonitoringDashboard")
public class MonitoringDashboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(MonitoringDashboardApplication.class, args);
	}

}
