package Keywords;

import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.model.service.util.ExceptionUtil;
import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Page;

import Utilities.WrapperUtilities;

public class UI extends WrapperUtilities {
	
	public static void NavigateToURL(String URL, ThreadLocal<Page> tab, ThreadLocal<ExtentTest> logger) {
		
		try {
			
		TestReport.Log(logger, "Navigating to: "+URL+" -"+tab.get());
		tab.get().navigate(URL);
		TestReport.Pass(logger, "Navigated Successfully to: "+URL+" -"+tab.get());
		
		}catch(Exception e) {
			
			TestReport.Log(logger, ExceptionUtil.getStackTrace(e));
			TestReport.Fail(logger, "Navigation to "+URL+" failed"+" -"+tab.get());
		}
		
	}
	
	@SuppressWarnings("null")
	public static void Click(String locator, ThreadLocal<Page> tab, ThreadLocal<ExtentTest> logger, String... Frame) {
		
		try {
			
		TestReport.Log(logger, "Clicking to: "+locator+" -"+tab.get());
		
		if(Frame.length>0)
		{
			
			FrameLocator frame = switchToFrame(locator, tab, logger, Frame);
			frame.locator(locator).click();
			
			TestReport.Pass(logger, "Clicked Successfully to: "+frame.locator(locator).toString()+" -"+tab.get());
		}
		else
		{
			tab.get().click(locator);
			
			TestReport.Pass(logger, "Clicked Successfully to: "+locator+" -"+tab.get());
		}
		
		
		
		}catch(Exception e) {
			
			System.out.println(tab.get());
			TestReport.Log(logger, ExceptionUtil.getStackTrace(e));
			TestReport.Fail(logger, "Clicking to "+locator+" failed"+" -"+tab.get());
			Assert.fail();
		}
		
	}
	
	public static void Type(String locator, String text, ThreadLocal<Page> tab, ThreadLocal<ExtentTest> logger, String... Frame) {
		
		try {
			
		TestReport.Log(logger, "Typing: "+text+" to "+locator+" -"+tab.get());
		
		if(Frame.length>0)
		{
			
			FrameLocator frame = switchToFrame(locator, tab, logger, Frame);
			frame.locator(locator).fill(text);
			
			TestReport.Pass(logger, "Typed Successfully to: "+frame.locator(locator).toString()+" -"+tab.get());
		}
		else
		{
			tab.get().fill(locator, text);
			
			TestReport.Pass(logger, "Successfully typed: "+text+" to "+locator+" -"+tab.get());
		}
		
		
		}catch(Exception e) {
			
			TestReport.Log(logger, ExceptionUtil.getStackTrace(e));
			TestReport.Fail(logger, "Typing "+text+" failed to "+locator+" -"+tab.get());
			Assert.fail();
		}
		
	}
	
	public static String getText(String locator, ThreadLocal<Page> tab, ThreadLocal<ExtentTest> logger, String... Frame) {
		
		try {
			
		TestReport.Log(logger, "Getting Text from "+locator+" -"+tab.get());
		
		String loc;
		
		if(Frame.length>0)
		{
			
			FrameLocator frame = switchToFrame(locator, tab, logger, Frame);
			loc = frame.locator(locator).textContent();
			
			TestReport.Pass(logger, "getText Successfully to: "+frame.locator(locator).toString()+" -"+tab.get());
		}
		else
		{
			loc = tab.get().textContent(locator);
			
			TestReport.Pass(logger, "Text from locator "+locator+" is "+loc+" -"+tab.get());
		}
		
		 
		
		return loc;
		
		}catch(Exception e) {
			
			TestReport.Log(logger, ExceptionUtil.getStackTrace(e));
			TestReport.Fail(logger, "Failed to getText from "+locator+" -"+tab.get());
			Assert.fail();
			return null;
		}
		
	}
	
	public static FrameLocator switchToFrame(String locator, ThreadLocal<Page> tab, ThreadLocal<ExtentTest> logger, String... Frame) {
		
		try {
			
			TestReport.Log(logger, "No. of Frames found for locator: "+locator+" is: "+Frame.length+" -"+tab.get());
			
			FrameLocator frame = tab.get().frameLocator(Frame[0]);
			
			if(Frame.length>1)
			{
				for(int i=1; i<Frame.length ; i++)
				{
					frame = frame.frameLocator(Frame[i]);
				}
			}
			
			return frame;
		
		
		}catch(Exception e) {
			
			TestReport.Log(logger, ExceptionUtil.getStackTrace(e));
			TestReport.Fail(logger, "Failed to switchToFrame from frames defined as: \n"+Frame.toString()+" -"+tab.get());
			Assert.fail();
			return null;
		}
		
	}

	}
