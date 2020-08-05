package com.pastbook.automation.test;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.pastbook.automation.core.UITestBase;
import com.pastbook.automation.pages.HomePage;
import com.pastbook.automation.pages.PastBookCreatePage;
import com.pastbook.automation.pages.SideMenuPage;
import com.pastbook.automation.pages.SignInPage;
import com.pastbook.automation.pojo.SignIn;
import com.pastbook.automation.util.ConfigUtil;
import com.pastbook.automation.util.DataLoader;
import com.pastbook.automation.util.ExcelDataHandler;

/**
 * @author Akila
 *
 */
public class TestSignInFlowWithExsistingEmailUser extends UITestBase {

	SignIn signInData = new SignIn();
	HomePage homepage;
	SideMenuPage sidemenu;
	SignInPage signinpage;

	@Parameters({ "TestUrl", "excelSheetName" })
	@BeforeClass
	public void setupTest(String testUrl, String excelSheetName) {
		try {
			driver.get(testUrl);
			getTestData(ConfigUtil.getConfigUtil().getProperty("excel.path"), excelSheetName);

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	@Test(priority = 0)
	public void testPassbookWebAppkicationAvailability() {

		/******************************************************************/
		ITestResult result = Reporter.getCurrentTestResult();
		result.setAttribute("TestName",
				"Verify if a user will be able to login with a valid username and valid password for Exsisting users");
		result.setAttribute("Expected", "Should be logged in Successfully and Should display Create Pastbook Page");
		/******************************************************************/
		try {

			homepage = new HomePage(driver);
			sidemenu = homepage.navigateToSideMenu();
			if (sidemenu.getPageAvailability()) {

				signinpage = sidemenu.navigateToSignInPage();
				if (signinpage == null) {
					result.setAttribute("Actual", "SignInPage Not pop up");
					Assert.fail("SignInPage Not pop up");

				} else if (signinpage.getPageAvailability()) {
					signinpage.clickUserEmailLink();
					signinpage.enterEmail(signInData.getUserName());
					signinpage.clickEmailSubmitButton();
					signinpage.clickPasswordField();
					signinpage.enterPasswordField(signInData.getPassword());
					signinpage.clickPasswordSubmitButton();
					PastBookCreatePage createPage = null;
					
					try {
						createPage = signinpage.navigateToPastbookCreatePage();
					} catch (Exception e) {
						e.printStackTrace();
					}

					if (createPage.getPageAvailability()) {
						String expectedCcreatePageText = " Hey! It seems you have not created any PastBook yet, go ahead!";

						if (expectedCcreatePageText.equals(createPage.getCreatepage_openingtext_element().getText())) {
							result.setAttribute("Actual",
									"logged in Successfully and Should display Create Pastbook Page");
						} else {
							result.setAttribute("Actual", "Not Displaying Pastbook Create page for exsisting User");
							Assert.fail("Not Displaying Pastbook Create page for exsisting User");
						}

					} else {
						result.setAttribute("Actual", "SignInPage Not Loaded in the Popup Window");
						Assert.fail("SignInPage Not Loaded in the Popup Window");
					}

				} else {
					result.setAttribute("Actual", "SignInPage Not Loaded in the Popup Window");
					Assert.fail("SignInPage Not Loaded in the Popup Window");
				}

			} else {
				result.setAttribute("Actual", "SideMenu is not Displayed");
				Assert.fail("SideMenu is not Displayed");
			}
		} catch (Exception e) {
			result.setAttribute("Actual", "testPassbookWebAppkicationAvailability execution error due to ");
			Assert.fail("testPassbookWebAppkicationAvailability execution error due to <br>", e);
		}
	}

	public SignIn getTestData(String excelFilePath, String SheetNum) throws Exception {

		DataLoader dataLoader = new DataLoader();
		signInData = dataLoader.getSignInData(new ExcelDataHandler().getExcelData(excelFilePath, SheetNum))[0][0];
		return signInData;
	}

}
