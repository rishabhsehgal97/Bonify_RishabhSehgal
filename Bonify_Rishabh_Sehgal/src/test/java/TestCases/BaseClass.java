//Automated Testing in Java

package TestCases;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
//import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import Utilities.ReadConfig;

public class BaseClass {

	ReadConfig readconfig = new ReadConfig();

	public String baseURL = readconfig.getbaseURL();
	public String LEmailAddress = readconfig.getLEmailAddress();
	public String LPassword = readconfig.getLPassword();
	public String REmailAddress = readconfig.getREmailAddress();
	public String RPassword = readconfig.getRPassword();

	public static WebDriver driver;
	public static Logger logger;

	@Parameters("browser")
	@SuppressWarnings("deprecation")
	@BeforeClass
	public void setup(String br) {

		logger = Logger.getLogger("Bonify");
		PropertyConfigurator.configure("Log4j.properties");

		if (br.equals("firefox")) {
			System.setProperty("webdriver.firefox.driver", readconfig.getFirefoxpath());
			driver = new FirefoxDriver();
		}

		else if (br.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", readconfig.getChromepath());
			driver = new ChromeDriver();
		}

		driver.manage().deleteAllCookies();
		driver.get(baseURL);
		logger.info("********************************************************************************");
		logger.info("Bonify Landingpage is opened");

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);

		driver.switchTo().defaultContent();

	}

	@AfterClass
	public void tearDown() {
		logger.info("********************************************************************************");
		driver.quit();
	}

	public static void captureScreen(WebDriver driver, String tname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "/Screenshots/" + tname + ".png");
		FileUtils.copyFile(source, target);
		System.out.println("Screenshot taken");
	}

	public WebDriver getDriver() {

		return driver;
	}

}
