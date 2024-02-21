package Keywords;

import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.model.service.util.ExceptionUtil;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;

import Utilities.WrapperUtilities;

public class REST extends WrapperUtilities {
	
	/**
	 * HTTP GET Request with input as URL only
	 * @param URL URL to GET
	 * @param api_request_context API Request Context Object
	 * @param logger Test logging Object
	 * @author Kapil Madan
	 * @return APIResponse Object
	 */
	public static APIResponse get(String URL, ThreadLocal<APIRequestContext> api_request_context, ThreadLocal<ExtentTest> logger)
	{
		TestReport.Log(logger, "HTTP GET called on :"+URL+" -"+getAPIRequestContext(api_request_context, logger));
		
		APIResponse response = null;
		
		try {
			response = getAPIRequestContext(api_request_context, logger).get(URL);
		}
		catch(Exception e)
		{
			TestReport.Log(logger, ExceptionUtil.getStackTrace(e));
			TestReport.Fail(logger, "HTTP GET method failed"+" -"+getAPIRequestContext(api_request_context, logger));
			Assert.fail();
		}
		
		TestReport.Pass(logger, "HTTP GET called successfully on :"+URL+" -"+getAPIRequestContext(api_request_context, logger));
		
		return response;

	}
	
	/**
	 * HTTP GET Request with input as URL and Query Params, Headers
	 * @param URL URL to GET
	 * @param options Object containing Query Param, Headers
	 * @param api_request_context API Request Context Object
	 * @param logger Test logging Object
	 * @author Kapil Madan
	 * @return APIResponse Object
	 */
	public static APIResponse get(String URL, RequestOptions options, ThreadLocal<APIRequestContext> api_request_context, ThreadLocal<ExtentTest> logger)
	{
		TestReport.Log(logger, "HTTP GET called on :"+URL+" with query params: "+options.toString()+" -"+getAPIRequestContext(api_request_context, logger));
		
		APIResponse response = null;
		
		try {
			response = getAPIRequestContext(api_request_context, logger).get(URL, options);
		}
		catch(Exception e)
		{
			TestReport.Log(logger, ExceptionUtil.getStackTrace(e));
			TestReport.Fail(logger, "HTTP GET method failed"+" -"+getAPIRequestContext(api_request_context, logger));
			Assert.fail();
		}
		
	
		TestReport.Pass(logger, "HTTP GET called successfully on :"+URL+" with Query Paramns and Headers as: "+options.toString()+" -"+getAPIRequestContext(api_request_context, logger));
		
		return response;
	}
	
	/**
	 * HTTP POST Request with input as URL and Query Params, Headers
	 * @param URL URL to POST
	 * @param options Object containing Query Param, Headers and Body
	 * @param api_request_context API Request Context Object
	 * @param logger Test logging Object
	 * @author Kapil Madan
	 * @return APIResponse Object
	 */
	public static APIResponse post(String URL, RequestOptions options, ThreadLocal<APIRequestContext> api_request_context, ThreadLocal<ExtentTest> logger)
	{
		TestReport.Log(logger, "HTTP POST called on :"+URL+"+ -"+getAPIRequestContext(api_request_context, logger));
		
		APIResponse response = null;
		
		try {
			response = getAPIRequestContext(api_request_context, logger).post(URL, options);
		}
		catch(Exception e)
		{
			TestReport.Log(logger, ExceptionUtil.getStackTrace(e));
			TestReport.Fail(logger, "HTTP POST method failed"+" -"+getAPIRequestContext(api_request_context, logger));
			Assert.fail();
		}
	
		TestReport.Pass(logger, "HTTP POST called successfully on :"+URL+" -"+getAPIRequestContext(api_request_context, logger));
		
		
		return response;
	}
	
	/**
	 * Assert response status code
	 * @param response APIResponse Object
	 * @param code Expected status code
	 * @param logger Test logging Object
	 * @author Kapil Madan
	 */
	public static void assertStatusCode(APIResponse response, int code, ThreadLocal<ExtentTest> logger)
	{
		TestReport.Log(logger, "Asserting actual status code: "+response.status()+" with expected status: "+code+" -"+getAPIRequestContext(request, logger));
		
		try {
			Assert.assertEquals(response.status(), code);
		}
		catch(Exception e)
		{
			TestReport.Log(logger, ExceptionUtil.getStackTrace(e));
			TestReport.Fail(logger, "assertStatusCode failed"+" -"+getAPIRequestContext(request, logger));
			Assert.fail();
		}
		
		TestReport.Pass(logger, "Expected status code found: "+code+" -"+getAPIRequestContext(request, logger));
	}
}
