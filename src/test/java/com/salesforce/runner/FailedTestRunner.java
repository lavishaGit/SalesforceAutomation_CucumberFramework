package com.salesforce.runner;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
		features = {"@target/failed-scenarios.txt"},
		glue= {"stepdef"},
		monochrome = true,
		
		plugin={"pretty",
				"html:target/cucumber-html-report.html",
				"json:target/cucumber-json-report.json",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
		        }
		
		)
public class FailedTestRunner extends AbstractTestNGCucumberTests{
	
}
