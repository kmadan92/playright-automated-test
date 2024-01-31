package Utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;


public class ParentUtilities {
	
	public static ThreadLocal<ExtentTest> logger  =  new ThreadLocal<ExtentTest>();
	public static ExtentReports extent = null;
	

}
