package com.TestVagrant.SampleTests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.TestVagrant.BaseTest.BasePage;
import com.TestVagrant.PageObjects.LandingPage;

public class SignInTest extends BasePage {

	@Test
	public void shouldThrowAnErrorIfSignInDetailsAreMissing() {
		LandingPage lp = new LandingPage(driver);
		lp.checkForErrorInSignSubmissions();
	}
}
