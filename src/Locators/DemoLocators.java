package Locators;



import com.microsoft.playwright.Locator;

import Utilities.WrapperUtilities;

public class DemoLocators extends WrapperUtilities {
	
	/*
	 * page.locator("text=New Customer").textContext(); is equivalent to
	 * page.locator("'New Customer'").textContext();
	 * page.locator("h2:has-text('New Customer')").textContext(); when tag has text
	 * page.locator("div.well h2:has-text('New Customer')").textContext(); parent having class attribute
	 */
	
	public static Locator username = tab.get().locator("//input[name='username']");
	public static Locator password = tab.get().locator("//input[name='password']");
	public static Locator login = tab.get().locator("//input[value='Log In']");

}
