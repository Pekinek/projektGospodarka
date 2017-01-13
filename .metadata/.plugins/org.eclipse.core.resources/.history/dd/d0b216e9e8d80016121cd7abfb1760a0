package PageObjects;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Pages.NewOffers;
import TestData.Browser;

public class Table extends Browser{
	private static final String TABLE_WITH_OFFERS_XPATH = "//*[@ng-if='vm.offers.length > 0']";
	private static final String OFFERS_IN_TABLE_XPATH = "//*[@class='ng-scope' and @ng-repeat='offer in vm.offers track by $index']";
	private static final String FILTER_FIELD_CSS = ".form-control";
	
	public List<WebElement> getOffers(WebDriver driver){
		return driver.findElements(By.xpath(OFFERS_IN_TABLE_XPATH));
	}
	
	public WebElement getOfferByTitle(WebDriver driver, String title){
		waitUntilElementIsNotVisible(driver, By.xpath(TABLE_WITH_OFFERS_XPATH));
		for(WebElement offer : this.getOffers(driver)){
			String textInOffer = offer.getAttribute("textContent");
			if(textInOffer.contains(title)) return offer;
		}
		return null;
	}
	
	public void filterOffers(WebDriver driver, String valueToFilter){
		waitUntilPageFinishLoading(driver);
		driver.findElement(By.cssSelector(FILTER_FIELD_CSS)).sendKeys(valueToFilter);
		try{
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_ENTER);
		}catch(Exception e){
		}
		waitUntilPageFinishLoading(driver);
	}

}
