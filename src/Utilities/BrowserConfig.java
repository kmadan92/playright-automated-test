package Utilities;


import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Playwright;
public class BrowserConfig extends WrapperUtilities {
	
	
	public static Browser getBrowser( ThreadLocal<ExtentTest> logger) {
		
		playwright.set(Playwright.create());
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
		
		
		return browser.get();
		
	}

}
