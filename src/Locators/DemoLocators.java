package Locators;

import Utilities.WrapperUtilities;

public class DemoLocators extends WrapperUtilities {
	
	/*
	 * page.locator("text=New Customer").textContext(); is equivalent to
	 * page.locator("'New Customer'").textContext();
	 * page.locator("h2:has-text('New Customer')").textContext(); when tag has text
	 * page.locator("div.well h2:has-text('New Customer')").textContext(); parent having class attribute
	 * page.locator("select#someid:has(option[value='India']") to automate select
	 * page.locator("span:has-text('LogIn), span:has-text('SignIn)")  OR conditon with CSS based on text
	 * page.locator("//input[@value='Log In'] | //input[@value='Log In']")   OR condition with xpath
	 */
	
//	public static Locator username = getPage(logger).locator("//input[@name='username']");
//	public static Locator password = getPage(logger).locator("//input[@name='password']");
//	public static Locator login = getPage(logger).locator("//input[@value='Log In']");
//	
//	public static Locator frame_test = getPage(logger).frameLocator("//input[@value='Log In']").locator(login);
	
	public static String username ="//input[@name='username']";
	public static String password ="//input[@name='password']";
	public static String login ="//input[@value='Log In']";
	
	public static String frame_test ="//input[@value='Log In']";

}
