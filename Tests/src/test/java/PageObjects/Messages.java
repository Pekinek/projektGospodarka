package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import TestData.Browser;

public class Messages extends Browser {
	private static final String MESSAGES_TITLE_CSS = ".toast-title";
	private static final String MESSAGES_TEXT_CSS = ".toast-message";
	private static final String VALIDATION_ERROR_CSS = ".validation-error>span";
	
	public String getTitleOfMessage(WebDriver driver){
		waitUntilElementIsNotVisible(driver, By.cssSelector(MESSAGES_TITLE_CSS));
		return driver.findElement(By.cssSelector(MESSAGES_TITLE_CSS)).getText();
	}
	
	public String getTextOfMessage(WebDriver driver){
		waitUntilElementIsNotVisible(driver, By.cssSelector(MESSAGES_TEXT_CSS));
		return driver.findElement(By.cssSelector(MESSAGES_TEXT_CSS)).getText();
	}
	
	public String getValidationErrors(WebDriver driver){
		waitUntilElementIsNotVisible(driver, By.cssSelector(VALIDATION_ERROR_CSS));
		return driver.findElement(By.cssSelector(VALIDATION_ERROR_CSS)).getText();
	}

}