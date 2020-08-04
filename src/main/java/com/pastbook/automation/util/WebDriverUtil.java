package com.pastbook.automation.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

public class WebDriverUtil {

	public static WebDriver getDriver(String url) {

		WebDriver driver = null;
		String browser = ConfigUtil.getConfigUtil().getProperty("browser");

		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					ConfigUtil.getConfigUtil().getProperty(browser + ".driver.path"));
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.geko.driver",
					ConfigUtil.getConfigUtil().getProperty(browser + ".driver.path"));
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("ie")) {
			System.setProperty("webdriver.ie.driver", ConfigUtil.getConfigUtil().getProperty(browser + ".driver.path"));
			driver = new InternetExplorerDriver();
		} else if (browser.equalsIgnoreCase("safari")) {
			System.setProperty("webdriver.safari.driver",
					ConfigUtil.getConfigUtil().getProperty(browser + ".driver.path"));
			driver = new SafariDriver();
		} else {
			System.out.println("No Web driver Initialited NULL");
		}
		driver.get(url);
		driver.manage().window().maximize();
		return driver;

	}

	public static WebDriver getDriver() {

		WebDriver driver = null;
		String browser = ConfigUtil.getConfigUtil().getProperty("browser");

		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					ConfigUtil.getConfigUtil().getProperty(browser + ".driver.path"));
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.geko.driver",
					ConfigUtil.getConfigUtil().getProperty(browser + ".driver.path"));
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("ie")) {
			System.setProperty("webdriver.ie.driver", ConfigUtil.getConfigUtil().getProperty(browser + ".driver.path"));
			driver = new InternetExplorerDriver();
		} else if (browser.equalsIgnoreCase("safari")) {
			System.setProperty("webdriver.safari.driver",
					ConfigUtil.getConfigUtil().getProperty(browser + ".driver.path"));
			driver = new SafariDriver();
		} else {
			System.out.println("No Web driver Initialited NULL");
		}

		driver.manage().window().maximize();
		return driver;

	}

}
