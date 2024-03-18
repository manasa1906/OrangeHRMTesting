package com.user.automation.common.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.user.automation.common.utils.SupportBrowser;

public class LoginPage extends SupportBrowser {

	private static WebDriver driver;
	@FindBy(how = How.NAME, using = "username")
	private WebElement username;

	@FindBy(how = How.NAME, using = "password")
	private WebElement password;

	@FindBy(how = How.XPATH, using = "//div[@class='oxd-form-actions orangehrm-login-action']//button")
	private WebElement loginButton;

	@FindBy(how = How.XPATH, using = "//div[@class='oxd-topbar-header-userarea']//img[@alt='profile picture']")
	private WebElement profileImage;

	@FindBy(how = How.XPATH, using = "//div[@class='oxd-alert-content oxd-alert-content--error' and @data-v-87fcf455='']")
	private WebElement errorMessage;

	@FindBy(how = How.XPATH, using = "//div[@class='oxd-select-text-input' and text()='-- Select --']")
	private static WebElement jobTitleDD;

	@FindBy(how = How.XPATH, using = "//div[@class='oxd-select-text-input' and text()='-- Select --']")
	private static WebElement vacancyDD;

	@FindBy(how = How.XPATH, using = "//div[@class='oxd-select-text-input' and text()='-- Select --']")
	private static WebElement hiringManagerDD;

	@FindBy(how = How.XPATH, using = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[3]/div/div/div/div[2]/div/div/div[1]")
	private static WebElement methodDD;

	@FindBy(how = How.XPATH, using = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[4]/button[2]")
	private static WebElement search;

	@FindBy(how = How.XPATH, using = "//div[@class='' and @data-v-957b4417='']")
	private static WebElement usernameErrorMessage;

	@FindBy(how = How.XPATH, using = "//span[@class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message' and @data-v-7b563373='']")
	private static WebElement passwordErrorMessage;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void navigateToURL(String url) {
		driver.get(url);
	}

	public WebElement findElementByName(String name) {
		return driver.findElement(By.name(name));
	}

	public static WebElement findElementByXPath(String xpath) {
		return driver.findElement(By.xpath(xpath));
	}

	public void enterText(WebElement element, String text) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(element));
		element.sendKeys(text);
	}

	public static void clickButton(WebElement button) {
		button.click();
	}

	public void clickLoginButton() {
		clickButton(loginButton);
	}

	public boolean isElementDisplayed(WebElement element) {
		return element.isDisplayed();
	}

	public boolean isErrorDisplayed() {
		if (errorMessage.isDisplayed()) {
			return true;
		} else {
			return false;
		}

	}

	public WebElement getProfileImage() {
		return profileImage;
	}

	public boolean errorMessagesDisplayed() {

		if (isElementDisplayed(usernameErrorMessage) && isElementDisplayed(passwordErrorMessage)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isProfileDisplayed() {
		if (profileImage.isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}

	public String getCurrentURL() {
		return driver.getCurrentUrl();
	}

	public void login(String username, String password) {

		enterText(this.username, username);
		enterText(this.password, password);
		loginButton.click();

	}

	public boolean checkLoginButton() {

		clickButton(loginButton);

		if (profileImage.isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}

	public void quitDriver() {
		if (driver != null) {
			driver.quit();
		}
	}

	public static void jobTitle(String desiredText) {

		clickButton(jobTitleDD);
		WebElement desiredOption = findElementByXPath("//*[contains(text(),'" + desiredText + "')]");
		desiredOption.click();
	}

	public static void vacancy(String desiredText) {
		clickButton(vacancyDD);
		WebElement desiredVacancyOption = findElementByXPath("//*[contains(text(),'" + desiredText + "')]");
		desiredVacancyOption.click();
	}

	public static void hiringManager(String desiredText) {

		clickButton(hiringManagerDD);
		WebElement desiredHROption = findElementByXPath("//*[contains(text(),'" + desiredText + "')]");
		desiredHROption.click();
	}

	public static void method(String desiredText) {
		clickButton(methodDD);
		WebElement desiredMethodOption = findElementByXPath("//*[contains(text(),'" + desiredText + "')]");
		desiredMethodOption.click();
	}

	public static void search() {

		clickButton(search);
	}
}