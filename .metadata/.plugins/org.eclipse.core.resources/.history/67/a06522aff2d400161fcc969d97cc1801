package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import TestData.Browser;

public class BugReport extends Browser{
	private String email = "Test@test.com";
	private String title = "Bug";
	private String description = "Dodawanie ofert nie działa";
	private static final String E_MAIL_FIELD_CSS = "#email";
	private static final String TITLE_FIELD_CSS = "#title";
	private static final String DESCRIPTION_CSS = "#description";
	private static final String SENT_BUTTON_CSS = "#edit_submit";
	
	public BugReport provideDataToReportBug(WebDriver driver){
		driver.findElement(By.cssSelector(E_MAIL_FIELD_CSS)).sendKeys(email);
		driver.findElement(By.cssSelector(TITLE_FIELD_CSS)).sendKeys(title);
		driver.findElement(By.cssSelector(DESCRIPTION_CSS)).sendKeys(description);
		return this;
	}
	
	public BugReport provideDataToReportBug(WebDriver driver, String email, String title, String description){
		driver.findElement(By.cssSelector(E_MAIL_FIELD_CSS)).sendKeys(email);
		driver.findElement(By.cssSelector(TITLE_FIELD_CSS)).sendKeys(title);
		driver.findElement(By.cssSelector(DESCRIPTION_CSS)).sendKeys(description);
		return this;
	}
	
	public BugReport pressSentButton(WebDriver driver){
		driver.findElement(By.cssSelector(SENT_BUTTON_CSS)).click();
		return this;
	}

}
