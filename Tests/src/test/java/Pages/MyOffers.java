package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import PageObjects.Table;
import TestData.Browser;

public class MyOffers extends Browser{
	private static final String CHECK_BUTTON_CSS = ".btn.btn-primary";
	private Table table = new Table();
	
	public MyOffers pressCheckButtonForOffert(WebDriver driver, String title){
		WebElement offert = table.getOfferByTitle(driver, title);
		offert.findElement(By.cssSelector(CHECK_BUTTON_CSS)).click();
		return this;
	}
	
	public MyOffers selectPage(WebDriver dirver, int pageNo){
		driver.findElement(By.xpath("//*[@ng-click='setCurrent(pageNumber)' and text()='"+pageNo+"']")).click();
		return this;
	}
	
	public DetailsOfOffer getDetailsOfOffer(){
		return new DetailsOfOffer();
	}
	
	public MyOffers presentOffersSelectedByString(WebDriver driver, String value){
		table.filterOffers(driver, value);
		return this;
	}
	
}
