package PageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginFlow {

	WebDriver ldriver;
	JavascriptExecutor js;

	public LoginFlow(WebDriver rdriver) {
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}
	
	/******* FindBy *******/

	@FindBy(xpath = "//a[@data-testid='login-button']")
	WebElement EinloggenCTA;
	
	@FindBy(xpath = "//input[@id='loginId']")
	WebElement LEmailAdresseField;
	
	@FindBy(xpath = "//input[@id='password']")
	WebElement LPasswort;
	
	@FindBy(xpath = "//input[@id='login-button']")
	WebElement LSubmitButton;
	
	@FindBy(xpath = "//div[contains(@class,'forgot-password')]/following::span[@data-testid='printErrorAlerts-']")
	WebElement LWrongCredentialsMsg;

	/******* Methods *******/

	public void ClickEinloggenCTA() 
	{
		EinloggenCTA.click();
	}

	public void EnterLEmailAdresseField(String LEmailAddress) 
	{
		LEmailAdresseField.sendKeys(LEmailAddress);
	}

	public void EnterLPasswortField(String LPassword) 
	{
		LPasswort.sendKeys(LPassword);
	}


	public void ClickLSubmitButton() 
	{
		LSubmitButton.click();
	}


	public boolean LAlretWrongCredentialsMsg() {
		try {
			return LWrongCredentialsMsg.isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}
	}
	}
	
