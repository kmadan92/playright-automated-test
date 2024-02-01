package Tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import Utilities.ParentUtilities;

public class DemoTest1 extends ParentUtilities {
	Browser web_browser;
	Page tab;
	
	@BeforeClass
	public void BeforeClass_DemoTest1() {
		
		web_browser = OpenBrowser(System.getProperty("Browser"), logger);
	}
	
	@BeforeTest
	public void BeforeTest_DemoTest1() {
		
		tab = OpenTab(web_browser, logger);
	}
	
	@AfterClass
	public void AfterClass_DemoTest1() {
		
		playwright.get().close();
	}
	
	@AfterTest
	public void AfterTest_DemoTest1() {
		
	}

}
