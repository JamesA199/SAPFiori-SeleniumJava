package com.SAPFiori.pageObj;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.SAPFiori.AUTActions.AUTActions;
import com.SAPFiori.BaseClass.BaseClass;

public class CompanyCodePg_obj extends BaseClass 
{
	@FindBy(id = "accountingDocumentVHDialogFilterBarId-filterItemControl_BASIC-CompanyCode-valueHelpDialog-smartFilterBar-btnBasicSearch-I")
	WebElement oSearchField_TXTBx;		
	
	@FindBy(id = "accountingDocumentVHDialogFilterBarId-filterItemControl_BASIC-CompanyCode-valueHelpDialog-smartFilterBar-btnBasicSearch-search")
	WebElement oCompanyGo_BTN;	
	
	@FindBy(id = "accountingDocumentVHDialogFilterBarId-filterItemControl_BASIC-CompanyCode-valueHelpDialog-ok-BDI-content")
	WebElement oOK_BTN;	
	
	@FindBy(id = "accountingDocumentVHDialogFilterBarId-filterItemControl_BASIC-CompanyCode-valueHelpDialog-table-rowsel0")
	WebElement oRow1ChBox_ChkBx;	
				  
	
	//initialize the page
	public CompanyCodePg_obj()
	{
		PageFactory.initElements(getDriver(), this);
	}	
	
	public void type_CompanySearchTxtBx(String srchData)
	{
		AUTActions.type(oSearchField_TXTBx, srchData);
	}	
	public void click_CompanyGoBtn()
	{
		AUTActions.click(oCompanyGo_BTN, "CompanyGoBtn");

	}
	public void click_Row1ChBoxChkBx()
	{
		AUTActions.click(oRow1ChBox_ChkBx, "Row1ChBoxChkBx");

	}	

	public void click_OKBtn()
	{
		AUTActions.click(oOK_BTN, "OKBtn");

	}	
	
}
