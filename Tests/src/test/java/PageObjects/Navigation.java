package PageObjects;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Pages.AddOffers;
import Pages.BugReport;
import Pages.Favorites;
import Pages.Login;
import Pages.MyAccount;
import Pages.MyOffers;
import Pages.NewOffers;
import TestData.Browser;

public class Navigation extends Browser{
	private static final String LOGIN_XPATH = "//*[@ui-sref='login.login']";
	private static final String MY_ACCOUNT_XAPTH = "//*[@class='description ng-binding' and text()='Moje konto']";
	private static final String MY_OFFERS_XPATH = "//*[@class='description ng-binding' and text()='Moje oferty']";
	private static final String FAVORITES_XPATH = "//*[@class='description ng-binding' and text()='Ulubione']";
	private static final String NEW_OFFERS_XPATH = "//*[@ui-sref='main.offers']";
	private static final String ADD_OFFER_XPATH = "//*[@ui-sref='main.add-offer']";
	private static final String BUG_REPORT_XPATH = "//*[text()='Zgłaszanie błędów']";
	public static final String TITLE_ON_MY_ACCOUNT_PAGE_XPATH = "//*[@class='ng-binding' and text()='Moje konto']";
	public static final String TITLE_ON_MY_OFFERS_PAGE_XPATH = "//*[@class='ng-binding' and text()='Moje oferty']";
	public static final String TITLE_ON_FAVORITES_PAGE_XPATH = "//*[@class='ng-binding' and text()='Ulubione']";
	public static final String TITLE_ON_NEW_OFERS_PAGE_XPATH = "//*[@class='ng-binding' and text()='Najnowsze oferty']";
	public static final String TITLE_ON_DETAILS_PAGE_XPATH = "//*[contains(text(),'Oferta')]";
	public static final String TITLE_ON_ADD_OFERS_PAGE_XPATH = "//*[@class='ng-binding' and text()='Dodaj ofertę']";
	public static final String TITLE_ON_BUG_REPORT_PAGE_XPATH = "//*[@class='ng-binding' and text()='Zgłaszanie problemów / sugestii']";
	public static final String LOGOUT_BUTTON_CSS = ".ng-scope[ng-click='header.logout();']";
	private JavascriptExecutor js;
	Robot robot;
	
	public void openSidebar(WebDriver driver){
		try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }  
		robot.mouseMove(10, 500);
	}
	
	public void clickUsingJS(WebDriver driver,WebElement element){
		js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", element);
	}
	
	public Login goToLoginPage(WebDriver driver){
		this.openSidebar(driver);
		WebElement login = driver.findElement(By.xpath(LOGIN_XPATH));
		this.clickUsingJS(driver, login);
		waitUntilElementIsNotVisible(driver, By.id("login"));
		return new Login();
	}
	
	public NewOffers goToNewOffersPage(){
		this.openSidebar(driver);
		WebElement newOffers = driver.findElement(By.xpath(NEW_OFFERS_XPATH));
		this.clickUsingJS(driver, newOffers);
		waitUntilElementIsNotVisible(driver, By.xpath(TITLE_ON_NEW_OFERS_PAGE_XPATH));
		return new NewOffers();
	}
	
	public AddOffers goToAddOffersPage(WebDriver driver){
		this.openSidebar(driver);
		WebElement addOffers = driver.findElement(By.xpath(ADD_OFFER_XPATH));
		this.clickUsingJS(driver, addOffers);
		waitUntilElementIsNotVisible(driver, By.xpath(TITLE_ON_ADD_OFERS_PAGE_XPATH));
		return new AddOffers();
	}
	
	public BugReport goToBugReportPage(WebDriver driver){
		this.openSidebar(driver);
		WebElement bugReport = driver.findElement(By.xpath(BUG_REPORT_XPATH));
		this.clickUsingJS(driver, bugReport);
		waitUntilElementIsNotVisible(driver, By.xpath(TITLE_ON_BUG_REPORT_PAGE_XPATH));
		return new BugReport();
	}
	
	public MyAccount goToMyAccountPage(WebDriver driver){
		this.openSidebar(driver);
		WebElement myAccount = driver.findElement(By.xpath(MY_ACCOUNT_XAPTH));
		this.clickUsingJS(driver, myAccount);
		waitUntilElementIsNotVisible(driver, By.xpath(TITLE_ON_MY_ACCOUNT_PAGE_XPATH));
		return new MyAccount();
	}
	
	public MyOffers goToMyOffersPage(WebDriver driver){
		this.openSidebar(driver);
		WebElement myOffers = driver.findElement(By.xpath(MY_OFFERS_XPATH));
		this.clickUsingJS(driver, myOffers);
		waitUntilElementIsNotVisible(driver, By.xpath(TITLE_ON_MY_OFFERS_PAGE_XPATH));
		return new MyOffers();
	}
	
	public Favorites goToFavoritesPage(WebDriver driver){
		this.openSidebar(driver);
		WebElement favorites = driver.findElement(By.xpath(FAVORITES_XPATH));
		this.clickUsingJS(driver, favorites);
		waitUntilElementIsNotVisible(driver, By.xpath(TITLE_ON_FAVORITES_PAGE_XPATH));
		return new Favorites();
	}

	public void logout(WebDriver driver){
		try{
			if(driver.findElement(By.cssSelector(".toast-message")).isDisplayed()) waitUntilElementIsVisible(driver, By.cssSelector(".toast-message"));
		}catch(Exception e){
			
		}
		
		waitUntilElementIsNotVisible(driver, By.cssSelector(LOGOUT_BUTTON_CSS));
		driver.findElement(By.cssSelector(LOGOUT_BUTTON_CSS)).click();
	}

}
