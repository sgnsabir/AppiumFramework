/*
 * Since Amazon is a commercial website, so it is not good
 * to run more automation testing on it, this test is incomplete, 
 * just to showcasing executing automation in mobile browser.
 */

package testautomation;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.AssertJUnit;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testautomation.testUtils.MobileBrowserBaseTest;

public class MobileBrowserTest extends MobileBrowserBaseTest {
	@Test(dataProvider = "hashMapData")
	public void signUpThroughChangeAddress(HashMap<String, String> input) throws InterruptedException {
		// adb shell input keyevent 111
		amazon.visitAmazon();
		amazon.selectChangAddress();
		amazon.getSignUpForm();
		amazon.fillName(input.get("name"));
		amazon.fillEmail(input.get("email"));
		amazon.fillPassword(input.get("password"));
		amazon.submitForm();
		String actualText = amazon.getVerifyEmailPageTitle();
		AssertJUnit.assertEquals(actualText, "Verify email address");
		String actualErrorMessage = amazon.getErrorMessage();
		AssertJUnit.assertEquals(actualErrorMessage, "Invalid OTP. Please check your code and try again.");
	}
	
	@DataProvider
	public Object[][] hashMapData() throws IOException {
		List<HashMap<String, String>> data = getJsonData(
				System.getProperty("user.dir") + "\\src\\test\\java\\testautomation\\testData\\eCommerce.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}
	
}
