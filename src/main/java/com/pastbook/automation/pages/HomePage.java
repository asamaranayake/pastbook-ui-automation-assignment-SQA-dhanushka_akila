package com.pastbook.automation.pages;

/**
 * @author Akila
 *
 */
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

import com.pastbook.automation.core.PageBase;

public class HomePage extends PageBase {

	@FindBy(xpath = "//*[@id='home']/div[6]/div[5]/a[1]/img")
	private WebElement passBookLogo;

	@CacheLookup
	@FindBy(xpath = "//*[@id='home']/nav/header/ul[1]")
	private WebElement sideMenu_button;

	public HomePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public SideMenuPage navigateToSideMenu() {

		SideMenuPage sidemenuPage = new SideMenuPage(driver);
		
		this.sideMenu_button.click();

		return sidemenuPage;
	}

	@Override
	public boolean getPageAvailability() {
		return getPageAvailability(passBookLogo);
	}

}
