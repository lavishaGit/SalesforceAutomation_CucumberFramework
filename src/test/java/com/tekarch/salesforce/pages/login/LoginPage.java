package com.tekarch.salesforce.pages.login;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.asserts.SoftAssert;

import com.tekarch.salesforce.pages.base.BasePage;

import stepdef.Hooks;

public class LoginPage extends BasePage{
	@FindBy(xpath="//*[@id='username']") WebElement userName;
	@FindBy(xpath="//*[@id='password']") WebElement password;
	@FindBy(id="Login") WebElement loginButton;
	@FindBy(id="error")	WebElement labelTxt ;
	@FindBy(id="rememberUn")WebElement remembercheck;
	@FindBy(xpath="//span[@id='idcard-identity']") WebElement userNameTxt ;
	@FindBy(id="forgot_password_link") WebElement forgotPassBttn;
	@FindBy(css ="div#error.loginError" )WebElement loginErrorlabel;


	public LoginPage(WebDriver driver) {
		super(driver);
		
	}
	
	public void enterUserName(String data) {
		//userNameElement.sendKeys(data);
		elementSendText(userName, data, "Username textbox");

		//extentReport.logTestInfo("username data is entered in username field");
	}
	public void enterPassword(String data) {
		elementSendText(password, data, "password field ");

	}
	
	
	
	public String getTitleOfThePage() {
		//waitUntilPageLoads();
		return getPagetitle();
	}
	
	public String getCurrrenURL() {
		//waitUntilPageLoads();
		return getCurrentURL();
	}
	
	public void waitforVisibilityLogin(int timeoutInSeconds, String objectName) throws Exception {
	    waitForVisibilty(loginButton, timeoutInSeconds, objectName);
	}

	public void waitforVisibilityUsername(int timeoutInSeconds, String objectName) throws Exception {
	    waitForVisibilty(userName, timeoutInSeconds, objectName);
	}

	public void waitforVisibilityPassward(int timeoutInSeconds, String objectName) throws Exception {
	    waitForVisibilty(password, timeoutInSeconds, objectName);
	}
	
	public void waitforVisibilityUserlabeltxt(int timeoutInSeconds, String objectName) throws Exception {
	    waitForVisibilty(userNameTxt, timeoutInSeconds, objectName);
	}
	public void waitforVisibilityloginError(int timeoutInSeconds, String objectName) throws Exception {
	    waitForVisibilty(loginErrorlabel, timeoutInSeconds, objectName);
	}

	public WebDriver clickElement(String objname) {
		
		clickbutton(loginButton, objname);
		return driver;
	}
	public String getText(String obj) {
		String str= getText(labelTxt);
		return str;
	}
	public String getTextUser(String obj) {
		String str= getText(userNameTxt);
		return str;
	}
public void clickCheckBox(String objname) {
		
		clickbutton(remembercheck, objname);
	}
public WebDriver clickForgotBttn(String objname) {
	
	clickbutton(forgotPassBttn, objname);
	return driver;

}
public String getTextLoginError(String obj) {
	String str= getText(loginErrorlabel);
	return str;
}
}
