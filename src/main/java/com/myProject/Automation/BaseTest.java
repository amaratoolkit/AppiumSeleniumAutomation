package com.myProject.Automation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class BaseTest {

	public static AndroidDriver<AndroidElement> androidDriver;
	public static WebDriver driver;
	public static ExtentReports extent;
	public static ExtentTest test;
	public ITestResult result;

	@BeforeSuite
	public void launchApplication() throws FileNotFoundException, IOException, ParseException, MalformedURLException {

		Report.initializeReport();
		String version = TestData.readData("deviceCapabilities", "deviceVersion");
		String deviceName = TestData.readData("deviceCapabilities", "deviceName");
		String apptypeName = TestData.readData("platform", "appType");

		if (apptypeName.equals("web")) {
			driver = new SafariDriver();
			driver.get("https://www.flipkart.com/");
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

		} else {

			DesiredCapabilities dc = new DesiredCapabilities();
			dc.setCapability("platformName", "Android");
			dc.setCapability("platformVersion", version);
			dc.setCapability("automationName", "UiAutomator2");
			//dc.setCapability("appPackage", "com.flipkart.android");
			// dc.setCapability("appActivity",
			// "com.nobroker.app.activities.NBSplashScreen");
			dc.setCapability("app", System.getProperty("user.dir") + "/mobileApps/Flipkart.apk");
			dc.setCapability("deviceName", deviceName);
			dc.setCapability("autoGrantPermissions", "true");
			dc.setCapability("autoAcceptAlerts", "true");
			androidDriver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"), dc);

			androidDriver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void reportTest(Method method) {
		test = extent.startTest(this.getClass().getSimpleName() + "::" + method.getName());
	}

	@AfterSuite
	public void afterSuite() {
		Report.endReport();
		driver.quit();
	}

	@BeforeMethod
	public void startReport(Method method) {
		reportTest(method);
	}

	@AfterMethod
	public void afterMethod() throws Exception {
		// testFails();
		extent.endTest(test);
	}
}
