package TestCases;


import java.io.IOException;

import org.openqa.selenium.*;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;
import PageObjects.Registration;

public class TC_001 extends BaseClass {
	
	
    Registration RP;


    @Test(priority = 1)
    public void CoockiesPopupAccept() throws InterruptedException {
    	
    	logger.info("Bonify Landingpage is opened");
    	Thread.sleep(5000);
    	
        JavascriptExecutor js = (JavascriptExecutor) driver;

        WebElement acceptButton = (WebElement) js.executeScript(
            "const shadowHost = document.querySelector('#usercentrics-root');" +
            "if (!shadowHost) return null;" +
            "const shadowRoot = shadowHost.shadowRoot;" +
            "if (!shadowRoot) return null;" +
            "return shadowRoot.querySelector(\"button[data-testid='uc-accept-all-button']\");"
        );

        if (acceptButton != null) {
            acceptButton.click();
            System.out.println("Clicked on 'ZUSTIMMEN & WEITER' button.");
        } else {
            System.out.println("Accept button not found.");
        }
        
		logger.info("Cookies Block Handeled");


    }
    
    @Test(priority = 2)
    public void ClickingRegistrationCTA() throws InterruptedException {
    	
        RP = new Registration(driver);
        RP.ClickNewToBoinfy();
		logger.info("Click on 'Neu bei Bonify' on top Header");

        driver.get(
				"https://sso.bonify.de/oauth2/register?tenantId=d53c5515-7538-45f1-ac0c-4f7021bb2e62&client_id=c7c95483-c023-4cc6-9e99-9f5aaabcff29&redirect_uri=https%3A%2F%2Fmy.bonify.de%2Fcallback&response_type=code&_gl=1*18p3vp4*_gcl_au*MzgwODE5NDE0LjE3NDUzMTEzOTYuMTQxMzczODE0NS4xNzQ1NDA1MTAyLjE3NDU0MDUxMDI.*_ga*NDc4NzgxMDUwLjE3NDUzMTEzOTc.*_ga_Y16SD8LR07*MTc0NTQ4MDk1My41LjEuMTc0NTQ4MTQwMS41NS4wLjA.");
		Thread.sleep(2000);
    }
    
    @Test(priority = 3)
    public void RepeatCoockiesPopupAccept() throws InterruptedException  {
    	CoockiesPopupAccept();
		logger.info("Cookies Block Handeled again in Registration Page");

    }

    @Test(priority = 4)
    public void RFieldValidation() throws InterruptedException, IOException {
    	
    	Thread.sleep(2000);

        RP = new Registration(driver);
        RP.EnterREmailAdresseField(REmailAddress);
		logger.info("Entered EmailAddress on Registraion Page");
        RP.EnterRPasswortErstellenField(RPassword);
		logger.info("Entered Password on Registraion Page");
        RP.ClickCheckboxRegistration();
		logger.info("Clicked on CheckBox on Registraion Page");
        RP.ClickRSubmitButton();
		logger.info("Clicked on Submit Button on Registraion Page");
        
    	Thread.sleep(2000);
    	
    	if(!RP.RAlretWrongCredentialsMsg())
		{
			Assert.assertTrue(true);
			logger.info("Registration Test (TC_001) PASSED");
			captureScreen(driver,"Passed Registration Test");
		}
		else
		{
			captureScreen(driver,"Failed Registration Test");
			Assert.assertTrue(false);
			logger.info("Registration test FAILED");
		}

    }
}
