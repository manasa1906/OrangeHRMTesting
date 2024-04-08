package com.user.automation.common.utils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.user.automation.common.pages.LoginPage;
import com.user.automation.common.pages.TestPage;
import com.user.automation.common.pages.UserPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SupportBrowser {
	private static WebDriver driver;
	private static UserPage userPage;
	public static LoginPage loginPage;
	public static TestPage testPage;

	@BeforeClass
	public static void setUp() {
		try {

			ChromeOptions options = new ChromeOptions();
			String path = System.getProperty("user.dir") + "\\src\\test\\resources\\addons\\ublock.crx";

			//			Map<String, Object> prefs = new HashMap<>();
			//			prefs.put("profile.default_content_settings.popups", 0);

			options.addExtensions(new File(path));
			//			options.setExperimentalOption("prefs", prefs);

			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(options);

			driver.manage().window().maximize();
			driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(70, TimeUnit.SECONDS);

			Thread.sleep(1000);
			userPage = new UserPage(driver);
			loginPage = new LoginPage(driver);
			testPage = new TestPage(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static WebDriver getDriver() {
		return driver;
	}

	public UserPage getUserPage() {
		return userPage;
	}

	public LoginPage getLoginPage() {
		return loginPage;
	}

	public TestPage getTestPage() {
		return testPage;
	}

	private static WebDriver createDriver(String browser) {
		ChromeOptions options = new ChromeOptions();
		if (browser.equalsIgnoreCase("chrome")) {

			Map<String, Object> prefs = new HashMap<>();
			prefs.put("profile.default_content_settings.popups", 0);
			options.setExperimentalOption("prefs", prefs);

			WebDriverManager.chromedriver().setup();
			return new ChromeDriver(options);
		} else if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			return new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			return new EdgeDriver();
		} else if (browser.equalsIgnoreCase("safari")) {
			WebDriverManager.safaridriver().setup();
			return new SafariDriver();
		} else {
			WebDriverManager.chromedriver().setup();
			return new ChromeDriver(options);
		}
	}

	@AfterClass
	public static void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
}
