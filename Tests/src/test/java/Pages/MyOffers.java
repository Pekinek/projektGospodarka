package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import PageObjects.ConfirmWindow;
import PageObjects.Table;
import TestData.Browser;

public class MyOffers extends Browser{
	private static final String CHECK_BUTTON_CSS = ".btn.btn-primary";
	private static final String DELETE_BUTTON_CSS = ".btn.btn-danger.ng-scope";
	private static final String TITLE_OF_FIRST_OFFERT_XPATH = "html/body/div/div/div/div/div[2]/div[1]/div[1]/offer-box/div/div/div[1]/span";
	private Table table = new Table();
	
	public MyOffers pressCheckButtonForOffert(WebDriver driver, String title){
		WebElement offert = table.getOfferByTitle(driver, title);
		offert.findElement(By.cssSelector(CHECK_BUTTON_CSS)).click();
		return this;
	}
	
	public ConfirmWindow deleteOffer(WebDriver driver, String title){
		WebElement offert = table.getOfferByTitle(driver, title);
		offert.findElement(By.cssSelector(DELETE_BUTTON_CSS)).click();
		return new ConfirmWindow();
	}
	
	public ConfirmWindow deleteFirstOffer(WebDriver driver){
		waitUntilPageFinishLoading(driver);
		String title = driver.findElement(By.xpath(TITLE_OF_FIRST_OFFERT_XPATH)).getText();
		WebElement offert = table.getOfferByTitle(driver, title);
		offert.findElement(By.cssSelector(DELETE_BUTTON_CSS)).click();
		return new ConfirmWindow();
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
