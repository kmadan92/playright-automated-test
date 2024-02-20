package Tests;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import Utilities.WrapperUtilities;

public class DemoTest2 extends WrapperUtilities {
	
	@BeforeMethod
	public void BeforeMethod_DemoTest1(ITestContext test) {
		
		setupPlaywright(playwright, logger);
		setAPIRequest(playwright, api_request, logger);
		setAPIRequestContext(request, api_request, logger);
		setURL(logger);
	
	}
	
	@AfterClass
	public void AfterClass_DemoTest1() {
		
		closePlayright(logger);
	}
	
	@AfterMethod
	public void AfterMethod_DemoTest1(ITestResult result) {
		
	}

}
