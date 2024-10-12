package UIAutomation.MMT.Resources;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Util {

	// Method to extract the number from the given string
	public static int extractDay(String dateStr) {
		// Define a regular expression to match the number part (digits) at the start of
		// the string
		Pattern pattern = Pattern.compile("(\\d+)(?:st|nd|rd|th)?\\s+^[a-zA-Z0-9]+$", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(dateStr);

		// If the pattern matches, return the extracted number
		if (matcher.find()) {
			return Integer.parseInt(matcher.group(1)); // Get the first group (the number part)
		} else {
			throw new IllegalArgumentException("Invalid date format");
		}
	}

	public static WebElement waitForElementVisible(WebDriver driver, WebElement element, int timeoutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
		return wait.until(ExpectedConditions.visibilityOf(element));
	}

	// Generic method for waiting until an element is clickable
	public static WebElement waitForElementClickable(WebDriver driver, WebElement locator, int timeoutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
		return wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	public static void scrollByPixels(WebDriver driver, int pixels) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0," + pixels + ")");
	}

	public static WebElement fluentWaitForElementVisible(WebDriver driver, WebElement element) {
		Wait<WebDriver> fluentWait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class);

		return fluentWait.until(ExpectedConditions.visibilityOf(element));

	}

}
