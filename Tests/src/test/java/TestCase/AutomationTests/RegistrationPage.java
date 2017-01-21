package TestCase.AutomationTests;

import org.junit.Before;
import org.junit.Test;

import Pages.Registration;
import TestData.Browser;
import org.junit.Assert;

public class RegistrationPage extends Browser{
	
	@Before
	public void setuo(){
		prepareBrowser();
	}
	
	@Test
	public void checkFieldsOnRegistrationPage(){
		Registration registration = navigate
				.goToLoginPage(driver)
				.pressRegistrationLink(driver)
				.provideDataToRegistration(driver)
				.pressRegistrationButton(driver);
		
		waitUntilPageFinishLoading(driver);
		
		Assert.assertEquals("Twoje konto zostało utworzone. Możesz się zalogować.", messages.getTextOfMessage(driver));
		
		closeBrowser();
	}
}