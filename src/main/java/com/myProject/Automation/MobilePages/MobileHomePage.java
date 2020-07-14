package com.myProject.Automation.MobilePages;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

public class MobileHomePage {

	public AndroidDriver<AndroidElement> androidDriver;
	public KeywordFunctions actions;
	JavascriptExecutor executor = (JavascriptExecutor) androidDriver;
	public SeleniumWait seleniumWait;

	public MobileHomePage(AndroidDriver<AndroidElement> androidDriver) {

		PageFactory.initElements(new AppiumFieldDecorator(androidDriver), this);
		this.androidDriver = androidDriver;
		actions = new KeywordFunctions();
		seleniumWait = new SeleniumWait(androidDriver);
	}

	@AndroidFindBy(id = "select_btn")
	private AndroidElement continueInEnglish;
	
	@AndroidFindBy(id = "cart_bg_icon")
	private AndroidElement cartIcon;
	
	@AndroidFindBy(uiAutomator  = "new UiSelector().text(\"Login\")")
	private AndroidElement loginButton;
	
	@AndroidFindBy(accessibility  = "Phone Number")
	private AndroidElement phoneNumberForLogin;
	
	@AndroidFindBy(id = "tv_right_cta")
	private AndroidElement useEmailIdLink;
	
	@AndroidFindBy(accessibility = "Email ID")
	private AndroidElement emailInput;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().textContains(\"Continue\")")
	private AndroidElement continueButton;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Password\")")
	private AndroidElement passwordInput;
	
	
	@AndroidFindBy(uiAutomator = "new UiSelector().textContains(\"Your cart is empty\")")
	private AndroidElement yourCartIsEmptyMessage;
	
	@AndroidFindBy(id = "banner_image")
	private List<AndroidElement> categoriesList;
	
	@AndroidFindBy(id = "title_action_bar")
	private AndroidElement titleCategory;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"View All\")")
	private AndroidElement viewAllButton;
	
	@AndroidFindBy(xpath = "(//*[@text='View All']/ancestor:: androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[4]/following::android.widget.TextView)[2]")
	private AndroidElement mobilesCategory;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Latest Launches\")")
	private AndroidElement latestLaunchesSection;
	
	@AndroidFindBy(xpath = "//*[contains(@text,'Motorola')]")
	private AndroidElement selectMobileType;
	
	
	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"ADD TO CART\")")
	private AndroidElement addTocartButton;
	
	@AndroidFindBy(accessibility = "Back Button")
	private AndroidElement backButton;
	
	
	@AndroidFindBy(accessibility = "Drawer")
	private AndroidElement menuNavbar;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Electronics\")")
	private AndroidElement electronicsItem;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Mobiles\")")
	private AndroidElement mobileItem;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Best Battery Phones\")")
	private AndroidElement bestBatterySection;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().textContains(\"Place Order\")")
	private AndroidElement placeOrderButton;
	
	
	@AndroidFindBy(uiAutomator = "new UiSelector().resourceId(\"pincode\")")
	private AndroidElement pincodeInput;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().textContains(\"Building name\")")
	private AndroidElement buildingNameInput;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().textStartsWith(\"Road\")")
	private AndroidElement roadNameInput;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"City\")")
	private AndroidElement cityInput;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"State\")")
	private AndroidElement selectState;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Telangana\")")
	private AndroidElement stateNameradioButton;
	
	
	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Name\")")
	private AndroidElement userNameInput;
	
	@AndroidFindBy(uiAutomator = "new UiSelector().text(\"Save\")")
	private AndroidElement saveButton;
	
	public void loginAsUser() {
		
		seleniumWait.waitforElementClickable(continueInEnglish);

		actions.click(continueInEnglish, "continueInEnglish Button");
		seleniumWait.waitforElementClickable(useEmailIdLink);
		seleniumWait.waitForElementVisible(useEmailIdLink);
		actions.click(useEmailIdLink, "useEmailId Link");
		actions.type(emailInput, "siva6kolli@gmail.com", "email Input");
		//androidDriver.hideKeyboard();
		actions.click(continueButton, "continue Button");
		
		seleniumWait.waitforElementClickable(passwordInput);
		actions.type(passwordInput, "selenium", "password Input");
		//androidDriver.hideKeyboard();
		actions.click(continueButton, "continue Button");
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		actions.click(cartIcon, "cart icon");
		actions.isElementDisplayed(yourCartIsEmptyMessage, "yourCartIsEmptyMessage");
	}
	
	public String getTextOfEmptyCart() {
		return actions.getTextMessage(yourCartIsEmptyMessage, "yourCartIsEmptyMessage");
	}
	
	public void tapOnBackButton() {
		actions.click(backButton, "backButton");
	}
	
	public void selectMobileCategory() {
		actions.click(menuNavbar, "menuNavbar");
		actions.click(electronicsItem, "electronicsItem");
		actions.scrollToElementUsingText("Mobiles");
		actions.click(mobileItem, "mobileItem");
	}
	
	public void selectCategory() {
		for(int i=2; i<categoriesList.size(); i++) {
			actions.click(categoriesList.get(i), "Electronics Category");
			seleniumWait.waitForElementVisible(titleCategory);
			if(titleCategory.getText().equals("Electronics Store")){
				actions.click(categoriesList.get(i), "Electronics Category");
			} else {
				tapOnBackButton();
			}
			
		}
		
		//actions.click(categoriesList.get(5), "Electronics Category");
	}
	
	public String getTitleOfPage() {
		return actions.getTextMessage(titleCategory, "titleCategory");
	}
	
	public void gotoMobilesCategory() {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		actions.scrollDownUsingDynamicCordeinates();
		actions.scrollDownUsingDynamicCordeinates();
		actions.isElementDisplayed(viewAllButton, "viewAllButton");
		actions.click(mobilesCategory, "mobilesCategory");
	}
	
	public void purchaseMobile() {
		actions.scrollToElementUsingText("Best Battery Phones");
		actions.isElementDisplayed(viewAllButton, "viewAllButton");
		actions.click(viewAllButton, "viewAllButton");
		actions.click(selectMobileType, "selectMobileType");
		actions.click(addTocartButton, "addTocartButton");
		androidDriver.navigate().back();
	}
	
	public void verifyCartItem() {
		actions.click(cartIcon, "cart icon");
		actions.isElementDisplayed(selectMobileType, "selectMobileType");
	}
	
	public void placeOrder() {
		actions.click(placeOrderButton, "placeOrderButton");
		
		actions.type(pincodeInput, "500085", "pincode Input");
		actions.type(buildingNameInput, "GopisPuram", "Building Input");
		actions.type(roadNameInput, "GopisRoad", "Road Input");
		actions.type(cityInput, "Hyderabad", "City Input");
		actions.click(selectState, "select state");
		actions.scrollToElementUsingText("Telangana");
		actions.click(stateNameradioButton, "stateNameradioButton");
		actions.type(userNameInput, "Gopi","userNameInput");
		actions.scrollToElementUsingText("Save");
		actions.click(saveButton, "Save Button");
		
		
	}
	
	
	
}
