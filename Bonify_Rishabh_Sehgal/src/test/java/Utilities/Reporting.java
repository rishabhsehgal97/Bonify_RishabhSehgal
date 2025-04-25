package Utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

import TestCases.BaseClass;

public class Reporting extends TestListenerAdapter {
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest logger;

	public void onStart(ITestContext testContext) {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); // Time stamp
		String repName = "Test-Report-" + timeStamp + ".html";

		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/" + repName);// specify location of the report
																											

		htmlReporter.loadXMLConfig(System.getProperty("user.dir") + "/extent-config.xml");

		extent = new ExtentReports();

		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host name", "localhost");
		extent.setSystemInfo("Environemnt", "QA");
		extent.setSystemInfo("user", "Rishabh Sehgal");

		htmlReporter.config().setDocumentTitle("JdMart Project"); // Tile of report
		htmlReporter.config().setReportName("Functional Test Automation Report"); // name of the report
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP); // location of the chart
		htmlReporter.config().setTheme(Theme.DARK);
	}

//	public void onTestSuccess(ITestResult tr) {
//		logger = extent.createTest(tr.getName()); // create new entry in the report
//		logger.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN)); // send the passed
//																							// information to the report																					// with GREEN color
//																							// highlighted
//	}

//	public void onTestFailure(ITestResult tr) {
//		logger = extent.createTest(tr.getName()); // create new entry in the report
//		logger.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED)); // send the passed information
//																							// to the report with GREEN
//																							// color highlighted
//
//		String screenshotPath = System.getProperty("user.dir") + "/Screenshots/" + tr.getName() + ".png";
//
//		File f = new File(screenshotPath);
//
//		if (f.exists()) {
//			try {
//				logger.fail("Screenshot is below:" + logger.addScreenCaptureFromPath(screenshotPath));
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//
//	}
	
	@Override
	public void onTestSuccess(ITestResult tr) {
	    logger = extent.createTest(tr.getName()); // create new entry in the report
	    logger.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));

	    try {
	        // Get driver from test class
	        WebDriver driver = ((BaseClass) tr.getInstance()).getDriver();

	        // Capture screenshot
	        BaseClass.captureScreen(driver, tr.getName());

	        // Screenshot path
	        String screenshotPath = System.getProperty("user.dir") + "/Screenshots/" + tr.getName() + ".png";
	        File f = new File(screenshotPath);

	        if (f.exists()) {
	            logger.pass("Screenshot is below:",
	                MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	
	@Override
	public void onTestFailure(ITestResult tr) {
	    logger = extent.createTest(tr.getName()); // Create a new entry in the report
	    logger.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED));

	    // Try capturing the screenshot here directly
	    try {
	        // Get driver instance from the test class
	        WebDriver driver = ((BaseClass) tr.getInstance()).getDriver();

	        // Call captureScreen
	        captureScreen(driver, tr.getName());

	        // Now build screenshot path
	        String screenshotPath = System.getProperty("user.dir") + "/Screenshots/" + tr.getName() + ".png";
	        File f = new File(screenshotPath);

	        if (f.exists()) {
	            // Attach screenshot properly using MediaEntityBuilder
	            logger.fail("Screenshot is below:",
	                MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}


	private void captureScreen(WebDriver driver, String name) {
		// TODO Auto-generated method stub
		
	}

	public void onTestSkipped(ITestResult tr) {
		logger = extent.createTest(tr.getName()); // create new entry in the report
		logger.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE));
	}

	public void onFinish(ITestContext testContext) {
		extent.flush();
	}
}
