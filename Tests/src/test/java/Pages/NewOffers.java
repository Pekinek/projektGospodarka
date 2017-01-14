package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import PageObjects.Table;
import TestData.Browser;

public class NewOffers extends Browser {
	private static final String CHECK_BUTTON_CSS = ".btn.btn-primary";
	private static final String TITLE_OF_FIRST_OFFERT_XPATH = "html/body/div/div/div/div/div[2]/div[1]/div[1]/offer-box/div/div/div[1]/span";
	private static final String PRICE_OF_FIRST_OFFERT_XPATH = "html/body/div/div/div/div/div[2]/div[1]/div[1]/offer-box/div/div/div[2]/div/div/span[1]";
	private static final String TYPE_OF_FIRST_OFFERT_XPATH = "html/body/div/div/div/div/div[2]/div[1]/div[1]/offer-box/div/div/div[2]/div/div/span[2]";
	private static final String CITY_OF_FIRST_OFFERT_XPATH = "html/body/div/div/div/div/div[2]/div[1]/div[1]/offer-box/div/div/div[2]/div/div/span[3]";
	private static final String DESTINY_OF_FIRST_OFFERT_XPATH = "html/body/div/div/div/div/div[2]/div[1]/div[1]/offer-box/div/div/div[2]/div/div/span[4]";
	private static final String TITLE_FIELD_CSS = "#title";
	private static final String CITY_FIELD_CSS = "#place";
	private static final String DESTINY_FIELD_CSS = "#purpose";
	private static final String PRICE_FIELD_CSS = "#price";
	private static final String TYPE_FIELD_CSS = "#type";
	private Table table = new Table();
	
	public DetailsOfOffer pressCheckButtonForOffert(WebDriver driver, String title){
		WebElement offert = table.getOfferByTitle(driver, title);
		offert.findElement(By.cssSelector(CHECK_BUTTON_CSS)).click();
		return new DetailsOfOffer();
	}
	
	public DetailsOfOffer pressCheckButtonForFirstOffert(WebDriver driver){
		String title = driver.findElement(By.xpath(TITLE_OF_FIRST_OFFERT_XPATH)).getText();
		WebElement offert = table.getOfferByTitle(driver, title);
		offert.findElement(By.cssSelector(CHECK_BUTTON_CSS)).click();
		return new DetailsOfOffer();
	}
	
	public NewOffers selectPage(WebDriver dirver, int pageNo){
		driver.findElement(By.xpath("//*[@ng-click='setCurrent(pageNumber)' and text()='"+pageNo+"']")).click();
		return this;
	}
	
	public String getTitleForFirstOffert(WebDriver driver){
		return driver.findElement(By.xpath(TITLE_OF_FIRST_OFFERT_XPATH)).getText();
	}
	
	public String getTypeForFirstOffert(WebDriver driver){
		return driver.findElement(By.xpath(TYPE_OF_FIRST_OFFERT_XPATH)).getText();
	}
	
	public String getPriceForFirstOffert(WebDriver driver){
		return driver.findElement(By.xpath(PRICE_OF_FIRST_OFFERT_XPATH)).getText();
	}
	
	public String getCityForFirstOffert(WebDriver driver){
		return driver.findElement(By.xpath(CITY_OF_FIRST_OFFERT_XPATH)).getText();
	}
	
	public String getDestinyForFirstOffert(WebDriver driver){
		return driver.findElement(By.xpath(DESTINY_OF_FIRST_OFFERT_XPATH)).getText();
	}
	
	public DetailsOfOffer getDetailsOfOffer(){
		return new DetailsOfOffer();
	}
	
	public NewOffers presentOffersSelectedByString(WebDriver driver, String value){
		table.filterOffers(driver, value);
		return this;
	}
	
	public NewOffers setTitleForFiltr(WebDriver driver, String title){
		driver.findElement(By.cssSelector(TITLE_FIELD_CSS)).sendKeys(title);
		return this;
	}
	
	public NewOffers selectTypeForFiltr(WebDriver driver, String type){
		Select typeDropdown = new Select(driver.findElement(By.cssSelector(TYPE_FIELD_CSS)));
		if(type!=null && type!="") typeDropdown.selectByValue(type);
		return this;
	}
	
	public NewOffers setCityForFiltr(WebDriver driver, String city){
		driver.findElement(By.cssSelector(CITY_FIELD_CSS)).sendKeys(city);
		return this;
	}
	
	public NewOffers setDestinyForFiltr(WebDriver driver, String destiny){
		Select destinyDropdown = new Select(driver.findElement(By.cssSelector(DESTINY_FIELD_CSS)));
		if(destiny!=null && destiny!="") destinyDropdown.selectByValue(destiny);
		return this;
	}
	
	public NewOffers setPriceForFiltr(WebDriver driver, String price){
		driver.findElement(By.cssSelector(PRICE_FIELD_CSS)).sendKeys(price);
		return this;
	}
}
