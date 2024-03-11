package com.SAPFiori.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.SAPFiori.AUTActions.AUTActions;
import com.SAPFiori.BaseClass.BaseClass;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ExtentReportListener  implements ITestListener{
	protected static ExtentReports reports;
	protected static ExtentTest test;

	private static String resultpath = getResultPath();

	
	BaseClass baseClass;
	

	String ReportLocation = "test-output/AutoReport/" + resultpath + "/";
	
	public static void deleteDirectory(File directory) {
		if (directory.exists()) {
			File[] files = directory.listFiles();
			if (null != files) {
				for (int i = 0; i < files.length; i++) {
					System.out.println(files[i].getName());
					if (files[i].isDirectory()) {
						deleteDirectory(files[i]);
					} else {
						files[i].delete();
					}
				}
			}
		}
	}

	private static String getResultPath() {
		
		resultpath = "SAPFiori";//new SimpleDateFormat("yyyy-MM-dd hh-mm.ss").format(new Date());
		if (!new File(resultpath).isDirectory()) 
		{
			new File(resultpath);
		}
		return resultpath;
	}

	public void onTestStart(ITestResult result) {

		test = reports.startTest(result.getMethod().getMethodName());
		test.log(LogStatus.INFO, result.getMethod().getMethodName());
		AUTActions.LogIt(null, "*BEGIN test "+result.getMethod().getMethodName()+"*", "STARTTC");
		
		//System.out.println(result.getTestClass().getTestName());
		//System.out.println(result.getMethod().getMethodName());
	}

	public void onTestSuccess(ITestResult result) {
		test.log(LogStatus.PASS, "Test is pass");
		AUTActions.LogIt(null, "*END test "+result.getMethod().getMethodName()+"*", "ENDTC");

	}

	public void onTestFailure(ITestResult result) {
		test.log(LogStatus.FAIL, "Test is fail");
		try {
			//test.addScreenCapture(ElementActions.capture(BaseClass.getDriver()));
			test.log(LogStatus.FAIL,test.addScreenCapture(AUTActions.capture(BaseClass.getDriver(), result.getMethod().getMethodName())));	
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) {
		test.log(LogStatus.SKIP, "Test is skipped");

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) 
	{
		String timeStamp1 = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());//time stamp
		System.out.println(ReportLocation + "  ReportLocation");
		reports = new ExtentReports(ReportLocation + "ExtentReport-"+timeStamp1+".html");
		test = reports.startTest("");

	}

	public void onFinish(ITestContext context) {
		String testname = context.getName();
		//String Suitename = context.getSuite();
		AUTActions.LogIt(null, "*END testNG test: "+testname+", Suitename: "+context.getSuite()+"*", "ENDTC");
		reports.endTest(test);
		reports.flush();

	}
	


}
