package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import TestData.Browser;

public class Messages extends Browser {
	private static final String MESSAGES_TITLE = ".toast-title";
	private static final String MESSAGES_TEXT = ".toast-message";
	
	public String getTitleOfMessage(WebDriver driver){
		waitUntilElementIsNotVisible(driver, By.cssSelector(MESSAGES_TITLE));
		return driver.findElement(By.cssSelector(MESSAGES_TITLE)).getText();
	}
	
	public String getTextOfMessage(WebDriver driver){
		waitUntilElementIsNotVisible(driver, By.cssSelector(MESSAGES_TEXT));
		return driver.findElement(By.cssSelector(MESSAGES_TEXT)).getText();
	}

}
