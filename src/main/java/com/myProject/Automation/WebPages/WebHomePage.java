package com.myProject.Automation.WebPages;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.myProject.Automation.KeywordFunctions;
import com.myProject.Automation.SeleniumWait;

import io.appium.java_client.HidesKeyboard;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.offset.PointOption;

public class WebHomePage {

	public WebDriver driver;
	public KeywordFunctions actions;
	JavascriptExecutor executor = (JavascriptExecutor) driver;
	public SeleniumWait seleniumWait;

	public WebHomePage(WebDriver driver) {

		PageFactory.initElements(driver, this);
		this.driver = driver;
		actions = new KeywordFunctions();
		seleniumWait = new SeleniumWait(driver);
	}

	@FindBy(xpath = "//form/div/input[@type='text']")
	private WebElement userNameInput;
	
	@FindBy(xpath = "//form/div/input[@type='password']")
	private WebElement passwordInput;
	
	@FindAll({
		   @FindBy(xpath = "//*[text()='Hello, Sign in']"),
		   @FindBy(xpath = "//div[@class='nav-right']/a[text()='Sign In']")
		})
	private WebElement signIn;
	
	@FindBy(xpath = "//span[text()='Login']/../../button")
	private WebElement loginButton;
	
	
	public void loginAsUser() {
		seleniumWait.waitforElementPresent(By.xpath("//form/div/input[@type='text']"));
		userNameInput.sendKeys("test@gmail.com");
		passwordInput.sendKeys("test");
		loginButton.click();
	
	}
}
