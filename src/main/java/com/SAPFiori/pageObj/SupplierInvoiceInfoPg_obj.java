package com.SAPFiori.pageObj;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.text.*;
import java.util.*;
import com.SAPFiori.AUTActions.AUTActions;
import com.SAPFiori.BaseClass.BaseClass;
import com.SAPFiori.utilities.Log;

public class SupplierInvoiceInfoPg_obj extends BaseClass
{
	@FindBy(id = "application-SupplierInvoice-process-component---MMIV_HEADER_ID_S1--idS2P.MM.MSI.ObjectHeaderInvoiceStatus-subtitle")
	WebElement oHeaderInvoiceStatus_LBL;

	@FindBy(xpath = "//span[text()='Debit']")
	WebElement oDebitCreditType_DRPDn;	
	
	@FindBy(xpath = "//bdi[text()='No']")
	WebElement oSuccessSupplierInvoice_NO_BTN;
	
	public SupplierInvoiceInfoPg_obj()
	{
		PageFactory.initElements(getDriver(), this);
	}	


	public String getText_oHeaderInvoiceStatus_LBL()
	{
		AUTActions.explicitWait(oHeaderInvoiceStatus_LBL, 60); 
		String invoiceNumber  = oHeaderInvoiceStatus_LBL.getText();
		String[] invoiceNumber1 = invoiceNumber.split("/");
		/*for (int i = 0; i < invoiceNumber1.length; i++) 
		{
			Log.info("invoice Number: "+invoiceNumber1[i]); // output
		}*/
		Log.info("Split invoice number removed year: "+invoiceNumber1[0]); // output
		return invoiceNumber1[0];//+"/"+invoiceNumber1[1];
	}	
	
}
