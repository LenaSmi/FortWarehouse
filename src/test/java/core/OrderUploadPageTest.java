package core;

import static org.junit.Assert.*;

import java.awt.AWTException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class OrderUploadPageTest {
	
	WebDriver driver = new FirefoxDriver();
	private String baseUrl = "http://dev.fortwarehouse.com:8950";
	//private String baseUrl = "http://www.fortwarehouse.com/";
	LoginPage elem = new LoginPage();
	HomePage home = new HomePage();
	OrderUploadPage orderUpload = new OrderUploadPage();
	private String expectedUrlAfterLogin = baseUrl + "/index.cfm";
	private String textExpectedAfterLogin = "fortwh qa (FORT Warehouse Demo)";
	private String userName = "fortwh";
	private String password = "fortqa333";
	private String seller = "Moscow";
	private DateFormat dateFormat = new SimpleDateFormat("yyyy MM dd HH mm ss");
	private Date date = new Date();
	private String newDate = dateFormat.format(date);
	private File scrFile;
	private BasePage base = new BasePage();
	

	@Before
	public void setUp() throws Exception {
		driver.get(baseUrl + "/login/login.cfm");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		elem.login(userName, password, driver, textExpectedAfterLogin, expectedUrlAfterLogin);
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

	// Test verifies that user can upload a single order with selected Seller, order format and other fields by default 
	//@Ignore
	@Test
	public void test_03_0001() throws FileNotFoundException, IOException {
		String xpathSelector = null;
		String url = null;
		String textExpected = null;
		String menuItem = null;
		String xpathExpected = null;
		String orderDataCorrect = null;
		String uploadFormat = null;

		Properties properties = new Properties();

		properties.load(new FileInputStream("./src/main/resources/OrderUploadPageTest.properties"));

		xpathSelector = properties.getProperty("xpathSelector");
		url = properties.getProperty("url");
		textExpected = properties.getProperty("textExpected");
		menuItem = properties.getProperty("menuItem");
		xpathExpected = properties.getProperty("xpathExpected");
		orderDataCorrect = properties.getProperty("orderDataCorrect");
		uploadFormat = properties.getProperty("uploadFormat");

		
		home.verifyLinkOpenSameTab(driver, xpathSelector, url, textExpected, menuItem, xpathExpected);
		orderUpload.singleOrderUpload(driver, seller,orderDataCorrect, uploadFormat);
		
		//take a screenshot
		scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		//copy screenshot to c:\tmp\
		FileUtils.copyFile(scrFile, new File("c:\\tmp\\screenshot" + newDate + ".png"));
					
		assertFalse("Order wasn't uploaded!", base.isElementPresent("html/body/div[1]/div/table[2]/tbody/tr[1]/td", driver));
		
	}

	// Test verifies that user can't upload a single order with selected Seller, incorrect order format and other fields
	// by default 
	//@Ignore
	@Test
	public void test_03_0002() throws FileNotFoundException, IOException {
		String xpathSelector = null;
		String url = null;
		String textExpected = null;
		String menuItem = null;
		String xpathExpected = null;
		String orderDataCorrect = null;
		String uploadFormat = null;

		Properties properties = new Properties();

		properties.load(new FileInputStream("./src/main/resources/OrderUploadPageTest.properties"));

		xpathSelector = properties.getProperty("xpathSelector");
		url = properties.getProperty("url");
		textExpected = properties.getProperty("textExpected");
		menuItem = properties.getProperty("menuItem");
		xpathExpected = properties.getProperty("xpathExpected");
		orderDataCorrect = properties.getProperty("orderDataIncorrect");
		uploadFormat =properties.getProperty("uploadFormatIncorrect");

			
		home.verifyLinkOpenSameTab(driver, xpathSelector, url, textExpected, menuItem, xpathExpected);
		orderUpload.singleOrderUpload(driver, seller,orderDataCorrect, uploadFormat);
		
		//take a screenshot
		scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		//copy screenshot to c:\tmp\
		FileUtils.copyFile(scrFile, new File("c:\\tmp\\screenshot" + newDate + ".png"));
		assertEquals("Download Invalid Orders", driver.findElement(By.xpath("html/body/div[1]/div/table"
				+ "[1]/tbody/tr[10]/td[2]")).getText());
		
		}
	
	// Test verifies that user can upload  orders from file with selected Seller, correct order format and other fields
	// by default 
	//@Ignore
	@Test
	public void test_03_0003() throws FileNotFoundException, IOException, InterruptedException, AWTException {
		String xpathSelector = null;
		String url = null;
		String textExpected = null;
		String menuItem = null;
		String xpathExpected = null;
		String uploadFormat = null;
		String pathToFile = null;

		Properties properties = new Properties();

		properties.load(new FileInputStream("./src/main/resources/OrderUploadPageTest.properties"));

		xpathSelector = properties.getProperty("xpathSelector");
		url = properties.getProperty("url");
		textExpected = properties.getProperty("textExpected");
		menuItem = properties.getProperty("menuItem");
		xpathExpected = properties.getProperty("xpathExpected");
		uploadFormat = properties.getProperty("uploadFormat");
		pathToFile = properties.getProperty("pathToFile");

			
		home.verifyLinkOpenSameTab(driver, xpathSelector, url, textExpected, menuItem, xpathExpected);
		orderUpload.multipleOrdersUploadTabDelFile(driver, seller, uploadFormat, pathToFile);
		//take a screenshot
		scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		//copy screenshot to c:\tmp\
		FileUtils.copyFile(scrFile, new File("c:\\tmp\\screenshot" + newDate + ".png"));
		assertEquals("Orders are not uploaded", "Download Valid Orders", driver.findElement(By.xpath("html/body/div[1]/div/table/tbody/tr[12]/td[2]/a")).getText());
	}

}
