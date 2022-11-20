package com.employeeapi.base;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestBase {

	public static RequestSpecification httprequest;
	public static Response response;
	public String empID="54769"; // Hard coded - Input for get details of Single Employee and Update employee
	
	public Logger logger;
	
	@BeforeClass
	public void setup() {
		
		logger=Logger.getLogger("EmployeesRestAPI"); // added logger
		PropertyConfigurator.configure("Log4j.properties"); // added logger
		logger.setLevel(Level.DEBUG);
	}
}
