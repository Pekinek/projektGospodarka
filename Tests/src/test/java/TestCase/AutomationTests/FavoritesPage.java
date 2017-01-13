package TestCase.AutomationTests;

import org.junit.Before;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.javascript.host.Navigator;

import Pages.DetailsOfOffer;
import Pages.Login;
import Pages.NewOffers;
import TestData.Browser;
import junit.framework.Assert;

public class FavoritesPage extends Browser{
	
	@Before
	public void setuo(){
		prepareBrowser();
	}
	
	@Test
	public void addToFavorites(){
		Login login = navigate
				.goToLoginPage(driver)
				.provideDataToLogin(driver)
				.pressLoginButton(driver);
		
		waitUntilPageFinishLoading(driver);
		
		NewOffers offer = new NewOffers();
		offer.pressCheckButtonForFirstOffert(driver);
		
		waitUntilPageFinishLoading(driver);
		
		DetailsOfOffer details = new DetailsOfOffer();
		details.addToFavorites(driver);
		
		Assert.assertEquals("Dodano ofertę do ulubionych", messages.getTextOfMessage(driver));
		
		navigate.logout(driver);
		
		closeBrowser();
	}
	
	@Test
	public void addComment(){
		Login login = navigate
				.goToLoginPage(driver)
				.provideDataToLogin(driver)
				.pressLoginButton(driver);
		
		waitUntilPageFinishLoading(driver);
		
		NewOffers offer = new NewOffers();
		DetailsOfOffer details = offer.pressCheckButtonForFirstOffert(driver).addComment(driver, "Test");
		
		waitUntilPageFinishLoading(driver);
		
		Assert.assertEquals("Komentarz został dodany.", messages.getTextOfMessage(driver));
		//Assert.assertEquals("Test", details.getCommentByUser(driver, "Test1"));
		
		navigate.logout(driver);
		
		closeBrowser();
	}

}
