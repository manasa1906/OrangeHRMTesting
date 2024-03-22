package orangehrmtests;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.user.automation.common.pages.LoginPage;
import com.user.automation.common.utils.SupportBrowser;

public class LoginTest extends SupportBrowser {
	private static Logger logger = Logger.getLogger(LoginTest.class.getName());
	private LoginPage loginPage;

	@Test
	public void linkTest() {
		WebDriver driver = getDriver();
		LoginPage loginPage = getLoginPage();
		loginPage.navigateToHomePage();

		logger.info("Page title: " + driver.getTitle());
		logger.info("Current URL: " + loginPage.getCurrentURL());

		SupportBrowser.tearDown();
	}

	@Test
	public void testLogin() throws IOException {

		loginPage = getLoginPage();
		loginPage.navigateToHomePage();

		loginPage.login("Admin", "admin123");
		if (loginPage.isElementDisplayed(loginPage.getProfileImage())) {
			logger.info("Successfully logged in");
		} else {
			logger.info("Login failed");
		}

		logger.info(loginPage.getCurrentURL());

	}

	@Test
	public void testEmptyUsernameAndPassword() throws IOException {
		LoginPage loginPage = getLoginPage();
		loginPage.navigateToHomePage();
		loginPage.login("", "");

		boolean b = loginPage.errorMessagesDisplayed();

		if (b) {
			logger.info("Error messages displayed for both username and password fields");

		} else {
			logger.info("Error messages not displayed properly.");
		}
	}

	@Test
	public void testInvalidLogin() throws IOException {
		LoginPage loginPage = getLoginPage();
		loginPage.navigateToHomePage();
		loginPage.login("Admin1", "admin123");
		boolean b = loginPage.isErrorDisplayed();
		if (b) {
			logger.log(Level.INFO, "Error message displayed ");
		} else {
			logger.log(Level.INFO, "Error message not displayed.");
		}

	}

	@Test
	public void testValidLogin() throws IOException {
		LoginPage loginPage = getLoginPage();
		loginPage.navigateToHomePage();
		loginPage.login("Admin", "admin123");
		boolean b = loginPage.isProfileDisplayed();
		if (b) {
			logger.info("successfully logged in");
		} else {
			Assert.fail("Error occured");
		}

	}

	@Test
	void recuritmentTest() throws InterruptedException {
		LoginPage loginPage = getLoginPage();
		loginPage.navigateToRecruitmentPage();
		loginPage.login("Admin", "admin123");
		LoginPage.jobTitle("Chief Executive Officer");
		LoginPage.vacancy("Sales Representative");
		LoginPage.hiringManager("Odis Adalwin");
		LoginPage.method("Manual");
	}

	@Test
	public void restrictedAccess() throws IOException {
		WebDriver driver = getDriver();
		LoginPage loginPage = getLoginPage();
		loginPage.navigateToDashboardPage();
		String homePage = "https://opensource-demo.orangehrmlive.com/";
		String username = "Admin";
		String password = "admin123";
		String currentUrl = driver.getCurrentUrl();
		if (!(currentUrl.equals(homePage))) {

			WebElement usernameField = loginPage.findElementByName("username");
			loginPage.enterText(usernameField, "Admin1");
			WebElement passwordField = loginPage.findElementByName("password");
			loginPage.enterText(passwordField, "admin123");
			String inputUserName = usernameField.getAttribute("value");
			String inputPassword = passwordField.getAttribute("value");

			if ((!username.equals(inputUserName)) || (!password.equals(inputPassword))) {
				loginPage.clickLoginButton();
				boolean b = loginPage.isErrorDisplayed();
				if (b) {
					logger.log(Level.INFO, "Error message displayed ");
				} else {
					logger.log(Level.INFO, "Error message not displayed.");
				}
			}
			if ((username.equals(inputUserName)) && (password.equals(inputPassword))) {
				boolean b = loginPage.checkLoginButton();
				Assert.assertTrue(b);
			}
		} else {
			Assert.fail("Error occured");
		}
	}
}
