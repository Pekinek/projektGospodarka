package Pages;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import TestData.Browser;

public class AddOffers extends Browser{
	private String title = "Nowe tanie mieszkanie ";
	private String city = "Wrocław";
	private String destiny = "sale";
	private String price = "1 000 000";
	private String type = "flat";
	private String description = "Super tanie mieszkanie. Więcej informacji pod numerem: xxx xxx xxx";
	private static final String TITLE_FIELD_CSS = "#title";
	private static final String CITY_FIELD_CSS = "#place";
	private static final String DESTINY_FIELD_CSS = "#purpose";
	private static final String PRICE_FIELD_CSS = "#price";
	private static final String TYPE_FIELD_CSS = "#type";
	private static final String DESCRIPTION_FIELD_CSS = "#description";
	private static final String ADD_IMAGE_BUTTON_XPATH = "//input[@type='file']";
	private static final String ADD_OFFERS_BUTTON_CSS = ".btn.btn-primary.pull-right.ng-binding";;
	private static final String PATH_TO_IMAGE = new File("src/test/resources/pokoj.jpg").getAbsolutePath();
	
	public AddOffers provideDataToAddOffer(WebDriver driver){
		Select destinyDropdown = new Select(driver.findElement(By.cssSelector(DESTINY_FIELD_CSS)));
		Select typeDropdown = new Select(driver.findElement(By.cssSelector(TYPE_FIELD_CSS)));
		
		driver.findElement(By.cssSelector(TITLE_FIELD_CSS)).sendKeys(title + getDate());
		driver.findElement(By.cssSelector(CITY_FIELD_CSS)).sendKeys(city);
		destinyDropdown.selectByValue(destiny);
		driver.findElement(By.cssSelector(PRICE_FIELD_CSS)).sendKeys(price);
		typeDropdown.selectByValue(type);
		driver.findElement(By.cssSelector(DESCRIPTION_FIELD_CSS)).sendKeys(description);
		return this;
	}
	
	public AddOffers provideDataToAddOffer(WebDriver driver, String title,String city,String destiny,String price,String type,String description){
		Select destinyDropdown = new Select(driver.findElement(By.cssSelector(DESTINY_FIELD_CSS)));
		Select typeDropdown = new Select(driver.findElement(By.cssSelector(TYPE_FIELD_CSS)));
		
		driver.findElement(By.cssSelector(TITLE_FIELD_CSS)).sendKeys(title);
		driver.findElement(By.cssSelector(CITY_FIELD_CSS)).sendKeys(city);
		if(destiny!=null && destiny!="") destinyDropdown.selectByValue(destiny);
		driver.findElement(By.cssSelector(PRICE_FIELD_CSS)).sendKeys(price);
		if(type!=null && type!="") typeDropdown.selectByValue(type);
		driver.findElement(By.cssSelector(DESCRIPTION_FIELD_CSS)).sendKeys(description);
		return this;
	}
	
	public AddOffers insertImage(WebDriver driver){
		WebElement pathField = driver.findElement(By.xpath(ADD_IMAGE_BUTTON_XPATH));
		pathField.sendKeys(PATH_TO_IMAGE);
		return this;
	}
	
	public AddOffers pressAddOferrButton(WebDriver driver){
		driver.findElement(By.cssSelector(ADD_OFFERS_BUTTON_CSS)).click();
		return this;
	}
	
	public String checkStatusOfAddButton(WebDriver driver){
		return driver.findElement(By.cssSelector(ADD_OFFERS_BUTTON_CSS)).getAttribute("disabled");
	}
	
	

}
