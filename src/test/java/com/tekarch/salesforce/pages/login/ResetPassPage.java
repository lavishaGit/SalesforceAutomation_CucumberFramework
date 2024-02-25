package com.tekarch.salesforce.pages.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.tekarch.salesforce.pages.base.BasePage;

public class ResetPassPage extends BasePage {
	@FindBy(xpath="//h1[text()='Check Your Email']") WebElement EmailTxt;


	public ResetPassPage (WebDriver driver) {
		super(driver);
	}

	public String getText(String obj) {
		String str= getText(EmailTxt);
		return str;
	}
	
	public String getTitleOfThePage() {
		//waitUntilPageLoads();
		return getPagetitle();
	}
}
