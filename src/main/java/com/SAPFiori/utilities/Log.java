package com.SAPFiori.utilities;

//import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.SAPFiori.BaseClass.BaseClass;

public class Log extends BaseClass
{
	
	// Initialize Log4j logs
	public static Logger Log = org.apache.log4j.Logger.getLogger(Log.class.getName());

	public static void startTestCase(String sTestCaseName)
	{		  
		Log.info("====================================="+sTestCaseName+" TEST START=========================================");
	}

	public static void endTestCase(String sTestCaseName)
	{
		Log.info("====================================="+sTestCaseName+" TEST END=========================================");
	}
	
	// Need to create below methods, so that they can be called  

	 public static void info(String message) 
	 {

			Log.info(message);

	 }

	 public static void warn(String message) 
	 {

		 Log.warn(message);

	 }

	 public static void error(String message, WebDriver driver) throws Throwable {

	    Log.error(message);

		}

	 public static void fatal(String message, WebDriver driver) throws Throwable {

	    Log.fatal(message);

		}

	 public static void debug(String message) {

	    Log.debug(message);

		}
	
}
