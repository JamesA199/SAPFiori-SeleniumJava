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
	public void JEDocumentImpactValidateTotals_test() throws Throwable
	{

		signInUserPg_Obj=new SignInUserPg_obj();	
		passwordUserPg_Obj=new PasswordUserPg_obj();	
		tAccountViewPg_obj=new TAccountViewPg_obj();
		displayJournalEntriesTacct_obj=new DisplayJournalEntriesTacct_obj();
		selectJournalEntryPg_obj=new SelectJournalEntryPg_obj();
		companyCodePg_obj=new CompanyCodePg_obj();
		
		AUTBusinessFlows.LoginDyn365();
		
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
		
		AUTBusinessFlows.openAppbyURL("AccountingDocument-impact");

		displayJournalEntriesTacct_obj.click_JournalEntryFilterBtn();		
		
		selectJournalEntryPg_obj.click_CompanyCodeFilterBtn();
		
		companyCodePg_obj.type_CompanySearchTxtBx("1710");
		
		companyCodePg_obj.click_CompanyGoBtn();
		Thread.sleep(1000);
		companyCodePg_obj.click_Row1ChBoxChkBx(); //click on the 1st row checkbox
	
		//getDriver().findElement(By.id("accountingDocumentVHDialogFilterBarId-filterItemControl_BASIC-CompanyCode-valueHelpDialog-table-rowsel0")).click();
		
		Thread.sleep(1000);
		companyCodePg_obj.click_OKBtn();
		selectJournalEntryPg_obj.type_FiscalYrTxtBx("2024");
		
		selectJournalEntryPg_obj.click_GoBtn();
		
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
		
		AUTBusinessFlows.srchIteminTbl(prop.getProperty("JEDocumentImpactTbl"), "659.99USD", "Credit", 8,"NA");		

	}
	
	
}
