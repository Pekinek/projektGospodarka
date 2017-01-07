package TestData;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import PageObjects.Messages;
import PageObjects.Navigation;

public class Browser {
	protected WebDriver driver;
	protected Navigation navigate;
	protected Messages messages;
	protected static final String LOADING_BAR_CSS = "#loading-bar";
	private WebDriverWait wait;
	private static final String address = "http://192.166.217.17/";
	private String handler;
	
	public Browser(){
	}
	
	public void prepareBrowser(){
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Zack\\Desktop\\Studia - mgr\\Semestr 1\\projektGospodarka-master\\chromeDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		handler = driver.getWindowHandle();
		driver.get(address);
		messages = new Messages();
		navigate = new Navigation();
		waitUntilElementIsNotVisible(driver, By.xpath(Navigation.TITLE_ON_NEW_OFERS_PAGE_XPATH));
	}
	
	public void waitUntilElementIsNotVisible(WebDriver driver, By locatorOfElement){
		wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locatorOfElement));
	}
	
	public void waitUntilElementIsVisible(WebDriver driver,By locatorOfElement){
		wait = new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(locatorOfElement));
	}
	
	public void waitUntilPageFinishLoading(WebDriver driver){
		waitUntilElementIsVisible(driver, By.xpath(LOADING_BAR_CSS));
		try{
			Thread.sleep(1000);
		}catch(Exception e){
		}
	}
	
	public void closeBrowser(){
		driver.switchTo().window(handler);
		driver.close();
	}
	
}