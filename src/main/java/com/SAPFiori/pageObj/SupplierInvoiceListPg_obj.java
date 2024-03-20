package com.SAPFiori.pageObj;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.SAPFiori.AUTActions.AUTActions;
import com.SAPFiori.BaseClass.BaseClass;

public class SupplierInvoiceListPg_obj extends BaseClass 
{
	@FindBy(id = "ui.s2p.mm.supplinvoice.list::sap.suite.ui.generic.template.ListReport.view.ListReport::C_SupplierInvoiceList--listReportFilter-filterItemControl_BASIC-SupplierInvoice-inner")
	WebElement oInvoiceDocNumber_TXTBx;
	
	@FindBy(id = "//*[@id=\"ui.s2p.mm.supplinvoice.list::sap.suite.ui.generic.template.ListReport.view.ListReport::C_SupplierInvoiceList--listReportFilter-btnGo-BDI-content\"]")
	
	WebElement oGo_BTN;	
	
	public SupplierInvoiceListPg_obj()
	{
		PageFactory.initElements(getDriver(), this);
	}
	
	public void type_InvoiceDocNumberTXTBx(String data)
	{
		AUTActions.type(oInvoiceDocNumber_TXTBx, data);
	}	
	
	public void click_GoBtn()
	{
		AUTActions.click(oGo_BTN, "Go_BTN");
	}		
	
}
