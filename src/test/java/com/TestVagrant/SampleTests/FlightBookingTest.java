package com.TestVagrant.SampleTests;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.TestVagrant.BaseTest.BasePage;
import com.TestVagrant.ExcelUtil.ExcelReader;
import com.TestVagrant.PageObjects.FlightBookingPage;


public class FlightBookingTest extends BasePage {

	@Test
	public void testThatResultsAppearForAOneWayJourney() {

		FlightBookingPage fbp = new FlightBookingPage(driver);
		ExcelReader excel = new ExcelReader(System.getProperty("user.dir") + "/src/main/resources/TestData/data.xlsx");
		String from = excel.getCellData("Sheet1", "From", 2);
		String to = excel.getCellData("Sheet1", "To", 2);
		fbp.checkFlights(from, to);

		Assert.assertTrue(isElementPresent(By.className("searchSummary")));
		driver.quit();
	}


	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

}
