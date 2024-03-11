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
	WebElement oSearch_BTN;	
	
	@FindBy(id = "accountingDocumentVHDialogFilterBarId-filterItemControl_BASIC-CompanyCode-valueHelpDialog-table-rowsel0")
	WebElement oCCCheckBox_ChKBx;	

	@FindBy(id = "accountingDocumentVHDialogFilterBarId-filterItemControl_BASIC-CompanyCode-valueHelpDialog-ok-BDI-content")
	WebElement oOK_BTN;	
				  
	
	//initialize the page
	public CompanyCodePg_obj()
	{
		PageFactory.initElements(getDriver(), this);
	}	
	
	public void type_SearchField(String srchData)
	{
		AUTActions.type(oSearchField_TXTBx, srchData);
	}	
	public void click_SearchBTN()
	{
		AUTActions.click(getDriver(), oSearch_BTN, "Button");

	}
	public void click_CCCheckBox()
	{
		AUTActions.click(getDriver(), oCCCheckBox_ChKBx, "CheckBox");

	}	

	public void click_OKBtn()
	{
		AUTActions.click(getDriver(), oOK_BTN, "Button");

	}	
	
}
