package UIAutomation.MMT.Resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxDriverLogLevel;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public WebDriver driver;
	public Properties prop;

	public WebDriver InitializeDriver() throws IOException {

		prop = new Properties();

		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\UIAutomation\\MMT\\Resources\\data.properties");

		prop.load(fis);

	    String Browsername=prop.getProperty("browser");

		//String Browsername = System.getProperty("browser"); // it will drive the browser from mabven command
		switch (Browsername.toLowerCase()) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().arch64().setup();
			FirefoxOptions options = new FirefoxOptions();
			options.setLogLevel(FirefoxDriverLogLevel.TRACE); // Detailed logging
			driver = new FirefoxDriver(options);
			break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;
		default:
			throw new IllegalArgumentException("Invalid browser: " + Browsername);
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		return driver;

	}

	public String getscreenshot(String Testcasename, WebDriver driver) throws IOException {
		// after creating driver instance in testnglistener this "driver" will points to
		// that driver declared in an argument
		TakesScreenshot ts = (TakesScreenshot) driver;

		File src = ts.getScreenshotAs(OutputType.FILE);

		// here we are giving path to store the screen shot
		// user.dir means path upto the project name
		// Testcasename we are deriving from listener class
		String destinationfile = System.getProperty("user.dir") + "\\reports\\" + Testcasename + ".png";

		FileUtils.copyFile(src, new File(destinationfile));

		return destinationfile; // we are returning destination file path here so that we can use it in
								// "addScreenCaptureFromPath" method
	}

}
