package com.pastbook.automation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

import com.pastbook.automation.core.PageBase;

/**
 * @author Akila
 *
 */
public class PastBookCreatePage extends PageBase {

	@FindBy(xpath = "//*[@id='createOptions']/div/div/button")
	private WebElement createOption_popup_close_button;

	@FindBy(xpath = "//*[@id='create']/div[6]/div[6]/div/div[1]/text()[3]")
	private WebElement createpage_openingtext_element;

	@CacheLookup
	@FindBy(xpath = "//*[@id='create']/nav/header/ul[1]")
	private WebElement side_menu_button;

	public PastBookCreatePage(WebDriver driver) {
		super(driver);
	}

	@Override
	public boolean getPageAvailability() {
		return getPageAvailability(createpage_openingtext_element);
	}

	public WebElement getCreatepage_openingtext_element() {
		return createpage_openingtext_element;
	}

	public WebElement getCreateOption_popup_close_button() {
		return createOption_popup_close_button;
	}

	public PastBookCreatePage closeCreateOptionPopup() {
		createOption_popup_close_button.click();
		return this;
	}

	public SideMenuPage navigateToSideMenu() {
		side_menu_button.click();
		SideMenuPage sideMenuPage = new SideMenuPage(driver);
		return sideMenuPage;
	}

}
