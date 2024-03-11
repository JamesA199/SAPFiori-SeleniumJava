package com.SAPFiori.pageObj;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.SAPFiori.AUTActions.AUTActions;
import com.SAPFiori.BaseClass.BaseClass;

public class DisplayJournalEntriesTacct_obj extends BaseClass
{
	@FindBy(xpath = "//span[@id='application-AccountingDocument-impact-component---main--TAccountAccountsGroupId-application-AccountingDocument-impact-component---main--tAccountPanelId-0-sum']")
	WebElement odebitAmount_LBL;
	//  span[@id='application-AccountingDocument-impact-component---main--TAccountAccountsGroupId-application-AccountingDocument-impact-component---main--tAccountPanelId-0-sum']
	@FindBy(id = "application-AccountingDocument-impact-component---main--smartFilterBar-filterItemControl_BASIC-accountingDocumentId-inner")
	WebElement oJournalEntry_TXTBx;	
				  
	@FindBy(id = "application-AccountingDocument-impact-component---main--smartFilterBar-btnGo-inner")
	WebElement oGo_BTN;		

	@FindBy(id = "application-AccountingDocument-impact-component---main--smartFilterBar-filterItemControl_BASIC-accountingDocumentId-vhi")
	WebElement oJournalEntryFilter_BTN;		

	@FindBy(xpath = "//span[@data-sap-ui-icon-content='']")
	WebElement oTableIcon_BTN;	
	
	//span[@data-sap-ui-icon-content='']
	
	//initialize the page
	public DisplayJournalEntriesTacct_obj()
	{
		PageFactory.initElements(getDriver(), this);
	}	

	public void type_JournalEntry_TXTBx(String journalEntry)
	{
		AUTActions.type(oJournalEntry_TXTBx, journalEntry);
	}		
	
	public String get_debitSum()
	{
		
		String label = odebitAmount_LBL.getText();
		return label;
	}		
	public void click_goBtn()
	{
		AUTActions.click(getDriver(), oGo_BTN, "oGo_BTN");

	}
	
	public void click_JournalEntryFilterBtn()
	{
		AUTActions.click(getDriver(), oJournalEntryFilter_BTN, "oJournalEntryFilter_BTN");

	}	
	public void click_TableIcon_Btn()
	{
		AUTActions.click(getDriver(), oTableIcon_BTN, "oTableIcon_BTN");

	}
	
}
