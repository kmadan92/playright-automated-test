package Utilities;


import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
public class BrowserConfig extends WrapperUtilities {
	
	
	
	public static ThreadLocal<Browser> getBrowser(String BrowserType, ThreadLocal<ExtentTest> logger) {
		
		playwright.set(Playwright.create());
		LaunchOptions lp = new LaunchOptions();
		
		if(System.getProperty("HeadlessExecution").equalsIgnoreCase("Yes")) {
			
			lp.setHeadless(true);
		}	
		else {
			lp.setHeadless(false);
		}
			
		if(BrowserType.equalsIgnoreCase("Chrome")){
			lp.setChannel("chrome");
			browser.set(playwright.get().chromium().launch(lp));
			
		}
		else if(BrowserType.equalsIgnoreCase("Edge")) {

			lp.setChannel("msedge");
			browser.set(playwright.get().chromium().launch(lp));
			
		}
		else if(BrowserType.equalsIgnoreCase("Firefox")) {

			browser.set(playwright.get().firefox().launch(lp));
			
		}
		else if(BrowserType.equalsIgnoreCase("Safari")) {

			browser.set(playwright.get().webkit().launch(lp));
			
		}
		else {
				Assert.fail("Browser does not exist");
		}
		
		return browser;
	}
	
	public static ThreadLocal<BrowserContext> getBrowserContext(Browser browser, ThreadLocal<ExtentTest> logger) {
		
		browser_context.set(browser.newContext());
		
		return browser_context;
		
	}
	
	public static ThreadLocal<Page> getPage(BrowserContext context, ThreadLocal<ExtentTest> logger) {
		
		tab.set(context.newPage());

		return tab;
		
	}

}
