package com.user.automation.common.utils;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
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
			Properties properties = new Properties();
			FileInputStream file = new FileInputStream("src/main/resources/config.properties");
			properties.load(file);
			String browser = properties.getProperty("browser");
			driver = createDriver(browser);
			driver.manage().window().maximize();
			driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(70, TimeUnit.SECONDS);
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
		if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			return new ChromeDriver();
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
			return new ChromeDriver();
		}
	}

	@AfterClass
	public static void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
}
