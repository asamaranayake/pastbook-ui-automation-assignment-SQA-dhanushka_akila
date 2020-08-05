package com.pastbook.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.pastbook.automation.core.PageBase;

/**
 * @author Akila
 *
 */
public class SideMenuPage extends PageBase {

	@FindBy(xpath = "//*[@id='sidemenu' and @class='active']")
	private WebElement sidemenu_element;

	@FindBy(xpath = "//*[@id='sidemenu']/div/a[7]")
	private WebElement signIn_button;

	@FindBy(xpath = "//*[@id='sidemenu']/div/a[9]")
	private WebElement signOut_button;

	public SideMenuPage(WebDriver driver) {
		super(driver);
	}

	@Override
	public boolean getPageAvailability() {

		return getPageAvailability(sidemenu_element);
	}

	public WebElement getSignInButtonElement() {

		return this.signIn_button;
	}

	public SignInPage navigateToSignInPage() {
		SignInPage signInPage = null;
		parentWindow = driver.getWindowHandle();

		this.signIn_button.click();
		windows = driver.getWindowHandles();

		if (windows.size() == 2) {

			for (String window : windows) {
				if (!parentWindow.equalsIgnoreCase(window)) {
					driver.switchTo().window(window);
					signInPage = new SignInPage(driver);
					break;
				}
			}

			return signInPage;
		} else {
			return signInPage;

		}

	}

	public HomePage clickSignOutButton() {
		signOut_button.click();
		HomePage homepage = new HomePage(driver);

		return homepage;

	}

}
