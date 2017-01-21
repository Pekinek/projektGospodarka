package Pages;

import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import TestData.Browser;

public class Registration extends Browser{
	private String email = "@gmail.com";
	private String name = "Test";
	private String surname = "TestTest";
	private String phoneNo = "123456789";
	private String login = "Test";
	private static final String E_MAIL_FIELD_CSS = "#email";
	private static final String NAME_FIELD_CSS = "#firstName";
	private static final String SURNAME_FIELD_CSS = "#lastName";
	private static final String PHONE_NO_FIELD_CSS = "#telephone";
	private static final String LOGIN_FIELD_CSS = "#login";
	private static final String REGISTRATION_BUTTON_CSS = "#login_submit";
	
	public Registration provideDataToRegistration(WebDriver driver){
		Date date = new Date();
		email = date.getTime()+email;
		login += date.getTime();
		driver.findElement(By.cssSelector(E_MAIL_FIELD_CSS)).sendKeys(email);
		driver.findElement(By.cssSelector(NAME_FIELD_CSS)).sendKeys(name);
		driver.findElement(By.cssSelector(SURNAME_FIELD_CSS)).sendKeys(surname);
		driver.findElement(By.cssSelector(PHONE_NO_FIELD_CSS)).sendKeys(phoneNo);
		driver.findElement(By.cssSelector(LOGIN_FIELD_CSS)).sendKeys(login);
		return this;
	}
	
	public Registration provideDataToRegistration(WebDriver driver, String email, String name, String surname, String phoneNo, String login, String password, String repeatPassword){
		driver.findElement(By.cssSelector(E_MAIL_FIELD_CSS)).sendKeys(email);
		driver.findElement(By.cssSelector(NAME_FIELD_CSS)).sendKeys(name);
		driver.findElement(By.cssSelector(SURNAME_FIELD_CSS)).sendKeys(surname);
		driver.findElement(By.cssSelector(PHONE_NO_FIELD_CSS)).sendKeys(phoneNo);
		driver.findElement(By.cssSelector(LOGIN_FIELD_CSS)).sendKeys(login);
		return this;
	}
	
	public Registration pressRegistrationButton(WebDriver driver){
		driver.findElement(By.cssSelector(REGISTRATION_BUTTON_CSS)).click();
		return this;
	}
	
	public String checkRegistrationButton(WebDriver driver){
		return driver.findElement(By.cssSelector(REGISTRATION_BUTTON_CSS)).getAttribute("disabled");
	}

}