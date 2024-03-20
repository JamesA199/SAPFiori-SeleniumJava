package com.SAPFiori.Tests;

import java.util.Base64;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.SAPFiori.AUTActions.AUTActions;
import com.SAPFiori.AUTBusinessFlows.AUTBusinessFlows;
import com.SAPFiori.BaseClass.BaseClass;
import com.SAPFiori.pageObj.AccountsPayaleOverviewPg_obj;
import com.SAPFiori.pageObj.CompanyCodePg_obj;
import com.SAPFiori.pageObj.DisplayJournalEntriesTacct_obj;
import com.SAPFiori.pageObj.HeaderDisplayPg_obj;
import com.SAPFiori.pageObj.PasswordUserPg_obj;
import com.SAPFiori.pageObj.SelectJournalEntryPg_obj;
import com.SAPFiori.pageObj.SignInUserPg_obj;
import com.SAPFiori.pageObj.TAccountViewPg_obj;
import com.SAPFiori.utilities.Log;

public class AOPayables_TC3 extends BaseClass
{
	
	AccountsPayaleOverviewPg_obj accountsPayaleOverviewPg_obj;

	
	@Test(groups = {"Regression"}, enabled=true, priority = 1)
	public void JGLLineItems_test() throws Throwable
	{

		accountsPayaleOverviewPg_obj=new AccountsPayaleOverviewPg_obj();	

		
		AUTBusinessFlows.LoginDyn365();
		
		AUTBusinessFlows.openAppbyURL("Account-overviewPayable");
		
		Thread.sleep(3000);
		//click Blocked Invoices Chart Original
		getDriver().findElement(By.id("application-Account-overviewPayable-component---card03_BlockedInvoicesChartOriginal_Tab1--ovpCardHeader")).click();
		accountsPayaleOverviewPg_obj.click_BlockedInvoicesChart_BTNTile();
		//supplier text field
		AUTActions.explicitWaitbyWE("//input[@id='application-Supplier-manageLineItems-component---fin.ap.lineitems.display.s1View--fin.ap.lineitems.display.SmartFilterBar-filterItemControl_BASIC-Supplier-inner']", 60);
		
		//name of screen capture...add command SCNCAP
		AUTActions.LogIt("ManageSupplierLineItems", "SCNCAP", "");
		//AUTActions.capture(getDriver(), "ManageSupplierLineItems");
		//Thread.sleep(10000);
	}
}
