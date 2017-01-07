package TestCase.AutomationTests;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import PageObjects.Messages;
import PageObjects.Navigation;
import Pages.BugReport;
import Pages.Login;
import TestData.Browser;
import junit.framework.Assert;

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

}
