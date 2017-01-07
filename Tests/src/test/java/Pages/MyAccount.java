package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import TestData.Browser;

public class MyAccount extends Browser{
	private static final String EMAIL_FIELD_CSS = "#email";
	private static final String NAME_FIELD_CSS = "#firstName";
	private static final String SURNAME_FIELD_CSS = "#lastName";
	private static final String PHONE_NO_FIELD_CSS = "#telephone";
	private static final String BUTTON_TO_ACCEPT_CHANGE_DATA_CSS = "#edit_submit";
	private static final String PASSWORD_FIELD_CSS = "#new_password";
	private static final String REPEAT_PASSWORD_FIELD_CSS = "#repeat_password";
	private static final String BUTTON_TO_ACCEPT_NEW_PASSWORD_CSS = "#login_submit";
	
	public MyAccount setNewEmail(WebDriver driver, String email){
		driver.findElement(By.cssSelector(EMAIL_FIELD_CSS)).sendKeys(email);
		return this;
	}
	
	public String getEmail(WebDriver driver){
		return driver.findElement(By.cssSelector(EMAIL_FIELD_CSS)).getText();
	}
	
	public MyAccount setNewName(WebDriver driver, String name){
		driver.findElement(By.cssSelector(NAME_FIELD_CSS)).sendKeys(name);
		return this;
	}
	
	public String getName(WebDriver driver){
		return driver.findElement(By.cssSelector(NAME_FIELD_CSS)).getText();
	}
	
	public MyAccount setNewSurname(WebDriver driver, String surname){
		driver.findElement(By.cssSelector(SURNAME_FIELD_CSS)).sendKeys(surname);
		return this;
	}
	
	public String getSurname(WebDriver driver){
		return driver.findElement(By.cssSelector(SURNAME_FIELD_CSS)).getText();
	}
	
	public MyAccount setNewPhoneNo(WebDriver driver, String phone){
		driver.findElement(By.cssSelector(PHONE_NO_FIELD_CSS)).sendKeys(phone);
		return this;
	}
	
	public String getPhoneNo(WebDriver driver){
		return driver.findElement(By.cssSelector(PHONE_NO_FIELD_CSS)).getText();
	}
	
	public MyAccount pressAcceptButtonForData(WebDriver driver){
		driver.findElement(By.cssSelector(BUTTON_TO_ACCEPT_CHANGE_DATA_CSS)).click();
		return this;
	}
	
	public MyAccount setNewPassword(WebDriver driver, String password){
		driver.findElement(By.cssSelector(PASSWORD_FIELD_CSS)).sendKeys(password);
		return this;
	}
	
	public MyAccount setRepeatedPassword(WebDriver driver, String rPassword){
		driver.findElement(By.cssSelector(REPEAT_PASSWORD_FIELD_CSS)).sendKeys(rPassword);
		return this;
	}
	
	public MyAccount pressToAcceptNewPassword(WebDriver driver){
		driver.findElement(By.cssSelector(BUTTON_TO_ACCEPT_NEW_PASSWORD_CSS)).click();
		return this;
	}
	
	public String checkIfAcceptButtonForPasswordIsDisabled(WebDriver driver){
		return driver.findElement(By.cssSelector(BUTTON_TO_ACCEPT_NEW_PASSWORD_CSS)).getAttribute("disabled");
	}

}