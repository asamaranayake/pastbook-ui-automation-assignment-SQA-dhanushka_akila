package com.pastbook.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.pastbook.automation.core.PageBase;

/**
 * @author Akila
 *
 */
public class PassBookCreatePage extends PageBase {

	@FindBy(xpath = "/html/body/div[3]/div/img")
	private WebElement signInPageLogoImage;

	@FindBy(xpath = "/html/body/div[4]/div/div/div[2]/small[2]")
	private WebElement signInPagedefult_text;

	@FindBy(xpath = "/html/body/div[4]/div/div/div[1]/div[2]/a")
	private WebElement use_email_link;

	@FindBy(xpath = "//input[@type='email']")
	private WebElement email_textbox;

	@FindBy(xpath = "//button[@type='submit']")
	private WebElement email_submit_button;

	@FindBy(xpath = "//div[@class='facebook-login']/a")
	private WebElement facebook_login_button;

	@FindBy(xpath = "//input[@type='password']")
	private WebElement password_textbox;

	public PassBookCreatePage(WebDriver driver) {
		super(driver);
	}

	@Override
	public boolean getPageAvailability() {
		return getPageAvailability(signInPageLogoImage);
	}

	public WebElement getFacebook_login_button() {
		return facebook_login_button;
	}

	public PassBookCreatePage clickUserEmailLink() {
		use_email_link.click();
		return this;
	}
	
	public PassBookCreatePage enterEmail(String userName) {
		email_textbox.sendKeys(userName);
		return this;
	}
	
	public PassBookCreatePage clickEmailSubmitButton() {
		email_submit_button.submit();
		return this;
	}

}
