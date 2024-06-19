package testautomation;

import org.testng.AssertJUnit;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testautomation.pageObjects.android.CartPage;
import testautomation.pageObjects.android.ProductCatalogue;
import testautomation.testUtils.AndroidBaseTest;

//This test is repetition of eCommerce_E2E_tc for some reason, please ignore
public class eCommerce_E2E_tc2 extends AndroidBaseTest {
	
	@Test
	public void fillForm_ErrorValidation() throws InterruptedException {
		formPage.clearNameField(); // not necessary till now will be removed in production
		formPage.submitForm();
		String toastMessage = formPage.getErrorMessage();
		AssertJUnit.assertEquals(toastMessage, "nPlease enter your name"); //intentionally failed
	}

	@Test(dataProvider = "getData", groups= {"Smoke"})
	public void fillForm_PositiveFlow(String name, String gender, String country) throws InterruptedException {
		formPage.setNameField(name);
		formPage.setGender(gender);
		formPage.setCountrySelection(country);
		formPage.submitForm();
		// validate form submitted without any error.
		AssertJUnit.assertTrue(formPage.submitFormValidation().size() < 1);
		formPage.back();
	}

	@Test(groups= {"Smoke"})
	public void totalAmountMatching() throws InterruptedException {
		formPage.setNameField("Testing");
		ProductCatalogue productCatalogue = formPage.submitForm();
		productCatalogue.addItemToCartByIndex(0);
		productCatalogue.addItemToCartByIndex(0);
		String productTitle1 = productCatalogue.getProductTitle(0);
		String productTitle2 = productCatalogue.getProductTitle(1);
		CartPage cartPage = productCatalogue.goToCartPage();

		System.out.println("product1: " + productTitle1 + "");
		System.out.println("product2: " + productTitle2 + "");

		String actualCartProductTitle1 = cartPage.getAddedCartItemTitle(0);
		String actualCartProductTitle2 = cartPage.getAddedCartItemTitle(1);
		AssertJUnit.assertEquals(productTitle1, actualCartProductTitle1);
		AssertJUnit.assertEquals(productTitle2, actualCartProductTitle2);

		double totalPrice = cartPage.getTotalPrice();
		double displayedTotalPrice = cartPage.getDisplayAmount();
		AssertJUnit.assertEquals(totalPrice, displayedTotalPrice);

		cartPage.setCheckbox();
		String termsTitle = cartPage.getTermsTitle();
		AssertJUnit.assertEquals(termsTitle, "Terms Of Conditions");
		cartPage.closeTermsPopup();
		cartPage.submitOrder();

		/*
		 * Thread.sleep(5000); // appium --allow-insecure chromedriver_autodownload
		 * Set<String> contexts = driver.getContextHandles(); for (String contextName :
		 * contexts) { System.out.println(contextName); }
		 * 
		 * driver.context("WEBVIEW_com.androidsample.generalstore");
		 * driver.findElement(By.name("q")).sendKeys("S G N Sabir");
		 * driver.findElement(By.name("q")).sendKeys(Keys.ENTER); driver.pressKey(new
		 * KeyEvent(AndroidKey.BACK)); driver.context("NATIVE_APP");
		 */

	}

	// ACS->
	@DataProvider
	public Object[][] getData() {
		return new Object[][] { { "Tester", "female", "Argentina" }, { "Testing", "male", "Brazil" } };
	}

}
