package Utilities;


import com.aventstack.extentreports.ExtentTest;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import Keywords.TestReport;

public class BrowserConfig extends WrapperUtilities {
	
	
	
	public static ThreadLocal<Browser> getBrowser(String BrowserType, ThreadLocal<ExtentTest> logger) {
		
		playwright.set(Playwright.create());
		LaunchOptions lp = new LaunchOptions();
		
		if(System.getProperty("HeadlessExecution").equalsIgnoreCase("Yes")) {
			
			lp.setHeadless(true);
//			TestReport.Pass(logger, "Headless Execution Initiated Successfully");
		}	
		else {
			lp.setHeadless(false);
//			TestReport.Pass(logger, "Headful Execution Initiated Successfully");
		}
			
		if(BrowserType.equalsIgnoreCase("Chrome")){
//			TestReport.Log(logger, "Launching Chrome Browser.....");
			lp.setChannel("chrome");
			browser.set(playwright.get().chromium().launch(lp));
//			TestReport.Pass(logger, "Chrome Browser Launched Successfully");
			
		}
		else if(BrowserType.equalsIgnoreCase("Edge")) {
//			TestReport.Log(logger, "Launching Edge Browser.....");
			lp.setChannel("msedge");
			browser.set(playwright.get().chromium().launch(lp));
//			TestReport.Pass(logger, "Edge Browser Launched Successfully");
			
		}
		else if(BrowserType.equalsIgnoreCase("Firefox")) {
//			TestReport.Log(logger, "Launching Firefox Browser.....");
			browser.set(playwright.get().firefox().launch(lp));
//			TestReport.Pass(logger, "Firefox Browser Launched Successfully");
			
		}
		else if(BrowserType.equalsIgnoreCase("Safari")) {
//			TestReport.Log(logger, "Launching Safari Browser.....");
			browser.set(playwright.get().webkit().launch(lp));
//			TestReport.Pass(logger, "Safari Browser Launched Successfully");
			
		}
		else {
//			TestReport.Fail(logger, "Safari Browser Launched Successfully");
		}
		
		return browser;
	}
	
	public static ThreadLocal<BrowserContext> getBrowserContext(Browser browser, ThreadLocal<ExtentTest> logger) {
		
//		TestReport.Log(logger, "Initializing Browser Context.....");
		browser_context.set(browser.newContext());
//		TestReport.Pass(logger, "New Browser Context Set Successfully");
		
		return browser_context;
		
	}
	
	public static ThreadLocal<Page> getPage(BrowserContext context, ThreadLocal<ExtentTest> logger) {
		
//		TestReport.Log(logger, "Opening Browser Tab.....");
		tab.set(context.newPage());
//		TestReport.Pass(logger, "New Browser Tab Opened");
		
		return tab;
		
	}

}
