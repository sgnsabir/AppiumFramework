package testautomation.pageObjects.android;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
//Just to demonstrate test on Mobile browser I used multiple pages in single class, ideally it is not the best practice.
public class AmazonMobileBrowserPages{
	AppiumDriver driver;

	public AmazonMobileBrowserPages(AppiumDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

//This is a high level overview of different path to to sign up flow

	@FindBy(css = "input[data-action-type='DISMISS']")
	private WebElement popupDismiss;

	@FindBy(css = "input[data-action-type='SELECT_LOCATION']")
	private WebElement changeAddress;

	@FindBy(css = "[aria-label*='your address']")
	private WebElement signIn;

	@FindBy(xpath = "//*[text()='Create account']")
	private WebElement createAccount;

	@FindBy(id = "ap_customer_name")
	private WebElement nameField;

	@FindBy(id = "ap_email")
	private WebElement emailField;

	@FindBy(id = "ap_password")
	private WebElement passwordField;

	@FindBy(id = "ap_password_check")
	private WebElement passwordCheckField;

	@FindBy(id = "continue")
	private WebElement continueButton;

	@FindBy(xpath = "(//h1)[1]")
	private WebElement pageTitle;

	@FindBy(id = "cvf-input-code")
	private WebElement insertCode;

	@FindBy(css = "[aria-label*='OTP Button']")
	private WebElement createAccountButton;

	@FindBy(xpath = "(//div[contains(text(),'Invalid OTP.')])[1]")
	private WebElement errorMessage;

	public void visitAmazon() throws InterruptedException {
		driver.get("https://www.amazon.com/");
		Thread.sleep(2000);
	}

	public void dismissPopup() {
		popupDismiss.click();
	}

	public void selectChangAddress() throws InterruptedException {
		changeAddress.click();
		Thread.sleep(1000);
	}

	public void getSignUpForm() throws InterruptedException {
		signIn.click();
		Thread.sleep(1000);
		createAccount.click();
		Thread.sleep(1000);
	}

	public void fillName(String name) {
		nameField.sendKeys(name);
	}

	public void fillEmail(String email) {
		emailField.sendKeys(email);

	}

	public void fillPassword(String password) {
		passwordField.sendKeys(password);
		// passwordCheckField.sendKeys("Muttaqi@143");

	}

	public void submitForm() throws InterruptedException {
		continueButton.click();
		Thread.sleep(1000);
	}

	public String getVerifyEmailPageTitle() {
		return pageTitle.getText();
	}

	public String getErrorMessage() throws InterruptedException {
		insertCode.sendKeys("123456");
		createAccountButton.click();
		Thread.sleep(1000);
		return errorMessage.getText();
	}

}
