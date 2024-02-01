package Keywords;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Utilities.WrapperUtilities;

public class TestReport extends WrapperUtilities {
	
	public static void Log(ThreadLocal<ExtentTest> logger, String message) {

		try {
			System.out.println(message);
			logger.get().log(Status.INFO, message);
			}

		catch(Exception e){
			e.printStackTrace();
		
		}
		}
		
		public static void Pass(ThreadLocal<ExtentTest> logger, String message) {

			try {
				System.out.println(message);
				logger.get().pass(message);
				}

			catch(Exception e){
				e.printStackTrace();
		}
		}
			
			public static void Fail(ThreadLocal<ExtentTest> logger, String message) {

				try {
					System.out.println(message);
					logger.get().fail(message);
					}

				catch(Exception e){
					e.printStackTrace();
			}
			}

}
