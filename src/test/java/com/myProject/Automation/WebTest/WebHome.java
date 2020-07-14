package com.myProject.Automation.WebTest;

import org.testng.annotations.*;

import com.myProject.Automation.BaseTest;
import com.myProject.Automation.WebPages.WebHomePage;

public class WebHome extends BaseTest{
	
	public WebHomePage webHomePage;
	
	@BeforeTest
	public void initialize() {
		webHomePage = new WebHomePage(driver);
	}
	
	@Test
	public void loginTest() {
		webHomePage.loginAsUser();
	}

}
