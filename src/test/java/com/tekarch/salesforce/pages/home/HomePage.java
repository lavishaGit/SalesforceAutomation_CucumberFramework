package com.tekarch.salesforce.pages.home;

import org.apache.commons.exec.LogOutputStream;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.tekarch.salesforce.pages.base.BasePage;

public class HomePage extends BasePage {
	@FindBy(xpath="//*[@id='userNavLabel']") WebElement useraccount;
	@FindBy(xpath="//a[@title='Logout']") WebElement logout;
	@FindBy(xpath="//li[@id='home_Tab']//a")WebElement HomeTxt;
	

	public HomePage(WebDriver driver) {
		super(driver);
	}
	public String gettext() {
		return getText(HomeTxt);
	}
	
	public WebDriver logout() throws InterruptedException {
		  logoutAccount(useraccount, logout);
		  return driver;
	}
	
	public String getTitleOfThePage() {
		//waitUntilPageLoads();
		return getPagetitle();
	}
	public void waitforVisibilityUser(int timeoutInSeconds, String objectName) throws Exception {
	    waitForVisibilty(useraccount, timeoutInSeconds, objectName);
	}

	public void waitforVisibilitylogout(int timeoutInSeconds, String objectName) throws Exception {
	    waitForVisibilty(logout, timeoutInSeconds, objectName);
	}
	/*public String getTextFromStudentRegistrationFormText() {
		waitForVisibility(studentRegistration, 30,"studetn resitration text area");
		String data= getTextFromElement(studentRegistration, "stu regi form text");
		System.out.println("text extracted from registartion page="+data);
		return data;
	}*/
}
