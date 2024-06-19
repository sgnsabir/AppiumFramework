package testautomation.pageObjects.android;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import testautomation.utils.AndroidActions;

public class CartPage extends AndroidActions {
	AndroidDriver driver;

	public CartPage(AndroidDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(id = "com.androidsample.generalstore:id/productName")
	private List<WebElement> productTitle;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/productPrice")
	private List<WebElement> productPrices;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/totalAmountLbl")
	private WebElement displayedTotalPriceString;

	@AndroidFindBy(className = "android.widget.CheckBox")
	private WebElement checkBox;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/termsButton")
	private WebElement terms;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/alertTitle")
	private WebElement termsTitle;

	@AndroidFindBy(id = "android:id/button1")
	private WebElement acceptButton;

	@AndroidFindBy(id = "com.androidsample.generalstore:id/btnProceed")
	private WebElement completePurchaseButton;

	public String getAddedCartItemTitle(int index) {
		return productTitle.get(index).getText();
	}

	public double getTotalPrice() {
		int productCount = productPrices.size();
		double totalPrice = 0;
		for (int i = 0; i < productCount; i++) {
			String amountString = productPrices.get(i).getText();
			double price = getFormattedAmount(amountString);
			totalPrice = totalPrice + price;
		}
		return totalPrice;
	}

	public Double getDisplayAmount() {
		String toalPriceString = displayedTotalPriceString.getText();
		return getFormattedAmount(toalPriceString);
	}

	public void setCheckbox() {
		checkBox.click();
	}

	public String getTermsTitle() {
		longPressAction(terms);
		return termsTitle.getText();
	}

	public void closeTermsPopup() {
		acceptButton.click();
	}

	public void submitOrder() {
		completePurchaseButton.click();
		// dummyApp
	}

}
