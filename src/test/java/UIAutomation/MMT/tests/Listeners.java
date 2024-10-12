package UIAutomation.MMT.tests;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGListener;
import org.testng.ITestResult;
import org.testng.internal.annotations.IListeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import UIAutomation.MMT.Resources.BaseTest;
import UIAutomation.MMT.Resources.ExtentReport;

public class Listeners extends BaseTest implements ITestListener {
	//we need to declare all objects/variables outside the method so that it can be accesible from other method
	ExtentTest test;
	ExtentReports extent=ExtentReport.GetExtentreport();
	ThreadLocal<ExtentTest> extenttest=new ThreadLocal<ExtentTest>();
	
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		//as result object has all information aboout test we will fetch the test name using report argumrnt
		
		 test=extent.createTest(result.getMethod().getMethodName());
		
		 //we need to create threadlocal class to keep object thread safe for parallel execution of test cases
		  extenttest.set(test);
		
		
	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		
		//we'll call threadlocal class object instead of test so that mthod won't override
		
		extenttest.get().log(Status.PASS, "test passed" );//on test success it will 
	}

	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		//screenshot on test failure
		extenttest.get().fail(result.getThrowable()); //on test failure it will give throwed exception from result
				WebDriver driver=null; //declaring null driver
				
				String Testname=result.getMethod().getMethodName();
				
				try {
					
					//here we are trying first to get actual class then instance of that class i.e real class then u will try to retrieve the fiels
					//we require driver so we'll add driver & then the instance which gets crested after running test & cast it as (webdriver)result
					
					driver=(WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
				
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				try {
					//we'll call threadlocal class object to print screenshot in the report
					//adding getscreenshot method as a path as it will return destination
					
					extenttest.get().addScreenCaptureFromPath(getscreenshot(Testname,driver), result.getMethod().getMethodName());
			
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
		
		
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		extent.flush();//at the end of the test we need to flush extent object
	}

}
