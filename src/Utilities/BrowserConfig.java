package Utilities;


import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Playwright;

import Keywords.TestReport;

public class BrowserConfig extends ParentUtilities {
	
	public static Browser getBrowser(String BrowserType, ThreadLocal<ExtentTest> logger) {
		
		Playwright playwright = Playwright.create();
		Browser browser = null;
		boolean headless;
		
		if(System.getProperty("Headless Execution").equalsIgnoreCase("Yes")) {
			
			headless=true;
			TestReport.Pass(logger, Status.INFO, "Headless Execution Initiated Successfully");
		}	
		else {
			headless=false;
			TestReport.Pass(logger, Status.INFO, "Headful Execution Initiated Successfully");
		}
			
		
		if(BrowserType.equalsIgnoreCase("Chrome")){
			TestReport.Log(logger, Status.INFO, "Launching Chrome Browser.....");
			browser= playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(headless));
			TestReport.Pass(logger, Status.INFO, "Chrome Browser Launched Successfully");
			
		}
		else if(BrowserType.equalsIgnoreCase("Firefox")) {
			TestReport.Log(logger, Status.INFO, "Launching Firefox Browser.....");
			browser= playwright.firefox().launch();
			TestReport.Pass(logger, Status.INFO, "Firefox Browser Launched Successfully");
			
		}
		else if(BrowserType.equalsIgnoreCase("Safari")) {
			TestReport.Log(logger, Status.INFO, "Launching Safari Browser.....");
			browser= playwright.webkit().launch();
			TestReport.Pass(logger, Status.INFO, "Safari Browser Launched Successfully");
			
		}
		else {
			TestReport.Fail(logger, Status.FAIL, "Safari Browser Launched Successfully");
		}
		
		return browser;
	}

}
