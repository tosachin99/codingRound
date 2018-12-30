package com.TestVagrant.PageObjects;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.TestVagrant.Helpers.WaitHelper;

public class FlightBookingPage {
	private static final Logger log = Logger.getLogger(FlightBookingPage.class.getName());
	WebDriver driver;
	WaitHelper wh;

	@FindBy(linkText = "Flights")
	private WebElement lnkTxt_Flights;

	@FindBy(id = "OneWay")
	private WebElement radio_OneWay;

	@FindBy(id = "FromTag")
	private WebElement txt_From;

	@FindBy(id = "ToTag")
	private WebElement txt_To;

	@FindBy(id = "SearchBtn")
	private WebElement btn_Search;

	@FindBy(xpath = "//div[@class='monthBlock first']//table[@class='calendar']")
	private WebElement datePickerTable;

	public FlightBookingPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wh = new WaitHelper(driver);
	}

	public void checkFlights(String From, String To) {
		lnkTxt_Flights.click();
		wh.waitForElementVisible(radio_OneWay, 10, 300);
		radio_OneWay.click();
		txt_From.clear();
		txt_From.sendKeys(From);
		waitFor(4000);
		List<WebElement> originOptions = driver.findElement(By.id("ui-id-1")).findElements(By.tagName("li"));
		originOptions.get(0).click();
		txt_To.clear();
		txt_To.sendKeys(To);
		waitFor(6000);
		List<WebElement> destinationOptions = driver.findElement(By.id("ui-id-2")).findElements(By.tagName("li"));
		destinationOptions.get(0).click();

		selectCurrentDate();
		btn_Search.click();

	}

	public void selectCurrentDate() {
		String today = getCurrentDay();
		System.out.println("Today's number: " + today + "\n");
		List<WebElement> columns = datePickerTable.findElements(By.tagName("td"));

		for (WebElement cell : columns) {
			if (cell.getText().equals(today)) {
				cell.click();
				break;
			}
		}
	}

	private String getCurrentDay() {
		// Create a Calendar Object
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		int todayInt = calendar.get(Calendar.DAY_OF_MONTH);
		System.out.println("Today Int: " + todayInt + "\n");
		String todayStr = Integer.toString(todayInt);
		System.out.println("Today Str: " + todayStr + "\n");

		return todayStr;
	}

	private void waitFor(int durationInMilliSeconds) {
		try {
			Thread.sleep(durationInMilliSeconds);
		} catch (InterruptedException e) {
			e.printStackTrace(); // To change body of catch statement use File | Settings | File Templates.
		}
	}
}
