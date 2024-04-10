package orangehrmtests;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.testng.annotations.Test;

import com.user.automation.common.pages.TestPage;
import com.user.automation.common.utils.SupportBrowser;

public class WebElementTest extends SupportBrowser {
	TestPage testPage;

	@Test
	public void txtBoxTest() throws Throwable {
		testPage = getTestPage();
		testPage.navigateToLink();
		Thread.sleep(3000);
		testPage.clickonTextBox();
		Random random = new Random();
		int randomNumber = random.nextInt(999);
		//		testPage.enterDeatilsOfTextBox("Manaasa", "12@gmail.com", "Abc", "123");
		//		testPage.validateTextBox("Manaasasa", "12@gmail.com", "Abc", "123");
		String constantPart = "manasa";
		String constantAddressBeforePart = "h.no-";
		String constantAddressAfterPart = ",dollars colony, bangalore";
		String randomAddressPart = constantAddressBeforePart + randomNumber + constantAddressAfterPart;
		String randomName = constantPart + testPage.generateRandomString(4);
		String randomEmail = randomName + "@gmail.com";
		testPage.enterDeatilsOfTextBox(randomName, randomEmail, randomAddressPart, randomAddressPart);
		testPage.extractDetails();
	}

	@Test
	public void radioBtnTest() throws Throwable {
		testPage = getTestPage();
		testPage.navigateToLink();
		Thread.sleep(3000);
		testPage.clickRadioBtn();
		testPage.selectRadioBtn("Impressive");

	}

	@Test
	public void buttonTest() throws Throwable {
		List<String> buttonList = new ArrayList<>();
		buttonList.add("doubleClick");
		buttonList.add("rightClick");
		buttonList.add("dynamicClick");
		testPage = getTestPage();
		testPage.navigateToLink();
		Thread.sleep(3000);
		testPage.clickOnButtons();
		testPage.clickButtons(buttonList);
		testPage.validateButton(buttonList);

	}
}
