package UIAotpmation.MMT.PageObject;

import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.util.ArrayList;
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



public class TrainSearchResult {

	WebDriver driver;

	public TrainSearchResult(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[@class='FilterCard_HeadingText__JgVS0' and contains(text(),'Quick Filters')]")
	private WebElement QuickfilterHeading;
	
	@FindBy(xpath = "//img[@alt='tgBanner']")
	private WebElement SearchPageBanner;

	@FindBy(xpath = "//span[contains(text(), 'Quick Filters')]/parent::div/following-sibling::div/ul/li/label/div/p")
	private List<WebElement> QuickfilterOptions;

	@FindBy(xpath = "//span[contains(text(), 'Ticket Types')]/parent::div/following-sibling::div/ul/li/label/div/p")
	private List<WebElement> TicketTypes;

	@FindBy(xpath = "//ul[@class='SortBy_sortTabsWrapper__xhdWo']/li/div/p")
	private List<WebElement> SortByOptions;

	@FindBy(xpath = "//div[@class='ListingCard_listItem__9YPIT']/p")
	private List<WebElement> TrainNames;

	@FindBy(xpath = "p[contains(text(), 'Sevagram Exp')]/ancestor::div[@class='ListingCard_listingSectionList__lgFWg']/following-sibling::div/div[2]/div//p[contains(text(),\"2A\")]\r\n"
			+ "")
	private WebElement SeatType;
	
	
	public void verifysearchpage() {
		Util.fluentWaitForElementVisible(driver, QuickfilterHeading);
	}
	
	
	public void printquickfilteroptions() {
		
		for(WebElement wb:QuickfilterOptions) {
			
			System.out.println(wb.getText());
			
		}
	}
	
public void verifytickettypes(String type1, String type2) {
		
	List<String> lst = new ArrayList<String>();
	lst.add(type1);
	lst.add(type2);
		for(int i=0;i<TicketTypes.size();i++) {
			
			Assert.assertEquals(lst.get(i),TicketTypes.get(i).getText());
			
		}
	}

public void verifySortByoptions(int count) {
	
	Assert.assertEquals(count, SortByOptions.size());
	for(WebElement wb:SortByOptions) {
		System.out.println(wb.getText());
	}
	
}

public TravellerInfoPage selectTrain(String train, String coach) {
	    int i=0;
		while(TrainNames.get(i).getText().equals(train)) {
			Util.scrollByPixels(driver, 400);
			i++;
		}
			driver.findElement(By.xpath("//p[contains(text(), '"+train+"')]/ancestor::div[@class='ListingCard_listingSectionList__lgFWg']/following-sibling::div/div//p[contains(text(),'"+coach+"')]")).click();
			
			TravellerInfoPage tip = new TravellerInfoPage(driver);
			
			return tip;
	
	}
	
}


