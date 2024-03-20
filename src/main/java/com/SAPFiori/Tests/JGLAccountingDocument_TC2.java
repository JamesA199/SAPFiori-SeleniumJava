package com.SAPFiori.Tests;

import java.util.Base64;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.SAPFiori.AUTActions.AUTActions;
import com.SAPFiori.AUTBusinessFlows.AUTBusinessFlows;
import com.SAPFiori.BaseClass.BaseClass;
import com.SAPFiori.pageObj.HeaderDisplayPg_obj;
import com.SAPFiori.pageObj.CompanyCodePg_obj;
import com.SAPFiori.pageObj.DisplayJournalEntriesTacct_obj;
import com.SAPFiori.pageObj.PasswordUserPg_obj;
import com.SAPFiori.pageObj.SelectJournalEntryPg_obj;
import com.SAPFiori.pageObj.SignInUserPg_obj;
import com.SAPFiori.pageObj.TAccountViewPg_obj;
import com.SAPFiori.utilities.Log;

public class JGLAccountingDocument_TC2 extends BaseClass 
{
	HeaderDisplayPg_obj headerDisplayPg_obj;
	SignInUserPg_obj signInUserPg_Obj;
	PasswordUserPg_obj passwordUserPg_Obj;
	TAccountViewPg_obj tAccountViewPg_obj;
	DisplayJournalEntriesTacct_obj displayJournalEntriesTacct_obj;	
	SelectJournalEntryPg_obj selectJournalEntryPg_obj;
	CompanyCodePg_obj companyCodePg_obj;	
	
	@Test(groups = {"Regression"}, enabled=true, priority = 1)
	public void JGLUseTiles_Filters_test() throws Throwable
	{
		headerDisplayPg_obj=new HeaderDisplayPg_obj();	
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
			Log.info("There are "+allElements.size()+ "Cash Discount tiles.");
			for(int i=0; i<allElements.size();i++)
			{
				Log.info(allElements.get(i).getText());
			}
		}
		else
		{
			Log.info("*****WARNING No tiles found for Cash Discount.");
		}

		AUTBusinessFlows.openAppbyURL("AccountingDocument-impact");
		
		displayJournalEntriesTacct_obj.type_JournalEntry_TXTBx("100000000/1710/0E/2024");
		Thread.sleep(2000);	
		AUTActions.useKeyBoard("ENTER", 1);

		displayJournalEntriesTacct_obj.click_goBtn();
		Thread.sleep(2000);		
		String debitAmount = displayJournalEntriesTacct_obj.get_debitSum();
		if (debitAmount.equals("Debit: -659.99 USD"))
		{
			AUTActions.LogIt("Found debit amount equals: "+debitAmount, "PASS", null);
			
		}
		else
		{
			AUTActions.LogIt("Did NOT find debit amount '-659.99 USD' equals: "+debitAmount, "WARNING", null);
		}		
		AUTBusinessFlows.validate_JournalEnry("Journal Entry 100000000", 0);
		displayJournalEntriesTacct_obj.click_JournalEntryFilterBtn();		
		
		selectJournalEntryPg_obj.click_CompanyCodeFilterBtn();
		
		companyCodePg_obj.type_CompanySearchTxtBx("1710");
		
		companyCodePg_obj.click_CompanyGoBtn();
				
		Thread.sleep(1000);
		AUTBusinessFlows.srchIteminTbl(prop.getProperty("CompanyCodeTBL"), "1710", "Company Code", 1, "Click");

		Thread.sleep(1000);
		companyCodePg_obj.click_OKBtn();
		selectJournalEntryPg_obj.type_FiscalYrTxtBx("2024");
		
		selectJournalEntryPg_obj.click_GoBtn();
		
		Thread.sleep(3000);
		AUTBusinessFlows.srchIteminTbl(prop.getProperty("AccountingDocumentTbl"), "100000002", "Journal Entry", 2, "Click");
		Thread.sleep(3000);
	
		selectJournalEntryPg_obj.click_Ok_Btn();
		Thread.sleep(3000);
		displayJournalEntriesTacct_obj.click_goBtn();
		Thread.sleep(2000);
		String debitAmount1 = displayJournalEntriesTacct_obj.get_debitSum();
		if (debitAmount1.equals("Debit: -1,979.97 USD"))
		{
			AUTActions.LogIt("Found debit amount equals: "+debitAmount1, "PASS", "");
		}
		else
		{
			AUTActions.LogIt("Did NOT find debit amount '-1,979.97 USD' equals: "+debitAmount1, "WARNING", "");
		}	
		AUTActions.LogIt("DebitAmount1", "SCNCAP", "");
		AUTBusinessFlows.click_JEChcBx(0);
	
		String debitAmount2 = displayJournalEntriesTacct_obj.get_debitSum();
		if (debitAmount2.equals("Debit: -1,319.98 USD"))
		{
			AUTActions.LogIt("Found debit amount equals: "+debitAmount2, "PASS", "");
		}
		else
		{
			AUTActions.LogIt("Did NOT find debit amount '-1,319.98 USD' equals: "+debitAmount2, "WARNING", "");
		}		
		AUTActions.LogIt("DebitAmount2", "SCNCAP", "");
		AUTBusinessFlows.click_JEChcBx(0);

		AUTBusinessFlows.click_JEChcBx(1);

		String debitAmount3 = displayJournalEntriesTacct_obj.get_debitSum();
		if (debitAmount3.equals("Debit: -659.99 USD"))
		{
			AUTActions.LogIt("Found debit amount equals: "+debitAmount3, "PASS", "");
		}
		else
		{
			AUTActions.LogIt("Did NOT find debit amount '-659.99 USD' equals: "+debitAmount3, "WARNING", "");
		}			
		AUTActions.LogIt("DebitAmount3", "SCNCAP", "");
		displayJournalEntriesTacct_obj.click_TableIcon_Btn();
		Thread.sleep(2000);
		AUTBusinessFlows.srchIteminTbl(prop.getProperty("JEDocumentImpactTbl"), "13711400 (WIP Acc Rev)", "GL Account", 3, "NA");
		
		Thread.sleep(1000);
		
	
		//headerDisplayPg_obj.click_SignOut_MnuItem(); //sign out of the fiori app
	}
	
	@Test(groups = {"Regression"}, enabled=true, priority = 2)
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
		AUTActions.LogIt("JEDocumentImpactTbl", "SCNCAP", "");
	}	
	
}
