package com.pastbook.automation.test;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.pastbook.automation.core.UITestBase;
import com.pastbook.automation.pages.HomePage;
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
	HomePage homepage ;
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
		result.setAttribute("TestName", "Login Test");
		result.setAttribute("Expected", "Should be logged in Successfully");
		/******************************************************************/
		
		homepage = new HomePage(driver);
		sidemenu = homepage.navigateToSideMenu();
		if(sidemenu.getPageAvailability()) {
			
			signinpage = sidemenu.navigateToSignInPage();
			if(signinpage == null) {
				result.setAttribute("Actual","SignInPage Not pop up");
				Assert.fail("SignInPage Not pop up");
			}else {
				
				
			}
			
		}else {
			result.setAttribute("Actual","SideMenu is not Displayed");
			Assert.fail("SideMenu is not Displayed");
		}
		
	}

	public SignIn getTestData(String excelFilePath, String SheetNum) throws Exception {

		DataLoader dataLoader = new DataLoader();
		signInData = dataLoader.getSignInData(new ExcelDataHandler().getExcelData(excelFilePath, SheetNum))[1][0];
		return signInData;
	}

}
