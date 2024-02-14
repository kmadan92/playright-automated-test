package Keywords;

import java.nio.file.Paths;

import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.model.service.util.ExceptionUtil;
import com.microsoft.playwright.Download;
import com.microsoft.playwright.FrameLocator;
import com.microsoft.playwright.Page;

import Utilities.WrapperUtilities;

/**
 * API's  for interaction with UI elements on a webpage
 * @author Kapil Madan
 *
 */
public class UI extends WrapperUtilities {
	
	/** 
	 * Navigate to a URL
	 * @param URL Complete URL to navigate
	 * @param tab Current page object
	 * @param logger Test logging object
	 */
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
	
	/**
	 * Click on a Web Element
	 * @param locator Locator of UI on page
	 * @param tab Current page object
	 * @param logger Test logging object
	 * @param Frame Frame is varargs. Define comma separated values for Frame. If no Frame is involved, leave it empty
	 */
	@SuppressWarnings("null")
	public static void Click(String locator, ThreadLocal<Page> tab, ThreadLocal<ExtentTest> logger, String... Frame) {
		
		try {
			
		TestReport.Log(logger, "Clicking to: "+locator+" -"+tab.get());
		
		if(Frame.length>0)
		{
			
			FrameLocator frame = switchToFrame(tab, logger, Frame);
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
	
	/**
	 * Type to any Input field
	 * @param locator Locator of UI on page
	 * @param tab Current page object
	 * @param logger Test logging object
	 * @param Frame Frame is varargs. Define comma separated values for Frame. If no Frame is involved, leave it empty
	 * @param text Input Text to fill the field
	 */
	public static void Type(String locator, String text, ThreadLocal<Page> tab, ThreadLocal<ExtentTest> logger, String... Frame) {
		
		
		try {
			
		TestReport.Log(logger, "Typing: "+text+" to "+locator+" -"+tab.get());
		
		if(Frame.length>0)
		{
			
			FrameLocator frame = switchToFrame(tab, logger, Frame);
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
	
	/**
	 * get text from UI Element
	 * @param locator Locator of UI on page
	 * @param tab Current page object
	 * @param logger Test logging object
	 * @param Frame Frame is varargs. Define comma separated values for Frame. If no Frame is involved, leave it empty
	 * @return Text of the UI element
	 */
	public static String getText(String locator, ThreadLocal<Page> tab, ThreadLocal<ExtentTest> logger, String... Frame) {
	
		
		try {
			
		TestReport.Log(logger, "Getting Text from "+locator+" -"+tab.get());
		
		String loc;
		
		if(Frame.length>0)
		{
			
			FrameLocator frame = switchToFrame(tab, logger, Frame);
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
	
	/**
	 * Switch to Frame
	 * @param tab Current page object
	 * @param logger Test logging object
	 * @param Frame Frame is varargs. Define comma separated values for Frame. If no Frame is involved, leave it empty
	 * @return FrameLocator, which is final final frame inside which UI element resides
	 */
	public static FrameLocator switchToFrame(ThreadLocal<Page> tab, ThreadLocal<ExtentTest> logger, String... Frame) {
		
		
		try {
			
			TestReport.Log(logger, "No. of Frames found is: "+Frame.length+" -"+tab.get());
			
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
	
	/**
	 * Handle a Alert or Prompt on a Web Page
	 * @param text When text=accept or cancel, alert will be treated as accept/cancel alert. When text = some text, alert will be treated as prompt
	 * @param tab Current page object
	 * @param logger Test logging object
	 */
	public static void actionOnAlert(String text, ThreadLocal<Page> tab, ThreadLocal<ExtentTest> logger) {
		
		try {
			
			TestReport.Log(logger, "Navigating to Alert on Page"+" -"+tab.get());
			
			if(text.equalsIgnoreCase("accept"))
			{
				getTab(tab, logger).onceDialog(dialog ->{
				String textOnAlert = dialog.message();
				TestReport.Log(logger, "Text on Alert says: "+textOnAlert);
				dialog.accept();
				TestReport.Pass(logger, "Alert Accepted"+" -"+tab.get());
			});
			}
			else if(text.equalsIgnoreCase("cancel"))
			{
				getTab(tab, logger).onceDialog(dialog ->{
				String textOnAlert = dialog.message();
				TestReport.Log(logger, "Text on Alert says: "+textOnAlert);
				dialog.dismiss();
				TestReport.Pass(logger, "Alert Dismissed"+" -"+tab.get());
				});
			}
			else
			{
				getTab(tab, logger).onceDialog(dialog ->{
				String textOnAlert = dialog.message();
				TestReport.Log(logger, "Text on Alert says: "+textOnAlert);
				dialog.accept(text);
				TestReport.Pass(logger, "Prompt Passed to Alert: "+text+" -"+tab.get());
				});
			}
		
		
		}catch(Exception e) {
			
			TestReport.Log(logger, ExceptionUtil.getStackTrace(e));
			TestReport.Fail(logger, "actionOnAlert failed"+" -"+tab.get());
			Assert.fail();
			
		}
		
	}
	
	public static void ClickAndDownload(String locator, ThreadLocal<Page> tab, ThreadLocal<ExtentTest> logger, String ...Frame) {
		
		try {
			
			TestReport.Log(logger, "Starting Download......"+" -"+tab.get());
			
			Download download;
			
			if(Frame.length>0)
			{
				
				FrameLocator frame = switchToFrame(tab, logger, Frame);
				frame.locator(locator).click();
				
				download = tab.get().waitForDownload(() -> {
					
					frame.locator(locator).click();
					
				});
				
			}
			else
			{
					download = tab.get().waitForDownload(() -> {
					
						tab.get().click(locator);
					
				});
				
			}
			
			download.saveAs(Paths.get(System.getProperty("user.dir")+System.getProperty("file.separator")+"Downloads"+System.getProperty("file.separator"), download.suggestedFilename()));
		
			TestReport.Pass(logger, "Download Completed. File is saved at: "+download.path().toString()+" -"+tab.get());
		
		}catch(Exception e) {
			
			TestReport.Log(logger, ExceptionUtil.getStackTrace(e));
			TestReport.Fail(logger, "ClickAndDownload failed"+" -"+tab.get());
			Assert.fail();
			
		}
		
	}

	}
