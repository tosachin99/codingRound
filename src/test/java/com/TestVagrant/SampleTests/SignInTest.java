package com.TestVagrant.SampleTests;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.TestVagrant.BaseTest.BasePage;


public class SignInTest extends BasePage{


    @Test
    public void shouldThrowAnErrorIfSignInDetailsAreMissing() {

        waitFor(2000);

        driver.findElement(By.linkText("Your trips")).click();
        driver.findElement(By.id("SignIn")).click();

        driver.findElement(By.id("signInButton")).click();

        String errors1 = driver.findElement(By.id("errors1")).getText();
        Assert.assertTrue(errors1.contains("There were errors in your submission"));
        driver.quit();
    }

    private void waitFor(int durationInMilliSeconds) {
        try {
            Thread.sleep(durationInMilliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }


}
