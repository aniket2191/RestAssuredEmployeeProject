package com.employeeapi.TestCase;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employeeapi.Base.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC002_Get_Single_EmployeeData extends TestBase{
	
	@BeforeClass
	public void getSingleEmployeeData() throws InterruptedException
	{
		logger.info("***********Started TC002_Get_Single_EmployeeData*********");
		RestAssured.baseURI="https://gorest.co.in/public/v2";
		httpRequest=RestAssured.given();
	    response=httpRequest.request(Method.GET, "/users/"+empId);
	    
	   // System.out.println("Response : "+response.getBody().asString());	
	    Thread.sleep(3000);
	}
	
	@Test
	public void checkResponseBody()
	{
		logger.info("****Checking Response Body****");
		
		String resBody=response.getBody().asString();
		logger.info("Response Body==>"+resBody);
		assertTrue(resBody!=null);
	}
	
	@Test
	public void checkStatusCode()
	{
		logger.info("****Checking Status Code****");
		
		int statusCode=response.getStatusCode();
		logger.info("Status Code==>"+statusCode);
		assertEquals(statusCode, 200);
	}
	
	@Test
	public void checkStatusLine()
	{
		logger.info("****Checking Status Line****");
		
		String statusLine=response.getStatusLine();
		logger.info("Status Code==>"+statusLine);
		assertEquals(statusLine, "HTTP/1.1 200 OK");
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
		assertEquals(contentEncoding, "gzip");
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
		logger.info("***********Finished TC002_Get_Single_EmployeeData*********");
	}
	
}
