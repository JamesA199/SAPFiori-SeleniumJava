package com.SAPFiori.AUTActions;


import static org.testng.Assert.assertEquals;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
//import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

//import com.SAPFiori.utilities.Log;
import com.relevantcodes.extentreports.LogStatus;
import com.SAPFiori.AUTActions.AUTActions;
//import com.mystore.actioninterface.ActionInterface;
import com.SAPFiori.BaseClass.BaseClass;
import com.SAPFiori.utilities.Log;

//public class Action extends BaseClass implements ActionInterface {
public class AUTActions extends BaseClass 
{
    
	public static void scrollByVisibilityOfElement(WebDriver driver, WebElement ele) 
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", ele);
	}

	public static void assertEqualsTitle(String strPageTitle) 
	{
	    assertEquals(strPageTitle, getDriver().getTitle());
	}
	
	public static void switchTabs(int tabIndex, String strtabName) 
	{
		
		try 
		{
			//Get the current window title
			ArrayList<String> tabs = new ArrayList<String> (getDriver().getWindowHandles());	
			Log.info("There are '"+tabs.size()+"' tabs open.");
			Log.info("Current open tab: "+getDriver().getTitle());
			getDriver().switchTo().window(tabs.get(tabIndex));

		  	if (strtabName.equals(getDriver().getTitle())) //check here if the tab name is correct if not fail
			{
		  		Log.info("Switched to tab '"+strtabName+"' using tab index of: "+tabIndex);
			}
		  	else
		  	{
		  		Log.warn("***Warning-switch to tab '"+strtabName+"', but the tab name does not match expected tab name: "+strtabName);
		  	}			

		} 
		catch (Exception e) //this is catching any assert errors e.g. out of bounds tab number due to incorrect index
		{
			Log.info("***Fail-Could NOT switch to tab '"+strtabName+"' using tab index of: "+tabIndex);
			Assert.fail("***Fail-Could NOT switch to tab '"+strtabName+"' using tab index of: "+tabIndex);
		} 

	}	
	public static void closeBrsTab(int tabIndex, String strtabName) 
	{
		
		AUTActions.implicitWait(getDriver(), 10);

		try 
		{
			//Get the current window title
			ArrayList<String> tabs = new ArrayList<String> (getDriver().getWindowHandles());	
			Log.info("There are '"+tabs.size()+"' tabs open.");
			Log.info("Current open tab: "+getDriver().getTitle());
			getDriver().switchTo().window(tabs.get(tabIndex));
			
		  	if (strtabName.equals(getDriver().getTitle())) //check here if the tab name is correct if not fail
			{
		  		Log.info("Switched to tab '"+strtabName+"' using tab index of: "+tabIndex);
			}
		  	else
		  	{
		  		Log.warn("***Warning-switch to tab '"+strtabName+"'. Tab name does not match expected tab name: "+strtabName);
		  	}	
		  	
			getDriver().switchTo().window(tabs.get(tabIndex)).close();
			Log.info("Closed tab '"+strtabName+"' using tab index of: "+tabIndex);
		} 
		catch (Exception e) //this is catching any assert errors e.g. out of bounds tab number due to incorrect index
		{
			Log.info("***Fail-Could NOT close tab '"+strtabName+"' using tab index of: "+tabIndex);
			Assert.fail("***Fail-Could NOT close tab '"+strtabName+"' using tab index of: "+tabIndex);
		} 
	
		//Get the current window title
		/*ArrayList<String> tabs = new ArrayList<String> (getDriver().getWindowHandles());
		Log.info("There are '"+tabs.size()+"' tabs open.");
		Log.info("Current open tab: "+getDriver().getTitle());
		//switch and focus on the tab  (tabs numbers start at 0) 
		getDriver().switchTo().window(tabs.get(tabIndex));

	  	if (tabName.equals(getDriver().getTitle()))
		{
	  		Log.info("Switched to tab '"+getDriver().getTitle()+"' using tab index of: "+tabIndex);
		}
	  	else
	  	{
	  		Log.info("***Fail-Could NOT switch to tab '"+tabName+"'");
			//Assert.assertTrue(false);
			Assert.fail("***Fail-Could NOT switch to tab '"+tabName+"' using tab index of: "+tabIndex);
	  	}*/

	}		

	public static void click(WebDriver driver, WebElement ele, String strWebElmentType) 
	{

		boolean flag = false;
		try {
			AUTActions.explicitWait(getDriver(), ele, 60);
			flag = ele.isDisplayed();
			Actions act = new Actions(driver);
			act.moveToElement(ele).click().build().perform();
			flag = true;

		} catch (Exception e) {
			AUTActions.LogIt(ele, "Is not displayed: "+strWebElmentType, "FAIL");
			//Log.info("Is not displayed: "+strWebElmentType+" WebElement: "+ele);
			//System.out.println("Is not displayed: "+strWebElmentType+" WebElement: "+ele);
			flag = false;
		} finally {
			if (flag) {

				//System.out.println("Clicked on "+strWebElmentType+" WebElement: "+ele);
				//Log.info("Clicked on "+strWebElmentType+" WebElement: "+ele);
				AUTActions.LogIt(ele, "Clicked on "+strWebElmentType, "INFO");
			} else {
				AUTActions.LogIt(ele, "***Fail-Unable to click: "+strWebElmentType, "FAIL");
				//System.out.println("Unable to click: "+strWebElmentType+" WebElement: "+ele);
				//Log.info("***Fail-Unable to click: "+strWebElmentType+" WebElement: "+ele);
	    		//Assert.assertTrue(false);
	    		//Assert.fail("***Fail-Unable to click: "+strWebElmentType+" WebElement: "+ele);
			}

		}
	}

	/*public static String getLabel(WebElement ele) 
	{

		boolean flag = false;
		
		try 
		{
			AUTActions.explicitWait(getDriver(), ele, 10);
			ele.isDisplayed();
			String label = ele.getText();
			flag = true;
		} 
		catch (Exception e) 
		{
			AUTActions.LogIt(ele, "***FAIL element NOT found: "+ele, "FAIL");
			// System.out.println("Location not found: "+locatorName);
			//Log.info("***FAIL Location NOT found element: "+ele);
    		//test.log(LogStatus.ERROR, "***Fail-Location NOT found element: "+ele);
    		//Assert.fail("***Fail-Location NOT found element: "+ele);
			flag = false;
		} 
		finally 
		{
			if (flag) 
			{

				AUTActions.LogIt(ele, "PASS found element: "+ele, "PASS");
				//System.out.println("Successfully Found element at");
				//Log.info("Successfully Found element at: "+ele);
	    		//test.log(LogStatus.PASS, "Successfully Found element at: "+ele);
				
			} 
			else 
			{
				AUTActions.LogIt(ele, "***Fail Unable to find element at: "+ele, "FAIL");
				//System.out.println("Unable to locate element at");
				//Log.info("***Fail-Unable to locate element at: "+ele);
	    		//test.log(LogStatus.ERROR, "***Fail-unable to locate element at: "+ele);
	    		//Assert.fail("***Fail-unable to locate element at: "+ele);
			}
			
		}

	}*/
	
	public static boolean findElement(WebDriver driver, WebElement ele) 
	{
		boolean flag = false;
		try {
			AUTActions.explicitWait(getDriver(), ele, 10);
			ele.isDisplayed();
			flag = true;
		} 
		catch (Exception e) 
		{
			AUTActions.LogIt(ele, "***FAIL element NOT found: "+ele, "FAIL");
			// System.out.println("Location not found: "+locatorName);
			//Log.info("***FAIL Location NOT found element: "+ele);
    		//test.log(LogStatus.ERROR, "***Fail-Location NOT found element: "+ele);
    		//Assert.fail("***Fail-Location NOT found element: "+ele);
			flag = false;
		} finally {
			if (flag) 
			{
				AUTActions.LogIt(ele, "Successfully found element at: "+ele, "PASS");
				//System.out.println("Successfully Found element at");
				//Log.info("Successfully Found element at: "+ele);
	    		//test.log(LogStatus.PASS, "Successfully Found element at: "+ele);
			} else 
			{
				AUTActions.LogIt(ele, "***Fail Unable to find element at: "+ele, "FAIL");
				//System.out.println("Unable to locate element at");
				//Log.info("***Fail-Unable to locate element at: "+ele);
	    		//test.log(LogStatus.ERROR, "***Fail-unable to locate element at: "+ele);
	    		//Assert.fail("***Fail-unable to locate element at: "+ele);
			}
		}
		return flag;
	}

	public static boolean isDisplayed(WebDriver driver, WebElement ele) 
	{
		boolean flag = false;
		//ElementActions.explicitWait(getDriver(), ele, 10);
		flag = findElement(driver, ele);
		if (flag) {
			flag = ele.isDisplayed();
			if (flag) 
			{
				AUTActions.LogIt(ele, "The element is displayed: ", "PASS");
				//System.out.println("The element is Displayed: "+ele);
				//Log.info("The element is Displayed: "+ele);
	    		//test.log(LogStatus.PASS, "The element is Displayed: "+ele);
	    		
				
			} 
			else 
			{
				AUTActions.LogIt(ele, "***Fail-The element is NOT displayed: ", "FAIL");
				//System.out.println("The element is NOT displayed: "+ele);
				//Log.info("***Fail-The element is NOT displayed: "+ele);
	    		//test.log(LogStatus.FAIL, "***Fail-The element is NOT displayed: "+ele);
	    		//Assert.fail("***Fail-The element is NOT displayed: "+ele);
			}
		} 
		else 
		{
			
			AUTActions.LogIt(ele, "***Fail-The element could NOT be found: ", "FAIL");
			//Log.info("***Fail-The element could NOT be found: "+ele);
    		//test.log(LogStatus.FAIL, "***Fail-The element could NOT be found: "+ele);
    		//Assert.fail("***Fail-The element could NOT be found: "+ele);

		}
		return flag;
	}

	public static boolean isSelected(WebDriver driver, WebElement ele) {
		boolean flag = false;
		AUTActions.explicitWait(getDriver(), ele, 10);
		flag = findElement(driver, ele);
		if (flag) {
			flag = ele.isSelected();
			if (flag) {
				//System.out.println("The element is Selected: "+ele);
				Log.info("The element is selected: "+ele);
			} else {
				Log.info("***Fail-The element is NOT selected: "+ele);
	    		Assert.fail("***Fail-The element could NOT selected: "+ele);
			}
		} else {
			Log.info("***Fail-The element could NOT be found: "+ele);
    		Assert.fail("***Fail-The element could NOT be found: "+ele);
		}
		return flag;
	}

	public static boolean isEnabled(WebDriver driver, WebElement ele) {
		boolean flag = false;
		AUTActions.explicitWait(getDriver(), ele, 10);
		flag = findElement(driver, ele);
		if (flag) {
			flag = ele.isEnabled();
			if (flag) {
				//System.out.println("The element is Enabled: "+ele);
				Log.info("The element is Enabled: "+ele);
			} else {
				//System.out.println("The element is not Enabled: "+ele);
				Log.info("***Fail-The element is not Enabled: "+ele);
	    		Assert.fail("***Fail-The element is not Enabled: "+ele);
			}
		} else {
			System.out.println("Not Enabled "+ele);
			Log.info("The element could NOT be found: "+ele);
    		Assert.fail();
		}
		return flag;
	}

	/**
	 * Type text at location
	 * 
	 * @param locatorName
	 * @param text
	 * @return - true/false
	 */
	public static boolean type(WebElement ele, String text) 
	{
		boolean flag = false;

		try {
			AUTActions.explicitWait(getDriver(), ele, 10);
			flag = ele.isDisplayed();
			ele.click();
			ele.clear();
			ele.sendKeys(text);
			// logger.info("Entered text :"+text);
			flag = true;
		} catch (Exception e) {
			AUTActions.LogIt(null, "Location NOT found: "+ele, "FAIL");   		
			flag = false;
		} finally {
			if (flag) {
				//System.out.println("Successfully entered value: "+text+", into: "+ele);
				//Log.info("Typed text: "+text+", into: "+ele);
				AUTActions.LogIt(null, "Typed text: "+text+", into: "+ele, "PASS");	    				
			} else {
				//System.out.println("Unable to enter value: "+text+", into: "+ele);
				//Log.info("Unable to type text: "+text+", into: "+ele);
	    		//Assert.fail("Unable to type text: "+text+", into: "+ele);
				AUTActions.LogIt(null, "Unable to type text: "+text+", into: "+ele, "FAIL");	 
			}
		}
		return flag;
	}

	public boolean selectBySendkeys(String value,WebElement ele) {
		boolean flag = false;
		try {
			ele.sendKeys(value);
			flag = true;
			return true;
		} catch (Exception e) {

			return false;
		} finally {
			if (flag) {
				System.out.println("Select value from the DropDown");		
			} else {
				System.out.println("Not Selected value from the DropDown");
				// throw new ElementNotFoundException("", "", "")
			}
		}
	}

	/**
	 * select value from DropDown by using selectByIndex
	 * 
	 * @param locator     : Action to be performed on element (Get it from Object
	 *                    repository)
	 * 
	 * @param index       : Index of value wish to select from dropdown list.
	 * 
	 * @param locatorName : Meaningful name to the element (Ex:Year Dropdown, items
	 *                    Listbox etc..)
	 * 
	 */
	public boolean selectByIndex(WebElement element, int index) {
		boolean flag = false;
		try {
			Select s = new Select(element);
			s.selectByIndex(index);
			flag = true;
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			if (flag) {
				System.out.println("Option selected by Index");
			} else {
				System.out.println("Option not selected by Index");
			}
		}
	}

	/**
	 * select value from DD by using value
	 * 
	 * @param locator     : Action to be performed on element (Get it from Object
	 *                    repository)
	 * 
	 * @param value       : Value wish to select from dropdown list.
	 * 
	 * @param locatorName : Meaningful name to the element (Ex:Year Dropdown, items
	 *                    Listbox etc..)
	 */

	public boolean selectByValue(WebElement element,String value) {
		boolean flag = false;
		try {
			Select s = new Select(element);
			s.selectByValue(value);
			flag = true;
			return true;
		} catch (Exception e) {

			return false;
		} finally {
			if (flag) {
				System.out.println("Option selected by Value");
			} else {
				System.out.println("Option not selected by Value");
			}
		}
	}

	/**
	 * select value from DropDown by using selectByVisibleText
	 * 
	 * @param locator     : Action to be performed on element (Get it from Object
	 *                    repository)
	 * 
	 * @param visibletext : VisibleText wish to select from dropdown list.
	 * 
	 * @param locatorName : Meaningful name to the element (Ex:Year Dropdown, items
	 *                    Listbox etc..)
	 */

	public static boolean selectByVisibleText(WebElement ele, String visibletext, String strWebElementType) {
		boolean flag = false;
		try {
			Select s = new Select(ele);
			s.selectByVisibleText(visibletext);
			flag = true;
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			if (flag) {
				//Log.info("Select "+strWebElementType+" item: "+visibletext+" WebElement: "+ele);
			} else {
				//Log.info("Could NOT select "+strWebElementType+" item: "+visibletext+" WebElement: "+ele);
	    		Assert.fail();
			}
		}
	}

	public static boolean mouseHoverByJavaScript(WebElement ele, String sWebElement) {
		boolean flag = false;
		try {
			WebElement mo = ele;
			String javaScript = "var evObj = document.createEvent('MouseEvents');"
					+ "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);"
					+ "arguments[0].dispatchEvent(evObj);";
			JavascriptExecutor js = (JavascriptExecutor) getDriver();
			js.executeScript(javaScript, mo);
			flag = true;
			return true;
		}

		catch (Exception e) {

			return false;
		} finally {
			if (flag) {
				System.out.println("Performed Mouse Hover over "+ele+" "+sWebElement);
			} else {
				System.out.println("Could NOT perform a Mouse Hover over "+ele+" "+sWebElement);
			}
		}
	}

	public boolean JSClick(WebDriver driver, WebElement ele) {
		boolean flag = false;
		try {
			// WebElement element = driver.findElement(locator);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", ele);
			// driver.executeAsyncScript("arguments[0].click();", element);

			flag = true;

		}

		catch (Exception e) {
			throw e;

		} finally {
			if (flag) {
				System.out.println("Click Action is performed");
			} else if (!flag) {
				System.out.println("Click Action is not performed");
			}
		}
		return flag;
	}
	public boolean switchToFrameByIndex(WebDriver driver,int index) {
		boolean flag = false;
		try {
			new WebDriverWait(driver, null).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//iframe")));
			//new WebDriverWait(driver, 15).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//iframe")));
			driver.switchTo().frame(index);
			flag = true;
			return true;
		} catch (Exception e) {

			return false;
		} finally {
			if (flag) {
				System.out.println("Frame with index \"" + index + "\" is selected");
			} else {
				System.out.println("Frame with index \"" + index + "\" is not selected");
			}
		}
	}

	/**
	 * This method switch the to frame using frame ID.
	 * 
	 * @param idValue : Frame ID wish to switch
	 * 
	 */

	public boolean switchToFrameById(WebDriver driver,String idValue) {
		boolean flag = false;
		try {
			driver.switchTo().frame(idValue);
			flag = true;
			return true;
		} catch (Exception e) {

			e.printStackTrace();
			return false;
		} finally {
			if (flag) {
				System.out.println("Frame with Id \"" + idValue + "\" is selected");
			} else {
				System.out.println("Frame with Id \"" + idValue + "\" is not selected");
			}
		}
	}

	/**
	 * This method switch the to frame using frame Name.
	 * 
	 * @param nameValue : Frame Name wish to switch
	 * 
	 */

	public boolean switchToFrameByName(WebDriver driver,String nameValue) {
		boolean flag = false;
		try {
			driver.switchTo().frame(nameValue);
			flag = true;
			return true;
		} catch (Exception e) {

			return false;
		} finally {
			if (flag) {
				System.out.println("Frame with Name \"" + nameValue + "\" is selected");
			} else if (!flag) {
				System.out.println("Frame with Name \"" + nameValue + "\" is not selected");
			}
		}
	}


	public boolean switchToDefaultFrame(WebDriver driver) {
		boolean flag = false;
		try {
			driver.switchTo().defaultContent();
			flag = true;
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (flag) {
				// SuccessReport("SelectFrame ","Frame with Name is selected");
			} else if (!flag) {
				// failureReport("SelectFrame ","The Frame is not selected");
			}
		}
	}


	public void mouseOverElement(WebDriver driver,WebElement element) {
		boolean flag = false;
		try {
			new Actions(driver).moveToElement(element).build().perform();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (flag) {
				System.out.println(" MouserOver Action is performed on ");
			} else {
				System.out.println("MouseOver action is not performed on");
			}
		}
	}


	public boolean moveToElement(WebDriver driver, WebElement ele) {
		boolean flag = false;
		try {
			// WebElement element = driver.findElement(locator);
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].scrollIntoView(true);", ele);
			Actions actions = new Actions(driver);
			// actions.moveToElement(driver.findElement(locator)).build().perform();
			actions.moveToElement(ele).build().perform();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public boolean mouseover(WebDriver driver, WebElement ele) {
		//boolean flag = false;
		try {
			new Actions(driver).moveToElement(ele).build().perform();
			//flag = true;
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			/*
			 * if (flag) {
			 * SuccessReport("MouseOver ","MouserOver Action is performed on \""+locatorName
			 * +"\""); } else {
			 * failureReport("MouseOver","MouseOver action is not performed on \""
			 * +locatorName+"\""); }
			 */
		}
	}

	public boolean draggable(WebDriver driver,WebElement source, int x, int y) {
		boolean flag = false;
		try {
			new Actions(driver).dragAndDropBy(source, x, y).build().perform();
			Thread.sleep(5000);
			flag = true;
			return true;

		} catch (Exception e) {
		
			return false;
			
		} finally {
			if (flag) {
				System.out.println("Draggable Action is performed on \""+source+"\"");			
			} else if(!flag) {
				System.out.println("Draggable action is not performed on \""+source+"\"");
			}
		}
	}

	public boolean draganddrop(WebDriver driver,WebElement source, WebElement target) {
		boolean flag = false;
		try {
			new Actions(driver).dragAndDrop(source, target).perform();
			flag = true;
			return true;
		} catch (Exception e) {

			return false;
		} finally {
			if (flag) {
				System.out.println("DragAndDrop Action is performed");
			} else if(!flag) {
				System.out.println("DragAndDrop Action is not performed");
			}
		}
	}
	

	public boolean slider(WebDriver driver,WebElement ele, int x, int y) {
		boolean flag = false;
		try {
			// new Actions(driver).dragAndDropBy(dragitem, 400, 1).build()
			// .perform();
			new Actions(driver).dragAndDropBy(ele, x, y).build().perform();// 150,0
			Thread.sleep(5000);
			flag = true;
			return true;
		} catch (Exception e) {

			return false;
		} finally {
			if (flag) {
				System.out.println("Slider Action is performed");
			} else {
				System.out.println("Slider Action is not performed");
			}
		}
	}
	

	public boolean rightclick(WebDriver driver,WebElement ele) {
		boolean flag = false;
		try {
			Actions clicker = new Actions(driver);
			clicker.contextClick(ele).perform();
			flag = true;
			return true;
			// driver.findElement(by1).sendKeys(Keys.DOWN);
		} catch (Exception e) {

			return false;
		} finally {
			if (flag) {
				System.out.println("RightClick Action is performed");
			} else {
				System.out.println("RightClick Action is not performed");
			}
		}
	}
	

	public boolean switchWindowByTitle(WebDriver driver,String windowTitle, int count) {
		boolean flag = false;
		try {
			Set<String> windowList = driver.getWindowHandles();

			String[] array = windowList.toArray(new String[0]);

			driver.switchTo().window(array[count-1]);

			if (driver.getTitle().contains(windowTitle)){
				flag = true;
			}else{
				flag = false;
			}
			return flag;
		} catch (Exception e) {
			//flag = true;
			return false;
		} finally {
			if (flag) {
				System.out.println("Navigated to the window with title");
			} else {
				System.out.println("The Window with title is not Selected");
			}
		}
	}

	public boolean switchToNewWindow(WebDriver driver) {
		boolean flag = false;
		try {

			Set<String> s=driver.getWindowHandles();
			Object popup[]=s.toArray();
			driver.switchTo().window(popup[1].toString());
			flag = true;
			return flag;
		} catch (Exception e) {
			flag = false;
			return flag;
		} finally {
			if (flag) {
				System.out.println("Window is Navigated with title");				
			} else {
				System.out.println("The Window with title: is not Selected");
			}
		}
	}

	public boolean switchToOldWindow(WebDriver driver) {
		boolean flag = false;
		try {

			Set<String> s=driver.getWindowHandles();
			Object popup[]=s.toArray();
			driver.switchTo().window(popup[0].toString());
			flag = true;
			return flag;
		} catch (Exception e) {
			flag = false;
			return flag;
		} finally {
			if (flag) {
				System.out.println("Focus navigated to the window with title");			
			} else {
				System.out.println("The Window with title: is not Selected");
			}
		}
	}

	public int getColumncount(WebElement row) {
		List<WebElement> columns = row.findElements(By.tagName("td"));
		int a = columns.size();
		System.out.println(columns.size());
		for (WebElement column : columns) {
			System.out.print(column.getText());
			System.out.print("|");
		}
		return a;
	}
	

	public int getRowCount(WebElement table) {
		List<WebElement> rows = table.findElements(By.tagName("tr"));
		int a = rows.size() - 1;
		return a;
	}
	
	
	/**
	 * Verify alert present or not
	 * 
	 * @return: Boolean (True: If alert preset, False: If no alert)
	 * 
	 */

	public boolean Alert(WebDriver driver) {
		boolean presentFlag = false;
		Alert alert = null;

		try {
			// Check the presence of alert
			alert = driver.switchTo().alert();
			// if present consume the alert
			alert.accept();
			presentFlag = true;
		} catch (NoAlertPresentException ex) {
			// Alert present; set the flag

			// Alert not present
			ex.printStackTrace();
		} finally {
			if (!presentFlag) {
				System.out.println("The Alert is handled successfully");		
			} else{
				System.out.println("There was no alert to handle");
			}
		}

		return presentFlag;
	}

	public boolean launchUrl(WebDriver driver,String url) {
		boolean flag = false;
		try {
			driver.navigate().to(url);
			flag = true;
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			if (flag) {
				System.out.println("Successfully launched "+url);				
			} else {
				System.out.println("Failed to launch "+url);
			}
		}
	}
	

	public boolean isAlertPresent(WebDriver driver) 
	{ 
		try 
		{ 
			driver.switchTo().alert(); 
			return true; 
		}   // try 
		catch (NoAlertPresentException Ex) 
		{ 
			return false; 
		}   // catch 
	}
	

	public String getTitle(WebDriver driver) {
		boolean flag = false;

		String text = driver.getTitle();
		if (flag) {
			System.out.println("Title of the page is: "+text);
		}
		return text;
	}
	

	public String getCurrentURL(WebDriver driver)  {
		boolean flag = false;

		String text = driver.getCurrentUrl();
		if (flag) {
			System.out.println("Current URL is: "+text);
		}
		return text;
	}
	

	public boolean click1(WebElement locator, String locatorName) {
		boolean flag = false;
		try {
			locator.click();
			flag = true;
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			if (flag) {
				System.out.println("Able to click on "+locatorName);
			} else {
				System.out.println("Unable to click on "+locatorName);
			}
		}

	}
	

	public static void fluentWait(WebDriver driver,WebElement element, int timeOut) {
	    Wait<WebDriver> wait = null;
	    try {
	        wait = new FluentWait<WebDriver>((WebDriver) driver)
	        		.withTimeout(Duration.ofSeconds(20))
	        	    .pollingEvery(Duration.ofSeconds(2))
	        	    .ignoring(Exception.class);
	        wait.until(ExpectedConditions.visibilityOf(element));
	        element.click();
	    }catch(Exception e) {
	    }
	}

	public static void implicitWait(WebDriver driver, long iTime) {
		//driver.manage().timeouts().implicitlyWait(i);
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(iTime));
		System.out.println("implicitWait...: "+ iTime);
	}

	public static void explicitWait(WebDriver driver, WebElement element, long iTime ) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(iTime));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public static void pageLoadTimeOut(WebDriver driver, long iTime) {
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(iTime));
		//System.out.println("pageLoadTimeOut...: "+ iTime);
		//driver.manage().timeouts().pageLoadTimeout(timeOut);
	}

	public static String screenShot(WebDriver driver, String filename) {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "\\ScreenShots\\" +driver.getClass()+"_"+ filename + "_" + dateName + ".png";

		try {
			FileUtils.copyFile(source, new File(destination));
		} catch (Exception e) {
			e.getMessage();
		}
		// This new path for jenkins
		String newImageString = "http://localhost:8082/job/MyStoreProject/ws/MyStoreProject/ScreenShots/" + filename + "_"
				+ dateName + ".png";
		return newImageString;
	}

	public String getCurrentTime() {
		String currentDate = new SimpleDateFormat("yyyy-MM-dd-hhmmss").format(new Date());
		return currentDate;
	}

	
	//******************************window methods
	
	public static String getMainWindowHandle(WebDriver driver) {
		return driver.getWindowHandle();
	}
	
	
	public static String getCurrentWindowTitle(WebDriver driver) {
		String windowTitle = driver.getTitle();
		return windowTitle;
	}

	public static boolean closeAllOtherWindows(WebDriver driver, String openWindowHandle) {
		Set<String> allWindowHandles = driver.getWindowHandles();
		for (String currentWindowHandle : allWindowHandles) {
			if (!currentWindowHandle.equals(openWindowHandle)) {
				driver.switchTo().window(currentWindowHandle);
				driver.close();
			}
		}
		
		driver.switchTo().window(openWindowHandle);
		if (driver.getWindowHandles().size() == 1)
			return true;
		else
			return false;
	}
	public static void waitForNewWindowAndSwitchToIt(WebDriver driver) throws InterruptedException 
	{
        String cHandle = driver.getWindowHandle();
        String newWindowHandle = null;
        Set<String> allWindowHandles = driver.getWindowHandles();
        
        //Wait for 20 seconds for the new window and throw exception if not found
        for (int i = 0; i < 30; i++) {
            if (allWindowHandles.size() > 1) {
                for (String allHandlers : allWindowHandles) {
                    if (!allHandlers.equals(cHandle))
                    	newWindowHandle = allHandlers;
                }
                driver.switchTo().window(newWindowHandle);
                break;
            } else {
                Thread.sleep(1000);
            }
        }
        if (cHandle == newWindowHandle) {
            throw new RuntimeException(
                    "Time out - No window found");
        }
	}

	public static String randomestring(int iNumb)
	{
		String generatedstring=RandomStringUtils.randomAlphabetic(iNumb);
		return(generatedstring);
	}
	
	public static String randomeNum(int iNumb) 
	{
		String generatedNumber = RandomStringUtils.randomNumeric(iNumb);
		return (generatedNumber);
	}	
	
	//extract only numbers from a string of characters 
	//Parameter str = string of char
	public static String extractInt(String str)
    {
        // Replacing every non-digit number
        // with a space(" ")
        str = str.replaceAll("[^0-9]", " "); // regular expression
 
        // Replace all the consecutive white
        // spaces with a single space
        str = str.replaceAll(" +", " ");
 
        if (str.equals(""))
            return "-1";
 
        return str;
    }

	//dynamic wait for the file to download
	//once downloaded check that the file exists in the download folder
	public static boolean checkForFileDownload(String strDownLoadFolder, String strFileName) throws Throwable
	{
 
    	File file = new File(strDownLoadFolder, strFileName);
    	FluentWait<File>wait = new FluentWait<File>(file)
    					.withTimeout(Duration.ofMinutes(5))
    					.pollingEvery(Duration.ofSeconds(5))
    					.ignoring(Exception.class)
    					.withMessage("File has NOT completed downloading: "+strFileName);
    	
    	
    	try
    	{
        	boolean bdownloadFlag = wait.until(f -> f.exists()&& f.canRead());
        	if (bdownloadFlag)
        	{
        		//Log.info("File has completed downloading: "+strFileName);
        	}
    	} 
    	catch (TimeoutException e) 
    	{
    		//Log.info("File has NOT completed downloading: "+strFileName);
    	}
		
		//Log.info("Checking for file in download folder: "+strFileName);		
		File folder = new File(strDownLoadFolder);
		File[] listOfFiles = folder.listFiles();
		boolean checkForFile = false;
		for (File listOfFile : listOfFiles)
		{
			if (listOfFile.isFile())
			{
				String fileName = listOfFile.getName();
				if (fileName.matches(strFileName)) 
				{
					//Log.info("Found file: "+strFileName+" in folder: "+strDownLoadFolder);	
					checkForFile = true;
				}
			}
		}
		
		if (checkForFile == false)
		{ 
			//Log.info("File "+strFileName+" has been downloaded, but could not be found in download folder: "+strDownLoadFolder+", "+strFileName);	
		}
		
		return checkForFile;
	}
	//***dangerous...know what folder you are deleting content from!!!!!!*****
	public static void cleanFolder(String strfileDownLoadLocation) throws IOException
	{
		File directory = new File(strfileDownLoadLocation);
		FileUtils.cleanDirectory(directory);	
		//AUTActions.LogIt(null, "Directory has been cleaned", "INFO");
	}		
	
	//read and return PDF content for verification
	public static String readPdfContent(String url, Boolean bFlagcontent) throws IOException 
	{
		//System.out.println("getInputStream");		
		//URI pdfUrl = Paths.get(url).toUri() ;
		
		URL pdfUrl = new URL(url);

		InputStream in = pdfUrl.openStream();
		BufferedInputStream bf = new BufferedInputStream(in);
		PDDocument doc = PDDocument.load(bf);
		String content = new PDFTextStripper().getText(doc);
		if (bFlagcontent)
		{
			int totalPDFPages = getPageCount(doc);
			//Assert.assertEquals(totalPDFPages, 47);
			//AUTActions.LogIt(null, "The total number of pages "+totalPDFPages, "INFO");
			//AUTActions.LogIt(null, "***Begin writing out PDF content***", "INFO");
			//AUTActions.LogIt(null, content, "INFO");
			//AUTActions.LogIt(null, "***End writing out PDF content***", "INFO");			
		}

		doc.close();
	
		return content;
	
	
	}

	public static int getPageCount(PDDocument doc) 
	{
		//get the total number of pages in the pdf document
		int pageCount = doc.getNumberOfPages();
		return pageCount;
		
	}
	
	public static String capture(WebDriver driver, String screeshotname) throws IOException 
	{
		String timeStamp1 = new SimpleDateFormat("mm-ss").format(new Date());//time stamp
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File Dest = new File("src/../test-output/AutoReport/Screenshots/" + screeshotname+"_"+timeStamp1+".png");
		String errflpath = Dest.getAbsolutePath();
		FileUtils.copyFile(scrFile, Dest);
		//ElementActions.LogIt(null, "screenshot taken", "INFO");
		return errflpath;
	}	

	public static String capture1(WebDriver driver) throws IOException 
	{
		String timeStamp1 = new SimpleDateFormat("mm-ss").format(new Date());//time stamp
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		File Dest = new File("src/../test-output/AutoReport/Screenshots/-"+timeStamp1+".png");
		String errflpath = Dest.getAbsolutePath();
		FileUtils.copyFile(scrFile, Dest);
		//ElementActions.LogIt(null, "screenshot taken", "INFO");
		return errflpath;
	}		
	
	/**
	 * Get the method name for a depth in call stack. <br />
	 * Utility function
	 * @param depth depth in the call stack (0 means current method, 1 means call method, ...)
	 * @return method name
	 */
	public static String getMethodName(final int depth)
	{
	  final StackTraceElement[] ste = Thread.currentThread().getStackTrace();

	  //System. out.println(ste[ste.length-depth].getClassName()+"#"+ste[ste.length-depth].getMethodName());
	  // return ste[ste.length - depth].getMethodName();  //Wrong, fails for depth = 0
	  return ste[ste.length - 1 - depth].getMethodName(); //Thank you Tom Tresansky
	}
	public static void useKeyBoard(String key) throws InterruptedException
	{	
		Actions keyType = new Actions(getDriver());
		if (key.equals("SPACE"))
		{
			keyType.sendKeys(Keys.SPACE).build().perform();
		}
		else if(key.equals("ENTER")) 
		{
			keyType.sendKeys(Keys.ENTER).build().perform();
		}
		else if(key.equals("ESCAPE")) 
		{
			keyType.sendKeys(Keys.ESCAPE).build().perform();
		}		
		test.log(LogStatus.INFO,"Used keyboard: "+key);
		Thread.sleep(2000);
	}
		
	public static void LogIt(WebElement element, String strMsg, String strStatus)
	{
		
		if (strStatus.equals("PASS")) 
		{
			System.out.println("PASS "+strMsg+" "+element);
			Log.info("PASS "+strMsg+" "+element); //log4j
			test.log(LogStatus.PASS, strMsg+" "+element);
		} 
		else if(strStatus.equals("INFO")) 
		{
			System.out.println("INFO "+strMsg+" "+element);
			Log.info("INFO "+strMsg+" "+element);
			test.log(LogStatus.INFO, strMsg+" "+element);

		}		
		else if(strStatus.equals("FAIL")) 
		{
			System.out.println("***FAIL "+strMsg+" "+element);
			Log.info("***FAIL "+strMsg+" "+element);
			test.log(LogStatus.FAIL, strMsg+" "+element);
			Assert.fail("***FAIL "+strMsg+" "+element);
		}
		else if(strStatus.equals("ERROR")) 
		{
			System.out.println("***ERROR "+strMsg+" "+element);
			Log.info("***ERROR "+strMsg+" "+element);
			test.log(LogStatus.ERROR, strMsg+" "+element);
			Assert.fail("***ERROR "+strMsg+" "+element);
		}
		else if(strStatus.equals("WARNING")) 
		{
			System.out.println("***WARNING "+strMsg+" "+element);
			Log.warn("***WARNING "+strMsg+" "+element);
			test.log(LogStatus.WARNING, strMsg+" "+element);
		}
		else if(strStatus.equals("STARTTC")) 
		{
			System.out.println(strMsg);
			Log.startTestCase(strMsg);
			test.log(LogStatus.INFO, strMsg);
		}
		else if(strStatus.equals("ENDTC")) 
		{
			System.out.println(strMsg);
			Log.endTestCase(strMsg);
			test.log(LogStatus.INFO, strMsg);
		}
	}	
	
	
}