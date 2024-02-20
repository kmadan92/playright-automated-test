package Keywords;

import com.aventstack.extentreports.ExtentTest;
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
		TestReport.Log(logger, "HTTP GET called on :"+URL);
		
		APIResponse response = getAPIRequestContext(api_request_context, logger).get(URL);
		
		TestReport.Pass(logger, "HTTP GET called successfully on :"+URL);
		
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
		TestReport.Log(logger, "HTTP GET called on :"+URL+" with query params: "+options.toString());
		
		APIResponse response = getAPIRequestContext(api_request_context, logger).get(URL, options);
	
		TestReport.Log(logger, "HTTP GET called successfully on :"+URL+" with Query Paramns and Headers as: "+options.toString());
		
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
		TestReport.Log(logger, "HTTP POST called on :"+URL+" with query params: "+options.toString());
		
		APIResponse response = getAPIRequestContext(api_request_context, logger).post(URL, options);
	
		TestReport.Log(logger, "HTTP POST called successfully on :"+URL+" with Query params, Headers and body as: "+options.toString());
		
		return response;
	}
}
