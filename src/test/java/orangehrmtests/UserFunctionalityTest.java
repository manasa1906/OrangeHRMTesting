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
		Thread.sleep(3000);
		userPage.enterUsername("salim011");
		Thread.sleep(3000);
		userPage.selectRole("ESS");
		userPage.enterEmployeename("Manav");
		userPage.selectStatus("Enabled");
		userPage.search();
		/*//	String result = userPage.searchResult();
		//	logger.info(result);
		*/ }

	@Test
	public void testUserAdd() throws InterruptedException {
		UserPage userPage = getUserPage();
		userPage.navigateToURL("https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewSystemUsers");
		userPage.login("Admin", "admin123");
		userPage.Add();
		userPage.addRole("Admin");
		userPage.addStatus("Enabled");
		userPage.addUser("Manasa");
		/*	if (userPage.getErrorMessageElement() == null) {
				Assert.fail("Error message 'Already exists' is not present.");
			}*/
		userPage.addEmployee("Rahul Das");
		userPage.addPassword("xyz@123");
		userPage.addConfirmPassword("xyz@123");
		userPage.save();

	}

	@Test
	public void testDeleteUser() throws InterruptedException {
		UserPage userPage = getUserPage();
		userPage.navigateToURL("https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewSystemUsers");
		userPage.login("Admin", "admin123");
		userPage.delete("Manasa");
		Thread.sleep(3000);
	}
}