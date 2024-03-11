package com.SAPFiori.BaseClass;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;

import com.SAPFiori.AUTActions.AUTActions;
import com.SAPFiori.utilities.ExtentManager;
import com.SAPFiori.utilities.ExtentReportListener;
import com.SAPFiori.utilities.Log;

import io.github.bonigarcia.wdm.WebDriverManager;

@Listeners(ExtentReportListener.class)
public class BaseClass extends ExtentReportListener
{
	public static Properties prop;
	//public static WebDriver driver; 
	public static Logger Logger;
	
	//Declare ThreadLocal Driver Parallel testing using Java ThreadLocal Class
	public static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();
	public static WebDriver getDriver()
	{
		//get driver from threadlocalmap
		return driver.get();
	}
	
	@Parameters("browser")
	@BeforeMethod(groups = { "Smoke", "Sanity", "Regression", "DDT" })
	public void setup(String browser) throws IOException
	{
		launchApp(browser); 
	}
	
	@BeforeSuite(groups = { "Smoke", "Sanity", "Regression", "DDT"  })
	public void loadConfig()
	{
		ExtentManager.setExtent();
		Logger = org.apache.log4j.Logger.getLogger("eCommStore");
		PropertyConfigurator.configure("Log4j.properties");
		DOMConfigurator.configure("log4j.xml");
		try 
		{
			prop = new Properties();
			FileInputStream ip = new FileInputStream(
				System.getProperty("user.dir") + "\\Configuration\\config.properties");
			prop.load(ip);
			Log.info("BeforeSuite Loaded config file: "+ System.getProperty("user.dir") + "\\Configuration\\config.properties");
		
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void launchApp(String browserName) throws IOException 
	{
		//String browserName = prop.getProperty("browser");
		if (browserName.equalsIgnoreCase("Chrome")) 
		{
			//setup the download preferences and location. 
			//force chrome to use auto download rather than the save as dialog
			ChromeOptions options = new ChromeOptions();
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("download.default_directory", prop.getProperty("fileDownLoadLocation"));
			prefs.put("download.prompt_for_download", false); 
			prefs.put("profile.default_content_setting_values.notifications", 2); //0 = enable, 2 = disable ...this is for browser pop ups dialogs
			Log.info("File download location: "+prop.getProperty("fileDownLoadLocation"));
			options.setExperimentalOption("prefs", prefs);
			
			//WebDriverManager.chromedriver().setup();
			System.setProperty("webdriver.chrome.driver", prop.getProperty("webdriverExe"));
			// Set Browser to ThreadLocalMap
			driver.set(new ChromeDriver(options));

		} 
		else if (browserName.equalsIgnoreCase("FireFox")) 
		{
			WebDriverManager.firefoxdriver().setup();
			// Set Browser to ThreadLocalMap
			driver.set(new FirefoxDriver());

		} 
		else if (browserName.equalsIgnoreCase("IE")) 
		{
			WebDriverManager.iedriver().setup();
			driver.set(new InternetExplorerDriver());
		}
		
		AUTActions.implicitWait(getDriver(), 10);
		AUTActions.pageLoadTimeOut(getDriver(), 30);		
		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		getDriver().get(prop.getProperty("url"));
		Log.info("Browser "+browserName+" has been opened with URL: "+prop.getProperty("url"));

	}

	/*@Parameters("browser")
	@AfterMethod(groups = { "Smoke", "Sanity", "Regression", "DDT"  })
	public void tearDown(String browser)
	{
		getDriver().quit();
		AUTActions.LogIt(null, "Closed browser "+browser, "INFO");
	}*/	
	
	@AfterSuite(groups = { "Smoke", "Sanity", "Regression", "DDT" })
	public void aftersuite()
	{
		ExtentManager.endReport();
		Log.info("AfterSuite ExtentManager.endReport");

	}
}
