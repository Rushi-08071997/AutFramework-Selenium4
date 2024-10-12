package UIAutomation.MMT.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import UIAotpmation.MMT.PageObject.FlightSearch;
import UIAotpmation.MMT.PageObject.HomePage;
import UIAutomation.MMT.Resources.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;

public class FlightBookTest extends BaseTest {
	WebDriver driver;
	HomePage hp;
	FlightSearch fs;

	@BeforeMethod
	public void launchSite() throws IOException {
		driver = InitializeDriver();
		hp = new HomePage(driver);
		driver.manage().window().maximize();
		driver.get(prop.getProperty("url"));
	}

	@Test(dataProvider = "testData")
	public void landingPageTest(HashMap<String, String> testData) throws InterruptedException {
		if (hp.closepopupDisplayed()) {
			hp.closepopup();
		}
		hp.islogodisplayed();
		hp.navbarMenucount(9);
		hp.selectFromCity(testData.get("source"));
		hp.selectToCity(testData.get("destination"));
		hp.isCalenderDisplayed();
		hp.selectDate(testData.get("date1"));
		hp.selectTraveller(2, 3);
		hp.selectTravelClass(testData.get("TravelClass"));
		hp.submitTravellerInfo();
		hp.selectCategory(testData.get("PassengerType1"));
		fs = hp.searchflight();
		fs.validateNetworkissue();
	}

	@Test(dataProvider = "testData")
	public void roundTripTest(HashMap<String, String> testData) throws InterruptedException {
		if (hp.closepopupDisplayed()) {
			hp.closepopup();
		}
		hp.islogodisplayed();
		hp.navbarMenucount(9);
		hp.clickOnRoundTrip();
		hp.selectFromCity(testData.get("source"));
		hp.selectToCity(testData.get("destination"));
		hp.selectRoundTripDate(testData.get("date2"));
		hp.selectTraveller(2, 3);
		hp.selectTravelClass(testData.get("TravelClass"));
		hp.submitTravellerInfo();
		hp.selectCategory(testData.get("PassengerType1"));
		fs = hp.searchflight();
		fs.validateNetworkissue();
	}

	@Test(dataProvider = "testData")
	public void validateSearchError(HashMap<String, String> testData) {
		if (hp.closepopupDisplayed()) {
			hp.closepopup();
		}
		hp.islogodisplayed();
		hp.navbarMenucount(9);
		hp.selectFromCity(testData.get("source"));
		hp.selectToCity(testData.get("destination"));
		hp.isCalenderDisplayed();
		hp.selectDate(testData.get("date1"));
		hp.selectTraveller(2, 2);
		hp.selectTravelClass(testData.get("TravelClass"));
		hp.submitTravellerInfo();
		hp.selectCategory(testData.get("PassengerType2"));
		fs = hp.searchflight();
		hp.validateErrorMessage();
	}

	@Test(dataProvider = "testData")
	public void validateSameCityErrorMessage(HashMap<String, String> testData) {
		if (hp.closepopupDisplayed()) {
			hp.closepopup();
		}
		hp.islogodisplayed();
		hp.navbarMenucount(9);
		hp.selectFromCity(testData.get("source"));
		hp.selectToCity(testData.get("source"));
		hp.validateSameCityErrorMessage();
	}

	@AfterMethod
	public void closeBrowser() throws IOException {
		driver.close();
	}

	@DataProvider(name = "testData")
	public Object[][] getData() {
		// Create a HashMap for each set of test data
		HashMap<String, String> data1 = new HashMap<String, String>();
		data1.put("source", "Nagpur");
		data1.put("destination", "Bengaluru");
		data1.put("date1", "15th November 2024");
		data1.put("date2", "15th November");
		data1.put("TravelClass", "Business");
		data1.put("PassengerType1", "Regular");
		data1.put("PassengerType2", "Senior Citizen");
		
		HashMap<String, String> data2 = new HashMap<String, String>();
		data2.put("source", "Nagpur");
		data2.put("destination", "Mumbai");
		data2.put("date1", "15th December 2024");
		data2.put("date2", "15th December");
		data2.put("TravelClass", "Business");
		data2.put("PassengerType1", "Regular");
		data2.put("PassengerType2", "Senior Citizen");


		// Return the HashMaps as Object arrays
		return new Object[][] { { data1},{data2} };
	}
	
	

}
