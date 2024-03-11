package com.SAPFiori.pageObj;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.SAPFiori.AUTActions.AUTActions;
import com.SAPFiori.BaseClass.BaseClass;

public class SelectJournalEntryPg_obj extends BaseClass
{
	@FindBy(id = "accountingDocumentVHDialogFilterBarId-filterItemControl_BASIC-CompanyCode-vhi")
	WebElement oCompanyCodeFilter_BTN;		
	
	@FindBy(id = "accountingDocumentVHDialogFilterBarId-btnGo-BDI-content")
	WebElement oGo_BTN;	

	@FindBy(id = "accountingDocumentVHDialogFilterBarId-filterItemControl_BASIC-FiscalYear-inner")
	WebElement oFiscalYr_TXTBx;		

	@FindBy(id = "accountingDocumentVHDialogId-ok-BDI-content")
	WebElement oOk_BTN;	
	
	//initialize the page
	public SelectJournalEntryPg_obj()
	{
		PageFactory.initElements(getDriver(), this);
	}	
	
	public void click_JournalEntryFilterBtn()
	{
		AUTActions.click(getDriver(), oCompanyCodeFilter_BTN, "oCompanyCodeFilter_BTN");

	}

	public void click_Go_Btn()
	{
		AUTActions.click(getDriver(), oGo_BTN, "oGo_BTN");

	}	

	public void click_Ok_Btn()
	{
		AUTActions.click(getDriver(), oOk_BTN, "oOk_BTN");

	}		
	
	public void type_FiscalYrTxtBx(String data)
	{
		AUTActions.type(oFiscalYr_TXTBx, data);
	}	
}
