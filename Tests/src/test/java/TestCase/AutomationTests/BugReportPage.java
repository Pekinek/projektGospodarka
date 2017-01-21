package TestCase.AutomationTests;

import org.junit.Before;
import org.junit.Test;

import Pages.BugReport;
import Pages.Login;
import TestData.Browser;
import org.junit.Assert;

public class BugReportPage extends Browser{
	
	@Before
	public void setup(){
		prepareBrowser();
	}
	
	@Test
	public void createCorrectBugReport(){
		Login login = navigate
				.goToLoginPage(driver)
				.provideDataToLogin(driver)
				.pressLoginButton(driver);
		
		waitUntilPageFinishLoading(driver);
		
		BugReport bug = navigate
				.goToBugReportPage(driver)
				.provideDataToReportBug(driver)
				.pressSentButton(driver);
		
		Assert.assertEquals("Problem został zgłoszony administratorowi. Odpowiedź zostanie wysłana na maila.", messages.getTextOfMessage(driver));
		
		navigate.logout(driver);
		closeBrowser();
		
	}
	
	@Test
	public void bugReportWithoutEmail(){
		Login login = navigate
				.goToLoginPage(driver)
				.provideDataToLogin(driver)
				.pressLoginButton(driver);
		
		waitUntilPageFinishLoading(driver);
		
		BugReport bug = navigate
				.goToBugReportPage(driver)
				.provideDataToReportBug(driver, "", "title", "test1");
		
		Assert.assertEquals("Niepoprawny format maila", messages.getValidationErrors(driver));
		Assert.assertEquals("true", bug.checkReportButton(driver));
		
		navigate.logout(driver);
		
		closeBrowser();
	}
	
	@Test
	public void bugReportWithoutTitle(){
		Login login = navigate
				.goToLoginPage(driver)
				.provideDataToLogin(driver)
				.pressLoginButton(driver);
		
		waitUntilPageFinishLoading(driver);
		
		BugReport bug = navigate
				.goToBugReportPage(driver)
				.provideDataToReportBug(driver, "Test@Test.com", "", "test1");
		
		Assert.assertEquals("true", bug.checkReportButton(driver));
		
		navigate.logout(driver);
		
		closeBrowser();
	}
	
	@Test
	public void bugReportWithoutDescription(){
		Login login = navigate
				.goToLoginPage(driver)
				.provideDataToLogin(driver)
				.pressLoginButton(driver);
		
		waitUntilPageFinishLoading(driver);
		
		BugReport bug = navigate
				.goToBugReportPage(driver)
				.provideDataToReportBug(driver, "Test@Test.com", "title", "");
		
		Assert.assertEquals("true", bug.checkReportButton(driver));
		
		navigate.logout(driver);
		
		closeBrowser();
	}

}