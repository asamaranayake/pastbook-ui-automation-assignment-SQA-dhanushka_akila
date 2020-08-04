package com.pastbook.automation.pages;

import java.util.Base64;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.pastbook.automation.core.PageBase;

/**
 * @author Akila
 *
 */
public class SignInPage extends PageBase {

	@FindBy(xpath = "/html/body/div[3]/div/img")
	private WebElement signInPageLogoImage;

	@FindBy(xpath = "/html/body/div[4]/div/div/div[2]/small[2]")
	private WebElement signInPagedefult_text;

	@FindBy(xpath = "/html/body/div[4]/div/div/div[1]/div[2]/a")
	private WebElement use_email_link;

	@FindBy(xpath = "//input[@type='email']")
	private WebElement email_textbox;

	@FindBy(xpath = "//button[@type='submit']")
	private WebElement submit_button;

	@FindBy(xpath = "//div[@class='facebook-login']/a")
	private WebElement facebook_login_button;

	@FindBy(id = "form-static-email")
	private WebElement entered_email_element;

	@FindBy(xpath = "//input[@type='password']")
	private WebElement password_textbox;

	public SignInPage(WebDriver driver) {
		super(driver);
	}

	@Override
	public boolean getPageAvailability() {
		return getPageAvailability(signInPageLogoImage);
	}

	public WebElement getFacebook_login_button() {
		return facebook_login_button;
	}

	public SignInPage clickUserEmailLink() {
		use_email_link.click();
		return this;
	}

	public SignInPage enterEmail(String userName) {
		email_textbox.sendKeys(userName);
		return this;
	}

	public SignInPage clickEmailSubmitButton() {
		submit_button.submit();
		return this;
	}

	public SignInPage clickPasswordField() {
		password_textbox.click();
		password_textbox.clear();
		return this;
	}

	public SignInPage enterPasswordField(String password) {
		password_textbox.sendKeys(new String(Base64.getDecoder().decode(password)));
		return this;
	}

	public SignInPage clickPasswordSubmitButton() {
		submit_button.submit();
		return this;
	}
	
	
	
	

	public PassBookCreatePage navigateToPastbookCreatePage() throws Exception {
		PassBookCreatePage createPage = null;

		if (windows.size() == 1) {
			driver.switchTo().window(parentWindow);
			createPage = new PassBookCreatePage(driver);
		} else {
			throw new Exception("SignIn Window is not Closed");
		}

		return createPage;
	}

	public PastBookPreviewPage navigateToPastbookPreviewPage() throws Exception {
		PastBookPreviewPage previewPage = null;

		if (windows.size() == 1) {
			driver.switchTo().window(parentWindow);
			previewPage = new PastBookPreviewPage(driver);
		} else {
			throw new Exception("SignIn Window is not Closed");
		}

		return previewPage;
	}
	
	
	public FacebookPage navigateToFacebookLoginPage() {
		
		
		FacebookPage fbpage = new FacebookPage(driver);
		return fbpage;
	}

	

	public PassBookCreatePage emailLogingIntoCreatePage(String UN, String PW) throws Exception {

		return navigateToPastbookCreatePage();
	}

	public PastBookPreviewPage emailLogingIntoPreviewPage(String UN, String PW) throws Exception {

		return navigateToPastbookPreviewPage();
	}

}
