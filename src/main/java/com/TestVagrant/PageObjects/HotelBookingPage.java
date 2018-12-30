package com.TestVagrant.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.TestVagrant.BaseTest.BasePage;
import com.TestVagrant.Helpers.WaitHelper;

public class HotelBookingPage extends BasePage {
WebDriver driver;
	@FindBy(linkText = "Hotels")
	private WebElement hotelLink;

	@FindBy(id = "Tags")
	private WebElement localityTextBox;

	@FindBy(id = "SearchHotelsButton")
	private WebElement searchButton;

	@FindBy(id = "travellersOnhome")
	private WebElement travellerSelection;

	public HotelBookingPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	public void searchHotel(String locality, String travellers) {
		hotelLink.click();
		localityTextBox.sendKeys(locality);
		travellerSelection.click();
		Select sel = new Select(travellerSelection);
		sel.selectByVisibleText(travellers);
		searchButton.click();

	}
}
