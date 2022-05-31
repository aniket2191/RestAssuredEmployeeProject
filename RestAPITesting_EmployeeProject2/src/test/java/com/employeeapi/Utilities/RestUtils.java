package com.employeeapi.Utilities;

import org.apache.commons.lang3.RandomStringUtils;

public class RestUtils {

	public static String empName()
	{
         return "rivika"+RandomStringUtils.randomAlphabetic(1);	   	
	}
	public static String empEmail()
	{
		return "rivika1"+RandomStringUtils.randomNumeric(1)+"@gmail.com";
	}

}
