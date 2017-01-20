package TestCase.AutomationTests;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import PageObjects.Navigation;
import Pages.AddOffers;
import Pages.Login;
import TestData.Browser;
import org.junit.Assert;

public class AddOffersPage extends Browser{
	
	@Before
	public void setup(){
		prepareBrowser();
	}
	
	@Test
	public void correctAddingOffer(){
		Login loginPage = navigate
				.goToLoginPage(driver)
				.provideDataToLogin(driver)
				.pressLoginButton(driver);
		
		waitUntilPageFinishLoading(driver);
		
		AddOffers addOffers = navigate
				.goToAddOffersPage(driver)
				.provideDataToAddOffer(driver)
				.insertImage(driver)
				.pressAddOferrButton(driver);
				
		Assert.assertEquals("Dodano ofertę", messages.getTitleOfMessage(driver));
		Assert.assertEquals("Oferta jest już dostępna dla innych użytkowników.", messages.getTextOfMessage(driver));
		
		waitUntilPageFinishLoading(driver);
		
		navigate.logout(driver);
		
		closeBrowser();
	}
	
	@Test
	public void incorrectAddingOffer_withoutImage(){
		Login loginPage = navigate
				.goToLoginPage(driver)
				.provideDataToLogin(driver)
				.pressLoginButton(driver);
		
		waitUntilPageFinishLoading(driver);
		
		AddOffers addOffers = navigate
				.goToAddOffersPage(driver)
				.provideDataToAddOffer(driver);
				
		Assert.assertEquals("true", addOffers.checkStatusOfAddButton(driver));
		
		navigate.logout(driver);
		
		closeBrowser();
	}
	
	@Test
	public void incorrectAddingOffer_onlyTitleProvided(){
		Login loginPage = navigate
				.goToLoginPage(driver)
				.provideDataToLogin(driver)
				.pressLoginButton(driver);
		
		waitUntilPageFinishLoading(driver);
		
		AddOffers addOffers = navigate
				.goToAddOffersPage(driver)
				.provideDataToAddOffer(driver, "Nowe ogłoszenie", "","","","","");
				
		Assert.assertEquals("true", addOffers.checkStatusOfAddButton(driver));
		
		navigate.logout(driver);
		
		closeBrowser();
	}	
	
	@Test
	public void incorrectAddingOffer_onlyCityProvided(){
		Login loginPage = navigate
				.goToLoginPage(driver)
				.provideDataToLogin(driver)
				.pressLoginButton(driver);
		
		waitUntilPageFinishLoading(driver);
		
		AddOffers addOffers = navigate
				.goToAddOffersPage(driver)
				.provideDataToAddOffer(driver, "", "Wrocław","","","","");
				
		Assert.assertEquals("true", addOffers.checkStatusOfAddButton(driver));
		
		navigate.logout(driver);
		
		closeBrowser();
	}	
	
	@Test
	public void incorrectAddingOffer_onlyDestinyProvided(){
		Login loginPage = navigate
				.goToLoginPage(driver)
				.provideDataToLogin(driver)
				.pressLoginButton(driver);
		
		waitUntilPageFinishLoading(driver);
		
		AddOffers addOffers = navigate
				.goToAddOffersPage(driver)
				.provideDataToAddOffer(driver, "", "","rent","","","");
				
		Assert.assertEquals("true", addOffers.checkStatusOfAddButton(driver));
		
		navigate.logout(driver);
		
		closeBrowser();
	}	
	
	@Test
	public void incorrectAddingOffer_onlyPriceProvided(){
		Login loginPage = navigate
				.goToLoginPage(driver)
				.provideDataToLogin(driver)
				.pressLoginButton(driver);
		
		waitUntilPageFinishLoading(driver);
		
		AddOffers addOffers = navigate
				.goToAddOffersPage(driver)
				.provideDataToAddOffer(driver, "", "","","1 000 zł","","");
				
		Assert.assertEquals("true", addOffers.checkStatusOfAddButton(driver));
		
		navigate.logout(driver);
		
		closeBrowser();
	}	
	
	@Test
	public void incorrectAddingOffer_onlyTypeProvided(){
		Login loginPage = navigate
				.goToLoginPage(driver)
				.provideDataToLogin(driver)
				.pressLoginButton(driver);
		
		waitUntilPageFinishLoading(driver);
		
		AddOffers addOffers = navigate
				.goToAddOffersPage(driver)
				.provideDataToAddOffer(driver, "", "","","","room","");
				
		Assert.assertEquals("true", addOffers.checkStatusOfAddButton(driver));
		
		navigate.logout(driver);
		
		closeBrowser();
	}	
	
	@Test
	public void incorrectAddingOffer_onlyDescriptionProvided(){
		Login loginPage = navigate
				.goToLoginPage(driver)
				.provideDataToLogin(driver)
				.pressLoginButton(driver);
		
		waitUntilPageFinishLoading(driver);
		
		AddOffers addOffers = navigate
				.goToAddOffersPage(driver)
				.provideDataToAddOffer(driver, "", "","","","","Tanio pokój dla studenta.");
				
		Assert.assertEquals("true", addOffers.checkStatusOfAddButton(driver));
		
		navigate.logout(driver);
		
		closeBrowser();
	}	
}
