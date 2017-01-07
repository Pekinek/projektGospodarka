package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import TestData.Browser;

public class Login extends Browser{

	private static final String login = "Test1";
	private static final String password = "Test1";
	private static final String LOGIN_FIELD_CSS = "#login";
	private static final String PASSWORD_FIELD_CSS = "#password";
	private static final String LOGIN_BUTTON_CSS = "#login_submit";
	private static final String REGISTRATION_LINK_CSS = ".register-link";
	
	public Login(){
		super();
	}
	
	public Login provideDataToLogin(WebDriver driver){
		driver.findElement(By.cssSelector(LOGIN_FIELD_CSS)).sendKeys(login);
		driver.findElement(By.cssSelector(PASSWORD_FIELD_CSS)).sendKeys(password);
		return this;
	}
	
	public Login provideDataToLogin(WebDriver driver, String login, String password){
		driver.findElement(By.cssSelector(LOGIN_FIELD_CSS)).sendKeys(login);
		driver.findElement(By.cssSelector(PASSWORD_FIELD_CSS)).sendKeys(password);
		return this;
	}
	
	public Login pressLoginButton(WebDriver driver){
		driver.findElement(By.cssSelector(LOGIN_BUTTON_CSS)).click();
		return this;
	}
	
	public Registration pressRegistrationLink(WebDriver driver){
		driver.findElement(By.cssSelector(REGISTRATION_LINK_CSS)).click();
		return new Registration();
	}
	
}
