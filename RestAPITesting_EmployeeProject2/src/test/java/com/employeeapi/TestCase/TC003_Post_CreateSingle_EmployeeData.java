package com.employeeapi.TestCase;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.Base.TestBase;
import com.employeeapi.Utilities.RestUtils;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC003_Post_CreateSingle_EmployeeData extends TestBase{
	
	String empName=RestUtils.empName();
	String empEmail=RestUtils.empEmail();
	
	@BeforeClass
	public void createSingleEmployeeData() throws InterruptedException
	{
		logger.info("***********Started TC003_Post_CreateSingle_EmployeeData*********");
		RestAssured.baseURI="https://gorest.co.in/public/v2";
		httpRequest=RestAssured.given();
		httpRequest.auth().oauth2("e1717560f23d7df94cd4e46653f567819474f2412654d153083e789d0e6444cf");
		
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("name", empName);
		jsonObject.put("email", empEmail);
		jsonObject.put("gender", "female");
		jsonObject.put("status", "active");
		
		httpRequest.body(jsonObject.toJSONString());
		httpRequest.header("Content-Type", "application/json");
	
	    response=httpRequest.request(Method.POST, "/users");
	    
	   // System.out.println("Response : "+response.getBody().asString());	
	    Thread.sleep(3000);
	}
	
	@Test
	public void checkResponseBody()
	{
		logger.info("****Checking Response Body****");
		
		String resBody=response.getBody().asString();
		logger.info("Response Body==>"+resBody);
		assertEquals(resBody.contains(empName),true);
		assertEquals(resBody.contains(empEmail),true);
	}
	
	@Test
	public void checkStatusCode()
	{
		logger.info("****Checking Status Code****");
		
		int statusCode=response.getStatusCode();
		logger.info("Status Code==>"+statusCode);
		assertEquals(statusCode, 201);
	}
	
	@Test
	public void checkStatusLine()
	{
		logger.info("****Checking Status Line****");
		
		String statusLine=response.getStatusLine();
		logger.info("Status Code==>"+statusLine);
		assertEquals(statusLine, "HTTP/1.1 201 Created");
	}

	@Test
	public void checkContentType()
	{
		logger.info("****Checking Content Type****");
		
		String contentType=response.contentType();
		logger.info("Content Type==>"+contentType);
		assertEquals(contentType, "application/json; charset=utf-8");
	}
	
	@Test
	public void checkContentEncoding()
	{
		logger.info("****Checking Content Encoding****");
		
		String contentEncoding=response.getHeader("Content-Encoding");
		logger.info("Content Encoding==>"+contentEncoding);
		assertEquals(contentEncoding, null);
	}
	
	@Test
	public void checkServer()
	{
		logger.info("****Checking Server****");
		
		String server=response.getHeader("Server");
		logger.info("Server==>"+server);
		assertEquals(server, "nginx");
	}
	
	@AfterClass
	public void tearDown()
	{
		logger.info("***********Finished TC003_Post_CreateSingle_EmployeeData*********");
	}
	
}
