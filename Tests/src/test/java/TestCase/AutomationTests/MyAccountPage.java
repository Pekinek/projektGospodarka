package TestCase.AutomationTests;

import org.junit.Before;
import org.junit.Test;

import Pages.Login;
import Pages.MyAccount;
import TestData.Browser;
import org.junit.Assert;

public class MyAccountPage extends Browser{
	
	@Before
	public void setuo(){
		prepareBrowser();
	}
	
	@Test
	public void checkMyData(){
		Login login = navigate
				.goToLoginPage(driver)
				.provideDataToLogin(driver)
				.pressLoginButton(driver);
		
		waitUntilPageFinishLoading(driver);
		
		MyAccount myAccount = navigate
				.goToMyAccountPage(driver);
		
		waitUntilPageFinishLoading(driver);
		
		Assert.assertEquals("Test1@gmail.com", myAccount.getEmail(driver));
		Assert.assertEquals("Test1", myAccount.getName(driver));
		Assert.assertEquals("Test1", myAccount.getSurname(driver));
		Assert.assertEquals("123456789", myAccount.getPhoneNo(driver));
		
		navigate.logout(driver);
		
		closeBrowser();
	}

}
