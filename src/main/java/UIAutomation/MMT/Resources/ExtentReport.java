package UIAutomation.MMT.Resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReport {

	static ExtentReports extent;
	
	//we are declaring method as static 
	public static ExtentReports GetExtentreport() {
		
		String path=System.getProperty("user.dir")+"\\reports\\index.html";

		ExtentSparkReporter reporter=new ExtentSparkReporter(path);

		reporter.config().setReportName("Web Automation Result");

		reporter.config().setDocumentTitle("Test Results");

		extent=new ExtentReports();

		extent.attachReporter(reporter);

		extent.setSystemInfo("SDET", "Rushi B");
		
		return extent; //we need to return extent report through method
	}
}
 