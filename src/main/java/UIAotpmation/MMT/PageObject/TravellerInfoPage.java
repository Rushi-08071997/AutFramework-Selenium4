package UIAotpmation.MMT.PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import UIAutomation.MMT.Resources.Util;

public class TravellerInfoPage {

	WebDriver driver;

	public TravellerInfoPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//h2[contains(text(),\"Select Travellers\")]")
	private WebElement SelectTravellerHeading;

	@FindBy(xpath = "//div[@class='trStatusBlock']/p/span[1]")
	private WebElement ValidateCoach;

	@FindBy(xpath = "//div[@class='trStatusBlock']//span[2]/span")
	private WebElement GetAvailabilityStatus;

	@FindBy(xpath = "//p[contains(text(),'Your Boarding Station')]/following-sibling::p/span[2]")
	private WebElement ValidateBoardingStation;

	@FindBy(xpath = "//span[contains(text(), 'Add Traveller')]")
	private WebElement ClickOnAddTraveller;

	@FindBy(xpath = "//span[contains(text(),'Add Traveller Information')]")
	private WebElement AddTravellerInfo;

	@FindBy(xpath = "//input[@placeholder='Enter Traveller Name']")
	private WebElement TravellerName;

	@FindBy(xpath = "//input[@placeholder='Enter Age']")
	private WebElement TravellerAge;

	@FindBy(xpath = "//label[contains(text(),'Gender')]/following-sibling::div/p")
	private WebElement TravellerGender;

	@FindBy(xpath = "//label[contains(text(),'Nationality')]/following-sibling::div/p")
	private WebElement TravellerNationality;

	@FindBy(xpath = "//label[contains(text(),'Berth Preference')]/following-sibling::div/p")
	private WebElement BirthPreference;

	@FindBy(xpath = "//button[contains(text(),'Add')]")
	private WebElement clickonAdd;

	@FindBy(xpath = "//input[@placeholder='Enter IRCTC username']")
	private WebElement EnterUsername;

	@FindBy(xpath = "//span[contains(text(),'Enter IRCTC Username')]")
	private WebElement EnterUsernamePopup;

	@FindBy(xpath = "//input[@name='IRCTCUserName']")
	private WebElement SendUsername;

	@FindBy(xpath = "//span[contains(text(),'Submit')]")
	private WebElement SubmitUsername;

	@FindBy(xpath = "//input[@placeholder='Enter Email Id']")
	private WebElement EnterEmailID;

	@FindBy(xpath = "//input[@placeholder='Enter Mobile Number']")
	private WebElement EnterMobNumber;

	@FindBy(xpath = "//p[contains(text(),'Select the State')]/following-sibling::div")
	private WebElement selectState;

	@FindBy(xpath = "//p[contains(text(),'Select the State')]/following-sibling::div/following-sibling::ul/li")
	private List<WebElement> States;
	
	@FindBy(xpath = "//span[@class='checkboxWpr']")
	private WebElement checkbox;

	@FindBy(xpath = "//p[@class='totalPriceValue']")
	private WebElement TicketValue;
	
	@FindBy(xpath = "//span[contains(text(),'Pay & Book Now')]")
	private WebElement ProceedPayment;
	
	@FindBy(xpath = "//button[contains(text(),'I don’t want to guarantee my trip')]")
	private WebElement CancelTripGurantee;
	
	@FindBy(xpath = "//span[contains(text(),'Please select a traveller')]")
	private WebElement TravellerErrorMessage;
	
	@FindBy(xpath = "//p[contains(text(),'Please enter IRCTC Username to continue')]")
	private WebElement UsernameErrorMessage;
	
	@FindBy(xpath = "//p[contains(text(),'Please enter correct email')]")
	private WebElement EMailErrorMessage;

	public boolean validatePageHeading() {
		Util.waitForElementVisible(driver, SelectTravellerHeading, 10);
		return SelectTravellerHeading.isDisplayed();
	}

	public void validateTraiName(String name) {

		WebElement TrainName = driver.findElement(By.xpath("//h3[contains(text(),'" + name + "')]"));

		Assert.assertEquals(name, TrainName.getText());

	}

	public void validateCoach(String Coach) {

		Assert.assertEquals(Coach, ValidateCoach.getText());

	}

	public void getAvailabilityStatus() {

		System.out.println(GetAvailabilityStatus.getText());

	}

	public void validateBoardingStation(String Boardingstation) {

		Assert.assertEquals(Boardingstation, ValidateBoardingStation.getText());

	}

	public void clickOnAddTraveller() {

		while (!ClickOnAddTraveller.isDisplayed()) {
			Util.scrollByPixels(driver, 400);
		}
		ClickOnAddTraveller.click();
	}

	public boolean verifyTravellerInfoPopup() {

		Util.waitForElementVisible(driver, AddTravellerInfo, 5);
		return AddTravellerInfo.isDisplayed();
	}

	public void addTravelllerInfo(String Name, String Age, String Gender, String Nationality, String Preference) {

		TravellerName.sendKeys(Name);
		TravellerAge.sendKeys(Age);
		TravellerGender.click();
		driver.findElement(By.xpath("//li//span[contains(text(),'" + Gender + "')]")).click();
		Assert.assertEquals(Nationality, TravellerNationality.getText());
		BirthPreference.click();
		driver.findElement(By.xpath("//span[contains(text(),'" + Preference + "')]")).click();
		clickonAdd.click();
	}

	public void scrollToAndClickAddUsername() {

		while (!EnterUsername.isDisplayed()) {
			Util.scrollByPixels(driver, 400);
		}
		EnterUsername.click();
	}

	public boolean verifyEnterUsernamePopup() {

		Util.waitForElementVisible(driver, EnterUsernamePopup, 5);
		return EnterUsernamePopup.isDisplayed();
	}

	public void addUsername(String UserName) {

		SendUsername.sendKeys(UserName);
		SubmitUsername.click();
	}

	public void scrollToAndAddEmailID(String EmailID) {

		while (!EnterEmailID.isDisplayed()) {
			Util.scrollByPixels(driver, 400);
		}
		EnterEmailID.sendKeys(EmailID);
	}

	public void addMobNumber(String Number) {

		EnterMobNumber.sendKeys(Number);
	}

	public void scrollToAddState() {

		while (!selectState.isDisplayed()) {
			Util.scrollByPixels(driver, 400);
		}
		selectState.click();
	}
	
	public void selectState(String state) {

		for(WebElement wb:States) {
			if(wb.getText().equalsIgnoreCase(state)){
				wb.click();
				break;
			}
		}
	}
	
	public void clickOnCheckbox( ) {
		
		checkbox.click();
		}
	
	public void validateTicketPrice(String Price) {
		
		Assert.assertEquals(Price,TicketValue.getText().substring(1));
	}
	
public void submitInfo( ) {
	Util.waitForElementClickable(driver, ProceedPayment, 5);
	ProceedPayment.click();
	}

public TrainReservePaymentPage toCancelTripGurantee( ) {
	
	Util.waitForElementVisible(driver, CancelTripGurantee, 5);
	
	CancelTripGurantee.click();
	
	TrainReservePaymentPage trp = new TrainReservePaymentPage(driver);
	
	return trp;
	}

public void verifyTravellererorMessage(String message)
{
	Assert.assertEquals(message, TravellerErrorMessage.getText());

}

public void verifyUsernameErrorMessage(String message)
{
	Assert.assertEquals(message, UsernameErrorMessage.getText());

}
public void verifyEmailErrorMessage(String message)
{
	Assert.assertEquals(message, EMailErrorMessage.getText());

}
}