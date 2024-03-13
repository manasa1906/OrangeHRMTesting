package com.user.automation.common.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UserPage {
	private static WebDriver driver;
	@FindBy(how = How.NAME, using = "username")
	private WebElement username;

	@FindBy(how = How.NAME, using = "password")
	private WebElement password;

	@FindBy(how = How.XPATH, using = "//div[@class='oxd-form-actions orangehrm-login-action']//button")
	private WebElement loginButton;

	@FindBy(how = How.XPATH, using = "//div[@class='oxd-topbar-header-userarea']//img[@alt='profile picture']")
	private WebElement profileImage;

	@FindBy(how = How.XPATH, using = "//label[text()='Username']/parent::div/following-sibling::div")
	private WebElement userInput;
	@FindBy(how = How.XPATH, using = "//label[text()='User Role']/parent::div/following-sibling::div/div[@class='oxd-select-wrapper']")
	private WebElement userRole;
	@FindBy(how = How.XPATH, using = "//label[text()='Employee Name']/parent::div/following-sibling::div//input[@placeholder='Type for hints...']")
	private WebElement employee;
	@FindBy(how = How.XPATH, using = "//label[text()='Status']/parent::div/following-sibling::div/div[@class='oxd-select-wrapper']")
	private WebElement status;
	@FindBy(how = How.XPATH, using = "//div[@class='oxd-form-actions']//button[contains(normalize-space(), 'Search')]")
	private WebElement search;

	@FindBy(how = How.XPATH, using = "//div[@class='orangehrm-header-container']//button[contains(@class, 'oxd-button--secondary') and contains(., 'Add')]")
	private WebElement add;
	@FindBy(how = How.XPATH, using = "//*[@id='app']/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[1]/div/div[2]/div/div/div[1]")
	private WebElement addUserRole;
	@FindBy(how = How.XPATH, using = "//div[label[text()='Employee Name']]/following-sibling::div//input[@placeholder='Type for hints...']")
	private WebElement addEmployee;
	@FindBy(how = How.XPATH, using = "//label[text()='Status']/parent::div/following-sibling::div//div[contains(@class, 'oxd-select-wrapper')]")
	private WebElement addStatus;
	@FindBy(how = How.XPATH, using = "//div[label[text()='Username']]/following-sibling::div//input")
	private WebElement addUser;
	@FindBy(how = How.XPATH, using = "//div[label[text()='Password']]/following-sibling::div//input")
	private WebElement addUserPassword;
	@FindBy(how = How.XPATH, using = "//div[label[text()='Confirm Password']]/following-sibling::div//input")
	private WebElement addUserConfirmPassword;
	@FindBy(how = How.XPATH, using = "//div[@class='oxd-form-actions']//button[contains(normalize-space(), 'Save')]")
	private WebElement Save;

	public UserPage(WebDriver driver) {
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

	public WebElement getProfileImage() {
		return profileImage;
	}

	public void clickLoginButton() {
		clickButton(loginButton);
	}

	public boolean isElementDisplayed(WebElement element) {
		return element.isDisplayed();
	}

	public void login(String username, String password) {

		enterText(this.username, username);
		enterText(this.password, password);
		loginButton.click();

	}

	public void enterUsername(String string) throws InterruptedException {
		clickButton(userInput);
		Thread.sleep(3000);
		enterText(userInput, string);
	}

	public void selectRole(String string) {
		clickButton(userRole);
		WebElement option = findElementByXPath("//*[text()='User Role']/../../div//*[text()='" + string + "']");
		clickButton(option);
	}

	public void enterEmployeename(String string) {
		clickButton(employee);
		enterText(employee, string);
		Actions actions = new Actions(driver);
		actions.sendKeys(Keys.ARROW_DOWN).perform();
		actions.sendKeys(Keys.ENTER).perform();
	}

	public void selectStatus(String string) {
		clickButton(status);
		WebElement option = findElementByXPath("//*[text()='Status']/../../div//*[text()='" + string + "']");
		clickButton(option);

	}

	public void search() {
		clickButton(search);

	}

	public void Add() {
		clickButton(add);

	}

	public void addRole(String string) {
		clickButton(addUserRole);
		WebElement option = findElementByXPath("//*[text()='User Role']/../../div//*[text()='" + string + "']");
		clickButton(option);

	}

	public void addStatus(String string) {
		clickButton(addStatus);
		WebElement option = findElementByXPath("//*[text()='Status']/../../div//*[text()='" + string + "']");
		clickButton(option);

	}

	public void addUser(String string) {
		clickButton(addUser);
		enterText(addUser, string);

	}

	public void addEmployee(String string) {
		clickButton(addEmployee);
		enterText(addEmployee, string);
		Actions actions = new Actions(driver);
		actions.sendKeys(Keys.ARROW_DOWN).perform();
		actions.sendKeys(Keys.ENTER).perform();

	}

	public void addPassword(String string) {
		clickButton(addUserPassword);
		enterText(addUserPassword, string);

	}

	public void addConfirmPassword(String string) {
		clickButton(addUserConfirmPassword);
		enterText(addUserConfirmPassword, string);

	}

	public void save() {
		clickButton(Save);
	}

	public void delete(String string) {

		WebElement dltbtn = findElementByXPath(
				"//div[contains(@class, 'oxd-table-row--with-border')]/div[contains(@class, 'oxd-padding-cell')]/div[contains(text(), '"
						+ string + "')]/../following-sibling::div//i[contains(@class, 'bi-trash')]");
		clickButton(dltbtn);
	}

}
