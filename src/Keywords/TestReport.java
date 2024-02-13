package Keywords;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Utilities.WrapperUtilities;

/**
 * Write Logs to Test reports
 * @author Kapil Madan
 *
 */
public class TestReport extends WrapperUtilities {
	
	/**
	 * Log Information to test report
	 * @param logger Test logging object
	 * @param message Message to Log
	 */
	public static void Log(ThreadLocal<ExtentTest> logger, String message) {

		try {
			System.out.println(message);
			logger.get().log(Status.INFO, message);
			}

		catch(Exception e){
			e.printStackTrace();
		
		}
		}
		
	/**
	 * Log Pass Status to test report
	 * @param logger Test logging object
	 * @param message Message to Log
	 */
		public static void Pass(ThreadLocal<ExtentTest> logger, String message) {

			try {
				System.out.println(message);
				logger.get().pass(message);
				}

			catch(Exception e){
				e.printStackTrace();
		}
		}
			
		/**
		 * Log Fail Status to test report
		 * @param logger Test logging object
		 * @param message Message to Log
		 */
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
