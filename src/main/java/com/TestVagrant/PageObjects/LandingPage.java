package com.TestVagrant.PageObjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.TestVagrant.BaseTest.BasePage;
import com.TestVagrant.Configs.Config;
import com.TestVagrant.Helpers.WaitHelper;

public class LandingPage{
	private static final Logger log = Logger.getLogger(LandingPage.class.getName());
	WebDriver driver;
	WaitHelper wh;

	public LandingPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wh = new WaitHelper(driver);
	}

	@FindBy(linkText = "Your trips")
	private WebElement lnk_yourTrips;

	@FindBy(id = "SignIn")
	private WebElement btn_signInButton;

	@FindBy(id = "signInButton")
	private WebElement SignInbtnInPopUp;

	public void checkForErrorInSignSubmissions() {
		lnk_yourTrips.click();
		btn_signInButton.click();
		driver.switchTo().frame("modal_window");
		log.info("Switched to Iframe of Login PopUp");
		wh.waitForElementVisible(SignInbtnInPopUp, 10, 300);
		SignInbtnInPopUp.click();
		String errorMsg = driver.findElement(By.id("errors1")).getText();
		log.info("Error Message Displayed is : \n "+errorMsg);
		Assert.assertTrue(errorMsg.contains("There were errors in your submission"));
	}
}
