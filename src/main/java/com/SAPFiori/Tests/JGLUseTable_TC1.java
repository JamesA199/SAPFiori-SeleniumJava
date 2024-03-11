package com.SAPFiori.Tests;

import java.time.Duration;
import java.util.Base64;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.SAPFiori.AUTActions.AUTActions;
import com.SAPFiori.BaseClass.BaseClass;
import com.SAPFiori.pageObj.CompanyCodePg_obj;
import com.SAPFiori.pageObj.DisplayJournalEntriesTacct_obj;
import com.SAPFiori.pageObj.PasswordUserPg_obj;
import com.SAPFiori.pageObj.SelectJournalEntryPg_obj;
import com.SAPFiori.pageObj.SignInUserPg_obj;
import com.SAPFiori.pageObj.TAccountViewPg_obj;
import com.SAPFiori.AUTBusinessFlows.AUTBusinessFlows;
import com.relevantcodes.extentreports.LogStatus;
import com.SAPFiori.utilities.Log;

public class JGLUseTable_TC1 extends BaseClass
{
	SignInUserPg_obj signInUserPg_Obj;
	PasswordUserPg_obj passwordUserPg_Obj;
	TAccountViewPg_obj tAccountViewPg_obj;
	DisplayJournalEntriesTacct_obj displayJournalEntriesTacct_obj;	
	SelectJournalEntryPg_obj selectJournalEntryPg_obj;
	CompanyCodePg_obj companyCodePg_obj;
	
	@Test(groups = {"Regression"}, enabled=true, priority = 1)
	public void JGLUseTable_test() throws Throwable
	{

		signInUserPg_Obj=new SignInUserPg_obj();	
		passwordUserPg_Obj=new PasswordUserPg_obj();	
		tAccountViewPg_obj=new TAccountViewPg_obj();
		displayJournalEntriesTacct_obj=new DisplayJournalEntriesTacct_obj();
		selectJournalEntryPg_obj=new SelectJournalEntryPg_obj();
		companyCodePg_obj=new CompanyCodePg_obj();
		
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
		//tAccountViewPg_obj.mouseHoverSearchAppsField();		

		//driver.findElement(By.xpath("//span[text()='Cash Discount Utilization']")).click();
		List<WebElement> allElements = getDriver().findElements(By.xpath("//*[contains(text(), 'Cash Discount')]"));
		
		if( allElements.size() > 0)
		{
			System.out.println("There are "+allElements.size()+ "Cash Discount tiles.");
			for(int i=0; i<allElements.size();i++)
			{
				System.out.println(allElements.get(i).getText());
			}
		}
		else
		{
			System.out.println("No tiles found for Cash Discount.");
		}
		
		//AUTBusinessFlows.srchforApp("General Ledger");
		
		AUTBusinessFlows.openAppbyURL("AccountingDocument-impact");

		displayJournalEntriesTacct_obj.click_JournalEntryFilterBtn();		
		
		selectJournalEntryPg_obj.click_JournalEntryFilterBtn();
		
		companyCodePg_obj.type_SearchField("1710");
		
		companyCodePg_obj.click_SearchBTN();
				
		//companyCodePg_obj.click_CCCheckBox();
		Thread.sleep(1000);

		getDriver().findElement(By.id("accountingDocumentVHDialogFilterBarId-filterItemControl_BASIC-CompanyCode-valueHelpDialog-table-rowsel0")).click();
		Thread.sleep(1000);
		companyCodePg_obj.click_OKBtn();
		selectJournalEntryPg_obj.type_FiscalYrTxtBx("2024");
		
		selectJournalEntryPg_obj.click_Go_Btn();
		
		Thread.sleep(3000);
		AUTBusinessFlows.srchIteminTbl(prop.getProperty("AccountingDocumentTbl"), "100000000", "Journal Enry", 2, "Click");
		Thread.sleep(3000);
	
		selectJournalEntryPg_obj.click_Ok_Btn();
		Thread.sleep(3000);
		displayJournalEntriesTacct_obj.click_goBtn();
		Thread.sleep(3000);
		displayJournalEntriesTacct_obj.click_TableIcon_Btn();
		Thread.sleep(2000);
		AUTBusinessFlows.srchIteminTbl(prop.getProperty("JEDocumentImpactTbl"), "13711400 (WIP Acc Rev)", "GL Account", 3, "NA");
		
		AUTBusinessFlows.srchIteminTbl(prop.getProperty("JEDocumentImpactTbl"), "659.99", "Credit", 8,"NA");		

		/*displayJournalEntriesTacct_obj.type_JournalEntry_TXTBx("100000000/1710/0E/2024");
		Thread.sleep(2000);		
		displayJournalEntriesTacct_obj.click_goBtn();
		Thread.sleep(2000);		
		String debitAmount = displayJournalEntriesTacct_obj.get_debitSum();
		if (debitAmount.equals("Debit: -659.99 USD"))
		{
			Log.info("Found debit amount equals: "+debitAmount);
		}
		else
		{
			Log.info("Did NOT find debit amount '-659.99 USD' equals: "+debitAmount);
		}*/

		/*WebElement element = getDriver().findElement(By.xpath("/*[contains(text(), 'Display Line Items in General Ledger')]"));
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(500);*/ 		
		
		/*WebElement element = getDriver().findElement(By.xpath("//a[@title='Display Line Items in General Ledger']//div"));
		Actions actions = new Actions(getDriver());
		actions.moveToElement(element);
		actions.perform();*/
		//Actions action1 = new Actions(getDriver());
		//action1.sendKeys(Keys.PAGE_DOWN).build().perform();
		//System.out.println("Page down");
		//Thread.sleep(2000);
		//tAccountViewPg_obj.click_AccountsPayableOverview();
		//tAccountViewPg_obj.click_DisplayLineItemsGL();
		//tAccountViewPg_obj.click_StatementofChange();				
		//tAccountViewPg_obj.click_DisplayJournalEntriesTAccountView();	
	}
	
	
}
