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
		userPage.navigateToURL("https://opensource-demo.orangehrmlive.com/");
		userPage.login("Admin", "admin123");
		if (userPage.isElementDisplayed(userPage.getProfileImage())) {
			logger.info("Successfully logged in");
		} else {
			logger.info("Login failed");
		}

	}

	@Test
	public void testUserSearch() throws InterruptedException {
		UserPage userPage = getUserPage();
		userPage.navigateToURL("https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewSystemUsers");
		userPage.login("Admin", "admin123");
		userPage.enterUsername("nalim");
		userPage.selectRole("ESS");
		userPage.enterEmployeename("Odis Adalwin");
		userPage.selectStatus("Enabled");
		userPage.search();
		String result = userPage.validate();
		logger.info(result);
	}

	@Test
	public void testUserAdd() throws InterruptedException {
		UserPage userPage = getUserPage();
		userPage.navigateToURL("https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewSystemUsers");
		userPage.login("Admin", "admin123");
		userPage.Add();
		userPage.addRole("Admin");
		userPage.addStatus("Enabled");
		userPage.addUser("HarshithaM");
		userPage.errorMessagesDisplayed();
		userPage.addEmployee("Odis Adalwin");
		userPage.addPassword("xyz@123");
		userPage.addConfirmPassword("xyz@123");
		userPage.save();
		String result = userPage.validate();
		logger.info(result);

	}

	@Test
	public void testDeleteUser() throws InterruptedException {
		UserPage userPage = getUserPage();
		userPage.navigateToURL("https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewSystemUsers");
		userPage.login("Admin", "admin123");
		userPage.delete("HarshithaM");

		String result = userPage.validate();
		logger.info(result);

	}

	@Test
	public void testEditUser() throws InterruptedException {
		UserPage userPage = getUserPage();
		userPage.navigateToURL("https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewSystemUsers");
		userPage.login("Admin", "admin123");
		userPage.edit("nalim");
		userPage.editRole("Admin");
		userPage.editStatus("Enabled");
		userPage.editEmployee("Odis Adalwin");
		userPage.editUser("Manasa sai");
		Thread.sleep(3000);
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