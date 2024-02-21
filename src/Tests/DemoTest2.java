package Tests;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;

import Keywords.REST;
import POJO.DemoUser;
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
	
	@Test
	public void test_1()
	{
		DemoUser user = DemoUser.builder().email("ahgsfg@gmail.com").gender("male").name("testuser").status("active").build();
		RequestOptions options = RequestOptions.create()
				.setHeader("Authorization", "bearer 3322910e72c996d3d648185bee9dcf056d0e65c6cf83a68ec4597b9da39bc1a2")
				.setData(user);
		APIResponse response = REST.post("https://gorest.co.in/public/v2/users", options, request, logger);
		REST.assertStatusCode(response, 201, logger);
	}

}
