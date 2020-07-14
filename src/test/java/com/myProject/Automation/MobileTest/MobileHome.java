package com.myProject.Automation.MobileTest;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.*;

import com.myProject.Automation.BaseTest;
import com.myProject.Automation.TestData;

import com.myProject.Automation.MobilePages.MobileHomePage;

public class MobileHome extends BaseTest {

	public MobileHomePage homePage;

	@BeforeTest
	public void initializePages() {

		homePage = new MobileHomePage(androidDriver);
	}

	@Test
	public void loginAsUser() throws FileNotFoundException, IOException, ParseException {
		String city = TestData.readData("noBroker", "city");
		String twoBHK = TestData.readData("noBroker", "2BHK");
		String threeBHK = TestData.readData("noBroker", "3BHK");
		String description = TestData.readData("noBroker", "descriptionLabel");

		homePage.loginAsUser();
		//Assert.assertEquals(homePage.getTextOfEmptyCart().trim(), "Your cart is empty!");
		homePage.tapOnBackButton();
	}

	@Test(dependsOnMethods = "loginAsUser")
	public void selectCategory() {
		homePage.selectMobileCategory();
		//assertEquals(homePage.getTitleOfPage(), "Electronics Store");
		//homePage.gotoMobilesCategory();
		//assertEquals(homePage.getTitleOfPage(), "Mobiles");

	}

	@Test(dependsOnMethods = "selectCategory")
	public void purchaseMobile() {
		//homePage.gotoMobilesCategory();
		homePage.purchaseMobile();
	}
	
	@Test(dependsOnMethods = "purchaseMobile")
	public void placeOrder() {
		homePage.verifyCartItem();
		homePage.placeOrder();
	}
}