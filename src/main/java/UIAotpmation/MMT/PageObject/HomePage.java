package UIAotpmation.MMT.PageObject;

import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import UIAutomation.MMT.Resources.Util;



public class HomePage {

	WebDriver driver;

	public HomePage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//img[@src='//promos.makemytrip.com/Growth/Images/3x/mmt_dt_header_icon_3x.png']")
	WebElement MMTLogo;

	@FindBy(xpath = "//ul[contains(@class, 'headerIconsGap')]/li")
	private List<WebElement> NavBarOptions;

	@FindBy(xpath = "//span[@class='commonModal__close']")
	WebElement closebutton;

	@FindBy(id = "fromCity")
	private WebElement SelectSource;

	@FindBy(xpath = "//input[@placeholder='From']")
	private WebElement selectFromCity;

//	@FindBy(xpath = "//div[@role='listbox']//ul//li/div/div/div//div/p")
//	private List<WebElement> FromCityList;

	@FindBy(xpath = "//label[@for='toCity']")
	private WebElement SelectDestination;

	@FindBy(xpath = "//input[@placeholder='To']")
	private WebElement selectToCity;

//	@FindBy(xpath = "//div[@role='listbox']//ul//li/div/div/div//div/p")
//	private List<WebElement> ToCityList;

	@FindBy(xpath = "//span[@data-cy='selectReturnDate']")
	private WebElement CalenderHeading;

	@FindBy(xpath = "//span[@aria-label='Next Month']")
	private WebElement NextMonth;

	@FindBy(xpath = "//div[@class='DayPicker-Month']//div[@class='DayPicker-Caption']/div")
	private List<WebElement> SelectMonth;

	@FindBy(xpath = "//div[@class='DayPicker-Month']/div[1]")
	private List<WebElement> MonthSelection;

	@FindBy(xpath = "//div[@class='DayPicker-Body']/div//div/div/p[1]")
	private List<WebElement> SelectDate;

	@FindBy(xpath = "//span[contains(text(),'Travellers')]")
	private WebElement SelectTraveller;

	@FindBy(xpath = "//p[contains(text(), 'ADULTS')]")
	private WebElement AdultDisplayed;

	@FindBy(xpath = "//li[contains(@data-cy, 'adults')]")
	private List<WebElement> SelectAdult;

	@FindBy(xpath = "//li[contains(@data-cy, 'children')]")
	private List<WebElement> SelectChildren;

	@FindBy(xpath = "//li[contains(@data-cy, 'travelClass')]")
	private List<WebElement> SelectTravelClass;

	@FindBy(xpath = "//button[contains(@data-cy, 'travellerApplyBtn')]")
	private WebElement Applybutton;

	@FindBy(xpath = "//div[@class='fareCardItem ']/div[2]")
	private List<WebElement> SelectTravellerCategory;

	@FindBy(xpath = "//span[@class='tooltip']")
	private WebElement Tooltip;

	@FindBy(id = "specialFareEorroMessage")
	private WebElement ErrorMessage;

	@FindBy(xpath = "//span[@data-cy='sameCityError']")
	private WebElement SameCityErrorMessage;
	
	@FindBy(xpath = "//li[@data-cy='roundTrip']")
	private WebElement roundtrip;

	@FindBy(xpath = "//p[@data-cy='submit']/a")
	private WebElement Searchbutton;
	
	@FindBy(xpath = "//li[@data-cy='menu_Trains']")
	private WebElement TrainOption;
	
	@FindBy(xpath = "//span[@data-cy='bookTrainTickets']")
	private WebElement VerifyTrainReservation ;
	

	public boolean closepopupDisplayed() {

		return closebutton.isDisplayed();
	}

	public void closepopup() {

		closebutton.click();
	}

	public boolean islogodisplayed() {

		return MMTLogo.isDisplayed();

	}

	public void navbarMenucount(int options) {

		int MenuCount = NavBarOptions.size();
		Assert.assertEquals(MenuCount, options);

	}

	public void selectFromCity(String FromCity) {
		SelectSource.click();
		selectFromCity.sendKeys(FromCity);
		driver.findElement(By.xpath("//span//span[contains(text(), '" + FromCity + "')]")).click();
	}

	public void selectToCity(String ToCity) {

		SelectDestination.click();
		selectToCity.sendKeys(ToCity);
		driver.findElement(By.xpath("//span//span[contains(text(), '" + ToCity + "')]")).click();
	}

	public boolean isCalenderDisplayed() {

		return CalenderHeading.isDisplayed();
	}

	public void selectDate(String date) {
		String month = date.substring(5);
		String day = date.substring(0, 2);
		
		

		while (!SelectMonth.get(0)
				.getText().contains(month)) {
			NextMonth.click();
		}
		driver.findElements(By.xpath("//div[@class='dateInnerCell']//p[1][contains(text(),'"+day+"')]")).get(0).click();
			
	}
	
	public void selectRoundTripDate(String date) throws InterruptedException {
		String month = date.substring(5);
		String day = date.substring(0, 2);

		while (!SelectMonth.get(0)
				.getText().contains(month)) {
			NextMonth.click();
		}
		
		driver.findElements(By.xpath("//div[@class='dateInnerCell']//p[1][contains(text(),'"+day+"')]")).get(0).click();

	}

	public void selectTraveller(int Adults, int children) {
		String str1 = Integer.toString(Adults);
		String str2 = Integer.toString(children);
		// Use String as CharSequence
		CharSequence char1 = str1;
		CharSequence char2 = str2;
		Util.waitForElementVisible(driver, SelectTraveller, 10);
		SelectTraveller.click();
		if (AdultDisplayed.isDisplayed()) {

			for (WebElement Ad : SelectAdult) {
				if (Ad.getText().contains(char1)) {
					Ad.click();
				}
			}
			for (WebElement ch : SelectChildren) {
				if (ch.getText().contains(char2)) {
					ch.click();
				}
			}
		}
	}

	public void selectTravelClass(String Travelclass) {

		for (WebElement tc : SelectTravelClass) {

			if (tc.getText().contains(Travelclass)) {

				tc.click();
			}
		}

	}

	public void submitTravellerInfo() {

		Applybutton.click();

	}

	public void selectCategory(String category) {
		for (WebElement wb : SelectTravellerCategory) {

			if (wb.getText().contains(category)) {

				wb.click();
			}
		}
	}

	public void validatetooltipMessage(String message) {

//		 JavascriptExecutor js = (JavascriptExecutor) driver;
//	     String tooltipContent = (String) js.executeScript(
//	            "return window.getComputedStyle(arguments[0],'::before').getAttribute('title');", Tooltip
//	        );

		System.out.println(Tooltip.getAttribute("title"));

		// Assert.assertEquals(Tooltip.getText(), message);
	}

	public void validateErrorMessage() {

		String errormessage = "Only adult passengers can avail of Senior Citizen fares. You may continue to book a regular fare or de-select all child/infant passengers to book the Senior Citizen fare";
		ErrorMessage.isDisplayed();
		Assert.assertEquals(ErrorMessage.getText(), errormessage);
	}

	public void validateSameCityErrorMessage() {

		String errormessage = "From & To airports cannot be the same";
		SameCityErrorMessage.isDisplayed();
		Assert.assertEquals(SameCityErrorMessage.getText(), errormessage);
	}
	
	public void clickOnRoundTrip() {
		
		roundtrip.click();
	}

	public FlightSearch searchflight() {
		Actions ac = new Actions(driver);
		ac.moveByOffset(20, 20).build().perform();
		Searchbutton.click();
		FlightSearch fs = new FlightSearch(driver);
		return fs;
	}
	
	public TrainReservationPage clickOnTrains() {
		
		TrainOption.click();
		TrainReservationPage tp = new TrainReservationPage(driver);
		return tp;
	}
	
	

}
