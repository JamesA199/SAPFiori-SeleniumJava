package com.SAPFiori.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
//import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
/**
 * ExtentManager class is used for Extent Report
 *  
 */
public class ExtentManager_bu {
	
	//public static ExtentHtmlReporter htmlReporter; //create  html file
	public static ExtentReports extent; //build the reports
	public static ExtentTest test; //defines test. add logs
	
	public static void setExtent() {
		System.out.println("setExtent");
		//htmlReporter= new ExtentHtmlReporter(System.getProperty("user.dir")+"/test-output/ExtentReport/"+"MyReport_"+BaseClass.getCurrentTime()+".html");
		//htmlReporter= new ExtentHtmlReporter(System.getProperty("user.dir")+"/test-output/ExtentReport/eCommStore-Report.html");
		System.out.println(System.getProperty("user.dir")+"/test-output/ExtentReport/SAPFiori-Report.html");
		//htmlReporter.loadXMLConfig(System.getProperty("user.dir")+"/extent-config.xml");
		//htmlReporter.config().setDocumentTitle("Automation Test Report");
		//htmlReporter.config().setReportName("OrangeHRM Test Automation Report");
		//htmlReporter.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		//extent.attachReporter(htmlReporter);
		
		extent.setSystemInfo("OS: ",System.getProperty("os.name"));
		extent.setSystemInfo("OS Ver.:",System.getProperty("os.version"));  
		extent.setSystemInfo("Host name","localhost");
		extent.setSystemInfo("Environemnt","QA");
		extent.setSystemInfo("user",System.getProperty("user.name"));	
		extent.setSystemInfo("Browser", "Chrome");
		extent.setSystemInfo("Tester", "jAnderson");
		
		extent.setSystemInfo("HostName", "MyHost");
		extent.setSystemInfo("ProjectName", "SAPFiori Automation Project");
		extent.setSystemInfo("Tester", "jAnderson");
		extent.setSystemInfo("OS", "Win11");


	}
	public static void endReport() {
		extent.flush();
	}
}
