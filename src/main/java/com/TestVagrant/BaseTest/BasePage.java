package com.TestVagrant.BaseTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.TestVagrant.Configs.Config;

public class BasePage {
	private static final Logger log = Logger.getLogger(BasePage.class.getName());

	public static WebDriver driver;
	public Properties OR;
	public File f1;
	public FileInputStream file;
	public Config config;

	@BeforeClass
	public void init() throws IOException {
		loadPropertiesFile();
		String log4jConfpath = "Log4j.properties";
		PropertyConfigurator.configure(log4jConfpath);
		String browser = new Config(OR).getExecutionBrowser();
		System.out.println("You have selected *" + browser + "* browser for Test Execution");
		selectBrowser(browser);
		driver.get(new Config(OR).getAppURL());
	}

	public void loadPropertiesFile() throws IOException {
		OR = new Properties();
		f1 = new File(System.getProperty("user.dir") + "/src/main/java/com/TestVagrant/Configs/config.properties");
		file = new FileInputStream(f1);
		OR.load(file);
		System.out.println("Loaded the Prop file");
		log.info("loading config.properties");
	}

	public static String getExecBrw() throws Exception {
		Properties p = new Properties();
		p.load(new FileInputStream(
				System.getProperty("user.dir") + "/src/main/java/com/TestVagrant/Configs/config.properties"));
		return p.getProperty("ExecutionBrowser");
	}

	/*
	 * **** Select the Browser.
	 */
	@SuppressWarnings("deprecation")
	public void selectBrowser(String browser) {
		if (System.getProperty("os.name").contains("Window")) {
			if (browser.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "/src/main/resources/drivers/chromedriver.exe");
				log.info("creating object of :" + browser.toString());
				driver = new ChromeDriver();
				driver.manage().window().maximize();
			} else {
				System.out.println("The browser choosed is incorrect");
			}
			/*
			 * Execution in case of Mac System.
			 */
		} else if (System.getProperty("os.name").contains("Mac")) {
			if (browser.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "/src/main/resources/drivers/chromedriver");
				log.info("creating object of :" + browser.toString());
				driver = new ChromeDriver();

			} else {
				System.out.println("The browser choosed is incorrect");
			}

		} else if (System.getProperty("os.name").contains("linux")) {
			if (browser.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "/src/main/resources/drivers/chromedriver_linux");
				log.info("creating object of :" + browser.toString());
				driver = new ChromeDriver();

			} else {
				System.out.println("The browser choosed is incorrect");
			}

		}

	}

	@AfterClass(alwaysRun = true)
	public void endTest() {
		driver.quit();
		log.info("Browser Closed");
	}
	
	public void log(String data) {
		log.info(data);
		Reporter.log(data);
	}

}
