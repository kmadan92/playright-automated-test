package Utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;


public class ParentUtilities {
	
	public static ThreadLocal<ExtentTest> logger  =  new ThreadLocal<ExtentTest>();
	public static ExtentReports extent = null;
	public static ThreadLocal<Playwright> playwright = null;
	public static ThreadLocal<Browser> browser = null;
	public static ThreadLocal<BrowserContext> context = null;
	public static ThreadLocal<Page> page = null;
	
	
	public static Browser OpenBrowser(String Browser, ThreadLocal<ExtentTest> logger) {
		
		
		return BrowserConfig.getBrowser(Browser, logger);
		
	}
	
	public static Page OpenTab(Browser browser, ThreadLocal<ExtentTest> logger) {
		
		
		return BrowserConfig.getPage(BrowserConfig.getBrowserContext(browser, logger), logger);
		
	}

}
