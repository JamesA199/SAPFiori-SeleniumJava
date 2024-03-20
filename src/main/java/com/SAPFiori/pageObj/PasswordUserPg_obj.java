package com.SAPFiori.pageObj;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.SAPFiori.AUTActions.AUTActions;
import com.SAPFiori.BaseClass.BaseClass;

public class PasswordUserPg_obj extends BaseClass
{
	
	@FindBy(css = "input#password")
	WebElement oPassword_TXTBx;
	
	@FindBy(xpath = "//button[contains(@class,'uid-login-as__submit-button test-button')]")
	WebElement oSignin_BTN;	

	//initialize the page
	public PasswordUserPg_obj()
	{
		PageFactory.initElements(getDriver(), this);
	}	
	public void type_pWord(String pword)
	{
		AUTActions.type(oPassword_TXTBx, pword);
	}
	
	public void click_Signin_BTN()
	{
		System.out.println("click_Signin_BTN");
		AUTActions.click( oSignin_BTN, "Button");

	}	
}
