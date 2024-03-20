package com.SAPFiori.pageObj;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.SAPFiori.AUTActions.AUTActions;
import com.SAPFiori.BaseClass.BaseClass;


public class TAccountViewPg_obj extends BaseClass
{
	@FindBy(id = "__NRZ4FBXV0JKSYDGPOLNBAX70-cl")
	WebElement oXIcon_BTN;

	@FindBy(id = "searchFieldInShell-input-inner")
	WebElement oSearchApps_TXTBx;	

	@FindBy(id = "searchFieldInShell-input-content")
	WebElement oSearchAppsOuter_TXTBx;		
	
	@FindBy(id = "searchFieldInShell-button-inner")
	WebElement oSearch_BTN;
	//searchFieldInShell-button-img

	@FindBy(xpath = "//*[contains(text(), 'Accounts Payable Overview')]")
	WebElement oAccountsPayableOverview_Tile;
	
	@FindBy(xpath = "//span[text()='Display Line Items in ']")
	WebElement oDisplayLineItemsGL_Tile;
	//*[contains(text(), 'Display Line Items')]
	
	@FindBy(xpath = "//span[text()='Statement of Changes in ']")
	WebElement oStatementofChanges_Tile;
	
	@FindBy(xpath = "//span[text()='Display Journal Entries']")
	WebElement oDisplayJournalEntriesTAccountView_Tile;		


	//initialize the page
	public TAccountViewPg_obj()
	{
		PageFactory.initElements(getDriver(), this);
	}	

	public void click_xIconBtn()
	{
		AUTActions.click(oXIcon_BTN, "XIcon_BTN");

	}	
	
	public void click_searchAppsField()
	{
		AUTActions.click(oSearchApps_TXTBx, "SearchApps_TXTBx");

	}	
	
	public void mouseHoverSearchAppsField()
	{
		AUTActions.explicitWait(oSearchAppsOuter_TXTBx, 60);
		AUTActions.mouseHoverByJavaScript(oSearch_BTN, "Search_BTN");
	}	
	
	public void waitSearchAppsField()
	{
		AUTActions.explicitWait(oSearchAppsOuter_TXTBx, 60);
		System.out.println("wait for Search Apps Field");
	}		
	
	public void type_searchAppsField(String sText)
	{
		AUTActions.type(oSearchApps_TXTBx, sText);
	}	

	public void click_searchBtn()
	{
		AUTActions.click(oSearch_BTN, "SearchButton_BTN");

	}		
	
	public void click_AccountsPayableOverview()
	{
		AUTActions.click(oAccountsPayableOverview_Tile, "AccountsPayableOverview_Tile");
	}	
	
	public void click_DisplayLineItemsGL()
	{
		AUTActions.click(oDisplayLineItemsGL_Tile, "DisplayLineItemsGL_Tile");

	}			
	public void click_StatementofChange()
	{
		AUTActions.click(oStatementofChanges_Tile, "StatementofChanges_Tile");

	}		
	public void click_DisplayJournalEntriesTAccountView()
	{
		AUTActions.click(oDisplayJournalEntriesTAccountView_Tile, "DisplayJournalEntriesTAccountView_Tile");

	}	
}
