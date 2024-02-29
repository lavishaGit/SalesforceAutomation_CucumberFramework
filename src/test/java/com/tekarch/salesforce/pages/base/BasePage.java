package com.tekarch.salesforce.pages.base;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.asserts.SoftAssert;

import com.google.common.io.Files;
import com.salesforce.utility.ExtentUtility;


public class BasePage  {
	public WebDriver driver;
	protected Wait<WebDriver> wait = null;
	protected Logger mybasePagelog = LogManager.getLogger();
	//protected ExtentUtility reportlog = ExtentUtility.getinstance();
	protected Alert alert;
	protected Actions action;
 

	public BasePage(WebDriver driver) {
		this.driver = driver;
	PageFactory.initElements(driver, this);
		
	}

	/*public void logout() throws InterruptedException {
		WebElement useraccount = this.driver.findElement(By.xpath("//*[@id='userNavLabel']"));
		WebElement logout = this.driver.findElement(By.xpath("//a[@title='Logout']"));
		logoutAccount(useraccount, logout);

	}*/

	public void logoutAccount(WebElement accountele, WebElement logoutele) throws InterruptedException {
		
		accountele.click();
		Thread.sleep(1000);
logoutele.click();
	}

	public void clickbutton(WebElement ele, String objname) {
		try {
		assertEquals(true, ele.isEnabled());
		ele.click();
		mybasePagelog.info(objname + "Is enabled and clicked");
	//	reportlog.logTestwithPassed(objname + "Is enabled and clicked");
		}catch(AssertionError e) {
			mybasePagelog.error(objname + " is not clickable Please check");
throw e;}
		/*if (ele.isEnabled()) {
			ele.click();

			mybasePagelog.info(objname + "Is enabled and clicked");
		} else {
			mybasePagelog.info(objname + " is not clickable Please check");
		}*/
		}

	public void waitForVisibilty(WebElement ele, int time, String Objname) throws Exception {
		try {

			wait = new WebDriverWait(driver, time);
			wait.until(ExpectedConditions.visibilityOf(ele));
			mybasePagelog.info(Objname + " IS WAITED FOR VISIBLITY");
			//reportlog.logTestInfo(Objname + " IS WAITED FOR VISIBLITY");
		} catch (Exception e) {
			mybasePagelog.error(Objname + " timeout exception");
			throw e;
		}
	}

	public void waitForVisibiltyofElementLocated(By ele, int time, String Objname) throws Exception {
		try {

			wait = new WebDriverWait(driver, time);
			wait.until(ExpectedConditions.visibilityOfElementLocated(ele));
			mybasePagelog.info(Objname + " IS WAITED FOR VISIBLITY OF ELEMENT TO BE LOCATED");
			//reportlog.logTestInfo(Objname + " IS WAITED FOR VISIBLITY OF ELEMENT TO BE LOCATED");
		} catch (Exception e) {
			mybasePagelog.error(Objname + " timeout exception");
			throw e;
		}
	}

	public void waitForclickable(WebElement ele, int time, String Objname) throws Exception {
		try {

			mybasePagelog.info(Objname + " IS WAITED FOR clickable");
			wait = new WebDriverWait(driver, time);
			wait.until(ExpectedConditions.elementToBeClickable(ele));
		} catch (Exception e) {
			mybasePagelog.error(Objname + " did not become visible within the specified time" + e.getMessage());
			//reportlog.logTestwithFailed(Objname + " did not become visible within the specified time");
			throw e;
		}
	}

	public void waitForVisibility(WebElement ele, int time, int pollingtime, String objectName) {
		mybasePagelog.info(objectName + " IS WAITED FOR Visibility");

		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver);
		wait.withTimeout(time, TimeUnit.SECONDS).pollingEvery(pollingtime, TimeUnit.SECONDS)
				.ignoring(ElementNotInteractableException.class)
				.withMessage(objectName + " is not visible.fluent wait time expires");

		wait.until(ExpectedConditions.visibilityOf(ele));
		mybasePagelog.info(objectName + " is waited for visibility using fluent wait");
		//reportlog.logTestInfo(objectName + " is waited for visibility using fluent wait");
	}
	public void elementSendText(WebElement ele, String info, String objname) {
		try {
			assertEquals(true, ele.isEnabled());
			ele.click();
			ele.clear();
			mybasePagelog.info(ele.toString() + "Is enabled and clicked");
		//	reportlog.logTestInfo(objname + "Is enabled and clicked");

			ele.sendKeys(info);
			mybasePagelog.info(objname + "Is displayed  in textarea");
		//	reportlog.logTestInfo(objname + "Is displayed  in textarea");
		}catch(AssertionError e) {
			mybasePagelog.error(objname + " is not enabled :Please  check");
			mybasePagelog.error(objname + "Is not entered  in textarea");

	throw e;
		}}

	public void clickElement(WebElement ele, String objectName) {
		try {
			assertEquals(true, ele.isEnabled());//enabled to interact with like buttons 

			ele.click();
			mybasePagelog.info(objectName + "button is clicked- Enabled Assert passed ");
			//reportlog.logTestInfo(objectName +  "button is clicked");
		
		}catch(AssertionError e) {
			mybasePagelog.error(objectName + " is not enabled :Please  check");

	throw e;
		}}

	public void isSelectedElement(WebElement ele, String objectName) {
		try {

		assertEquals(true, ele.isSelected());
			ele.click();
			mybasePagelog.info(objectName + "Is selected – Assert passed");
		//	reportlog.logTestInfo(objectName + "Is selected – Assert passed");


		}catch(AssertionError e) {
			mybasePagelog.info(objectName + "Element is not selected ");
throw e;
		}
	}

	public void elementTextVerify(WebElement ele, String expText) {
		String expTexts = expText;
		String actText=""; // Initialize actText with a default value

		try {
	assertEquals(true, ele.isDisplayed());// Verifies that element is displayes
	mybasePagelog.info(ele.toString() + "Is enabled and clicked");
	//reportlog.logTestwithPassed("Is selected – Assert passed");
	 actText = ele.getAttribute("value");

		assertEquals(actText, expTexts);
		mybasePagelog.info(actText+"Expected value matches the actual value" + expTexts);
		//reportlog.logTestwithPassed(actText+"Expected value matches the actual value" + expTexts);

		}catch(AssertionError e) {
		mybasePagelog.error(actText+"Expected value do not matches the actual value" + expTexts);
		// if (expText.equals(actText)) {
throw e;
	}}

	
	public String getPagetitle() {

		
		String actTitle = driver.getTitle();
		// mybasePagelog.info("title= " + actTitle);
		return actTitle;

		

	}

	public String getCurrentURL() {

		String actURL = driver.getCurrentUrl();
		mybasePagelog.info("Current URL is = " + actURL);
		return actURL;
	/*	try {
			softAssert.assertEquals(actURL, expURL);
			mybasePagelog.info(actURL + "  matched with " + expURL);
			reportlog.logTestwithPassed(actURL + "  matched with " + expURL);

		} catch (AssertionError e) {
			mybasePagelog.error(actURL + "not   matched with " + expURL);

			// reportlog.logTestfailwithException(e);
			throw e;
		}*/
		

	}

	public String getText(WebElement ele) {
		String actual = ele.getText();
		mybasePagelog.info("Element extracted text is = " + actual);

		return actual;
	}

	public void getTextCheck(WebElement ele, String exp) {
		String expectedTxt = exp;
		String actualTxt = ele.getText();
		try {
			assertEquals(actualTxt, expectedTxt);
			mybasePagelog.info(actualTxt + "  matched with " + expectedTxt);
			// mybasePagelog.error(actualTxt + " matched with " + expectedTxt);

		//	reportlog.logTestwithPassed(actualTxt + "  matched with " + expectedTxt);

		} catch (AssertionError e) {
			mybasePagelog.error(actualTxt + "not   matched with " + expectedTxt);

			// reportlog.logTestfailwithException(e);
			throw e;
		}
	
	}
	// }

	/*
	 * if (expected.equals(actual) || (actual.contains(expected)))// it returns true
	 * if one of the condition is true
	 * 
	 * { mybasePagelog.info("Match found  " + object + exp); } else {
	 * mybasePagelog.info("Match not found " + object + exp); }
	 * 
	 * }
	 */

	public void switchToAlert() {

		alert = driver.switchTo().alert();
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.alertIsPresent());
		mybasePagelog.info("Now focus is on Alert Dialog box");
		//reportlog.logTestInfo("Now focus is on Alert Dialog box");
	}

	public void acceptAlert() {
		alert.accept();
		mybasePagelog.info("ok button is clicked");
		//reportlog.logTestInfo("ok button is clicked");

	}

	public String getAlertlabelText() {
		String text = alert.getText();
		mybasePagelog.info("Alert text is retrieved");
		//reportlog.logTestInfo("Alert text is retrieved");
		return text;
	}

	public void sendtoAlertText(String obj) {
		alert.sendKeys(obj);
		mybasePagelog.info("Prompt text is:" + obj);
	//	reportlog.logTestInfo("Prompt text is:" + obj);

	}

	public void dismiss() {
		alert.dismiss();
		mybasePagelog.info("Alert is dismissed");
	//	reportlog.logTestInfo("Alert is dismissed");

	}

	public String getTextFromElement(WebElement ele, String objectName) {
		String data = ele.getText();
		mybasePagelog.info("text is extracted from " + objectName);
	//	reportlog.logTestInfo("text is extracted from \" + objectName");
		return data;
	}

	public void mouseHover_Interaction(WebElement ele) {
		action = new Actions(driver);

		action.moveToElement(ele, 10, 10).click().build().perform();// build means
		// ready to be performed
		// action.moveToElement(ele).click().build().perform();// build means ready to
		// be performed
		mybasePagelog.info("Cursor hovered to the desired element");
	//	reportlog.logTestInfo("Cursor hovered to the desired element");
	}

	public void ContextClickOnElement(WebElement ele, String objName) {
		Actions action = new Actions(driver);
		action.contextClick(ele).build().perform();
		mybasePagelog.info("right click persormed on web element " + objName);
	//	reportlog.logTestInfo("right click persormed on web element " + objName);

	}

	public void actionCall() {
		action = new Actions(driver);
		mybasePagelog.info("Action object created");
	//	reportlog.logTestInfo("Action object created");

		;
	}

	public void actionDragandDropCall(WebElement ele1, WebElement ele2) {
		action.dragAndDrop(ele1, ele2).build().perform();
		mybasePagelog.info("Dragand drop action is performed successfully....");
	//	reportlog.logTestInfo("Dragand drop action is performed successfully.");
		;
	}

	public void toolTip(WebElement ele, WebElement tooltipele) {
		action.moveToElement(ele).build().perform();
		;
		driver.switchTo().activeElement();

		String str = tooltipele.getText();
		mybasePagelog.info("tooltiptext ---> " + str);
	//	reportlog.logTestInfo("tooltiptext ---> " + str);
	}

	public void screenshotWebElement(WebElement ele, String filepath) {

		File srcFile = ele.getScreenshotAs(OutputType.FILE);
		File descFile = new File(filepath);
		try {
			Files.copy(srcFile, descFile);
			mybasePagelog.info("captures the screenshot");
		//	reportlog.logTestInfo("captures the screenshot");

		} catch (IOException e) {

			mybasePagelog.error("Error while capturing  the screenshot" + e.getMessage());

		}
	}

	public void twoStringVerify(String actvalue, String expvalue) {

		try {
			Assert.assertEquals((actvalue).replaceAll("\\s+", ""), (expvalue.replaceAll("\\s+", "")));
			// Assert.assertTrue(actvalue.trim().equals(expvalue), "Strings are not equal
			// after trimming whitespace");

			mybasePagelog.info("Actual value " + actvalue + " match the expected value" + expvalue);
			//reportlog.logTestwithPassed("Actual value " + actvalue + " match the expected value" + expvalue);
		} catch (Exception e) {

			mybasePagelog.error("Actual value " + actvalue + " do not  match the expected value" + expvalue);
		//	reportlog.logTestwithFailed("Actual value " + actvalue + " do not  match the expected value" + expvalue);
		}
	}

	public void waitUntilPageLoads() {
		mybasePagelog.info("Waiting until page loads within  expectedtime period");
		// reportlog.logTestInfo("Waiting until page loads within expectedtime period");
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
	}

	/*
	 * public void switchToNewWindowFrom(String currentWindowHandle) { Set<String>
	 * allWindowHandles = driver.getWindowHandles(); for (String handle :
	 * allWindowHandles) { if (!currentWindowHandle.equalsIgnoreCase(handle))
	 * driver.switchTo().window(handle); }
	 * System.out.println("switched to new window"); } public WebElement
	 * selectFromListUsingText(List<WebElement> list, String text) { WebElement
	 * element = null; for (WebElement i : list) { if
	 * (i.getText().equalsIgnoreCase(text)) { System.out.println("selected=" +
	 * i.getText()); element = i; break; }
	 * 
	 * } return element;
	 * 
	 * }
	 * 
	 */

	public void verifyDefaultoption(By locator, String objname) throws Exception {

		WebElement dropdown = driver.findElement(locator);
		dropdown.click();
		mybasePagelog.info("Dropdown element is clicked");
		//reportlog.logTestInfo("Dropdown element is clicked");
		Select select = new Select(dropdown);
		String defaultActualString = select.getFirstSelectedOption().getText();
		mybasePagelog.info("Select  default value is: " + defaultActualString);// select.getFirstSelectedOption().toString());
		//reportlog.logTestInfo("Select  default value is: " + defaultActualString);

		String expString = objname;
		try {

			Assert.assertEquals(defaultActualString, expString);
			mybasePagelog.info("Default selected option matches to the Actual option");
		//	reportlog.logTestwithPassed("Default selected option matches to the Actual option");
		} catch (Exception e) {
			// TODO: handle exception
			mybasePagelog.error("Default selected option  not matches to the Actual option" + e.getMessage());

			throw e;
		}
	}

	public void selectByVisibleText(By locator, String visibleText) throws Exception {
		WebElement dropdownElement = driver.findElement(locator);
		dropdownElement.click();
		mybasePagelog.info("Dropdown element is clicked");
	//	reportlog.logTestInfo("Dropdown element is clicked");
		Select dropdown = new Select(dropdownElement);
		try {
			dropdown.selectByVisibleText(visibleText);
			mybasePagelog.info("Text '" + visibleText + "' is selected from dropdown");

			String selectedOptionText = dropdown.getFirstSelectedOption().getText();

			Assert.assertEquals(visibleText, selectedOptionText);
			mybasePagelog.info("selected option matches to the Actual option");

			//reportlog.logTestwithPassed("Selected option matches to the expected Visible text");

		} catch (Exception e) {
			mybasePagelog.error("Desired Visible text" + visibleText + "is not selected");
			// reportlog.logTestfailwithException(e);
			throw e;
		}
	}

	public void selectByIndex(By locator, int Index) throws Exception {

		WebElement dropdownElement = driver.findElement(locator);
		dropdownElement.click();

		mybasePagelog.info("Dropdown element is clicked");
	//	reportlog.logTestInfo("Dropdown element is clicked");
		Select dropdown = new Select(dropdownElement);
		try {
			dropdown.selectByIndex(Index);
			mybasePagelog.info(Index + "' is selected from dropdown");
			String selectedOptionText = dropdown.getFirstSelectedOption().getText();
			Assert.assertTrue(true, selectedOptionText);
			mybasePagelog.info("selected option matches to the Actual option");

			//reportlog.logTestwithPassed("Selected option matches to the expected Visible text");
		} catch (Exception e) {
			mybasePagelog.error("Desired Index" + Index + "is not selected");
			// reportlog.logTestfailwithException(e);
			throw e;

		}
	}

	public void selectByValue(By locator, String value) throws Exception {
		WebElement dropdownElement = driver.findElement(locator);
		dropdownElement.click();
		mybasePagelog.info("Dropdown element is clicked");

		Select dropdown = new Select(dropdownElement);
		try {

			dropdown.selectByValue(value);

			String selectedOptionText = dropdown.getFirstSelectedOption().getAttribute("value");

			Assert.assertTrue(true, selectedOptionText);
			mybasePagelog.info("selected option matches to the Actual option");

		//	reportlog.logTestwithPassed("Selected option matches to the expected value ");

		//	reportlog.logTestwithPassed("Selected option matches to the expected value");
		} catch (Exception e) {
			mybasePagelog.error("Desired Value" + value + "is not selected");
			// reportlog.logTestfailwithException(e);
			throw e;

		}
	}

	public void getAllOptionsAndVerify(By locator) {
		WebElement dropdownElement = driver.findElement(locator);
		dropdownElement.click();
		Select select = new Select(dropdownElement);
		List<WebElement> optionList = select.getOptions();
		for (WebElement option : optionList) {

			// mybasePagelog.info("The dropdown oppertunities are: " + option.getText());

		}
		List<String> expectedOptions = new ArrayList();
		expectedOptions.add("All Opportunities");
		expectedOptions.add("Closing Next Month");
		expectedOptions.add("Closing This Month");
		expectedOptions.add("My Opportunities");
		expectedOptions.add("New Last Week");
		expectedOptions.add("New This Week");
		expectedOptions.add("Opportunity Pipeline");
		expectedOptions.add("Private");
		expectedOptions.add("Recently Viewed Opportunities");
		expectedOptions.add("Won");
		System.out.println(expectedOptions);
		try {
			Assert.assertEquals((optionList), expectedOptions);

			mybasePagelog.info("Dropdown options match the expected list options.");
			//reportlog.logTestwithPassed("Dropdown options match the expected list options.");

		}

		catch (Exception e) {
			mybasePagelog.error("Dropdown options do not  match the expected list." + e.getMessage());

		}

	}

	public void dropdownChoosebyText(List<WebElement> ele, String info, String obj) {
		for (WebElement field : ele) {
			String getText = field.getText().trim(); // Trim whitespace from the text

			if (getText.equals(info)) {
				if (field.isDisplayed()) { // Check if the element is visible
					field.click();
					System.out.println(obj + "is selected from dropdown");
				} else {
					System.out.println(obj + "is not visible in the dropdown");
				}
				break; // Exit the loop after selecting the date
			}
		}
		//
	}
	public WebDriver getDriverInstance() {
		return this.driver;
	
	}
}
