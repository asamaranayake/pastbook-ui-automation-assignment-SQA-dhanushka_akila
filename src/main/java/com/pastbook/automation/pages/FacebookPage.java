package com.pastbook.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.pastbook.automation.core.PageBase;

/**
 * @author Akila
 *
 */
public class FacebookPage extends PageBase {

	@FindBy(xpath = "//*[@id='u_0_14']/div[2]/div[1]/div[1]/button")
	private WebElement facebook_continue_button;

	@FindBy(xpath = "//*[@id='blueBarDOMInspector']/div/div[1]/div/div/h1/a")
	private WebElement facebook_logo_img;

	@FindBy(xpath = "//*[@id='email']")
	private WebElement facebook_email_textbox;

	@FindBy(xpath = "//*[@id='loginbutton']")
	private WebElement Login_as_facebook;

	public FacebookPage(WebDriver driver) {
		super(driver);
	}

	@Override
	public boolean getPageAvailability() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public PassBookCreatePage navigateToPastbookCreatePage() throws Exception {
		PassBookCreatePage createPage = null;

		if (windows.size() == 1) {
			driver.switchTo().window(parentWindow);
			createPage = new PassBookCreatePage(driver);
		} else {
			throw new Exception("Facebook SignIn Window is not Closed");
		}

		return createPage;
	}

	public PastBookPreviewPage navigateToPastbookPreviewPage() throws Exception {
		PastBookPreviewPage previewPage = null;

		if (windows.size() == 1) {
			driver.switchTo().window(parentWindow);
			previewPage = new PastBookPreviewPage(driver);
		} else {
			throw new Exception("Facebook SignIn Window is not Closed");
		}

		return previewPage;
	}
	

	public PassBookCreatePage facebookLogingIntoCreatePage(String fbUN, String fbPW) throws Exception {

		return navigateToPastbookCreatePage();

	}

	public PastBookPreviewPage facebookLogingIntoPreviewPage(String fbUN, String fbPW) throws Exception {

		return navigateToPastbookPreviewPage();

	}

}
