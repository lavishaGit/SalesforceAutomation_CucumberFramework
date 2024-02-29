package stepdef;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.salesforce.utility.Constants;
import com.salesforce.utility.ExtentUtility;
import com.salesforce.utility.PropertyUtility;
import com.tekarch.salesforce.pages.base.BasePage;
import com.tekarch.salesforce.pages.home.HomePage;
import com.tekarch.salesforce.pages.login.ForgotPasswordPage;
import com.tekarch.salesforce.pages.login.LoginPage;
import com.tekarch.salesforce.pages.login.ResetPassPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.reactivex.rxjava3.observers.BaseTestConsumer;

public class Login_stepdef {
	protected static ExtentUtility loginStepDefReportlog = ExtentUtility.getinstance();

	Logger loginPageLog = LogManager.getLogger();
	LoginPage login;
	HomePage homePage;
	ResetPassPage resetPage;
	ForgotPasswordPage forgotPassPage;
WebDriver driver= Hooks.getDriver();


	

	@Given("User is on Landing Page")
	public void user_is_on_landing_page() throws Exception {

		// Write code here that turns the phrase above into concrete actions
		loginPageLog.info("........ Initialized a  driver and launched Url successfully---------------");
		String expectedTxt = "Please enter your password.";
		String expTitle = "Login | Salesforce";
		// String
		// actTitle=pageManagerObjects.getLoginPageInstance().getTitleOfThePage();
		login = new LoginPage(Hooks.getDriver());
		String actTitle = login.getTitleOfThePage();

		try {
			Assert.assertEquals(actTitle, expTitle);
			loginPageLog.info(actTitle + "  matched with " + expTitle);
			loginStepDefReportlog.logTestwithPassed(actTitle + "  matched with " + expTitle);
			// reportlog.logTestwithPassed(actTitle + " matched with " + expTitle);
		} catch (AssertionError e) {
			loginPageLog.error(actTitle + "not   matched with " + expTitle);
			loginStepDefReportlog.logTestwithFailed(actTitle + "not   matched with " + expTitle);

			// reportlog.logTestfailwithException(e);
			throw e;
		}
		
	}

	@When("User enters  valid {string} in a User name field")
	public void user_enters_valid_in_a_user_name_field(String Username) {
		Username = PropertyUtility.readdatatofile(Constants.applicationPropertyPath, "username");
		login.enterUserName(Username);
		loginStepDefReportlog.logTestInfo("User name entered");
		// Write code here that turns the phrase above into concrete actions
	}

	@When("leaves Password field empty")
	public void leaves_password_field_empty() {
		// Write code here that turns the phrase above into concrete actions
		login.enterPassword("");
		loginPageLog.info("Enter empty data");
		loginStepDefReportlog.logTestInfo("Enter empty data");
	}

	@When("clicks login button")
	public void clicks_login_button() {
		// Write code here that turns the phrase above into concrete actions
		loginStepDefReportlog.logTestInfo(null);
		Hooks.setDriver(login.clickElement("login button"));
		loginStepDefReportlog.logTestInfo("clicks login button");

	}

	@Then("Verify User should see {string} error message")
	public void verify_user_should_see_error_message(String errMessage) {
		// String expectedTxt = "Please enter your password.";

		// Write code here that turns the phrase above into concrete actions
		System.out.println("Verify User should see error message");
		String actualTxt = login.getText("Label Text");
		try {
			Assert.assertEquals(actualTxt, errMessage);
			loginPageLog.info(actualTxt + "  matched with " + errMessage);
			loginStepDefReportlog.logTestwithPassed(actualTxt + "  matched with " + errMessage);

			// mybasePagelog.error(actualTxt + " matched with " + expectedTxt);

			// reportlog.logTestwithPassed(actualTxt + " matched with " + expectedTxt);

		} catch (AssertionError e) {
			loginPageLog.error(actualTxt + "not   matched with " + errMessage);
			loginStepDefReportlog.logTestwithFailed(actualTxt + "not   matched with " + errMessage);

			// reportlog.logTestfailwithException(e);
			throw e;
		}
		loginPageLog.info("All Assertons passed");
		loginStepDefReportlog.logTestInfo("All Assertons passed");

	}

	@Then("Verify user should  remain on a Login Page")
	public void verify_user_should_remain_on_a_login_page() {
		// Write code here that turns the phrase above into concrete actions
		System.out.println("Verify user should  remain on a Login Page");
		String expectedText = "Login | Salesforce";
		String pageSource = Hooks.getDriver().getPageSource();

		if (pageSource.contains(expectedText)) {
			loginPageLog.info("Expected text '" + expectedText + "' found on the page.");

			Assert.assertTrue(pageSource.contains(expectedText));
			loginStepDefReportlog.logTestwithPassed("Expected text '" + expectedText + "' found on the page.");
		} else {
			loginPageLog.error("Expected text '" + expectedText + "' not found on the page.");
			loginStepDefReportlog.logTestwithFailed("Expected text '" + expectedText + "' not found on the page.");

			Assert.fail();
		}
		loginPageLog.info("All Assertons passed");
		loginStepDefReportlog.logTestInfo("All Assertons passed");

	}

	@When("enters valid {string} in a password field")
	public void enters_valid_in_a_password_field(String password) {
		password = PropertyUtility.readdatatofile(Constants.applicationPropertyPath, "password");

		login.enterPassword(password);
		loginStepDefReportlog.logTestInfo("Password is entered");
	}

	@Then("User should redirects to Home Page")
	public void user_should_redirects_to_home_page() {
		// Write code here that turns the phrase above into concrete actions
		homePage = new HomePage(Hooks.getDriver());
		loginPageLog.info("User is lands to the Home Page");
		loginStepDefReportlog.logTestInfo("User is lands to the Home Page");

		
	}

	@Then("User should verify the home page title")
	public void user_should_verify_the_home_page_title() {
		String actTxtString = homePage.gettext();
		String exptxtString = "Home";
		if (actTxtString.equals(exptxtString)) {
			Assert.assertEquals(actTxtString, exptxtString);
			loginPageLog.info("Expected text " + exptxtString + " matches to the Actual Text: " + actTxtString);
			loginStepDefReportlog.logTestwithPassed(
					"Expected text " + exptxtString + " matches to the Actual Text: " + actTxtString);

		} else {
			loginPageLog
					.error("Expected text " + exptxtString + " does not matches to the Actual Text: " + actTxtString);
			loginStepDefReportlog.logTestwithFailed(
					"Expected text " + exptxtString + " does not matches to the Actual Text: " + actTxtString);

		}
		loginPageLog.info("All Assertons passed");
		loginStepDefReportlog.logTestInfo("All Assertons passed");

	}

	@When("enters valid {string} in a password field and clicks Remember me checkbox")
	public void enters_valid_in_a_password_field_and_clicks_remember_me_checkbox(String password) {
		password = PropertyUtility.readdatatofile(Constants.applicationPropertyPath, "password");

		login.enterPassword(password);
		loginPageLog.info("Password is entered");
	
		loginStepDefReportlog.logTestInfo("Password is entered");
		login.clickCheckBox("Remember Me check box ");
		loginPageLog.info("Remember Me check box clicked ");
		
		loginStepDefReportlog.logTestInfo("Remember Me check box clicked ");

	}

	@Then("clicks on User account link and clicks on logout button")
	public void clicks_on_user_account_link_and_clicks_on_logout_button() throws InterruptedException {
		// Write code here that turns the phrase above into concrete actions
		loginPageLog.info("User  redirects to Home Page");
		loginStepDefReportlog.logTestInfo("User  redirects to Home Page");
		String expTitle = "Home Page ~ Salesforce - Developer Edition";
		String actTitle = homePage.getTitleOfThePage();
		// homePage.wait(1000);

		try {
			Assert.assertEquals(actTitle, expTitle);
			loginPageLog.info(actTitle + "  matched with " + expTitle);
			loginStepDefReportlog.logTestwithPassed(actTitle + "  matched with " + expTitle);
		} catch (AssertionError e) {
			loginPageLog.error(actTitle + " not   matched with " + expTitle);
			loginStepDefReportlog.logTestwithFailed(actTitle + " not   matched with " + expTitle);
			;

			// reportlog.logTestfailwithException(e);
			throw e;
		}
		loginPageLog.info("All Assertons passed");
		loginStepDefReportlog.logTestInfo("All Assertons passed");

		Hooks.setDriver(homePage.logout());
	}

	@Then("Verify User redirects to the login page with  prefilled {string} in  username field")
	public void verify_user_redirects_to_the_login_page_with_prefilled_in_username_field(String string)
			throws Exception {
		// Write code here that turns the phrase above into concrete actions

		login.waitforVisibilityUserlabeltxt(30, "Username Label ");
		String explabelTxt = "sweety123@yahoo.com";
		String actlabelTxt = login.getTextUser("Username label");
		try {
			Assert.assertEquals(actlabelTxt, explabelTxt);
			loginPageLog.info(actlabelTxt + "  matched with " + explabelTxt);
			loginStepDefReportlog.logTestwithPassed(actlabelTxt + "  matched with " + explabelTxt);

		} catch (AssertionError e) {
			loginPageLog.error(actlabelTxt + " not   matched with " + explabelTxt);
			loginStepDefReportlog.logTestwithFailed(actlabelTxt + " not   matched with " + explabelTxt);
			// reportlog.logTestfailwithException(e);
			throw e;

		}
		loginPageLog.info("All Assertons passed");
		loginStepDefReportlog.logTestInfo("All Assertons passed");

	}

	@Then("clicks Forgot Password button")
	public void clicks_forgot_password_button() throws Exception {
		Hooks.setDriver(login.clickForgotBttn("Forgot Button"));
		forgotPassPage = new ForgotPasswordPage(Hooks.getDriver());

		String expTitle = "Forgot Your Password | Salesforce";
		String actTitle = forgotPassPage.getTitleOfThePage();
		// homePage.wait(1000);

		try {
			Assert.assertEquals(actTitle, expTitle);
			loginPageLog.info(actTitle + "  matched with " + expTitle);
			loginStepDefReportlog.logTestwithPassed(actTitle + "  matched with " + expTitle);

		} catch (AssertionError e) {
			loginPageLog.error(actTitle + " not   matched with " + expTitle);
			loginStepDefReportlog.logTestwithFailed(actTitle + " not   matched with " + expTitle);
			;

			// reportlog.logTestfailwithException(e);
			throw e;
		}
		forgotPassPage.waitforVisibilityUsername(30, "User Name ");
		String Username = PropertyUtility.readdatatofile(Constants.applicationPropertyPath, "username");

		forgotPassPage.enterUserName(Username);

		loginPageLog.info("All Assertons passed");
		loginStepDefReportlog.logTestInfo("All Assertons passed");

	}

	@Then("clicks on Continue button")
	public void clicks_on_continue_button() {
		// Write code here that turns the phrase above into concrete actions
		Hooks.setDriver(forgotPassPage.clickContinueBttn("Continue "));
	}

	@Then("Verify User redirects to the Reset Password Page with Page Title {string}")
	public void verify_user_redirects_to_the_reset_password_page_with_message(String expTitle1) {
		// Write code here that turns the phrase above into concrete actions
		resetPage = new ResetPassPage(Hooks.getDriver());
		String actTitle1 = forgotPassPage.getTitleOfThePage();

		try {
			Assert.assertEquals(actTitle1, expTitle1);
			loginPageLog.info(actTitle1 + "  matched with " + expTitle1);
			loginStepDefReportlog.logTestwithPassed(actTitle1 + "  matched with " + expTitle1);

		} catch (AssertionError e) {
			loginPageLog.error(actTitle1 + " not   matched with " + expTitle1);
			loginStepDefReportlog.logTestwithFailed(actTitle1 + " not   matched with " + expTitle1);
			;

			// reportlog.logTestfailwithException(e);
			throw e;
		}
		loginPageLog.info("All Assertons passed");
		loginStepDefReportlog.logTestInfo("All Assertons passed");

	}

	@When("enters invalid Password {string} in a password field")
	public void enters_invalid_in_a_password_field(String passwrd) {
		login.enterPassword(passwrd);
		loginPageLog.info("User enters invalid password");
		loginStepDefReportlog.logTestInfo("User enters invalid password");
	}

	@Then("Verify User should see the message {string}")
	public void verify_user_should_see_the_message(String explabel) {
		// Write code here that turns the phrase above into concrete actions
		String actErrorTxt = login.getTextLoginError("Login Error");

		Assert.assertEquals(true, actErrorTxt.contains(explabel));
		loginPageLog.info(actErrorTxt + "  matched with " + explabel);
		loginStepDefReportlog.logTestwithPassed(actErrorTxt + "  matched with " + explabel);

		loginPageLog.info("All Assertons passed");
		loginStepDefReportlog.logTestInfo("All Assertons passed");
	}

}
