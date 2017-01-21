package TestData;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import PageObjects.ConfirmWindow;
import PageObjects.Messages;
import PageObjects.Navigation;

public class Browser {
	protected WebDriver driver;
	protected Navigation navigate;
	protected Messages messages;
	protected ConfirmWindow confirmWindow;
	protected String dateToSave;
	protected static final String LOADING_BAR_CSS = "#loading-bar";
	private WebDriverWait wait;
	private static final String address = "http://192.166.217.17/";
	private String handler;
	private DateFormat dateFormat;
	private Date date;
	private String pathToFIle = new File("src/test/resources/date.txt").getAbsolutePath();
	private FileReader read;
	private BufferedReader buffereReader;
	private FileWriter write;
	private BufferedWriter buffereWriter;
	
	public Browser(){
	}
	
	public void prepareBrowser(){
		try{
			read = new FileReader(pathToFIle);
			buffereReader = new BufferedReader(read);
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Zack\\Desktop\\Studia - mgr\\Semestr 1\\projektGospodarka-master\\chromeDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		handler = driver.getWindowHandle();
		driver.get(address);
		messages = new Messages();
		navigate = new Navigation();
		waitUntilElementIsNotVisible(driver, By.xpath(Navigation.TITLE_ON_NEW_OFERS_PAGE_XPATH));
		try {
			dateToSave = buffereReader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
			Thread.sleep(5000);
		}catch(Exception e){
		}
	}
	
	public String getDate(){
		dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		date = new Date();
		dateToSave = dateFormat.format(date);
		try {
			write = new FileWriter(pathToFIle);
			buffereWriter = new BufferedWriter(write);
			buffereWriter.write(dateToSave);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dateToSave;
	}
	
	public void closeBrowser(){
		driver.switchTo().window(handler);
		driver.close();
	}
	
}