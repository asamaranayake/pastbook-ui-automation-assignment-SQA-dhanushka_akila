package com.pastbook.automation.pages;

import java.util.Base64;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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

	@FindBy(xpath = "//*[@id='pass']")
	private WebElement facebook_password_textbox;

	@FindBy(xpath = "//*[@id='loginbutton']")
	private WebElement Login_as_facebook;

	@FindBy(xpath = "//*[@id='u_0_15']/div[2]/div[1]/div[1]/button")
	private WebElement continue_button;

	@FindBy(xpath = "//*[@id='u_0_0']")
	private WebElement cancel_button;

	public FacebookPage(WebDriver driver) {
		super(driver);
	}

	@Override
	public boolean getPageAvailability() {
		// TODO Auto-generated method stub
		return false;
	}

	public PastBookCreatePage navigateToPastbookCreatePage() throws Exception {
		PastBookCreatePage createPage = null;

		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.numberOfWindowsToBe(1));

		if (windows.size() == 1) {
			driver.switchTo().window(parentWindow);
			createPage = new PastBookCreatePage(driver);
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

	public FacebookPage enterFbUserName(String fbUN) {

		facebook_email_textbox.clear();
		facebook_email_textbox.click();
		facebook_email_textbox.sendKeys(fbUN);

		return this;
	}

	public FacebookPage enterPassword(String fbPW) {
		facebook_password_textbox.clear();
		facebook_password_textbox.click();
		facebook_password_textbox.sendKeys(new String(Base64.getDecoder().decode(fbPW)));

		return this;
	}

	public PastBookCreatePage facebookLogingIntoCreatePage(String fbUN, String fbPW) throws Exception {

		return navigateToPastbookCreatePage();

	}

	public PastBookPreviewPage facebookLogingIntoPreviewPage(String fbUN, String fbPW) throws Exception {

		return navigateToPastbookPreviewPage();

	}

}
