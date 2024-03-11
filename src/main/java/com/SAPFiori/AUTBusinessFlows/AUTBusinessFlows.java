package com.SAPFiori.AUTBusinessFlows;

import static org.testng.Assert.assertEquals;

import java.util.Base64;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import com.SAPFiori.BaseClass.BaseClass;
import com.SAPFiori.pageObj.TAccountViewPg_obj;
import com.SAPFiori.utilities.Log;

public class AUTBusinessFlows extends BaseClass 
{
	static TAccountViewPg_obj tAccountViewPg_obj;
    boolean bflag = false;
    
	public static void openAppbyURL(String intentApp) 
	{
		//intent based navigation
		String currentURL = getDriver().getCurrentUrl();
		Log.info("Current url: "+currentURL);
		String newURL = currentURL.replace("Shell-home", intentApp);

		getDriver().navigate().to(newURL);
		Log.info("Browsed and opened new app: "+newURL);
	}
	
	public static void srchforApp(String tileApp) 
	{
		tAccountViewPg_obj=new TAccountViewPg_obj();
		tAccountViewPg_obj.click_searchAppsField();
		tAccountViewPg_obj.type_searchAppsField(tileApp);
		tAccountViewPg_obj.click_searchButton();
	}	
	public static boolean srchIteminTbl(String SAPtable, String strSrchData, String strcolName, int icolIndex, String tblAction) throws InterruptedException 
	{	
		Thread.sleep(2000);
        boolean bflag = false;
		WebElement oTable2 = getDriver().findElement(By.xpath(SAPtable));
		List<WebElement>TotalRowsList2 = oTable2.findElements(By.tagName("tr"));
		System.out.println("Total number of Rows in the table are : "+ TotalRowsList2.size());
		
        Log.info("Looking for table data: "+strSrchData+" under column name: "+strcolName);		
        for(int irow=1;irow < TotalRowsList2.size() + 1;irow++)
        {		
	
		    //WebElement table = getDriver().findElement(By.xpath ("table[@id='accountingDocumentVHDialogId-table-table']"));
		    String strSrchResults2 = oTable2.findElement(By.xpath (SAPtable+"/tr["+irow+"]/td[" + icolIndex + "]")).getText();
		    //oTable2.findElement(By.xpath (SAPtable+"/tr[1]/td[1]")).click();		
            if (strSrchData.equals(strSrchResults2))
            {
                Log.info("Found "+strSrchData+" under column name: "+strcolName+" in row: " + irow);
                if (tblAction == "Click")
                {
                    oTable2.findElement(By.xpath (SAPtable+"/tr["+irow+"]/td[1]")).click();
                    Log.info("Clicked row for: "+strSrchData);
                }                
                bflag = true;
                break;
            }
        }
        
		if (bflag == false)
		{
            Log.info("***Fail-Did NOT find "+strSrchData+" under column name.: "+strcolName+" in the customers table.");
 			//Assert.fail("***Fail-Did NOT find "+strSrchData+" under column name: "+strcolName+" in the customers table.");
		}
       
        return bflag;    
	}
	
	
	/*public static void func1()
	{
		String title = getDriver().getTitle();
		System.out.println(title);
		
		signInUserPg_Obj.type_userName("James.Anderson99@shaw.ca");
		signInUserPg_Obj.click_continueBtn();
		System.out.println("Waiting...");
		Thread.sleep(15000);
		byte[] decoded = Base64.getDecoder().decode("Wm4wMHB5OTkh");
		passwordUserPg_Obj.type_pWord(new String(decoded));
		passwordUserPg_Obj.click_Signin_BTN();
		tAccountViewPg_obj.waitSearchAppsField();
		Thread.sleep(10000);
		
		Actions action = new Actions(getDriver());
		action.sendKeys(Keys.ESCAPE).build().perform();
		System.out.println("escape pop up dialog");
		Thread.sleep(3000);
	}*/
	
}
