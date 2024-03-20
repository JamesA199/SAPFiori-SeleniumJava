package com.SAPFiori.pageObj;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.SAPFiori.AUTActions.AUTActions;
import com.SAPFiori.BaseClass.BaseClass;

public class SignInUserPg_obj extends BaseClass
{
	@FindBy(id = "j_username")
	WebElement oUser_TXTBx;

	@FindBy(css = "button#logOnFormSubmit>div")
	WebElement oContinue_Btn;	
	
	//initialize the page
	public SignInUserPg_obj()
	{
		PageFactory.initElements(getDriver(), this);
	}	

	public void type_userName(String uname)
	{
		AUTActions.type(oUser_TXTBx, uname);

	}
	
	public PasswordUserPg_obj click_continueBtn()
	{
		AUTActions.click(oContinue_Btn, "Button");
    	
		return new PasswordUserPg_obj();
	}		
	
}
