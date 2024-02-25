package com.tekarch.salesforce.pages.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.tekarch.salesforce.pages.base.BasePage;

public class ForgotPasswordPage extends BasePage {
	@FindBy(xpath="//input[@id='un']") WebElement userName;
	@FindBy(xpath="//input[@id='continue']") 	WebElement continueBttn;
	

	
	public ForgotPasswordPage(WebDriver driver) {
		super(driver);
	}
	
	public String getTitleOfThePage() {
		//waitUntilPageLoads();
		return getPagetitle();
	}
	public void enterUserName(String data) {
		//userNameElement.sendKeys(data);
		elementSendText(userName, data, "Username textbox");

		//extentReport.logTestInfo("username data is entered in username field");
	}public WebDriver clickContinueBttn(String objname) {
		
		clickbutton(continueBttn, objname);
		return driver;

		
	}
	public void waitforVisibilityUsername(int timeoutInSeconds, String objectName) throws Exception {
	    waitForVisibilty(userName, timeoutInSeconds, objectName);
	}
	
}
