package com.employeeapi.Base;


import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestBase {

	public RequestSpecification httpRequest;
	public Response response;
	public Logger logger;
	public int empId=2349; //hardcoded value

  @BeforeClass
  public void setUp() {
	  
   logger=Logger.getLogger("EmployeesLog");
   PropertyConfigurator.configure(".//logs/log4j.properties");
   logger.setLevel(Level.DEBUG);
   
  }
}
