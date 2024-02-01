package Tests;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import Utilities.WrapperUtilities;

public class DemoTest1 extends WrapperUtilities {
	
	
	@BeforeClass
	public void BeforeClass_DemoTest1() {
		
		browser.set(OpenBrowser(System.getProperty("Browser"), logger));
	}
	
	@BeforeTest
	public void BeforeTest_DemoTest1() {
		
		browser_context.set(OpenBrowserContext(browser.get(), logger));
		StartRecording(browser_context, logger);
		tab.set(OpenTab(browser_context.get(), logger));
	}
	
	@AfterClass
	public void AfterClass_DemoTest1() {
		
		closePlayright(logger);
	}
	
	@AfterTest
	public void AfterTest_DemoTest1(ITestResult result) {
		
		StopRecording(browser_context, result.getMethod().getMethodName(), logger);
	}

}
