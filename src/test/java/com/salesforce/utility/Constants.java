package com.salesforce.utility;

import java.util.Date;
import java.text.SimpleDateFormat;

public class Constants {
	static String timeStamp = new SimpleDateFormat("d.MMM.YY.HH.mm.ss").format(new Date());

	public static final String currentDir=System.getProperty("user.dir");
	public  static final String applicationPropertyPath=currentDir+"/src/test/resources/applicationProperties.properties";
	public static final String excelFilePath=currentDir+"/src/test/java/com/salesforce/utility/Excel.java";
	public static final String extentFilePath=currentDir+"/reports/"+timeStamp+"/sparx.html";
	public static final String screenshotsFilepath=currentDir+"/reports/screenshots/";

}
