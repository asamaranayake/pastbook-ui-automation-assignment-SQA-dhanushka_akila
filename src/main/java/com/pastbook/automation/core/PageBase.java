package com.pastbook.automation.core;


/**
 * @author Akila
 *
 */
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.pastbook.automation.util.ConfigUtil;

/**
 * Hello world!
 *
 */
public abstract class PageBase {

	protected WebDriver driver;
	protected String parentWindow;
	protected Set<String> windows;

	public PageBase(WebDriver driver) {
		String timeout = ConfigUtil.getConfigUtil().getProperty("default.timeout");
		this.driver = driver;
		PageFactory.initElements(driver, this);
		driver.manage().timeouts().implicitlyWait(Long.parseLong(timeout.trim()), TimeUnit.SECONDS);
	}

	public String getPageTitel() {
		return driver.getTitle();
	}

	public abstract boolean getPageAvailability();

	public boolean getPageAvailability(WebElement element) {

		WebDriverWait wait = new WebDriverWait(driver, 30);
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
			return true;
		} catch (Exception e) {
			return false;
		}

	};

	public void uploadFile(String ImagePath, WebElement element) throws Exception {

		try {

			element.click();
			String imagePath = new File(ImagePath).getAbsolutePath();
			StringSelection ss = new StringSelection(imagePath);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
			Robot robot = new Robot();
			Thread.sleep(2000);
			if (System.getProperty("os.name").equalsIgnoreCase("Linux")) {
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_L);
				robot.keyRelease(KeyEvent.VK_L);
				robot.keyRelease(KeyEvent.VK_CONTROL);
			}
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			Thread.sleep(1000);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clickESCButton() throws Exception {
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_ESCAPE);
	
		
	}
	
	
	public PageBase refreshPage() {

		driver.navigate().refresh();
		return this;
	}

	public WebDriver switchWindows(String pageTitle) {
		Set<String> windows = driver.getWindowHandles();

		for (String window : windows) {
			

			if (driver.getTitle().contains(pageTitle)) {
				driver.switchTo().window(window);
				return driver;
				
			}

		}
		return null;
	}
}
