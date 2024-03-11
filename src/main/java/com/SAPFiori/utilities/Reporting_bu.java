package com.SAPFiori.utilities;

//Listener class used to generate Extent reports

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
//import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

//import com.WebShopDemo.ActionDriver.Action;
import com.SAPFiori.BaseClass.BaseClass;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
//import com.aventstack.extentreports.rreporter.ExtentHtmlReporter;
//import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Reporting_bu extends TestListenerAdapter
{
	//public ExtentHtmlReporter htmlReporter;
	//public ExtentHtmlReporter htmlReporter1;
	public ExtentReports extent;
	public ExtentReports extent1;
	public ExtentTest logger;
	public ExtentTest logger1;
		
	public void onStart(ITestContext testContext)
	{
		//String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//time stamp
		//String repName="Test-Report-"+timeStamp+".html";
		String repName1="AutoTest-Report.html";
		
		/*htmlReporter=new ExtentHtmlReporter(System.getProperty("user.dir")+ "/test-output/"+repName);//specify location of the report
		htmlReporter.loadXMLConfig(System.getProperty("user.dir")+ "/extent-config.xml");
	
		extent=new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host name","localhost");
		extent.setSystemInfo("Environment","QA");
		extent.setSystemInfo("user",System.getProperty("user.name"));
		
		htmlReporter.config().setDocumentTitle("WebShop Test Project"); // Tile of report
		htmlReporter.config().setReportName("Functional Test Automation Report"); // name of the report
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP); //location of the chart
		htmlReporter.config().setTheme(Theme.DARK);*/
		
		//htmlReporter1=new ExtentHtmlReporter(System.getProperty("user.dir")+ "\\test-output\\AutoReport\\"+repName1);//specify location of the report with no timestamp
		//htmlReporter1.loadXMLConfig(System.getProperty("user.dir")+ "\\extent-config.xml");		
		System.out.println(System.getProperty("user.dir")+ "\\test-output\\AutoReport\\"+repName1);
		extent1=new ExtentReports();		
		//extent1.attachReporter(htmlReporter1);
		extent1.setSystemInfo("OS: ",System.getProperty("os.name"));
		extent1.setSystemInfo("OS Ver.:",System.getProperty("os.version"));  
		extent1.setSystemInfo("Host name","localhost");
		extent1.setSystemInfo("Environemnt","QA");
		extent1.setSystemInfo("user",System.getProperty("user.name"));	
	
		
		//htmlReporter1.config().setDocumentTitle("eCommerce Test Project"); // Tile of report
		//htmlReporter1.config().setReportName("Functional Test Automation Report"); // name of the report
		//htmlReporter1.config().setTestViewChartLocation(ChartLocation.TOP); //location of the chart
		//htmlReporter1.config().setTheme(Theme.DARK);
	}
	
	public void onTestSuccess(ITestResult tr)
	{
		//logger=extent.createTest("Test: "+tr.getName()); // create new entry in th report
		/*logger=extent.createTest("Test: "+tr.getMethod());
		logger.log(Status.PASS,MarkupHelper.createLabel(tr.getName(),ExtentColor.GREEN)); // send the passed information to the report with GREEN color highlighted*/
		
		logger1=extent1.createTest("Test: "+tr.getMethod());
		logger1.log(Status.PASS,MarkupHelper.createLabel(tr.getName(),ExtentColor.GREEN)); // send the passed information to the report with GREEN color highlighted
	}
	
	public void onTestFailure(ITestResult tr)
	{
		/*String timeStamp = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());//time stamp
		logger=extent.createTest("Test: "+tr.getMethod()); // create new entry in the report. add the name of the test case and the class\method (sub-test)
		//logger=extent.createTest(tr.getName()); // create new entry in the report. add the name of the test case and the class\method (sub-test)
		logger.log(Status.FAIL,MarkupHelper.createLabel(tr.getName(),ExtentColor.RED)); // send the passed information to the report with GREEN color highlighted
		logger.log(Status.INFO, tr.getThrowable()); //log the error*/
		
		String timeStamp1 = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());//time stamp
		logger1=extent1.createTest("Test: "+tr.getMethod()); // create new entry in the report. add the name of the test case and the class\method (sub-test)
		//logger1=extent.createTest(tr.getName()); // create new entry in the report. add the name of the test case and the class\method (sub-test)
		logger1.log(Status.FAIL,MarkupHelper.createLabel(tr.getName(),ExtentColor.RED)); // send the passed information to the report with GREEN color highlighted
		logger1.log(Status.INFO, tr.getThrowable()); //log the error
		
		
		/*ITestNGMethod s = tr.getMethod();
		String[] str1 = s.split(" ");
		for (int i = 0; i < str1.length; i++) {
			System.out.println(str1[i]); // output
			//Java
			//String
			//Split
			//Example*/
		
		//String screenshotPath=System.getProperty("user.dir")+"\\Screenshots\\Fail-"+tr.getName()+"_"+timeStamp+".png";
		String screenshotPath1=System.getProperty("user.dir")+"\\test-output\\AutoReport\\Fail\\Fail-"+tr.getName()+"_"+timeStamp1+".png";
		try 
		{
			//Action.captureScreen(BaseClass.driver, tr.getName());
			/*TakesScreenshot ts = (TakesScreenshot) BaseClass.driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			File target = new File(screenshotPath);
			System.out.println(source);
			System.out.println(target);
			FileUtils.copyFile(source, target);*/
	
			//Action.captureScreen(BaseClass.driver, tr.getName());
			TakesScreenshot ts1 = (TakesScreenshot) BaseClass.getDriver();
			File source1 = ts1.getScreenshotAs(OutputType.FILE);
			File target1 = new File(screenshotPath1);
			System.out.println(source1);
			System.out.println(target1);
			FileUtils.copyFile(source1, target1);	
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*File f = new File(screenshotPath); 
		if(f.exists())
		{
		try {
			logger.fail("Screenshot is below:" + logger.addScreenCaptureFromPath(screenshotPath));
			} 
		catch (IOException e) 
				{
				e.printStackTrace();
				}
		}*/
		
		File f1 = new File(screenshotPath1); 
		if(f1.exists())
		{
		logger1.fail("Screenshot is below:" + logger1.addScreenCaptureFromPath(screenshotPath1)); //add the screencapture to the html report (extent)
		}
	}
	
	public void onTestSkipped(ITestResult tr)
	{
		/*logger=extent.createTest(tr.getName()); // create new entry in th report
		logger.log(Status.SKIP,MarkupHelper.createLabel(tr.getName(),ExtentColor.ORANGE));*/
		
		logger1=extent1.createTest(tr.getName()); // create new entry in th report
		logger1.log(Status.SKIP,MarkupHelper.createLabel(tr.getName(),ExtentColor.ORANGE));
	}
	
	public void onFinish(ITestContext testContext)
	{
		extent1.flush();
	}
}
