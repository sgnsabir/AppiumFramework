package testautomation.pageObjects.android;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import testautomation.utils.AndroidActions;

public class FormPage extends AndroidActions {
	AndroidDriver driver;

	public FormPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(id = "com.androidsample.generalstore:id/nameField")
	private WebElement nameField;

	@AndroidFindBy(xpath = "//*[@text='Female']")
	private WebElement femaleOption;

	@AndroidFindBy(xpath = "//*[@text='Male']")
	private WebElement maleOption;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/spinnerCountry")
	private WebElement countryListButton;

	@AndroidFindBy(uiAutomator = "new UiScrollable(new UiSelector());")
	private WebElement scrollDown;

	@AndroidFindBy(className = "android.widget.Button")
	private WebElement submitButton;

	@AndroidFindBy(xpath = "(//android.widget.Toast)[1]")
	private WebElement errorMessage;

	@AndroidFindBy(xpath = "(//android.widget.Toast)[1]")
	private List<WebElement> toastValidation;

	public List<WebElement> submitFormValidation() {
		return toastValidation;
	}

	public String getErrorMessage() {
		return errorMessage.getText();
	}

	public void setNameField(String name) {
		nameField.sendKeys(name);
		driver.hideKeyboard();
	}

	public void clearNameField() {
		nameField.clear();
	}

	public void setGender(String gender) {
		if (gender.contains("female"))
			femaleOption.click();
		else
			maleOption.click();
	}

	public void setCountrySelection(String countryName) {
		countryListButton.click();
		scrollToText(countryName);
		driver.findElement(By.xpath("//*[@text='" + countryName + "']")).click();
	}

	public ProductCatalogue submitForm() {
		submitButton.click();
		return new ProductCatalogue(driver);
	}
	public void back() {
		driver.pressKey(new KeyEvent(AndroidKey.BACK));
	}
//This code is not working, giving alternate
//	public void setActivity() {
//		// adb shell dumpsys window | grep -E 'mCurrentFocus'
//		((JavascriptExecutor) driver).executeScript("mobile: startActivity", ImmutableMap.of("intent",
//				"com.androidsample.generalstore/com.androidsample.generalstore.MainActivity"));
//	}

}
