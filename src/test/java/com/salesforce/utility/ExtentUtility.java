package com.salesforce.utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentUtility {

	protected ExtentReports extent;
	protected ExtentSparkReporter spark;
	protected ExtentTest testlog;
	protected static ExtentUtility extentobj;

	private ExtentUtility() {

	}

	public static ExtentUtility getinstance() {
		if (extentobj == null) {

			extentobj = new ExtentUtility();

		}

		return extentobj;
	}

	public void startExtentReport() {

		extent = new ExtentReports();
		spark = new ExtentSparkReporter(Constants.extentFilePath);
		extent.setSystemInfo("Host Name", "Salesforce");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("User Name", "Lavisha");

		spark.config().setDocumentTitle("Test Execution extent");
		spark.config().setReportName("Salesforce Regression tests");
		spark.config().setTheme(Theme.DARK);

		extent.attachReporter(spark);

	}

	public void startExtentCreateReport(String methodname) {
		// this will return methodname to the testlog
		testlog = extent.createTest(methodname);

	}

	public void endReport() {
		extent.flush();

	}

	public void logTestInfo(String text) {
		//System.out.println("ObjectLogger->" + testlog);// here we are trying to print methodname
		testlog.info(text);

	}

	public void logTestwithPassed(String text) {
		//System.out.println("ObjectLogger->" + testlog);// here we are trying to print methodname
		testlog.pass(MarkupHelper.createLabel(text, ExtentColor.GREEN));

	}

	public void logTestfailwithException(Throwable e) {
		//System.out.println("ObjectLogger->" + testlog);// here we are trying to print methodname
		testlog.fail(e);

	}

	public void logTestwithFailed(String text) {
		System.out.println("ObjectLogger->" + testlog);// here we are trying to print methodname
		testlog.fail(MarkupHelper.createLabel(text, ExtentColor.RED));

	}

	public void logTestfailwithScreenshot(String filepath) {
		System.out.println("ObjectLogger->" + testlog);// here we are trying to print methodname
		testlog.fail(MediaEntityBuilder.createScreenCaptureFromPath(filepath).build());

	}

}
