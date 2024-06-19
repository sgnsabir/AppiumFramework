package testautomation;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import testautomation.testUtils.AndroidBaseTest;

public class eCommerce_tc_1 extends AndroidBaseTest {

	@Test
	public void fillForm_ErrorValidation() throws InterruptedException {
		formPage.clearNameField();
		formPage.submitForm();
		String toastMessage = formPage.getErrorMessage();
		AssertJUnit.assertEquals(toastMessage, "Please enter your name");
	}

	@Test
	public void fillForm_PositiveFlow() throws InterruptedException {
		formPage.setNameField("Sabir");
		formPage.setGender("female");
		formPage.setCountrySelection("Brazil");
		formPage.submitForm();
		// validate form submitted without any error.
		AssertJUnit.assertTrue(formPage.submitFormValidation().size() < 1);
	}

}
