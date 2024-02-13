package Tests;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import Keywords.UI;
import Locators.DemoLocators;
import Utilities.WrapperUtilities;

public class DemoTest1 extends WrapperUtilities {
	
	
	@BeforeClass
	public void BeforeClass_DemoTest1() {
		
		
	}
	
	@BeforeMethod
	public void BeforeMethod_DemoTest1(ITestContext test) {
		OpenBrowser(browser, logger);
		OpenBrowserContext(browser_context, browser, logger);
		OpenTab(tab, browser_context, logger);
		StartRecording(browser_context, logger);
		setURL(logger);
	
	}
	
	@AfterClass
	public void AfterClass_DemoTest1() {
		
		closePlayright(logger);
	}
	
	@AfterMethod
	public void AfterMethod_DemoTest1(ITestResult result) {
		
		StopRecording(browser_context, result.getMethod().getMethodName(), logger);
		CloseTab(tab, logger);
		CloseBrowserContext(browser_context, logger);
		CloseBrowser(browser, logger);
		
	}
	
	@Test
	public void negative_login() {
		
		UI.NavigateToURL(getURL(logger), tab , logger);
		UI.Type(DemoLocators.username, "abcd", tab, logger);
		UI.Type(DemoLocators.password, "defg", tab, logger);
		UI.Click(DemoLocators.login, tab, logger);
		
	}
	
	@Test
	public void negative_login_1() {
		
		UI.NavigateToURL(getURL(logger), tab, logger);
		UI.Type(DemoLocators.username, "ghgs", tab, logger);
		UI.Type(DemoLocators.password, "hdgd", tab, logger);
		UI.Click(DemoLocators.login,tab, logger);
		tab.get().close();
	}
	
	@Test
	public void negative_login_2() {
		
		UI.NavigateToURL(getURL(logger), tab, logger);
		UI.Type(DemoLocators.username, "ghdedgs", tab, logger);
		UI.Type(DemoLocators.password, "hddegd", tab, logger);
		UI.Click(DemoLocators.login, tab, logger);
	
	}
	
	@Test
	public void negative_login_3() {
		
		UI.NavigateToURL(getURL(logger), tab, logger);
		UI.Click("//*[contains(text(),'with in an')]", tab, logger);
		UI.Click("//input[@type='text']", tab, logger,"//iframe[contains(@src,'Multiple')]", "//iframe[contains(@src,'Single')]");
//		Page tab2 =  (Page) tab.get().frameLocator("//iframe[contains(@src,'Multiple')]");
//		Page tab3 = (Page) tab2.frameLocator("//iframe[contains(@src,'Single')]");
//		tab3.click("//input[@type='text']");
		
	}
	
	@Test
	public void negative_login_4() {
		
		UI.NavigateToURL(getURL(logger), tab, logger);
		UI.Click("//*[contains(text(),'with in an')]", tab, logger);
		UI.Click("//input[@type='text']", tab, logger,"//iframe[contains(@src,'Multiple')]", "//iframe[contains(@src,'Single')]");
//		Page tab2 =  (Page) tab.get().frameLocator("//iframe[contains(@src,'Multiple')]");
//		Page tab3 = (Page) tab2.frameLocator("//iframe[contains(@src,'Single')]");
//		tab3.click("//input[@type='text']");
		
	}
	
	@Test
	public void negative_login_5() {
		
		UI.NavigateToURL(getURL(logger), tab, logger);
		UI.Click("//*[contains(text(),'with in an')]", tab, logger);
		UI.Click("//input[@type='text']", tab, logger,"//iframe[contains(@src,'Multiple')]", "//iframe[contains(@src,'Single')]");
//		Page tab2 =  (Page) tab.get().frameLocator("//iframe[contains(@src,'Multiple')]");
//		Page tab3 = (Page) tab2.frameLocator("//iframe[contains(@src,'Single')]");
//		tab3.click("//input[@type='text']");
		
	}

}
