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
import io.restassured.path.json.JsonPath;

public class TC005_DeleteSingle_EmployeeData extends TestBase{
	
	@BeforeClass
	public void getDeleteSingleEmployeeData() throws InterruptedException
	{
		logger.info("***********Started TC005_DeleteSingle_EmployeeData*********");
		RestAssured.baseURI="https://gorest.co.in/public/v2";
		httpRequest=RestAssured.given();
		httpRequest.auth().oauth2("e1717560f23d7df94cd4e46653f567819474f2412654d153083e789d0e6444cf");
		
	    response=httpRequest.request(Method.GET, "/users");
	    System.out.println("GetResBody=>"+response.getBody().asString());
	    
	    JsonPath jsonPath=response.jsonPath();
	    String empid=(jsonPath.get("[0].id")).toString();
	    response=httpRequest.request(Method.DELETE, "/users/"+empid);	
	    Thread.sleep(3000);
	}
	
	@Test
	public void checkResponseBody()
	{
		logger.info("****Checking Response Body****");
		
		String resBody=response.getBody().asString();
		logger.info("Response Body==>"+resBody);
		assertTrue(resBody.isEmpty());
		System.out.println("Sucessfully deleted..");
	}
	
	@Test
	public void checkStatusCode()
	{
		logger.info("****Checking Status Code****");
		
		int statusCode=response.getStatusCode();
		logger.info("Status Code==>"+statusCode);
		assertEquals(statusCode, 204);
	}
	
	@Test
	public void checkStatusLine()
	{
		logger.info("****Checking Status Line****");
		
		String statusLine=response.getStatusLine();
		logger.info("Status Code==>"+statusLine);
		assertEquals(statusLine, "HTTP/1.1 204 No Content");
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
		logger.info("***********Finished TC005_DeleteSingle_EmployeeData*********");
	}
	
}
