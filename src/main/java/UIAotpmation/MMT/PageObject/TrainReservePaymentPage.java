package UIAotpmation.MMT.PageObject;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import UIAutomation.MMT.Resources.Util;

public class TrainReservePaymentPage {
	
	WebDriver driver;

	public TrainReservePaymentPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//span[contains(text(),'You are viewing this booking at the best price')]")
	private WebElement PaymantPageMessage;
	
	@FindBy(xpath = "//span[(contains(text(),'Payment options'))]/parent::p/following-sibling::ul/li/div/span[1]")
	private List<WebElement> PaymentOptions;
	
	
	public void PaymentPageDisplayed() {
		Util.fluentWaitForElementVisible(driver, PaymantPageMessage);
		PaymantPageMessage.isDisplayed();
	}
	
	public void verifyPaymentMethods(int count) {
		
		String MethodCount = String.valueOf(count);
		String ActualCount = String.valueOf(PaymentOptions.size());
		
		Assert.assertEquals(MethodCount,ActualCount );
		
		Map<Integer,String> hmap=new LinkedHashMap<Integer, String>();
		hmap.put(1, "Credit/Debit/ATM Card");
		hmap.put(2, "UPI Options");
		hmap.put(3, "Book Now Pay Later");
		hmap.put(4, "Net Banking");
		hmap.put(5, "Gift Cards & e-wallets");
		hmap.put(6, "EMI");
		hmap.put(7, "GooglePay");
		
		int i=1;
		for(WebElement wb:PaymentOptions) {
			
			Assert.assertEquals(hmap.get(i), wb.getText());
			i++;	
		}
		
		
	}
	
	
	
	
	
	
	

}

