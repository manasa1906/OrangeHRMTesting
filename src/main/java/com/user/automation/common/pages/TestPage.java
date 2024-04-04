package com.user.automation.common.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.user.automation.common.utils.SupportBrowser;

public class TestPage extends SupportBrowser {

	@FindBy(how = How.XPATH, using = "//div[@class='element-list collapse show']//li[@id='item-0']")
	private WebElement textBox;
	@FindBy(how = How.XPATH, using = "//input[@id='userName']")
	private WebElement userInp;
	@FindBy(how = How.XPATH, using = "//input[@id='userEmail']")
	private WebElement emailInp;
	@FindBy(how = How.XPATH, using = "//textarea[@id='currentAddress']")
	private WebElement currentAddressInp;
	@FindBy(how = How.XPATH, using = "//textarea[@id='permanentAddress']")
	private WebElement permanentAdressInp;
	@FindBy(how = How.XPATH, using = "//button[@id='submit']")
	private WebElement submit;
	private WebDriver driver;
	@FindBy(how = How.XPATH, using = "//div//p[@class='mb-1']")
	private List<WebElement> list;
	@FindBy(how = How.XPATH, using = "//div[@class='element-list collapse show']//li[@id='item-2']")
	private WebElement radioBtn;
	@FindBy(how = How.XPATH, using = "//p[@class='mt-3']")
	private WebElement validateRadioBtn;
	@FindBy(how = How.XPATH, using = "//div[@class='element-list collapse show']//li[@id='item-4']")
	private WebElement buttons;
	@FindBy(how = How.XPATH, using = "//button[@id ='doubleClickBtn']")
	private WebElement doubleClickBtn;
	@FindBy(how = How.XPATH, using = "//button[@id ='rightClickBtn']")
	private WebElement rightClickBtn;
	@FindBy(how = How.XPATH, using = "//button[@id ='gyayS']")
	private WebElement dynamicClickBtn;
	@FindBy(how = How.XPATH, using = "//div[@class='mt-4']//../p")
	private List<WebElement> validateButtonList;

	public TestPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void navigateToLink() {
		driver.get("https://demoqa.com/elements");
	}

	public void clickonTextBox() {
		//textBox.click();
		JSClick(textBox);
	}

	public void enterDeatilsOfTextBox(String username, String mail, String currAddress, String permAddress)
			throws Throwable {

		userInp.sendKeys(username);

		if (!isValidEmail(mail)) {
			Assert.fail("Invalid email format. Please enter a valid Gmail address.");
		}
		emailInp.sendKeys(mail);
		currentAddressInp.sendKeys(currAddress);
		permanentAdressInp.sendKeys(permAddress);
		//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		//		wait.until(ExpectedConditions.elementToBeClickable(submit));
		JSClick(submit);

	}

	public void validateTextBox() {

		for (WebElement element : list) {

			System.out.println(element.getText());
		}
		System.out.println("Entered details successfully");
	}

	private boolean isValidEmail(String email) {
		String regex = "^[A-Za-z0-9_][A-Za-z0-9._]*@gmail\\.com$";
		return email.matches(regex);
	}

	public void clickRadioBtn() {
		//radioBtn.click();
		JSClick(radioBtn);

	}

	public void JSClick(WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
	}

	public void selectRadioBtn(String string) throws Throwable {
		if (!string.equals("Yes") && !string.equals("No") && !string.equals("Impressive")) {
			Assert.fail("Invalid action specified: " + string);
		}
		if (string.equals("No")) {
			Assert.fail("cannot select No option");
		}
		WebElement SelectRadioBtn = findByXpath("//label[contains(text(),'" + string + "')]");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(SelectRadioBtn));
		JSClick(SelectRadioBtn);

	}

	public void validateRadio() {
		try {
			System.out.println(validateRadioBtn.getText());
		} catch (Exception e) {
			Assert.fail("Not selected any of the radio Buttons");
		}

	}

	private WebElement findByXpath(String string) {
		return driver.findElement(By.xpath(string));

	}

	public void clickOnButtons() throws Throwable {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.elementToBeClickable(buttons));
		//	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", buttons);

		JSClick(buttons);

	}

	public void clickButtons(List<String> actionsList) {
		Actions actions = new Actions(driver);
		try {
			for (String action : actionsList) {
				if ("doubleClick".equals(action)) {
					actions.doubleClick(doubleClickBtn).perform();
				} else if ("rightClick".equals(action)) {
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", rightClickBtn);
					actions.contextClick(rightClickBtn).perform();
				} else if ("dynamicClick".equals(action)) {
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", dynamicClickBtn);
					JSClick(dynamicClickBtn);
				} else {
					System.out.println("Invalid action specified");

				}
				validateButton();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void validateButton() {
		try {

			for (WebElement element : validateButtonList) {

				System.out.println("res" + element.getText());
			}

		} catch (Exception e) {
			Assert.fail("Not slected any of the buttons");
		}
	}
}
