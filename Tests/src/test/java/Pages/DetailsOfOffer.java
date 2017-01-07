package Pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import TestData.Browser;

public class DetailsOfOffer extends Browser {
	private static final String TITLE_XPATH = "//*[@class='title']/span[1]";
	private static final String DATE_XPATH = "//*[@class='title']/span[2]";
	private static final String IMAGE_CSS = ".images>img";
	private static final String DESCRIPTION_CSS = ".col-sm-12.ng-binding";
	private static final String PRICE_XPATH = "//*[@class='col-xs-12 col-md-6 descriptors'][1]/div[1]/span[2]";
	private static final String CITY_XPATH = "//*[@class='col-xs-12 col-md-6 descriptors'][1]/div[2]/span[2]";
	private static final String DESTINY_XPATH = "//*[@class='col-xs-12 col-md-6 descriptors'][1]/div[3]/span[2]";
	private static final String TYPE_XPATH = "//*[@class='col-xs-12 col-md-6 descriptors'][1]/div[4]/span[2]";
	private static final String OWNER_XPATH = "//*[@class='col-xs-12 col-md-6 descriptors'][2]/div[1]/span[2]";
	private static final String EMAIL_XPATH = "//*[@class='col-xs-12 col-md-6 descriptors'][2]/div[2]/span[2]";
	private static final String PHONE_NO_XPATH = "//*[@class='col-xs-12 col-md-6 descriptors'][2]/div[3]/span[2]";
	private static final String COMMENT_FIELD_CSS = ".form-control.ng-pristine.ng-untouched.ng-valid.ng-empty";
	private static final String FIRST_COMMENT_XPATH = "//*[@class='well ng-scope']/div[1]//a";
	private static final String ADD_TO_FAVORITES_BUTTON_XPATH = "//*[@ng-click='vm.addToFav(offer.id)']";
	
	public String getTitle(WebDriver driver){
		return driver.findElement(By.xpath(TITLE_XPATH)).getText();
	}
	
	public String getDate(WebDriver driver){
		return driver.findElement(By.xpath(DATE_XPATH)).getText();
	}
	
	public String getDescription(WebDriver driver){
		return driver.findElement(By.cssSelector(DESCRIPTION_CSS)).getText();
	}
	
	public String getPrice(WebDriver driver){
		return driver.findElement(By.xpath(PRICE_XPATH)).getText();
	}
	
	public String getCity(WebDriver driver){
		return driver.findElement(By.xpath(CITY_XPATH)).getText();
	}
	
	public String getDestiny(WebDriver driver){
		return driver.findElement(By.xpath(DESTINY_XPATH)).getText();
	}
	
	public String getType(WebDriver driver){
		return driver.findElement(By.xpath(TYPE_XPATH)).getText();
	}
	
	public String getOwnerOfOffer(WebDriver driver){
		return driver.findElement(By.xpath(OWNER_XPATH)).getText();
	}
	
	public String getEmail(WebDriver driver){
		return driver.findElement(By.xpath(EMAIL_XPATH)).getText();
	}
	
	public String getPhoneNo(WebDriver driver){
		return driver.findElement(By.xpath(PHONE_NO_XPATH)).getText();
	}
	
	public String getFristComment(WebDriver driver){
		return driver.findElement(By.xpath(FIRST_COMMENT_XPATH)).getText();
	}
	
	public String getCommentByUser(WebDriver driver, String userName){
		return driver.findElement(By.xpath("//*[@class='ng-binding' and text()='"+userName+"']")).getText();
	}
	
	public DetailsOfOffer addComment(WebDriver driver, String comment) throws AWTException{
		driver.findElement(By.cssSelector(COMMENT_FIELD_CSS)).sendKeys(comment);
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_ENTER);
		return this;
	}
	
	public DetailsOfOffer addToFavorites(WebDriver driver){
		driver.findElement(By.xpath(ADD_TO_FAVORITES_BUTTON_XPATH)).click();
		return this;
	}
	
	public String assertIfImageIsPresentOnPage(WebDriver driver){
		return driver.findElement(By.cssSelector(IMAGE_CSS)).getAttribute("complete");
	}

}