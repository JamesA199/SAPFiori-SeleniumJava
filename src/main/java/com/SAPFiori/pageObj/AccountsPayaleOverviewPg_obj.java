package com.SAPFiori.pageObj;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.SAPFiori.AUTActions.AUTActions;
import com.SAPFiori.BaseClass.BaseClass;

public class AccountsPayaleOverviewPg_obj extends BaseClass
{
	@FindBy(id = "application-Account-overviewPayable-component---card03_BlockedInvoicesChartOriginal_Tab1--ovpCardHeader")
	WebElement oBlockedInvoicesChart_BTNTile;	
	
	//initialize the page
	public AccountsPayaleOverviewPg_obj()
	{
		PageFactory.initElements(getDriver(), this);
	}
	
	public void click_BlockedInvoicesChart_BTNTile()
	{
		AUTActions.click(oBlockedInvoicesChart_BTNTile, "BlockedInvoicesChart_BTNTile");

	}	
	
}
