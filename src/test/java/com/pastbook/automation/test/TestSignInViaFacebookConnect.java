package com.pastbook.automation.test;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.pastbook.automation.core.UITestBase;
import com.pastbook.automation.pages.FacebookPage;
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
public class TestSignInViaFacebookConnect extends UITestBase {

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
		result.setAttribute("TestName", "Verify if a user will be able to Sign in Via Facebook Connect");
		result.setAttribute("Expected", "Should be logged in Successfully via Facebook");
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

					FacebookPage fbPag = signinpage.navigateToFacebookLoginPage();
					if (fbPag.getPageAvailability()) {

						fbPag.enterFbUserName(signInData.getUserName());
						fbPag.enterPassword(signInData.getPassword());
						fbPag.clickLoginbutton();

						if (fbPag.getPageAvailability(fbPag.getFacebook_continue_button())) {

							PastBookCreatePage createPage = null;

							try {
								createPage = fbPag.navigateToPastbookCreatePage();

							} catch (Exception e) {
								e.printStackTrace();
							}

							if (createPage.getPageAvailability()) {
								String expectedCcreatePageText = " Hey! It seems you have not created any PastBook yet, go ahead!";

								if (expectedCcreatePageText.equals(createPage.getCreatepage_openingtext_element().getText())) {
									result.setAttribute("Actual", "Sign in Successfully via Facebook and redirect to Create Pastbook Page");
								} else {
									result.setAttribute("Actual","User Canno't SignIn via Facebook ");
									Assert.fail("User Canno't SignIn via Facebook");
								}

							} else {
								result.setAttribute("Actual", "Pastbook Create Page is not Loading");
								Assert.fail("Pastbook Create Page is not Loading");
							}

						} else {
							result.setAttribute("Actual", "App Permission popup Not Loaded in the Popup Window");
							Assert.fail("App Permission popup Not Loaded in the Popup Window");
						}

					}else {
						result.setAttribute("Actual", "facebook login page Not Loaded in the Popup Window");
						Assert.fail("facebook login page Not Loaded in the Popup Window");
					}

				} else {
					result.setAttribute("Actual", "SignInPage Not Loaded in the Popup Window");
					Assert.fail("SignInPage Not Loaded in the Popup Window");
				}
			}else {
				result.setAttribute("Actual", "SideMenu is not Displayed");
				Assert.fail("SideMenu is not Displayed");
			}
		} catch (Exception e) {
			result.setAttribute("Actual", "testPassbookWebAppkicationAvailability execution error due to ");
			Assert.fail("testPassbookWebAppkicationAvailability execution error due to <br>", e);
		}
	}

	
	
	@AfterClass
	public void tearDown() {
		try {
			driver.wait(10000);
			driver.quit();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	public SignIn getTestData(String excelFilePath, String SheetNum) throws Exception {

		DataLoader dataLoader = new DataLoader();
		signInData = dataLoader.getSignInData(new ExcelDataHandler().getExcelData(excelFilePath, SheetNum))[2][0];
		return signInData;
	}

}
