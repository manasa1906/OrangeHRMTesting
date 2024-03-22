package com.user.automation.common.pages;

import java.time.Duration;
import java.util.logging.Logger;

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
import org.testng.Assert;

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

	@FindBy(how = How.XPATH, using = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[1]/div/div[2]/input")
	private WebElement userInput;
	@FindBy(how = How.XPATH, using = "//label[text()='User Role']/parent::div/following-sibling::div/div[@class='oxd-select-wrapper']")
	private WebElement userRole;
	@FindBy(how = How.XPATH, using = "//label[text()='Employee Name']/parent::div/following-sibling::div//input[@placeholder='Type for hints...']")
	private WebElement employee;
	@FindBy(how = How.XPATH, using = "//label[text()='Status']/parent::div/following-sibling::div/div[@class='oxd-select-wrapper']")
	private WebElement status;
	@FindBy(how = How.XPATH, using = "//div[@class='oxd-form-actions']//button[contains(normalize-space(), 'Search')]")
	private WebElement search;

	@FindBy(how = How.XPATH, using = "//button[contains(@class, 'oxd-button--label-danger')]")
	private WebElement delete;

	@FindBy(how = How.XPATH, using = "//div[@class='orangehrm-header-container']//button[contains(@class, 'oxd-button--secondary') and contains(., 'Add')]")
	private WebElement add;
	@FindBy(how = How.XPATH, using = "//label[text()='User Role']/parent::div/following-sibling::div//div[contains(@class, 'oxd-select-wrapper')]")
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

	@FindBy(how = How.XPATH, using = "//label[text()='User Role']/parent::div/following-sibling::div//div[contains(@class, 'oxd-select-wrapper')]")
	private WebElement editUserRole;
	@FindBy(how = How.XPATH, using = "//div[label[text()='Employee Name']]/following-sibling::div//input[@placeholder='Type for hints...']")
	private WebElement editEmployeename;
	@FindBy(how = How.XPATH, using = "//label[text()='Status']/parent::div/following-sibling::div//div[contains(@class, 'oxd-select-wrapper')]")
	private WebElement editUserStatus;
	@FindBy(how = How.XPATH, using = "//div[label[text()='Username']]/following-sibling::div//input")
	private WebElement editUsername;
	@FindBy(how = How.XPATH, using = "//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div/form/div[1]/div/div[5]/div/div[2]/div/label/span")
	private WebElement yesBtn;
	@FindBy(how = How.XPATH, using = "//div[label[text()='Password']]/following-sibling::div//input")
	private WebElement editUserPassword;
	@FindBy(how = How.XPATH, using = "//div[label[text()='Confirm Password']]/following-sibling::div//input")
	private WebElement editUserConfirmPassword;
	@FindBy(how = How.XPATH, using = "//span[contains(@class, 'oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message')]")
	private WebElement alreadyExisted;
	@FindBy(how = How.XPATH, using = "//div[@id='oxd-toaster_1']")
	private WebElement confirmation;
	private static Logger logger = Logger.getLogger(UserPage.class.getName());

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

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(element));
		element.clear();
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
		enterText(userInput, string);
	}

	public void selectRole(String string) {
		clickButton(userRole);
		WebElement option = findElementByXPath("//*[text()='User Role']/../../div//*[text()='" + string + "']");
		clickButton(option);
	}

	public void enterEmployeename(String string) throws InterruptedException {
		clickButton(employee);
		enterText(employee, string);
		Thread.sleep(2000);
		Actions actions = new Actions(driver);
		actions.sendKeys(Keys.ARROW_DOWN).perform();
		Thread.sleep(3000);
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
		if (string.length() < 5) {
			Assert.fail("Entered text must be at least 5 characters long.");
		}

		enterText(addUser, string);

	}

	public void addEmployee(String string) throws InterruptedException {
		clickButton(addEmployee);
		enterText(addEmployee, string);
		Actions actions = new Actions(driver);
		Thread.sleep(3000);
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

	public boolean checkIfExists(String username) {
		try {
			WebElement userElement = findElementByXPath(
					"//div[contains(@class, 'oxd-table-row--with-border')]/div[contains(@class, 'oxd-padding-cell')]/div[contains(text(), '"
							+ username + "')]");
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
			wait.until(ExpectedConditions.visibilityOf(userElement));
			return userElement != null;
		} catch (Exception e) {
			return false;
		}
	}

	public void delete(String username) throws InterruptedException {

		if (checkIfExists(username)) {
			WebElement deleteButton = findElementByXPath(
					"//div[contains(@class, 'oxd-table-row--with-border')]/div[contains(@class, 'oxd-padding-cell')]/div[contains(text(), '"
							+ username + "')]/../following-sibling::div//i[contains(@class, 'bi-trash')]");
			clickButton(deleteButton);
			clickButton(delete);
			logger.info("User " + username + " deleted successfully.");
		} else {
			logger.info("User " + username + "not found. Adding the user...");
			Add();
			addRole("Admin");
			addStatus("Enabled");
			addUser(username);
			addEmployee("Odis Adalwin");
			addPassword("xyz@123");
			addConfirmPassword("xyz@123");
			save();
			logger.info("User " + username + " added successfully.");
			WebElement deleteButton = findElementByXPath(
					"//div[contains(@class, 'oxd-table-row--with-border')]/div[contains(@class, 'oxd-padding-cell')]/div[contains(text(), '"
							+ username + "')]/../following-sibling::div//i[contains(@class, 'bi-trash')]");
			clickButton(deleteButton);
			clickButton(delete);
			logger.info("User " + username + " deleted successfully after adding.");
		}
	}

	public void edit(String string) throws InterruptedException {
		if (checkIfExists(string)) {
			WebElement editbtn = findElementByXPath(
					"//div[contains(@class, 'oxd-table-row--with-border')]/div[contains(@class, 'oxd-padding-cell')]/div[contains(text(), '"
							+ string + "')]/../following-sibling::div//i[contains(@class, 'oxd-icon bi-pencil-fill')]");

			clickButton(editbtn);
			logger.info("User " + string + " edited successfully.");
		} else {
			logger.info("User " + string + "not found. Adding the user...");
			Add();
			addRole("Admin");
			addStatus("Enabled");
			addUser(string);
			addEmployee("Bob Tester");
			Thread.sleep(3000);
			addPassword("xyz@123");
			addConfirmPassword("xyz@123");
			save();
			logger.info("User " + string + " added successfully.");
			WebElement editbtn = findElementByXPath(
					"//div[contains(@class, 'oxd-table-row--with-border')]/div[contains(@class, 'oxd-padding-cell')]/div[contains(text(), '"
							+ string + "')]/../following-sibling::div//i[contains(@class, 'oxd-icon bi-pencil-fill')]");
			clickButton(editbtn);
		}

	}

	public void editRole(String string) {
		clickButton(editUserRole);
		WebElement option = findElementByXPath("//*[text()='User Role']/../../div//*[text()='" + string + "']");
		clickButton(option);

	}

	public void editStatus(String string) {
		clickButton(editUserStatus);
		WebElement option = findElementByXPath("//*[text()='Status']/../../div//*[text()='" + string + "']");
		clickButton(option);

	}

	public void editUser(String string) throws InterruptedException {
		clickButton(editUsername);
		Thread.sleep(3000);
		Actions actions = new Actions(driver);
		actions.moveToElement(editUsername).click().keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL)
				.sendKeys(Keys.DELETE).sendKeys(string).perform();

	}

	public void editEmployee(String string) throws InterruptedException {

		clickButton(editEmployeename);

		Actions actions = new Actions(driver);
		actions.moveToElement(editEmployeename).click().keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL)
				.sendKeys(Keys.DELETE).perform();
		enterText(addEmployee, string);
		Thread.sleep(3000);
		actions.sendKeys(Keys.ARROW_DOWN).perform();
		actions.sendKeys(Keys.ENTER).perform();

	}

	public void editPassword(String string) {
		clickButton(editUserPassword);
		enterText(editUserPassword, string);

	}

	public void editConfirmPassword(String string) {
		clickButton(editUserConfirmPassword);
		enterText(editUserConfirmPassword, string);

	}

	public void clickYes() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(yesBtn));
		clickButton(yesBtn);

	}

	public String validate() throws Exception {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			wait.until(ExpectedConditions.visibilityOf(confirmation));
			String res = confirmation.getText();
			return res;
		} catch (Exception e) {

			Assert.fail("Confirmation message not found within the specified time");
			return null;
		}
	}

	public void navigateToHomePage() {
		navigateToURL("https://opensource-demo.orangehrmlive.com/");
	}

	public void navigateToAdminPage() {
		navigateToURL("https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewSystemUsers");
	}

	public void errorMessagesDisplayed() {
		try {
			String text = alreadyExisted.getText();
			if (text.equals("Already exists")) {
				Assert.fail("Already existed");

			}
		} catch (Exception e) {

		}

	}

}
