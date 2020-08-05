package com.pastbook.automation.core;
/**
 * @author Akila
 *
 */
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.pastbook.automation.util.ExtentReportManagerUtil;
import com.pastbook.automation.util.WebDriverUtil;

public abstract class UITestBase {

	protected static Logger logger;
	protected WebDriver driver = null;
	private static ExtentReports extent;
	private static ThreadLocal<ExtentTest> parentTest = new ThreadLocal<ExtentTest>();
	private static String screenshotPath = "Reports/Screenshots";

	public String TEST_URL = "";

	@BeforeSuite
	public synchronized void beforeSuite() throws Exception {
		logger = Logger.getLogger(this.getClass());
		driver = WebDriverUtil.getDriver();
		extent = ExtentReportManagerUtil.createInstance("ExtentReport.html");
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("ExtentReport.html");
		extent.attachReporter(htmlReporter);

	}

	@BeforeClass()
	public synchronized void beforeClass() throws Exception  {
		ExtentTest parent = extent
				.createTest(getClass().getName().substring(getClass().getName().lastIndexOf(".") + 1));
		parentTest.set(parent);
	}

	@AfterMethod
	public synchronized void afterMethod(ITestResult result) throws Exception {
		ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();
		
		ExtentTest child;
		String TestName;
		String Expected;
		String Actual;

		try {
			TestName = result.getAttribute("TestName").toString();
		} catch (Exception e) {
			TestName = "Test Name Unspecified";
			logger.error("Test Name Unspecified",e);
		}
		try {
			Expected = result.getAttribute("Expected").toString();
		} catch (Exception e) {
			Expected = "Expected Result Unspecified";
			logger.error("Expected Result Unspecified",e);
		}
		try {
			Actual = result.getAttribute("Actual").toString();
		} catch (Exception e) {
			Actual = "Actual Result Unspecified";
			logger.error("Actual Result Unspecified",e);
		}

		child = ((ExtentTest) parentTest.get()).createNode(TestName);
		child.info("Expected :" + Expected);
		child.info("Actual   :" + Actual);

		test.set(child);

		if (result.getStatus() == ITestResult.FAILURE) {

			((ExtentTest) test.get()).fail(result.getThrowable());

			try {
				child.addScreenCaptureFromPath(this.takeScreenshot(result.getAttribute("TestName").toString()));
			} catch (Exception arg6) {
				child.info(" Can\'t get Screenshot due to " + arg6);
			}

		} else if (result.getStatus() == ITestResult.SKIP) {

			((ExtentTest) test.get()).skip(result.getThrowable());

		} else
			((ExtentTest) test.get()).pass("Test passed");

		extent.flush();
	}

	public String takeScreenshot(String testDescription) throws Exception {
		String FileName = testDescription + ".jpg";
		String path = screenshotPath + "/" + FileName;

		try {
			File e = (File) ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(e, new File(path));
		} catch (WebDriverException e1) {
			logger.error("WebDriverException while running takeScreenshot",e1);
		} catch (IOException e2) {
			logger.error("IOException while running takeScreenshot  ",e2);
		}

		return path;
	}

}
