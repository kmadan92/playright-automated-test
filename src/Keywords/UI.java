package Keywords;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.model.service.util.ExceptionUtil;
import com.microsoft.playwright.Download;
import com.microsoft.playwright.FileChooser;
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
			
		TestReport.Log(logger, "Navigating to: "+URL+" -"+getTab(tab, logger));
		getTab(tab,logger).navigate(URL);
		TestReport.Pass(logger, "Navigated Successfully to: "+URL+" -"+getTab(tab,logger));
		
		}catch(Exception e) {
			
			TestReport.Log(logger, ExceptionUtil.getStackTrace(e));
			TestReport.Fail(logger, "Navigation to "+URL+" failed"+" -"+getTab(tab,logger));
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
			
		TestReport.Log(logger, "Clicking to: "+locator+" -"+getTab(tab,logger));
		
		if(Frame.length>0)
		{
			
			FrameLocator frame = switchToFrame(tab, logger, Frame);
			frame.locator(locator).click();
			
			TestReport.Pass(logger, "Clicked Successfully to: "+frame.locator(locator).toString()+" -"+getTab(tab,logger));
		}
		else
		{
			getTab(tab,logger).click(locator);
			
			TestReport.Pass(logger, "Clicked Successfully to: "+locator+" -"+getTab(tab,logger));
		}
		
		
		
		}catch(Exception e) {
			
			System.out.println(getTab(tab,logger));
			TestReport.Log(logger, ExceptionUtil.getStackTrace(e));
			TestReport.Fail(logger, "Clicking to "+locator+" failed"+" -"+getTab(tab,logger));
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
			
		TestReport.Log(logger, "Typing: "+text+" to "+locator+" -"+getTab(tab,logger));
		
		if(Frame.length>0)
		{
			
			FrameLocator frame = switchToFrame(tab, logger, Frame);
			frame.locator(locator).fill(text);
			
			TestReport.Pass(logger, "Typed Successfully to: "+frame.locator(locator).toString()+" -"+getTab(tab,logger));
		}
		else
		{
			getTab(tab,logger).fill(locator, text);
			
			TestReport.Pass(logger, "Successfully typed: "+text+" to "+locator+" -"+getTab(tab,logger));
		}
		
		
		}catch(Exception e) {
			
			TestReport.Log(logger, ExceptionUtil.getStackTrace(e));
			TestReport.Fail(logger, "Typing "+text+" failed to "+locator+" -"+getTab(tab,logger));
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
			
		TestReport.Log(logger, "Getting Text from "+locator+" -"+getTab(tab,logger));
		
		String loc;
		
		if(Frame.length>0)
		{
			
			FrameLocator frame = switchToFrame(tab, logger, Frame);
			loc = frame.locator(locator).textContent();
			
			TestReport.Pass(logger, "getText Successfully to: "+frame.locator(locator).toString()+" -"+getTab(tab,logger));
		}
		else
		{
			loc = getTab(tab,logger).textContent(locator);
			
			TestReport.Pass(logger, "Text from locator "+locator+" is "+loc+" -"+getTab(tab,logger));
		}
		
		 
		
		return loc;
		
		}catch(Exception e) {
			
			TestReport.Log(logger, ExceptionUtil.getStackTrace(e));
			TestReport.Fail(logger, "Failed to getText from "+locator+" -"+getTab(tab,logger));
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
			
			TestReport.Log(logger, "No. of Frames found is: "+Frame.length+" -"+getTab(tab,logger));
			
			FrameLocator frame = getTab(tab, logger).frameLocator(Frame[0]);
			
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
			TestReport.Fail(logger, "Failed to switchToFrame from frames defined as: \n"+Frame.toString()+" -"+getTab(tab,logger));
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
			
			TestReport.Log(logger, "Navigating to Alert on Page"+" -"+getTab(tab,logger));
			
			if(text.equalsIgnoreCase("accept"))
			{
				getTab(tab, logger).onceDialog(dialog ->{
				String textOnAlert = dialog.message();
				TestReport.Log(logger, "Text on Alert says: "+textOnAlert);
				dialog.accept();
				TestReport.Pass(logger, "Alert Accepted"+" -"+getTab(tab,logger));
			});
			}
			else if(text.equalsIgnoreCase("cancel"))
			{
				getTab(tab, logger).onceDialog(dialog ->{
				String textOnAlert = dialog.message();
				TestReport.Log(logger, "Text on Alert says: "+textOnAlert);
				dialog.dismiss();
				TestReport.Pass(logger, "Alert Dismissed"+" -"+getTab(tab,logger));
				});
			}
			else
			{
				getTab(tab, logger).onceDialog(dialog ->{
				String textOnAlert = dialog.message();
				TestReport.Log(logger, "Text on Alert says: "+textOnAlert);
				dialog.accept(text);
				TestReport.Pass(logger, "Prompt Passed to Alert: "+text+" -"+getTab(tab,logger));
				});
			}
		
		
		}catch(Exception e) {
			
			TestReport.Log(logger, ExceptionUtil.getStackTrace(e));
			TestReport.Fail(logger, "actionOnAlert failed"+" -"+getTab(tab,logger));
			Assert.fail();
			
		}
		
	}
	
	/**
	 * Downloading a file. Process will wait until full download is completed
	 * @param locator Locator of UI on page clicking on which downloades the file
	 * @param tab Current page object
	 * @param logger Test logging object
	 * @param Frame Frame is varargs. Define comma separated values for Frame. If no Frame is involved, leave it empty
	 */
	public static void ClickAndDownload(String locator, ThreadLocal<Page> tab, ThreadLocal<ExtentTest> logger, String ...Frame) {
		
		try {
			
			TestReport.Log(logger, "Starting Download......"+" -"+getTab(tab,logger));
			
			Download download;
			
			if(Frame.length>0)
			{
				
				FrameLocator frame = switchToFrame(tab, logger, Frame);
				frame.locator(locator).click();
				
				download = getTab(tab,logger).waitForDownload(() -> {
					
					frame.locator(locator).click();
					
				});
				
			}
			else
			{
					download = getTab(tab,logger).waitForDownload(() -> {
					
						getTab(tab, logger).click(locator);
					
				});
				
			}
			
			download.saveAs(Paths.get(System.getProperty("user.dir")+System.getProperty("file.separator")+"Downloads"+System.getProperty("file.separator"), download.suggestedFilename()));
		
			TestReport.Pass(logger, "Download Completed. File is saved at: "+download.path().toString()+" -"+getTab(tab,logger));
		
		}catch(Exception e) {
			
			TestReport.Log(logger, ExceptionUtil.getStackTrace(e));
			TestReport.Fail(logger, "ClickAndDownload failed"+" -"+getTab(tab,logger));
			Assert.fail();
			
		}
		
	}
	
	/**
	 * To upload a single or multiple file. Check html attribute of UI web element which opens file manager and then ser argument - type
	 * @param locator Locator of UI on page clicking on which opens file manager
	 * @param path Path Class object having all paths to files to be uploaded
	 * @param type type should be true if the html attribute of UI web element which opens file manager is of type='file' else type should be false.
	 * @param tab Current page object
	 * @param logger Test logging object
	 * @param Frame Frame is varargs. Define comma separated values for Frame. If no Frame is involved, leave it empty
	 */
	public static void UploadFile(String locator, Path path, Boolean type, ThreadLocal<Page> tab, ThreadLocal<ExtentTest> logger, String ...Frame) {
		
		try {
			
			TestReport.Log(logger, "Starting Upload......"+" -"+getTab(tab,logger));
			
			if(type)
			{
				if(Frame.length>0)
				{
				
				FrameLocator frame = switchToFrame(tab, logger, Frame);
				frame.locator(locator).setInputFiles(path);
								
				}
				else
				{
				getTab(tab, logger).setInputFiles(locator, path);
				
				}
			}
			else 
			{
				
				if(Frame.length>0)
				{
					
					FrameLocator frame = switchToFrame(tab, logger, Frame);
					FileChooser fileChooser = getTab(tab, logger).waitForFileChooser(() -> {
						  frame.locator(locator).click();
						});
					fileChooser.setFiles(path);
									
				}
				else
				{
					FileChooser fileChooser = getTab(tab, logger).waitForFileChooser(() -> {
						  getTab(tab, logger).click(locator);
						});
					fileChooser.setFiles(path);
					
				}
				
				TestReport.Pass(logger, "Upload Completed. "+" -"+getTab(tab,logger));
				
			}
		
			TestReport.Pass(logger, "Upload Completed. Files uploaded are: "+" -"+getTab(tab,logger));
		
		}catch(Exception e) {
			
			TestReport.Log(logger, ExceptionUtil.getStackTrace(e));
			TestReport.Fail(logger, "UploadFile failed"+" -"+getTab(tab,logger));
			Assert.fail();
			
		}
		
	}
	
	

	}
