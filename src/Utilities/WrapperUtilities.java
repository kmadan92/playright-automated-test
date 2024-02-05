package Utilities;

import java.nio.file.Paths;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.model.service.util.ExceptionUtil;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Tracing;

import Keywords.TestReport;


public class WrapperUtilities {
	
	public  ThreadLocal<ExtentTest> logger  =  new ThreadLocal<ExtentTest>();
	public  ExtentReports extent = null;
	public  ThreadLocal<Playwright> playwright;
	public  ThreadLocal<Browser> browser;
	public  ThreadLocal<BrowserContext> browser_context;
	public  ThreadLocal<Page> tab;
	public  String TracesDirectory = System.getProperty("user.dir")+"\\Traces";
	
	
	BrowserConfig browser_config = new BrowserConfig();
	
	public Browser OpenBrowser(String Browser, ThreadLocal<ExtentTest> logger) {
		
		try {
			
			return browser_config.getBrowser(Browser, logger).get();
		
		}catch(Exception e)
		{
			TestReport.Fail(logger, ExceptionUtil.getStackTrace(e));
			return null;
		}
		
	}
	
	public BrowserContext OpenBrowserContext(Browser browser, ThreadLocal<ExtentTest> logger) {
		
		try {
			
			return browser_config.getBrowserContext(browser, logger).get();
			
		}catch(Exception e)
		{
			TestReport.Fail(logger, ExceptionUtil.getStackTrace(e));
			return null;
		}
		
		
	}
	
	public Page OpenTab(BrowserContext browser_context, ThreadLocal<ExtentTest> logger) {
		
		try {
			
			return browser_config.getPage(browser_context, logger).get();
		
		}catch(Exception e)
		{
			TestReport.Fail(logger, ExceptionUtil.getStackTrace(e));
			return null;
		}
	}
	
	public void closePlayright(ThreadLocal<ExtentTest> logger) {
		
		try {
			
			playwright.get().close();
			TestReport.Pass(logger, "Gracefully Exited Playwright Server");
			
		}catch(Exception e)
		{
			TestReport.Fail(logger, ExceptionUtil.getStackTrace(e));
		}
		
	}
	
	public void StartRecording(ThreadLocal<BrowserContext> browser_context, ThreadLocal<ExtentTest> logger) {
		
		try {
			
			browser_context.get().tracing().start(new Tracing.StartOptions()
					  .setScreenshots(true)
					  .setSnapshots(true)
					  .setSources(true));
			
		}catch(Exception e)
		{
			TestReport.Fail(logger, ExceptionUtil.getStackTrace(e));
		}
	}
	
	public void StopRecording(ThreadLocal<BrowserContext> browser_context, String TestName, ThreadLocal<ExtentTest> logger) {
			
			try {
				
				browser_context.get().tracing().stop(new Tracing.StopOptions()
						  .setPath(Paths.get(TracesDirectory+"\\"+TestName)));
				
			}catch(Exception e)
			{
				TestReport.Fail(logger, ExceptionUtil.getStackTrace(e));
			}
		
	}
	
	

}
