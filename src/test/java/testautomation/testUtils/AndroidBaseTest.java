package testautomation.testUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.Properties;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import testautomation.pageObjects.android.FormPage;
import testautomation.utils.AppiumUtils;

public class AndroidBaseTest extends AppiumUtils {
	public AndroidDriver driver;
	public AppiumDriverLocalService service;
	public FormPage formPage;

	@BeforeClass(alwaysRun=true)
	public void configureAppium() throws URISyntaxException, IOException {
		Properties prop = new Properties();
		FileInputStream file = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\testautomation\\resources\\data.properties");
		prop.load(file);
		//send parameter value from Maven		
		String ipAddress = System.getProperty("ipAddress") != null ? System.getProperty("ipAddress"): prop.getProperty("ipAddress");
		String deviceName = System.getProperty("AndroidDeviceName")!=null? System.getProperty("AndroidDeviceName"): prop.getProperty("AndroidDeviceName");
		int port = Integer.parseInt(prop.getProperty("port"));
		
		service = startAppiumServer(ipAddress, port);
		
		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName(deviceName);
		options.setApp(System.getProperty("user.dir") + "\\src\\test\\java\\resources\\General-Store.apk");
		driver = new AndroidDriver(service.getUrl(), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		formPage = new FormPage(driver);
	}

	@AfterClass(alwaysRun=true)
	public void tearDown() {
		driver.quit();
		service.stop();
	}
}
