package com.user.automation.common.pages;

import java.time.Duration;
import java.util.List;
import java.util.Random;

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
	@FindBy(how = How.XPATH, using = "(//button[@type='button'])[4]")
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

	public void validateTextBox(String name, String email, String curr, String perm) {

		for (WebElement element : list) {

			System.out.println(element.getText());
			if (name != null) {
				String ExpectedResult = "Name:" + name;
				if (element.getText().equals(ExpectedResult)) {
					System.out.println("Name is validated");
				}
			}
			if (email != null) {
				String ExpectedResult = "Email:" + email;
				if (element.getText().equals(ExpectedResult)) {
					System.out.println("Email is validated");
				}
			}
			if (curr != null) {
				String ExpectedResult = "Current Address :" + curr;
				if (element.getText().equals(ExpectedResult)) {
					System.out.println("Current Address  is validated");
				}
			}
			if (perm != null) {
				String ExpectedResult = "Permananet Address :" + perm;
				if (element.getText().equals(ExpectedResult)) {
					System.out.println("Permananet Address  is validated");
				}
			}
		}
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
		validateRadio(string);

	}

	public void validateRadio(String option) {
		try {
			System.out.println(validateRadioBtn.getText());
			if (option.equals("Yes")) {
				String ExpectedRes = "You have selected Yes";
				if (((validateRadioBtn.getText()).equals(ExpectedRes))) {
					System.out.println("yes is validated");

				}
			}
			if (option.equals("Impressive")) {
				String ExpectedRes = "You have selected Impressive";
				if (((validateRadioBtn.getText()).equals(ExpectedRes))) {
					System.out.println("Impressive is validated");

				}
			}
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
					//	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", dynamicClickBtn);
					JSClick(dynamicClickBtn);
				} else {
					System.out.println("Invalid action specified");

				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void validateButton(List<String> actionsList) {
		try {

			for (WebElement element : validateButtonList) {

				System.out.println(element.getText());
				for (String action : actionsList) {
					if ("doubleClick".equals(action)) {
						String ExpectedResult = "You have done a double click";
						if (element.getText().equals(ExpectedResult)) {
							System.out.println("DoubleClick is validated");
						}
					}
					if ("rightClick".equals(action)) {
						String ExpectedResult = "You have done a right click";
						if (element.getText().equals(ExpectedResult)) {
							System.out.println("RightClick is validated");
						}
					}
					if ("dynamicClick".equals(action)) {
						String ExpectedResult = "You have done a dynamic click";
						if (element.getText().equals(ExpectedResult)) {
							System.out.println("DynamicClick is validated");
						}
					}
				}

			}

		} catch (Exception e) {
			Assert.fail("Not slected any of the buttons");
		}
	}

	public String generateRandomString(int length) {
		Random random = new Random();
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			sb.append(characters.charAt(random.nextInt(characters.length())));
		}
		return sb.toString();
	}

	public void extractDetails() {
		String givenUser = userInp.getAttribute("value");
		String givenEmail = emailInp.getAttribute("value");
		String givencurrAdd = currentAddressInp.getAttribute("value");
		String givenPermAdd = permanentAdressInp.getAttribute("value");
		validateTextBox(givenUser, givenEmail, givencurrAdd, givenPermAdd);
	}
}
