package Utilities;


import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Playwright;

import Keywords.TestReport;

public class BrowserConfig extends ParentUtilities {
	
	public static Browser getBrowser(String BrowserType, ThreadLocal<ExtentTest> logger) {
		
		Playwright playwright = Playwright.create();
		Browser browser = null;
		LaunchOptions lp = new LaunchOptions();
		
		if(System.getProperty("Headless Execution").equalsIgnoreCase("Yes")) {
			
			lp.setHeadless(true);
			TestReport.Pass(logger, Status.INFO, "Headless Execution Initiated Successfully");
		}	
		else {
			lp.setHeadless(false);
			TestReport.Pass(logger, Status.INFO, "Headful Execution Initiated Successfully");
		}
			
		if(BrowserType.equalsIgnoreCase("Chrome")){
			TestReport.Log(logger, Status.INFO, "Launching Chrome Browser.....");
			lp.setChannel("chrome");
			browser= playwright.chromium().launch(lp);
			TestReport.Pass(logger, Status.INFO, "Chrome Browser Launched Successfully");
			
		}
		else if(BrowserType.equalsIgnoreCase("Edge")) {
			TestReport.Log(logger, Status.INFO, "Launching Edge Browser.....");
			lp.setChannel("msedge");
			browser= playwright.firefox().launch(lp);
			TestReport.Pass(logger, Status.INFO, "Edge Browser Launched Successfully");
			
		}
		else if(BrowserType.equalsIgnoreCase("Firefox")) {
			TestReport.Log(logger, Status.INFO, "Launching Firefox Browser.....");
			browser= playwright.firefox().launch(lp);
			TestReport.Pass(logger, Status.INFO, "Firefox Browser Launched Successfully");
			
		}
		else if(BrowserType.equalsIgnoreCase("Safari")) {
			TestReport.Log(logger, Status.INFO, "Launching Safari Browser.....");
			browser= playwright.webkit().launch(lp);
			TestReport.Pass(logger, Status.INFO, "Safari Browser Launched Successfully");
			
		}
		else {
			TestReport.Fail(logger, Status.FAIL, "Safari Browser Launched Successfully");
		}
		
		return browser;
	}

}
