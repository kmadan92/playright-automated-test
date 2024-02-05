package Keywords;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.model.service.util.ExceptionUtil;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import Utilities.WrapperUtilities;

public class PlaywrightAPI extends WrapperUtilities {
	
	public static void NavigateToURL(String URL, ThreadLocal<Page> tab, ThreadLocal<ExtentTest> logger) {
		
		try {
			
		TestReport.Log(logger, "Navigating to: "+URL);
		tab.get().navigate(URL);
		TestReport.Pass(logger, "Navigated Successfully to: "+URL);
		
		}catch(Exception e) {
			
			TestReport.Log(logger, ExceptionUtil.getStackTrace(e));
			TestReport.Fail(logger, "Navigation to "+URL+" failed");
		}
		
	}
	
	public static void Click(Locator locator, ThreadLocal<ExtentTest> logger) {
		
		try {
			
		TestReport.Log(logger, "Clicking to: "+locator);
		locator.click();
		TestReport.Pass(logger, "Clicked Successfully to: "+locator);
		
		}catch(Exception e) {
			
			TestReport.Log(logger, ExceptionUtil.getStackTrace(e));
			TestReport.Fail(logger, "Clicking to "+locator+" failed");
		}
		
	}
	
	public static void Type(Locator locator, String text, ThreadLocal<ExtentTest> logger) {
		
		try {
			
		TestReport.Log(logger, "Typing: "+text+" to "+locator);
		locator.fill(text);
		TestReport.Pass(logger, "Successfully typed: "+text+" to "+locator);
		
		}catch(Exception e) {
			
			TestReport.Log(logger, ExceptionUtil.getStackTrace(e));
			TestReport.Fail(logger, "Typing "+text+" failed to "+locator);
		}
		
	}
	
	public static String getText(Locator locator, ThreadLocal<ExtentTest> logger) {
		
		try {
			
		TestReport.Log(logger, "Getting Text from "+locator);
		String loc = locator.textContent();
		TestReport.Pass(logger, "Text from locator "+locator+" is "+loc);
		 
		return loc;
		
		}catch(Exception e) {
			
			TestReport.Log(logger, ExceptionUtil.getStackTrace(e));
			TestReport.Fail(logger, "Failed to getText from "+locator);
			return null;
		}
		
	}

	}
