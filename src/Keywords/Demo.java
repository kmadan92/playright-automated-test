package Keywords;

import Utilities.WrapperUtilities;

public class Demo extends WrapperUtilities {
	
	/*
	 * Example 1: Storing browser session in json file for automatic logging
	 * 
	 * To store browser session in a json file, we need to run scenario atleast one
	 * 
	 * Page page = brContext.newContext()
	 * open page and login into app
	 * brContext.storageState(new BrowserContext.StorageStateOptions().setPath(Paths.get("applogin.json")));
	 * 
	 * Once the session has been successfully stored in json file, we need to call browser context with that
	 * 
	 * BrowserContext brcontext = browser.newContext(new Browser.NewContextOprions().setStorageStatePath(Paths.get("applogin.json")))
	 * page.navigate(application url) - user will automatically be login into app
	 */

}
