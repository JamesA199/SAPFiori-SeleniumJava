package com.SAPFiori.AUTBusinessFlows;

import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.util.Base64;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import com.SAPFiori.AUTActions.AUTActions;
import com.SAPFiori.BaseClass.BaseClass;
import com.SAPFiori.pageObj.HeaderDisplayPg_obj;
import com.SAPFiori.pageObj.PasswordUserPg_obj;
import com.SAPFiori.pageObj.SignInUserPg_obj;
import com.SAPFiori.pageObj.SupplierInvoicePg_obj;
import com.SAPFiori.pageObj.TAccountViewPg_obj;
import com.SAPFiori.utilities.Log;

public class AUTBusinessFlows extends BaseClass 
{
	static TAccountViewPg_obj tAccountViewPg_obj;
	static HeaderDisplayPg_obj headerDisplayPg_obj;
	static SignInUserPg_obj signInUserPg_Obj;
	static PasswordUserPg_obj passwordUserPg_Obj;
	static SupplierInvoicePg_obj supplierInvoicePg_obj;
    boolean bflag = false;
 
    public static void openAppbyURL(String intentApp) throws InterruptedException 
	{
		//intent based navigation
		String currentURL = getDriver().getCurrentUrl();
		AUTActions.LogIt("Current url: "+currentURL, "INFO", "");
		String newURL = currentURL.replace("Shell-home", intentApp);

		getDriver().navigate().to(newURL);
		Thread.sleep(1000);
		getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
		String Webpagetitle = getDriver().getTitle();
		AUTActions.LogIt("Opened Webpage: "+Webpagetitle, "INFO", "");
	
	}
	
	public static void srchforApp(String tileApp) 
	{
		tAccountViewPg_obj=new TAccountViewPg_obj();
		tAccountViewPg_obj.click_searchAppsField();
		tAccountViewPg_obj.type_searchAppsField(tileApp);
		tAccountViewPg_obj.click_searchBtn();
	}	
	public static boolean srchIteminTbl(String SAPtable, String strSrchData, String strcolName, int icolIndex, String tblAction) throws InterruptedException 
	{	
		Thread.sleep(1000);
        boolean bflag = false;
		
		WebElement oTable2 = getDriver().findElement(By.xpath(SAPtable));
		AUTActions.explicitWait(oTable2, 60);
		
		List<WebElement>TotalRowsList2 = oTable2.findElements(By.tagName("tr"));
		AUTActions.LogIt("Total number of Rows in the table are : "+ TotalRowsList2.size(), "INFO", "");
                
        for(int irow=1;irow < TotalRowsList2.size() + 1;irow++)
        {		
	
		    String strSrchResults2 = oTable2.findElement(By.xpath (SAPtable+"/tr["+irow+"]/td["+icolIndex+"]")).getText();
            if (strSrchData.equals(strSrchResults2))
            {
                Log.info("Found "+strSrchData+" under column name: "+strcolName+" in row: " + irow);
                if (tblAction == "Click")
                {
                    oTable2.findElement(By.xpath (SAPtable+"/tr["+irow+"]/td["+icolIndex+"]")).click();
            		AUTActions.LogIt("Clicked row for: "+strSrchData, "INFO", "");
                }                
                bflag = true;
                break;
            }

        }
        
		if (bflag == false)
		{
    		AUTActions.LogIt(null,"Did NOT find "+strSrchData+" under column name.: "+strcolName+" in the customers table.", "FAIL");
		}
       
        return bflag;    
	}
	public static void closeStartupWizard()
	{
		//x icon close 
		WebElement oXIcon = getDriver().findElement(By.xpath("//button[contains(@class,'help4-close help4-control-button')]"));
		AUTActions.explicitWait(oXIcon, 10);
		
		boolean bFlag = oXIcon.isDisplayed();
		if(bFlag == true)
		{
			getDriver().findElement(By.xpath("//button[contains(@class,'help4-close help4-control-button')]")).click();
    		AUTActions.LogIt("Closed Startup Wizard.", "INFO", "");
		}

	}	

	public static void addGLAcctLineItem(String CDType, String GLAccountItem, String amount, int index ) throws InterruptedException
	{
		
		supplierInvoicePg_obj=new SupplierInvoicePg_obj();
		
		supplierInvoicePg_obj.click_AddBtn();
		Thread.sleep(1000);	
		AUTActions.explicitWaitbyWE("//span[text()='Debit']", 60);

		getDriver().findElements(By.xpath("//span[text()='Debit']")).get(index).click();
		AUTActions.LogIt("Clicked Debit/Credit dropdown box", "INFO", "");
		Thread.sleep(1000);
		
		if (index == 0) 
		{
			//for first row of the GL Acct line item the index does not to be used  
			getDriver().findElement(By.xpath("//li[text()='"+CDType+"']")).click();
			
		}
		else
		{
			getDriver().findElements(By.xpath("//li[text()='"+CDType+"']")).get(index).click();	
		}
		
		AUTActions.LogIt("Select Debit/Credit list item: "+CDType, "INFO", "");
		getDriver().findElements(By.xpath("//*[contains(@id, 'GLAccountItemGLAccount')]/input")).get(index).click();
		getDriver().findElements(By.xpath("//*[contains(@id, 'GLAccountItemGLAccount')]/input")).get(index).sendKeys(GLAccountItem);
		AUTActions.LogIt("Enter GL account line item: "+GLAccountItem, "INFO", "");
		Thread.sleep(3000);
		getDriver().findElements(By.xpath("//*[contains(@id, 'GLAccountItemAmount')]/input")).get(index).click();	
		Thread.sleep(3000);
		getDriver().findElements(By.xpath("//*[contains(@id, 'GLAccountItemAmount')]/input")).get(index).clear();
		getDriver().findElements(By.xpath("//*[contains(@id, 'GLAccountItemAmount')]/input")).get(index).sendKeys(amount);
		AUTActions.LogIt("Enter GL Account amount: "+amount, "INFO", "");

	}
	
	public static String getToastMsg(String webLocator) throws InterruptedException
	{
		AUTActions.explicitWaitbyWE("//div[contains(@class, 'sapMMessageToast')]", 60); //wait for toast message
		String strMsg = getDriver().findElement(By.xpath(webLocator)).getText();
		
		if(strMsg.contains("successfully"))
		{
			AUTActions.LogIt("Found toast message....check of invoice was a success: "+strMsg, "PASS", "");
		}
		else
		{
			AUTActions.LogIt("Did not find toast message, check of invoice was NOT a success: "+strMsg, "FAIL", "");
		}
		
		return strMsg;
	}
	public static void LoginDyn365() throws InterruptedException
	{
		
		headerDisplayPg_obj = new HeaderDisplayPg_obj();
		signInUserPg_Obj = new SignInUserPg_obj();
		passwordUserPg_Obj = new PasswordUserPg_obj(); 
		tAccountViewPg_obj=new TAccountViewPg_obj();
		
		String title = getDriver().getTitle();
		AUTActions.LogIt("Webpage title: "+title, "INFO", "");
		
		signInUserPg_Obj.type_userName(prop.getProperty("username"));
		signInUserPg_Obj.click_continueBtn();
		System.out.println("Waiting...");
		Thread.sleep(15000);
		byte[] decoded = Base64.getDecoder().decode(prop.getProperty("password"));
		passwordUserPg_Obj.type_pWord(new String(decoded));
		passwordUserPg_Obj.click_Signin_BTN();
		tAccountViewPg_obj.waitSearchAppsField();
		
		Thread.sleep(10000);
		AUTBusinessFlows.closeStartupWizard();
		Thread.sleep(2000);
		AUTActions.LogIt("Finished signing in.", "INFO", "");
		
	}
	public static void ListClick(String xPath, int index) throws InterruptedException
	{
		getDriver().findElements(By.xpath(xPath)).get(index).click();
		AUTActions.useKeyBoard("SPACE", 1);
		AUTActions.LogIt("Selected list item: "+xPath+" at index: "+index, "INFO", "");
	}

	public static void click_JEChcBx(int index) throws InterruptedException
	{
		String journalEntry = getDriver().findElement(By.id("application-AccountingDocument-impact-component---main--JournalEntriesItemTitle-application-AccountingDocument-impact-component---main--AccountingDocumentsListId-"+index+"-inner")).getText();

		getDriver().findElement(By.id("application-AccountingDocument-impact-component---main--ColumnListItemADId-application-AccountingDocument-impact-component---main--AccountingDocumentsListId-"+index+"-selectMulti-CbBg")).click();

		AUTActions.LogIt("Checked list item checkbox for JEntry on JE list "+journalEntry+" at index "+index, "INFO", "");
		
	}	
	public static void validate_JournalEnry(String JEntry, int index) throws InterruptedException
	{
		String journalEntry = getDriver().findElement(By.id("application-AccountingDocument-impact-component---main--JournalEntriesItemTitle-application-AccountingDocument-impact-component---main--AccountingDocumentsListId-"+index+"-inner")).getText();

		if (JEntry.equals(journalEntry))
		{
			AUTActions.LogIt("Found Journal Entry "+JEntry+" on Account Document JE list item at index: "+index, "PASS", "");
		}
		else
		{
			AUTActions.LogIt("Did NOT find Journal Entry "+JEntry+" on Account Document JE list item at index: "+index+". Found this instead: "+journalEntry, "WARNING", "");
		}
	}		
	
}
