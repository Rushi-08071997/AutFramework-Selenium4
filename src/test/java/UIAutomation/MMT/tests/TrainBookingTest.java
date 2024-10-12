package UIAutomation.MMT.tests;

import java.io.IOException;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import UIAotpmation.MMT.PageObject.FlightSearch;
import UIAotpmation.MMT.PageObject.HomePage;
import UIAotpmation.MMT.PageObject.TrainReservationPage;
import UIAotpmation.MMT.PageObject.TrainReservePaymentPage;
import UIAotpmation.MMT.PageObject.TrainSearchResult;
import UIAotpmation.MMT.PageObject.TravellerInfoPage;
import UIAutomation.MMT.Resources.BaseTest;

public class TrainBookingTest extends BaseTest {
	WebDriver driver;
	HomePage hp;
	TrainReservationPage tp;
	TrainSearchResult tsr;
	TravellerInfoPage tip;
	TrainReservePaymentPage trp;
	

	@BeforeMethod
	public void initializedriver() throws IOException {
		driver = InitializeDriver();
		hp = new HomePage(driver);
		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();
	}

	@Test(dataProvider = "testData")
	public void trainReservationE2ETest(HashMap<String, String> testData) throws InterruptedException {
		hp.closepopup();
		hp.islogodisplayed();
		hp.navbarMenucount(9);
	    tp=hp.clickOnTrains();
		tp.selectFromCity(testData.get("source"));
		tp.selectToCity(testData.get("destination"));
		tp.selectDate(testData.get("date"));
		tp.selectTravelClass(testData.get("TravelClass"));
		tsr=tp.searchTrain();
		tsr.verifysearchpage();
		tsr.printquickfilteroptions();
		tsr.verifytickettypes(testData.get("TicketType1"), testData.get("TicketType2"));
		tsr.verifySortByoptions(5);
		tip=tsr.selectTrain(testData.get("TrainName"), testData.get("Coach"));
		tip.validatePageHeading();
		tip.validateTraiName(testData.get("TrainName"));
		tip.validateCoach(testData.get("Coach"));
		tip.getAvailabilityStatus();
		tip.validateBoardingStation(testData.get("BoardingStation"));
		tip.clickOnAddTraveller();
		tip.addTravelllerInfo(testData.get("Name"), testData.get("Age"),testData.get("Gender"),testData.get("Country"), testData.get("BirthPreference"));
		tip.scrollToAndClickAddUsername();
		tip.verifyEnterUsernamePopup();
		tip.addUsername(testData.get("UserName"));
		tip.scrollToAndAddEmailID(testData.get("EmailID"));
		tip.addMobNumber(testData.get("MobileNumber"));
		tip.scrollToAddState();
		tip.selectState(testData.get("state"));
		tip.clickOnCheckbox();
		tip.validateTicketPrice(testData.get("TicketPrice"));
		tip.submitInfo();
		trp=tip.toCancelTripGurantee();
		Thread.sleep(5000);
		trp.PaymentPageDisplayed();
		trp.verifyPaymentMethods(7);
	
		
	}
	
	@Test(dataProvider = "testData")
	public void validateErrorMessageforSameSourceAndDest(HashMap<String, String> testData) {
		hp.closepopup();
		hp.islogodisplayed();
		hp.navbarMenucount(9);
	    tp=hp.clickOnTrains();
		tp.selectFromCity(testData.get("source"));
		tp.selectToCity(testData.get("source"));
		tp.selectDate(testData.get("date"));
		tp.selectTravelClass(testData.get("TravelClass"));
		tsr=tp.searchTrain();
		tp.validateErrorMessage();
	}
	
	@Test(dataProvider = "testData")
	public void validateAddTravellerErrorMessage(HashMap<String, String> testData) throws InterruptedException {
		hp.closepopup();
		hp.islogodisplayed();
		hp.navbarMenucount(9);
	    tp=hp.clickOnTrains();
		tp.selectFromCity(testData.get("source"));
		tp.selectToCity(testData.get("destination"));
		tp.selectDate(testData.get("date"));
		tp.selectTravelClass(testData.get("TravelClass"));
		tsr=tp.searchTrain();
		tsr.verifysearchpage();
		tsr.printquickfilteroptions();
		tsr.verifytickettypes(testData.get("TicketType1"), testData.get("TicketType2"));
		tsr.verifySortByoptions(5);
		tip=tsr.selectTrain(testData.get("TrainName"), testData.get("Coach"));
		tip.validatePageHeading();
		tip.validateTraiName(testData.get("TrainName"));
		tip.validateCoach(testData.get("Coach"));
		tip.getAvailabilityStatus();
		tip.validateBoardingStation(testData.get("BoardingStation"));
		tip.submitInfo();
		tip.verifyTravellererorMessage(testData.get("TravellerErrorMessage"));
	}
	
	@Test(dataProvider = "testData")
	public void validateUsernameErrorMessage(HashMap<String, String> testData) throws InterruptedException {
		hp.closepopup();
		hp.islogodisplayed();
		hp.navbarMenucount(9);
	    tp=hp.clickOnTrains();
		tp.selectFromCity(testData.get("source"));
		tp.selectToCity(testData.get("destination"));
		tp.selectDate(testData.get("date"));
		tp.selectTravelClass(testData.get("TravelClass"));
		tsr=tp.searchTrain();
		tsr.verifysearchpage();
		tsr.printquickfilteroptions();
		tsr.verifytickettypes(testData.get("TicketType1"), testData.get("TicketType2"));
		tsr.verifySortByoptions(5);
		tip=tsr.selectTrain(testData.get("TrainName"), testData.get("Coach"));
		tip.validatePageHeading();
		tip.validateTraiName(testData.get("TrainName"));
		tip.validateCoach(testData.get("Coach"));
		tip.getAvailabilityStatus();
		tip.validateBoardingStation(testData.get("BoardingStation"));
		tip.clickOnAddTraveller();
		tip.addTravelllerInfo(testData.get("Name"), testData.get("Age"),testData.get("Gender"),testData.get("Country"), testData.get("BirthPreference"));
		tip.submitInfo();
		tip.verifyUsernameErrorMessage(testData.get("UsernameErrorMessage"));
	}
	
	@Test(dataProvider = "testData")
	public void validateEmailErrorMessage(HashMap<String, String> testData) throws InterruptedException {
		hp.closepopup();
		hp.islogodisplayed();
		hp.navbarMenucount(9);
	    tp=hp.clickOnTrains();
		tp.selectFromCity(testData.get("source"));
		tp.selectToCity(testData.get("destination"));
		tp.selectDate(testData.get("date"));
		tp.selectTravelClass(testData.get("TravelClass"));
		tsr=tp.searchTrain();
		tsr.verifysearchpage();
		tsr.printquickfilteroptions();
		tsr.verifytickettypes(testData.get("TicketType1"), testData.get("TicketType2"));
		tsr.verifySortByoptions(5);
		tip=tsr.selectTrain(testData.get("TrainName"), testData.get("Coach"));
		tip.validatePageHeading();
		tip.validateTraiName(testData.get("TrainName"));
		tip.validateCoach(testData.get("Coach"));
		tip.getAvailabilityStatus();
		tip.validateBoardingStation(testData.get("BoardingStation"));
		tip.clickOnAddTraveller();
		tip.addTravelllerInfo(testData.get("Name"), testData.get("Age"),testData.get("Gender"),testData.get("Country"), testData.get("BirthPreference"));
		tip.scrollToAndClickAddUsername();
		tip.verifyEnterUsernamePopup();
		tip.addUsername(testData.get("UserName"));
		tip.submitInfo();
		tip.verifyEmailErrorMessage(testData.get("EmailErrorMessage"));
	}
	
	
	
	@AfterMethod
	public void closeBrowser() throws IOException {
		driver.close();
	}
	
	
	 @DataProvider(name = "testData")
	    public Object[][] getData() {
	        // Creating a HashMap for test data q
	        HashMap<String, String> data1 = new HashMap<String, String>();
	        data1.put("source", "Nagpur");
	        data1.put("destination", "Bengaluru");
	        data1.put("date", "15th November 2024");
	        data1.put("TravelClass", "Third AC");
	        data1.put("TicketType1", "Free Cancellation");
	        data1.put("TicketType2", "Trip Gurantee");
	        data1.put("TrainName", "Sbc Rajdhani");
	        data1.put("Coach", "3A");
	        data1.put("BoardingStation", "NAGPUR (NGP) - 9:30 AM (15 Nov)");
	        data1.put("Name", "Rushikesh");
	        data1.put("Age", "27");
	        data1.put("Gender", "Male");
	        data1.put("Country", "India");
	        data1.put("BirthPreference", "Side Lower");
	        data1.put("UserName", "RUSHIKESHBANARASE");
	        data1.put("EmailID", "rushikeshbanarase@gmail.com");
	        data1.put("MobileNumber", "8793209457");
	        data1.put("state", "Maharashtra");
	        data1.put("TicketPrice", "2900");
	        data1.put("PaymentPageMessage", "Hey,\r\n"
	        		+ "	        You are viewing this booking at the best price");
	        data1.put("TravellerErrorMessage", "Please select a traveller");
	        data1.put("UsernameErrorMessage", "Please enter IRCTC Username to continue");
	        data1.put("EmailErrorMessage", "Please enter correct email");
	        
	        HashMap<String, String> data2 = new HashMap<String, String>();
	        data2.put("source", "Amravati");
	        data2.put("destination", "Mumbai");
	        data2.put("date", "15th November 2024");
	        data2.put("TravelClass", "Third AC");
	        data2.put("TicketType1", "Free Cancellation");
	        data2.put("TicketType2", "Trip Gurantee");
	        data2.put("TrainName", "Ami Csmt Sf Exp");
	        data2.put("Coach", "3A");
	        data2.put("BoardingStation", "AMRAVATI (AMI) - 7:10 PM (15 Nov)");
	        data2.put("Name", "Rushikesh");
	        data2.put("Age", "27");
	        data2.put("Gender", "Male");
	        data2.put("Country", "India");
	        data2.put("BirthPreference", "Side Lower");
	        data2.put("UserName", "RUSHIKESHBANARASE");
	        data2.put("EmailID", "rushikeshbanarase@gmail.com");
	        data2.put("MobileNumber", "8793209457");
	        data2.put("state", "Maharashtra");
	        data2.put("TicketPrice", "995");
	        data2.put("PaymentPageMessage", "Hey,\r\n"
	        		+ "	        You are viewing this booking at the best price");
	        data2.put("TravellerErrorMessage", "Please select a traveller");
	        data2.put("UsernameErrorMessage", "Please enter IRCTC Username to continue");
	        data2.put("EmailErrorMessage", "Please enter correct email");
	       
	        // Return the HashMaps
	        return new Object[][] {
	                { data1 }, { data2 }
	        };
	    }


}
