package stepdef;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.google.common.io.Files;
import com.salesforce.utility.Constants;
import com.salesforce.utility.ExtentUtility;
import com.salesforce.utility.PropertyUtility;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;


public class Hooks {

private static	WebDriver driver = null;

//private static WebDriver driver;
	protected static  ExtentUtility reportlog = ExtentUtility.getinstance();

	protected static Logger myBaseTestLog = LogManager.getLogger();

	// protected Alert alert;
	// protected WebDriver driver=null;
	
	//@Parameters({ "browser" })
@BeforeAll
public static void beforeAll() {
	reportlog.startExtentReport();
//reportlog.logTestInfo("Extent Report attached");
}



	@Before
	public void setUpEachScenario(	Scenario scenario) throws Exception {
		myBaseTestLog.info(".........Before setUp executed --------------");
		reportlog.startExtentCreateReport(scenario.getName());
	initializeBrowser("chrome");

String url = PropertyUtility.readdatatofile(Constants.applicationPropertyPath, "url");
		baseURL(url);
		reportlog.logTestInfo("Url is launched");

		waitUntilPageLoads();
	}

	@After
	public void tearDownAfterEachScenario() {
		reportlog.endReport();
		driverClose();
		myBaseTestLog.info("******tearDownAftereachScenario executed***********");
		reportlog.logTestInfo("******tearDownAftereachScenario executed***********");

	}
/*	public static WebDriver getDriver() {
        if (driver == null) {
            // Initialize WebDriver (Chrome in this example)
			WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
           
        }
      
        return driver;
        }*/
	
	
	
        @AfterStep
        public void AddScreenshotAfterEachScenario(Scenario scenario) throws IOException {
      	//  File  srcFile;
      if( scenario.isFailed()) {
    	 File  srcFile=  ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
   byte[] srcBytes= FileUtils.readFileToByteArray(srcFile);
      scenario.attach(srcBytes, "image/png", "image");
    
      String filename = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date());
      
		String path = Constants.screenshotsFilepath + filename + ".png";
        if (srcFile != null && srcFile.exists()) {

		File desFile=new File(path);
		Files.copy(srcFile, desFile);}
		reportlog.logTestfailwithScreenshot(path);
  	  reportlog.logTestwithFailed("Captures the screenshot");

        }
        }
        
        
	public void initializeBrowser(String browser) {

		if (browser.equalsIgnoreCase("Chrome")) {
			if (driver == null) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-notifications");
			myBaseTestLog.info("Browser instance has started");
			// reportlog.logTestInfo("Browser instance has started");
			// initializing the class level driver
			driver = new ChromeDriver();
			reportlog.logTestInfo("New Session started");
			}
	  
	} else if (browser.equalsIgnoreCase("firefox")) {

			// System.setProperty("webdriver.chrome.driver",
			// "/Users/nitin/Downloads/Drivers/geckodriver");
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			myBaseTestLog.info("Browser instance has started");
			reportlog.logTestInfo("New Session started");


		} else if (browser.equalsIgnoreCase("safari")) {

			// System.setProperty("webdriver.chrome.driver",
			// "/Users/nitin/Downloads/Drivers/safaridriver");
			WebDriverManager.safaridriver().setup();
			driver = new SafariDriver();
			myBaseTestLog.info("Browser instance has started");
			reportlog.logTestInfo("New Session started");


		} else {
			myBaseTestLog.error("Browser is not available"+browser);
			reportlog.logTestwithFailed("New Session not started");

		}

		// maximize the browser

}

	/*public void initialSetup() {

		driver.manage().window().maximize();
		// titleCheck("Login | Salesforce");
		String username = propertyUtilityClass.readdatatofile(Constants.applicationPropertyPath, "username");
		String passwrd = propertyUtilityClass.readdatatofile(Constants.applicationPropertyPath, "password");
		WebElement email_field = driver.findElement(By.xpath("//*[@id='username']"));
		waitForVisibilty(email_field, 30, "email_field is");
		elementSendText(email_field, username, "Username");
		myBaseTestLog.info("Email is entered in a Field ");

		WebElement password = driver.findElement(By.xpath("//*[@id='password']"));
		elementSendText(password, passwrd, "Password");
		myBaseTestLog.info("Password is entered in a Field ");

		WebElement loginButton = driver.findElement(By.id("Login"));
		waitForVisibilty(loginButton, 40, "Login ");
		buttonCheck(loginButton, "login  ");
		myBaseTestLog.info("Successfully logged to the Home page");
		reportlog.logTestwithPassed("Successfully logged in to Home page");

		// WebElement message_okbttn=
		// driver.findElement(By.xpath("//a[@class='continue']"));
		// if(message_okbttn.isDisplayed()) {
		// message_okbttn.click();}

	}

	public void headlessBrowser() {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("headless");
		options.setHeadless(true);
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		myBaseTestLog.info("Headless Excution started");

		myBaseTestLog.info("Successfully logged in a headless mode to Home page");
		reportlog.logTestwithPassed("Successfully logged in a headless mode to Home page");
	}*/
	//public BaseTest() {};
	//public BaseTest(WebDriver driver) {
  //      BaseTest.driver = driver;
  //  }
	public void driverClose() {
		getDriver().close();
		myBaseTestLog.info("browser is closed");
		// reportlog.logTestInfo( "browser is closed");
		setDriver(null);
		Assert.assertNull(getDriver());
	}

	public static void baseURL(String url) throws Exception {
		try {
			getDriver().get(url);
			myBaseTestLog.info(url + " is entered");
			/// reportlog.logTestInfo( "Valid URL is launched ");
		} catch (Exception e) {
			myBaseTestLog.error("Error occurred while navigating to URL: " + e.getMessage());
			// myBaseTestLog.error(e);
			throw e; // Rethrow the exception to propagate it further
		}
	}

	



	public void waitUntilPageLoads() {
		myBaseTestLog.info("Waiting until page loads within  expectedtime period");
		// reportlog.logTestInfo("Waiting until page loads within expectedtime period");
		getDriver().manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
	}



	public static WebDriver getDriver() {
		return driver;
	}



	public static void setDriver(WebDriver driver) {
		Hooks.driver = driver;
	}

}
