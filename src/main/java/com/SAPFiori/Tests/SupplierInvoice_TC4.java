package com.SAPFiori.Tests;

import java.util.Base64;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.SAPFiori.AUTActions.AUTActions;
import com.SAPFiori.AUTBusinessFlows.AUTBusinessFlows;
import com.SAPFiori.BaseClass.BaseClass;
import com.SAPFiori.pageObj.CompanyCodePg_obj;
import com.SAPFiori.pageObj.DisplayJournalEntriesTacct_obj;
import com.SAPFiori.pageObj.HeaderDisplayPg_obj;
import com.SAPFiori.pageObj.PasswordUserPg_obj;
import com.SAPFiori.pageObj.SelectJournalEntryPg_obj;
import com.SAPFiori.pageObj.SignInUserPg_obj;
import com.SAPFiori.pageObj.SupplierInvoiceInfoPg_obj;
import com.SAPFiori.pageObj.SupplierInvoiceListPg_obj;
import com.SAPFiori.pageObj.SupplierInvoicePg_obj;
import com.SAPFiori.pageObj.TAccountViewPg_obj;
import com.SAPFiori.utilities.Log;

public class SupplierInvoice_TC4 extends BaseClass
{
	
	SupplierInvoicePg_obj supplierInvoicePg_obj;
	SupplierInvoiceListPg_obj supplierInvoiceListPg_obj;
	SupplierInvoiceInfoPg_obj supplierInvoiceInfoPg_obj;
	
	@Test(groups = {"Regression"}, enabled=true, priority = 1)
	public void SICreate_test() throws Throwable
	{	
		supplierInvoicePg_obj=new SupplierInvoicePg_obj();
		supplierInvoiceListPg_obj=new SupplierInvoiceListPg_obj();
		supplierInvoiceInfoPg_obj=new SupplierInvoiceInfoPg_obj();
		
		AUTBusinessFlows.LoginDyn365();
		
		AUTBusinessFlows.openAppbyURL("SupplierInvoice-create");
		
		supplierInvoicePg_obj.selectTransactionTypeDrpDn("Invoice");
		supplierInvoicePg_obj.type_DatePickerTXTBx("TodayDate");
		supplierInvoicePg_obj.type_ReferenceTXTBx();
		supplierInvoicePg_obj.type_InvoicingPartyTXTBx("17100088 (DOMESTIC US SUPPLIER 88)");	
		Thread.sleep(1000);
		supplierInvoicePg_obj.click_oSubSectionHeader_Hdr();
		AUTActions.useKeyBoard("ARROWDOWN", 20);

	
		AUTBusinessFlows.addGLAcctLineItem("Debit", "10010000 (Petty Cash)", "5.00", 0);
		
		AUTBusinessFlows.addGLAcctLineItem("Credit", "10010000 (Petty Cash)", "5.00", 1);
		
		supplierInvoicePg_obj.click_CheckBtn();
		
		//success check
		AUTBusinessFlows.getToastMsg("//div[contains(@class, 'sapMMessageToast')]");
		AUTActions.LogIt("SupplierInvoice", "SCNCAP", "");
		supplierInvoicePg_obj.click_PostBtn();
		String invoiceNumber = supplierInvoicePg_obj.getText_oSupplierInvoiceNumber_LNK();

		supplierInvoicePg_obj.click_NOBtnN();
		AUTBusinessFlows.openAppbyURL("SupplierInvoice-list1");

		AUTBusinessFlows.srchIteminTbl(prop.getProperty("InvoiceListTbl"), invoiceNumber+"/2024", "Invoice Number", 3, "Click");
		AUTActions.pageLoadTimeOut(60);
		AUTActions.explicitWaitbyWE("//div[@id='application-SupplierInvoice-process-component---MMIV_HEADER_ID_S1--idS2P.MM.MSI.ObjectHeaderInvoiceStatus-subtitle']", 60);
		Thread.sleep(5000);
		String webPgTitle = getDriver().getTitle();
		AUTActions.LogIt("Webpage has loaded: "+webPgTitle, "INFO", "");
		if(webPgTitle.contains(prop.getProperty("SupplierInvoiceInfo_pgTitle")))
		{
			AUTActions.LogIt("Found web page: "+webPgTitle, "PASS", "");
		}
		else
		{
			AUTActions.LogIt("Did NOT find expected web page: "+prop.getProperty("SupplierInvoiceInfo_pgTitle"), "FAIL", "");
		}
				
		String invoiceNumberLBL = supplierInvoiceInfoPg_obj.getText_oHeaderInvoiceStatus_LBL();
		if(invoiceNumberLBL.equals(invoiceNumber))
		{
			AUTActions.LogIt("Found invoice number on Suppplier Invoice page: "+invoiceNumberLBL, "PASS", "");
		}
		else
		{
			AUTActions.LogIt("Expected invoice number '"+invoiceNumber+"' was NOT found on Suppplier Invoice page found this instead: "+invoiceNumberLBL, "FAIL", "");
		}

	}
}
