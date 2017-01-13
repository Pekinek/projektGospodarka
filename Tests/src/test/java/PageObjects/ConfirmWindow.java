package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import Pages.MyOffers;
import TestData.Browser;

public class ConfirmWindow extends Browser{
	private static final String YES_BUTTON_CSS = ".btn.btn-success";
	private static final String NO_BUTTON_CSS = ".btn.btn-danger";
	private static final String TEXT_CSS = ".ngdialog-content>div>.ng-binding";
	private static final String CLOSE_WINDOW_CSS = ".ngdialog-close";
	
	public String getText(WebDriver driver){
		waitUntilElementIsNotVisible(driver, By.cssSelector(TEXT_CSS));
		return driver.findElement(By.cssSelector(TEXT_CSS)).getText();
	}
	
	public MyOffers pressYesButton(WebDriver driver){
		waitUntilElementIsNotVisible(driver, By.cssSelector(YES_BUTTON_CSS));
		driver.findElement(By.cssSelector(YES_BUTTON_CSS)).click();
		waitUntilPageFinishLoading(driver);
		return new MyOffers();
	}
	
	public MyOffers pressNoButton(WebDriver driver){
		waitUntilElementIsNotVisible(driver, By.cssSelector(NO_BUTTON_CSS));
		driver.findElement(By.cssSelector(NO_BUTTON_CSS)).click();
		waitUntilPageFinishLoading(driver);
		return new MyOffers();
	}
	
	public MyOffers closeWindow(WebDriver driver){
		driver.findElement(By.cssSelector(CLOSE_WINDOW_CSS)).click();
		return new MyOffers();
	}

}
