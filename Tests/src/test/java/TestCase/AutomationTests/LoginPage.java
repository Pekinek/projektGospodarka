package TestCase.AutomationTests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Pages.Login;
import TestData.Browser;
import org.junit.Assert;

public class LoginPage extends Browser{
	
	@Before
	public void setup(){
		prepareBrowser();
	}
	
	@Test
	public void correctLogin(){
		Login loginPage = navigate
				.goToLoginPage(driver)
				.provideDataToLogin(driver)
				.pressLoginButton(driver);
		
		waitUntilPageFinishLoading(driver);
		
		navigate.logout(driver);
		
		closeBrowser();
	}
	
	@Test
	public void incorrectLogin(){
		Login loginPage = navigate
				.goToLoginPage(driver)
				.provideDataToLogin(driver, "ZłyUser", "ZłeHasło")
				.pressLoginButton(driver);
		
		Assert.assertEquals("Błąd logowania", messages.getTitleOfMessage(driver));
		Assert.assertEquals("Nieprawidłowe dane logowania lub użytkownik nie istnieje.", messages.getTextOfMessage(driver));
		
		closeBrowser();
	}
	
	@After
	public void finish(){
		driver.quit();
	}

}