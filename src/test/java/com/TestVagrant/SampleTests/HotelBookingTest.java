package com.TestVagrant.SampleTests;

import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.TestVagrant.BaseTest.BasePage;
import com.TestVagrant.ExcelUtil.ExcelReader;
import com.TestVagrant.PageObjects.HotelBookingPage;

public class HotelBookingTest extends BasePage{
	
	 @Test
	    public void shouldBeAbleToSearchForHotels() {
		 
		 HotelBookingPage hotelBooking = new HotelBookingPage(driver);
		ExcelReader excel = new ExcelReader(System.getProperty("user.dir")+"/src/main/resources/TestData/data.xlsx");
		hotelBooking.searchHotel(excel.getCellData("Sheet1", "Locality", 2),excel.getCellData("Sheet1", "Travellers", 2));
	    }

}
