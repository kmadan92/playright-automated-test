package Utilities;


import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Playwright;
public class BrowserConfig extends WrapperUtilities {
	
	
	public static Browser setupBrowser( ThreadLocal<Playwright> playwright, ThreadLocal<ExtentTest> logger) {
		
		LaunchOptions lp = new LaunchOptions();
		
		String headless = System.getProperty("HeadlessExecution");
		String BrowserType = System.getProperty("Browser");
		
		if(headless.equalsIgnoreCase("Yes")) {
			
			lp.setHeadless(true);
		}	
		else {
			lp.setHeadless(false);
		}
			
		if(BrowserType.equalsIgnoreCase("Chrome")){
			lp.setChannel("chrome");
			browser.set(getPlaywright(playwright, logger).chromium().launch(lp));
			
		}
		else if(BrowserType.equalsIgnoreCase("Edge")) {

			lp.setChannel("msedge");
			browser.set(getPlaywright(playwright, logger).chromium().launch(lp));
			
		}
		else if(BrowserType.equalsIgnoreCase("Firefox")) {

			browser.set(getPlaywright(playwright, logger).firefox().launch(lp));
			
		}
		else if(BrowserType.equalsIgnoreCase("Safari")) {

			browser.set(getPlaywright(playwright, logger).webkit().launch(lp));
			
		}
		else {
				Assert.fail("Browser does not exist");
		}
		
		
		return browser.get();
		
	}

}
