package TestCase.AutomationTests;

import org.junit.Before;
import org.junit.Test;

import Pages.Login;
import Pages.MyOffers;
import TestData.Browser;
import junit.framework.Assert;

public class MyOffersPage extends Browser{
	
	@Before
	public void setuo(){
		prepareBrowser();
	}
	
	@Test
	public void deleteOffer(){
		Login login = navigate
				.goToLoginPage(driver)
				.provideDataToLogin(driver)
				.pressLoginButton(driver);
		
		waitUntilPageFinishLoading(driver);
		
		MyOffers myOffers = navigate
				.goToMyOffersPage(driver)
				.deleteFirstOffer(driver)
				.pressYesButton(driver);
		
		Assert.assertEquals("Oferta została usunięta.", messages.getTextOfMessage(driver));
		
		navigate.logout(driver);
		
		closeBrowser();
	}
	

}
