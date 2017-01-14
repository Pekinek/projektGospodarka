package TestCase.AutomationTests;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import PageObjects.Navigation;
import Pages.NewOffers;
import TestData.Browser;
import junit.framework.Assert;

public class NewOffersPage extends Browser{
	
	@Before
	public void setuo(){
		prepareBrowser();
	}
	
	@Test
	public void checkOfferAsNotLoggedUser(){
		NewOffers offer = new NewOffers();
		offer.pressCheckButtonForOffert(driver, "Nowe tanie mieszkanie");

		waitUntilPageFinishLoading(driver);
		
		Assert.assertEquals("true", offer.getDetailsOfOffer().assertIfImageIsPresentOnPage(driver));
		//Assert.assertEquals("#5 Nowe tanie mieszkanie", offer.getDetailsOfOffer().getTitle(driver));
		Assert.assertEquals("02-01-2017 r.", offer.getDetailsOfOffer().getDate(driver));
		Assert.assertEquals("Super tanie mieszkanie. Wi?cej informacji pod numerem: xxx xxx xxx", offer.getDetailsOfOffer().getDescription(driver));
		Assert.assertEquals("1 000 000", offer.getDetailsOfOffer().getPrice(driver));
		Assert.assertEquals("Wroc?aw", offer.getDetailsOfOffer().getCity(driver));
		Assert.assertEquals("Sprzedaż", offer.getDetailsOfOffer().getDestiny(driver));
		Assert.assertEquals("Mieszkanie", offer.getDetailsOfOffer().getType(driver));
		Assert.assertEquals("Test1", offer.getDetailsOfOffer().getOwnerOfOffer(driver));
		Assert.assertEquals("michal.aniol.szczesniak@gmail.com", offer.getDetailsOfOffer().getEmail(driver));
		Assert.assertEquals("111111111", offer.getDetailsOfOffer().getPhoneNo(driver));	
		
		closeBrowser();
	}
	
}
