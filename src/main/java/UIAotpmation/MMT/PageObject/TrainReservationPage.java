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

public class TrainReservationPage {

	WebDriver driver;

	public TrainReservationPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "fromCity")
	private WebElement SelectSource;

	@FindBy(xpath = "//input[@placeholder='From']")
	private WebElement selectFromCity;

	@FindBy(xpath = "//label[@for='toCity']")
	private WebElement SelectDestination;

	@FindBy(xpath = "//input[@placeholder='To']")
	private WebElement selectToCity;

	@FindBy(xpath = "//span[@data-cy='travelDatelabel']")
	private WebElement ClickOnCalender;

	@FindBy(xpath = "//span[contains(@class, 'widgetSprite')]")
	private WebElement CalenderHeading;

	@FindBy(xpath = "//span[@aria-label='Next Month']")
	private WebElement NextMonth;

	@FindBy(xpath = "//div[@class='DayPicker-Month']/div[1]")
	private List<WebElement> SelectMonth;

	@FindBy(xpath = "//div[@class='DayPicker-Month']/div[1]")
	private List<WebElement> MonthSelection;

	@FindBy(xpath = "//div[@class='DayPicker-Body']/div//div/div/p[1]")
	private List<WebElement> SelectDate;

	@FindBy(xpath = "//span[@data-cy='class']")
	private WebElement SelectTravellerClass;

	@FindBy(xpath = "//ul[@class='travelForPopup']")
	private WebElement TravelClassPopup;

	@FindBy(xpath = "//ul[@class='travelForPopup']/li")
	private List<WebElement> TravelClass;

	@FindBy(xpath = "//li[@data-cy='menu_Trains']")
	private WebElement TrainOption;

	@FindBy(xpath = "//span[@data-cy='bookTrainTickets']")
	private WebElement VerifyTrainReservation;

	@FindBy(xpath = "//a[@data-cy='submit']")
	private WebElement searchtrain;

	@FindBy(xpath = "//span[contains(text(),'From & To station cannot be the same')]")
	private WebElement ErrorMessage;

	public void selectFromCity(String FromCity) {
		SelectSource.click();
		selectFromCity.sendKeys(FromCity);
		driver.findElement(By.xpath("//span[contains(text(), '" + FromCity + "')]")).click();
	}

	public void selectToCity(String ToCity) {

//		SelectDestination.click();
		selectToCity.sendKeys(ToCity);
		driver.findElement(By.xpath("//span[contains(text(), '" + ToCity + "')]")).click();
	}

	public boolean isCalenderDisplayed() {

		return CalenderHeading.isDisplayed();
	}

	public void clickOncalender() {

		ClickOnCalender.click();
	}

	public void selectDate(String date) {
		String month = date.substring(5);
		String day = date.substring(0, 2);

		while (!SelectMonth.get(0).getText().contains(month)) {
			NextMonth.click();
		}

		driver.findElements(By.xpath("//div[@class='DayPicker-Day' and contains(text(),'" + day + "' )]")).get(0)
				.click();

	}

	public void selectTravelClass(String Travelcategory) {

		SelectTravellerClass.click();

		if (TravelClassPopup.isDisplayed()) {

			for (WebElement wb : TravelClass) {

				if (wb.getText().contains(Travelcategory)) {

					wb.click();
					break;
				}
			}

		}

	}

	public TrainSearchResult searchTrain() {
		searchtrain.click();
		TrainSearchResult tsr = new TrainSearchResult(driver);
		return tsr;

	}
	
	public void validateErrorMessage() {
		
		ErrorMessage.isDisplayed();
		
	}
}
