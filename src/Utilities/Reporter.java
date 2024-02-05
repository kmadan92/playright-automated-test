package Utilities;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


public class Reporter extends WrapperUtilities implements ITestListener  { 

	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		logger.set(extent.createTest(result.getMethod().getMethodName()));
		logger.get().log(Status.INFO, result.getMethod().getMethodName()+" Started");
		System.out.println(result.getMethod().getMethodName()+" Started");
		extent.flush();
	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		logger.get().pass(result.getMethod().getMethodName()+" Completed Successfully");
		System.out.println(result.getMethod().getMethodName()+" Completed Successfully");
	}

	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		logger.get().log(Status.FAIL, result.getMethod().getMethodName()+" Failed");
		logger.get().fail(result.getThrowable());
		System.out.println(result.getMethod().getMethodName()+" Failed");
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		logger.set(extent.createTest(result.getMethod().getMethodName()));
		logger.get().skip("Test is skipped");
		System.out.println(result.getMethod().getMethodName()+" Skipped");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		extent = getReport();
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		extent.flush();
		
	}
	
}
