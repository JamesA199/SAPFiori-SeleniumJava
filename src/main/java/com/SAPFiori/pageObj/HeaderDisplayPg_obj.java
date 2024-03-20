package com.SAPFiori.pageObj;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.SAPFiori.AUTActions.AUTActions;
import com.SAPFiori.BaseClass.BaseClass;

public class HeaderDisplayPg_obj extends BaseClass
{
	@FindBy(id = "userActionsMenuHeaderButton")
	WebElement oProfile_Btn;	

	@FindBy(id = "__mbox-btn-0-BDI-content")
	WebElement oOk_Btn;	
	
	@FindBy(xpath = "//div[text()='Sign Out']")
	WebElement oSignOut_MnuItem;
	
	//initialize the page
	public HeaderDisplayPg_obj()
	{
		PageFactory.initElements(getDriver(), this);
	}
	
	public void click_SignOut_MnuItem() throws InterruptedException
	{
		AUTActions.click(oProfile_Btn, "profile_Btn");
		Thread.sleep(1000);
		AUTActions.click(oSignOut_MnuItem, "SignOut_MnuItem");
		Thread.sleep(1000);
		AUTActions.click(oOk_Btn, "Ok_Btn");
	}		

}
