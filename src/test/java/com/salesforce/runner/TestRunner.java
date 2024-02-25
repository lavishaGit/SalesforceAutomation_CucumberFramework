package com.salesforce.runner;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
		features = {"src/test/resources/Features"},
		glue= {"stepdef"},
		monochrome = true,
		
		plugin={"pretty",
				"html:target/cucumber-html-report.html",
				"json:target/cucumber-json-report.json",
				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
				"rerun:target/failed-scenarios.txt"
		        }
		
		)
public class TestRunner extends AbstractTestNGCucumberTests{
	
}
