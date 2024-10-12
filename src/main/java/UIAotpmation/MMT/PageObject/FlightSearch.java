package UIAotpmation.MMT.PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FlightSearch {
	
	WebDriver driver;

	public FlightSearch(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//p[contains(text(), \"NETWORK PROBLEM\")]")
	WebElement NetworkIssue;
	
	public void validateNetworkissue()
	{
		NetworkIssue.isDisplayed();
	}
}
