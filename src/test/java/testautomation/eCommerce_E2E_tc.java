package testautomation;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.AssertJUnit;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testautomation.pageObjects.android.CartPage;
import testautomation.pageObjects.android.ProductCatalogue;
import testautomation.testUtils.AndroidBaseTest;

public class eCommerce_E2E_tc extends AndroidBaseTest {

	@Test
	public void fillForm_ErrorValidation() throws InterruptedException {
		formPage.clearNameField(); // not necessary till now will be removed in production
		formPage.submitForm();
		String toastMessage = formPage.getErrorMessage();
		AssertJUnit.assertEquals(toastMessage, "Please enter your name");
	}

	@Test(dataProvider = "hashMapData")
	public void fillForm_PositiveFlow(HashMap<String, String> input) throws InterruptedException {
		formPage.setNameField(input.get("name"));
		formPage.setGender(input.get("gender"));
		formPage.setCountrySelection(input.get("country"));
		formPage.submitForm();
		// validate form submitted without any error.
		AssertJUnit.assertTrue(formPage.submitFormValidation().size() < 1);
		formPage.back();
	}

	@Test
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

	}

//	@BeforeMethod
//	public void preSetup() {
//		formPage.setActivity();
//	}

	// ACS-> we can use this when small amount of data
	@DataProvider
	public Object[][] getData() {
		return new Object[][] { { "Tester", "female", "Argentina" }, { "Testing", "male", "Brazil" } };
	}

	// Using HashMap<-> convert json to Json string -> Json string to HashMap ->
	// HashMap to Test
	@DataProvider
	public Object[][] hashMapData() throws IOException {
		List<HashMap<String, String>> data = getJsonData(
				System.getProperty("user.dir") + "\\src\\test\\java\\testautomation\\testData\\eCommerce.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}

}
