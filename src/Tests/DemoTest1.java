package Tests;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import Keywords.PlaywrightAPI;
import Locators.DemoLocators;
import Utilities.WrapperUtilities;

public class DemoTest1 extends WrapperUtilities {
	
	
	@BeforeClass
	public void BeforeClass_DemoTest1() {
		
		browser.set(OpenBrowser(System.getProperty("Browser"), logger));
		
	}
	
	@BeforeMethod
	public void BeforeMethod_DemoTest1(ITestContext test) {
		
		browser_context.set(OpenBrowserContext(browser.get(), logger));
		StartRecording(browser_context, logger);
		tab.set(OpenTab(browser_context.get(), logger));
		URL.set(System.getProperty("URL"));
	
		
	}
	
	@AfterClass
	public void AfterClass_DemoTest1() {
		
		closePlayright(logger);
	}
	
	@AfterMethod
	public void AfterMethod_DemoTest1(ITestResult result) {
		
		StopRecording(browser_context, result.getMethod().getMethodName(), logger);
	}
	
	@Test
	public void negative_login() {
		
		
		PlaywrightAPI.NavigateToURL(getURL(logger), tab, logger);
		PlaywrightAPI.Type(DemoLocators.username, TracesDirectory, logger);
		PlaywrightAPI.Type(DemoLocators.password, TracesDirectory, logger);
		PlaywrightAPI.Click(DemoLocators.login, logger);
		
	}

}
