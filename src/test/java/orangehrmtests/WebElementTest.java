package orangehrmtests;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import com.user.automation.common.pages.TestPage;
import com.user.automation.common.utils.SupportBrowser;

public class WebElementTest extends SupportBrowser {
	TestPage testPage;

	@Test
	public void txtBoxTest() throws Throwable {
		testPage = getTestPage();
		testPage.navigateToLink();
		testPage.clickonTextBox();
		testPage.enterDeatilsOfTextBox("Man", "12@gmail.com", "", "123");
		testPage.validateTextBox();
	}

	@Test
	public void radioBtnTest() throws Throwable {
		testPage = getTestPage();
		testPage.navigateToLink();
		testPage.clickRadioBtn();
		testPage.selectRadioBtn("Yes");
		testPage.validateRadio();
	}

	@Test
	public void buttonTest() throws Throwable {
		List<String> buttonList = new ArrayList<>();
		buttonList.add("doubleClick");
		buttonList.add("rightClick");
		buttonList.add("dynamicClick");
		testPage = getTestPage();
		testPage.navigateToLink();
		testPage.clickOnButtons();
		testPage.clickButtons(buttonList);

	}
}
