package com.myProject.Automation;

import java.time.Duration;
import java.util.HashMap;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class KeywordFunctions extends Report {
	public SeleniumWait seleniumWait;
	
	public KeywordFunctions() {
		seleniumWait = new SeleniumWait(androidDriver);
	}
	
	/**
	 * Type data in text boxes
	 * 
	 * @param element
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public boolean type(AndroidElement element, String data, String description) {

		try {
			// element.clear();
			seleniumWait.waitforElementClickable(element);
			element.clear();
			element.sendKeys(data);
			test.log(LogStatus.PASS,
					"Enter value " + "<b>" + data + "</b>" + " Successfully in " + "<b>" + description + "</b>");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL, "Failed to Enter Value " + "<b>" + data + "</b>" + " in " + description);
			getScreenShotWhenTestFailed();
			return false;
		}
	}

	/**
	 * Click on element
	 * 
	 * @param element
	 * @param description
	 * @return
	 */
	public boolean click(AndroidElement element, String description) {

		try {
			seleniumWait.waitforElementClickable(element);
			element.click();
			test.log(LogStatus.PASS, "Click Successful on " + "<b>" + description + "</b>");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL, "Failed to click " + "<b>" + description + "</b>");

			getScreenShotWhenTestFailed();
			return false;
		}
	}

	/**
	 * Scroll to element
	 * 
	 * @param description
	 * @return
	 */
	public boolean scrollToElementUsingText(String description) {
		try {
			androidDriver.findElementByAndroidUIAutomator(
					"new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""
							+ description + "\").instance(0))");
			test.log(LogStatus.PASS, "Successful scroll to Element " + "<b>" + description + "</b>");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL, "Failed to scroll to element " + "<b>" + description + "</b>");

			getScreenShotWhenTestFailed();
			return false;
		}
	}

	public void scrollDownUsingDynamicCordeinates() {
		Dimension size = driver.manage().window().getSize();
		int starty = (int) (size.height * 0.75);
		int endy = (int) (size.height * 0.35);
		TouchAction tAction = new TouchAction(androidDriver);
		tAction.press(PointOption.point(0, starty)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(3)))
				.moveTo(PointOption.point(0, endy)).release().perform();
	}

	public void scrollUpUsingDynamicCordeinates() {
		Dimension size = driver.manage().window().getSize();
		int starty = (int) (size.height * 0.40);
		int endy = (int) (size.height * 0.80);
		TouchAction tAction = new TouchAction(androidDriver);
		tAction.press(PointOption.point(0, starty)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(3)))
				.moveTo(PointOption.point(0, endy)).release().perform();
	}
	
	public void swipeLeftUsingDynamicCordeinates() {
		Dimension size = driver.manage().window().getSize();
		int startx = (int) (size.width * 0.85);
		int endx = (int) (size.width * 0.25);
		TouchAction tAction = new TouchAction(androidDriver);
		tAction.press(PointOption.point(startx, 0)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(3)))
				.moveTo(PointOption.point(endx, 0)).release().perform();
	}
	
	
	public void scrollUp() {
		JavascriptExecutor js = (JavascriptExecutor) androidDriver;
		HashMap<String, String> scrollObject = new HashMap<String, String>();
		scrollObject.put("direction", "down");

		js.executeScript("mobile: scroll", scrollObject);
	}

	/**
	 * is element displayed
	 * 
	 * @param element
	 * @param description
	 * @return
	 */
	public boolean isElementDisplayed(AndroidElement element, String description) {
		try {
			seleniumWait.waitforElementClickable(element);
			element.isDisplayed();
			test.log(LogStatus.PASS, "Element is  Successful displayed " + "<b>" + description + "</b>");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL, "Failed to display " + "<b>" + description + "</b>");
			return false;
		}
	}
	
	public String getTextMessage(AndroidElement element, String description) {
		String text = null;
		try {
			seleniumWait.waitforElementClickable(element);
			text = element.getText();
			test.log(LogStatus.PASS, "Element is  Successful displayed " + "<b>" + description + "</b>");
			return text;
		} catch (Exception e) {
			e.printStackTrace();
			test.log(LogStatus.FAIL, "Failed to display " + "<b>" + description + "</b>");
			return text;
		}
	}
	
    public static void highLighterMethod(AndroidElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
    }
}
