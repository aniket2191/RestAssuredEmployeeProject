package com.employeeapi.Utilities;



import org.testng.ITestContext;

import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class Listener extends TestListenerAdapter {
	
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extentReports;
	public ExtentTest extentTest;
	
	
	  public void onStart(ITestContext testContext) {
	   
		  //Reporter's basic config details set to Reporter 
		  sparkReporter=new ExtentSparkReporter(".//reports/EmpTesting.html");
		  sparkReporter.config().setDocumentTitle("Automation Report");
		  sparkReporter.config().setReportName("Rest Assuered API Testing");
		  sparkReporter.config().setTheme(Theme.DARK);
		  
		  //attaching report and setting system info to reports
		  extentReports=new ExtentReports();
		  extentReports.attachReporter(sparkReporter);
		  extentReports.setSystemInfo("Host Name", "localhost");
		  extentReports.setSystemInfo("Environmant", "QA");
		  extentReports.setSystemInfo("user", "aniket");


	  }
	  
	  public void onTestSuccess(ITestResult result) {

		 extentTest=extentReports.createTest(result.getName());//creating new entry
		 extentTest.log(Status.PASS,"Test case passed is : "+result.getName());//adding log details
		  
	  }


	  public void onTestFailure(ITestResult tr) {
		  extentTest=extentReports.createTest(tr.getName());//creating ner entry in report
		  extentTest.log(Status.FAIL, "Test Case is Failed"+tr.getName());//adding log details
		  extentTest.log(Status.FAIL, "Test Case is Failed"+tr.getThrowable());//adding exception

	  }


	  public void onTestSkipped(ITestResult tr) {
		  
		  extentTest=extentReports.createTest(tr.getName());
		  extentTest.log(Status.SKIP, "Test case is Skipped"+tr.getName());

	  }

	  public void onFinish(ITestContext testContext) {
		  
		  extentReports.flush();
		  
	  }

}
