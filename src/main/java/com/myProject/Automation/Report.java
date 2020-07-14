package com.myProject.Automation;

	import java.io.File;
	import java.lang.reflect.Method;
	import java.text.SimpleDateFormat;
	import java.util.Date;

	import org.apache.commons.io.FileUtils;
	import org.openqa.selenium.OutputType;
	import org.openqa.selenium.TakesScreenshot;
	import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;
	import com.relevantcodes.extentreports.LogStatus;

	public class Report extends BaseTest {

		public static void initializeReport() {
			extent = new ExtentReports(System.getProperty("user.dir") + "/report/ExtentReport.html", true);
			extent.loadConfig(new File(System.getProperty("user.dir") + "/extent-config.xml"));
		}

		public void getScreenShotWhenTestFailed() {

			try {
				String screenShotPath = getScreenshot(driver, test.getDescription());
				test.log(LogStatus.FAIL, "Screenshot below : " + test.addScreenCapture(screenShotPath));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		public void getScreenShotWhenTestPass() {

			try {
				String screenShotPath = getScreenshot(driver, test.getDescription());
				test.log(LogStatus.PASS, "Screenshot below : " + test.addScreenCapture(screenShotPath));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		

		public static void endReport() {
			extent.flush();
			extent.close();
			
			//driver.get(System.getProperty("user.dir") + "/report/ExtentReport.html");
			driver.quit();

		}

		public void reportTest(Method method) {
			test = extent.startTest(this.getClass().getSimpleName() + "::" + method.getName());
		}

		public static String getScreenshot(WebDriver driver, String screenshotName) throws Exception {
			String dateName = new SimpleDateFormat("yyyyMMMdd-hhmmss").format(new Date());
			TakesScreenshot ts = (TakesScreenshot) driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			String destination = screenshotName + "_" + dateName + ".png";
			File finalDestination = new File(System.getProperty("user.dir") + "/report/" + destination);

			FileUtils.copyFile(source, finalDestination);
			return destination;
		}
		
//		public static void takeFullScreenShot() {
//			ChromeDevToolsService devToolsService = DevToolsService.getDevToolsService(driver);
//
//			// Take fullscreen snapshot
//			FullScreenshot.captureFullPageScreenshot(devToolsService, "screenshot.png");
//		}

	}
