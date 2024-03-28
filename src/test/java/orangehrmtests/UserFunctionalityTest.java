package orangehrmtests;

import java.io.IOException;
import java.util.logging.Logger;

import org.testng.annotations.Test;

import com.user.automation.common.pages.UserPage;
import com.user.automation.common.utils.SupportBrowser;

public class UserFunctionalityTest extends SupportBrowser {
	private static Logger logger = Logger.getLogger(UserFunctionalityTest.class.getName());

	@Test
	public void testLogin() throws IOException {
		UserPage userPage = getUserPage();
		//userPage.navigateToURL("https://opensource-demo.orangehrmlive.com/");
		userPage.navigateToHomePage();
		userPage.login("Admin", "admin123");
		if (userPage.isElementDisplayed(userPage.getProfileImage())) {
			logger.info("Successfully logged in");
		} else {
			logger.info("Login failed");
		}

	}

	@Test
	public void testUserSearch() throws Exception {
		UserPage userPage = getUserPage();
		userPage.navigateToAdminPage();
		userPage.login("Admin", "admin123");
		userPage.enterUsername("nalim");
		userPage.selectRole("ESS");
		userPage.enterEmployeename("Bob Tester");
		userPage.selectStatus("Enabled");
		userPage.search();
		String result = userPage.validate();
		logger.info(result);
	}

	@Test
	public void testUserAdd() throws Exception {
		UserPage userPage = getUserPage();
		userPage.navigateToAdminPage();
		userPage.login("Admin", "admin123");
		userPage.Add();
		userPage.addRole("Admin");
		userPage.addStatus("Enabled");
		userPage.addUser("HarshithaM");
		userPage.errorMessagesDisplayed();
		userPage.addEmployee("Bob Tester");
		userPage.addPassword("xyz@123");
		userPage.addConfirmPassword("xyz@123");
		userPage.save();
		String result = userPage.validate();
		logger.info(result);

	}

	@Test
	public void testDeleteUser() throws Exception {
		UserPage userPage = getUserPage();
		userPage.navigateToAdminPage();
		userPage.login("Admin", "admin123");
		userPage.delete("Darell123");
		String result = userPage.validate();
		logger.info(result);

	}

	@Test
	public void testEditUser() throws Exception {
		UserPage userPage = getUserPage();
		userPage.navigateToAdminPage();
		userPage.login("Admin", "admin123");
		userPage.edit("nalimnsn");
		userPage.editRole("Admin");
		userPage.editStatus("Enabled");
		userPage.clickYes();
		userPage.editPassword("xyz@123");
		userPage.editConfirmPassword("xyz@123");
		Thread.sleep(3000);
		userPage.save();
		Thread.sleep(3000);
		String result = userPage.validate();
		logger.info(result);

	}
}