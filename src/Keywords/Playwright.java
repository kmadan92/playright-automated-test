package Keywords;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.model.service.util.ExceptionUtil;
import com.microsoft.playwright.Page;

import Utilities.WrapperUtilities;

public class Playwright extends WrapperUtilities {
	
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
	
	public static void Click(String locator, ThreadLocal<Page> tab, ThreadLocal<ExtentTest> logger) {
		
		try {
			
		TestReport.Log(logger, "Clicking to: "+locator);
		tab.get().click(locator);
		TestReport.Pass(logger, "Clicked Successfully to: "+locator);
		
		}catch(Exception e) {
			
			TestReport.Log(logger, ExceptionUtil.getStackTrace(e));
			TestReport.Fail(logger, "Clicking to "+locator+" failed");
		}
		
	}
	
	public static void Type(String locator, String text, ThreadLocal<Page> tab, ThreadLocal<ExtentTest> logger) {
		
		try {
			
		TestReport.Log(logger, "Typing: "+text+" to "+locator);
		tab.get().fill(locator, text);
		TestReport.Pass(logger, "Successfully typed: "+text+" to "+locator);
		
		}catch(Exception e) {
			
			TestReport.Log(logger, ExceptionUtil.getStackTrace(e));
			TestReport.Fail(logger, "Typing "+text+" failed to "+locator);
		}
		
	}

	}
