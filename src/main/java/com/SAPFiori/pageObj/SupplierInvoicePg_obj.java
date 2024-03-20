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

public class SupplierInvoicePg_obj extends BaseClass
{
	@FindBy(id = "application-SupplierInvoice-create-component---MMIV_HEADER_ID_S1--idS2P.MM.MSI.CESelectTransaction-label")
	WebElement oTransactionType_DRPDn;

	@FindBy(xpath = "//span[text()='Debit']")
	WebElement oDebitCreditType_DRPDn;	
	
	@FindBy(id = "application-SupplierInvoice-create-component---MMIV_HEADER_ID_S1--idS2P.MM.MSI.CEDatePickerDocumentDate-datePicker-inner")
	WebElement oDatePicker_TXTBx;

	@FindBy(id = "application-SupplierInvoice-create-component---MMIV_HEADER_ID_S1--idS2P.MM.MSI.CEInputInvoicingParty-input-inner")
	WebElement oInvoicingParty_TXTBx;	

	@FindBy(id = "application-SupplierInvoice-create-component---MMIV_HEADER_ID_S1--idS2P.MM.MSI.CEInputReference-input-inner")
	WebElement oReference_TXTBx;
	
	@FindBy(xpath = "application-SupplierInvoice-create-component---MMIV_HEADER_ID_S1--idS2P.MM.MSI.CEInputGLAccountItemGLAccount-__clone204-input-inner")
	WebElement oGLAccount_TXTBx;
	
	@FindBy(xpath = "(//input[@value='0.00'])[2]")
	WebElement oAmount_TXTBx;
	
	@FindBy(xpath = "//button[@title='Add G/L account item']//span")
	WebElement oAdd_BTN;


	@FindBy(id = "application-SupplierInvoice-create-component---MMIV_HEADER_ID_S1--idS2P.MM.MSI.ButtonCheck-BDI-content")
	WebElement oCheck_BTN;
	
	@FindBy(id = "application-SupplierInvoice-create-component---MMIV_HEADER_ID_S1--idS2P.MM.MSI.ObjectPageSubSectionHeader-innerGrid")
	WebElement oSubSectionHeader_HDR;	

	@FindBy(xpath = "//bdi[text()='Post']")
	WebElement oPost_BTN;
	
	
	@FindBy(id = "application-SupplierInvoice-create-component---app--Dialogs--idS2P.MM.MSI.LinkSuccessDialog")
	WebElement oSupplierInvoiceNumber_LNK;
	
	@FindBy(xpath = "//bdi[text()='No']")
	WebElement oSuccessSupplierInvoice_NO_BTN;
	
	public SupplierInvoicePg_obj()
	{
		PageFactory.initElements(getDriver(), this);
	}	

	public void selectTransactionTypeDrpDn(String transType)
	{
		//AUTActions.selectByVisibleText(oTransaction_DRPDn, transType, "DropDown");
		
		AUTActions.click(oTransactionType_DRPDn, "Transaction_DRPDn");
		WebElement oTransactionType = getDriver().findElement(By.xpath("//li[text()='"+transType+"']"));
		oTransactionType.click();
		Log.info("Selected Transaction Type drop down item: "+transType);
		
	}

	public void selectDebitCreditDrpDn(String data)
	{
		//AUTActions.selectByVisibleText(oTransaction_DRPDn, transType, "DropDown");
		
		AUTActions.click(oDebitCreditType_DRPDn, "Transaction_DRPDn");
		WebElement oTransactionType = getDriver().findElement(By.xpath("//li[text()='"+data+"']"));
		//li[text()='Credit']
		oTransactionType.click();
		Log.info("Selected Debit/Credit drop down item: "+data);
		
	}
	

	public void type_DatePickerTXTBx(String date)
	{
		//String timeStamp1 = new SimpleDateFormat("MM/dd/yyyy").format(new Date());//time stamp
		if (date.equals("TodayDate"))
		{
			AUTActions.type(oDatePicker_TXTBx, "03/13/2024");
		}
		else
		{
			AUTActions.type(oDatePicker_TXTBx, "03/13/2024");
		}
	}

	public void type_InvoicingPartyTXTBx(String data)
	{
		AUTActions.type(oInvoicingParty_TXTBx, data);
		AUTActions.explicitWait(oInvoicingParty_TXTBx, 60);
	}
	
	public void type_ReferenceTXTBx()
	{
		
		String rndNumb = AUTActions.randomeNum(3);
		String rndStr = AUTActions.randomestring(3);
		
		AUTActions.type(oReference_TXTBx, "RE-"+rndStr+rndNumb);
	}
	
	public void type_GLAccountTXTBx(String data)
	{
		AUTActions.type(oGLAccount_TXTBx, data);
	}	
	
	public void type_AmountTXTBx(String data)
	{
		AUTActions.type(oAmount_TXTBx, data);
	}
	
	public void click_AddBtn()
	{
		AUTActions.click( oAdd_BTN, "Add_BTN");

	}	
	public void click_CheckBtn()
	{
		AUTActions.click( oCheck_BTN, "Check_BTN");

	}
	public void click_PostBtn()
	{
		AUTActions.click(oPost_BTN, "Post_BTN");

	}	
	public void waitTransactionType()
	{
		AUTActions.explicitWait(oTransactionType_DRPDn, 60);
	}	
	
	public void click_oSubSectionHeader_Hdr()
	{
		AUTActions.click(oSubSectionHeader_HDR, "SubSectionHeader_HDR");

	}	
	
	public String getText_oSupplierInvoiceNumber_LNK()
	{
		AUTActions.explicitWait(oSupplierInvoiceNumber_LNK, 60); 

		String invoiceNumber  = oSupplierInvoiceNumber_LNK.getText();
		String[] invoiceNumber1 = invoiceNumber.split("/");
		/*for (int i = 0; i < invoiceNumber1.length; i++) 
		{
			Log.info("invoice Number: "+invoiceNumber1[i]); // output
		}*/
		Log.info("Split invoice number removed year: "+invoiceNumber1[0]); // output
		return invoiceNumber1[0];//+"/"+invoiceNumber1[1];
	}	
	
	public void click_NOBtnN()
	{
		AUTActions.click(oSuccessSupplierInvoice_NO_BTN, "NO_BTN");
	}					
}
