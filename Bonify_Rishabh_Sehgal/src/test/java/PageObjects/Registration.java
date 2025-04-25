package PageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Registration {

	WebDriver ldriver;
	JavascriptExecutor js;

	public Registration(WebDriver rdriver) {
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}

	/******* FindBy *******/

	@FindBy(xpath = "//a[@data-testid='register-button']//button")
	WebElement NewToBoinfyCTA;

	@FindBy(xpath = "//input[@id='user_email']")
	WebElement REmailAdresseField;

	@FindBy(xpath = "//input[@id='user_password']")
	WebElement RPasswortErstellenField;

	@FindBy(xpath = "//div[@class='consent-checkbox-text']")
	WebElement CheckboxRegistration;

	@FindBy(xpath = "//input[@id='form-submit']")
	WebElement RSubmitButton;

	@FindBy(xpath = "//div[@class='password-validation-wrapper']")
	WebElement RWrongCredentialsMsg;
	
	@FindBy(xpath = "//h2[@data-testid='CONFIRM_EMAIL_TITLE']")
	WebElement EmailVerificationPage;

	/******* Methods *******/

	public void ClickNewToBoinfy() {
		NewToBoinfyCTA.click();
	}

	public void EnterREmailAdresseField(String REmailAddress) {
		REmailAdresseField.sendKeys(REmailAddress);
	}

	public void EnterRPasswortErstellenField(String RPassword) {
		RPasswortErstellenField.sendKeys(RPassword);
	}

	public void ClickCheckboxRegistration() {
		CheckboxRegistration.click();
	}

	public void ClickRSubmitButton() {
		RSubmitButton.click();
	}

	public boolean RAlretWrongCredentialsMsg() {
		try {
			return RWrongCredentialsMsg.isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}
	}
	
	public String CheckEmailVerificationPage() 
	{
		return EmailVerificationPage.getText();
	}

}
