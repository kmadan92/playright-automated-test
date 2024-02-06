package Utilities;

import java.nio.file.Paths;

import org.testng.Assert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.model.service.util.ExceptionUtil;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Tracing;

import Keywords.TestReport;


public class WrapperUtilities {
	
	public static ThreadLocal<ExtentTest> logger  =  new ThreadLocal<ExtentTest>();
	public static ExtentReports extent = null;
	public static ThreadLocal<Playwright> playwright =  new ThreadLocal<>();
	public static ThreadLocal<Browser> browser =  new ThreadLocal<>();
	public static ThreadLocal<BrowserContext> browser_context  =  new ThreadLocal<>();
	public static ThreadLocal<Page> tab  =  new ThreadLocal<>();
	public static String TracesDirectory = System.getProperty("user.dir")+"/Traces";
	public static ThreadLocal<String> URL  = new ThreadLocal<String>();
	
	public  Browser OpenBrowser(String Browser, ThreadLocal<ExtentTest> logger) {
		
		try {
			
			return BrowserConfig.getBrowser(Browser, logger).get();
		
		}catch(Exception e)
		{
			//TestReport.Fail(logger, ExceptionUtil.getStackTrace(e));
			e.printStackTrace();
			Assert.fail();
			return null;
		}
		
	}
	
	public BrowserContext OpenBrowserContext(Browser browser, ThreadLocal<ExtentTest> logger) {
		
		try {
			
			return BrowserConfig.getBrowserContext(browser, logger).get();
			
		}catch(Exception e)
		{
			TestReport.Fail(logger, ExceptionUtil.getStackTrace(e));
			return null;
		}
		
		
	}
	
	public Page OpenTab(BrowserContext browser_context, ThreadLocal<ExtentTest> logger) {
		
		try {
			
			return BrowserConfig.getPage(browser_context, logger).get();
		
		}catch(Exception e)
		{
			TestReport.Fail(logger, ExceptionUtil.getStackTrace(e));
			return null;
		}
	}
	
	public String getURL(ThreadLocal<ExtentTest> logger) {
		
		return URL.get();
		
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
			TestReport.Log(logger, "Failure to start Recording");
			TestReport.Fail(logger, ExceptionUtil.getStackTrace(e));
		}
	}
	
	
	public void StopRecording(ThreadLocal<BrowserContext> browser_context, String TestName, ThreadLocal<ExtentTest> logger) {
			
			try {
				
				browser_context.get().tracing().stop(new Tracing.StopOptions()
						  .setPath(Paths.get(TracesDirectory+"/"+TestName)));
				
			}catch(Exception e)
			{
				TestReport.Log(logger, "Failure to stop Recording");
				TestReport.Fail(logger, ExceptionUtil.getStackTrace(e));
			}
		
	}
	
	public static ExtentReports getReport() {
		 
	 	ExtentReports extentrep = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter(System.getProperty("user.dir")+"/target/surefire-reports/TestReport.html");
		extentrep.attachReporter(spark);
		extentrep.setSystemInfo("QE Engineer", "Kapil Madan");
		return extentrep;

	}

}
