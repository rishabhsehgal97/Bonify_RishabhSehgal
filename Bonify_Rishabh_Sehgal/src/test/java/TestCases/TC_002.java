package TestCases;

import java.io.IOException;

import org.openqa.selenium.*;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.LoginFlow;

public class TC_002 extends BaseClass {

	LoginFlow LF;


	@Test(priority = 1)
	public void CoockiesPopupAccept() throws InterruptedException {

		logger.info("Bonify Landingpage is opened");
		Thread.sleep(5000);

		JavascriptExecutor js = (JavascriptExecutor) driver;

		WebElement acceptButton = (WebElement) js.executeScript(
				"const shadowHost = document.querySelector('#usercentrics-root');" + "if (!shadowHost) return null;"
						+ "const shadowRoot = shadowHost.shadowRoot;" + "if (!shadowRoot) return null;"
						+ "return shadowRoot.querySelector(\"button[data-testid='uc-accept-all-button']\");");

		if (acceptButton != null) {
			acceptButton.click();
			System.out.println("Clicked on 'ZUSTIMMEN & WEITER' button.");
		} else {
			System.out.println("Accept button not found.");
		}
		logger.info("Cookies Block Handeled");

	}

	@Test(priority = 2)
	public void ClickingLoginCTA() throws InterruptedException {

		LoginFlow LF = new LoginFlow(driver);
		LF.ClickEinloggenCTA();
		logger.info("Click on 'Login' on top Header");

		driver.get(
				"https://sso.bonify.de/oauth2/authorize?client_id=c7c95483-c023-4cc6-9e99-9f5aaabcff29&redirect_uri=https%3A%2F%2Fmy.bonify.de%2Fcallback&response_type=code&scope=openid%20profile&state=0d039712e11d44ce994a8e987e8ae0db&code_challenge=ZC3IiERmZuCMHeRKR1yxqfexk4EItJe-AhXeD0kSCLk&code_challenge_method=S256&response_mode=query");
		Thread.sleep(2000);

	}

	@Test(priority = 3)
	public void RepeatCoockiesPopupAccept() throws InterruptedException {
		CoockiesPopupAccept();
		logger.info("Cookies Block Handeled again in Login Page");

	}

	@Test(priority = 4)
	public void LFieldValidation() throws InterruptedException, IOException {

		LoginFlow LF = new LoginFlow(driver);

		Thread.sleep(2000);
		LF.EnterLEmailAdresseField(LEmailAddress);
		logger.info("Entered EmailAddress on Login Page");
		LF.EnterLPasswortField(LPassword);
		logger.info("Entered Password on Login Page");
		LF.ClickLSubmitButton();
		logger.info("Clicked on Submit Button on Login Page");
		Thread.sleep(2000);

		if(!LF.LAlretWrongCredentialsMsg())
		{
			Assert.assertTrue(true);
			logger.info("Login Test (TC_002) PASSED");
			captureScreen(driver,"Passed Login Test");

		}
		else
		{
			captureScreen(driver,"Failed Login Test");
			Assert.assertTrue(false);
			logger.info("Login test FAILED");
		}


	}
}
