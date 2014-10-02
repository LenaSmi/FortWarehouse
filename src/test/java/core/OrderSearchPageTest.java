package core;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class OrderSearchPageTest {
	
	WebDriver driver = new FirefoxDriver();
	private String baseUrl = "http://dev.fortwarehouse.com:8950/login/login.cfm";
	//private String baseUrl = "http://www.fortwarehouse.com/";
	LoginPage elem = new LoginPage();
	HomePage home = new HomePage();
	OrderSearchPage orderSearch = new OrderSearchPage();
	private String expectedUrlAfterLogin = "http://dev.fortwarehouse.com:8950/index.cfm";
	private String textExpectedAfterLogin = "fortwh qa (FORT Warehouse Demo)";
	private String userName = "fortwh";
	private String password = "fortqa333";
	private String seller = "Moscow";

	@Before
	public void setUp() throws Exception {
		driver.get(baseUrl);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		elem.login(userName, password, driver, textExpectedAfterLogin, expectedUrlAfterLogin);
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

	// Test verifies that user can find an order by order number 
	//@Ignore
	@Test
	public void test_04_0001() throws FileNotFoundException, IOException {
		String xpathSelector = null;
		String url = null;
		String textExpected = null;
		String menuItem = null;
		String xpathExpected = null;
		String orderNumber = null;
		

		Properties properties = new Properties();

		properties.load(new FileInputStream("./src/main/resources/TestOrderSearch.properties"));

		xpathSelector = properties.getProperty("xpathSelector");
		url = properties.getProperty("url");
		textExpected = properties.getProperty("textExpected");
		menuItem = properties.getProperty("menuItem");
		xpathExpected = properties.getProperty("xpathExpected");
		orderNumber = properties.getProperty("orderNumber");
				
		home.verifyLinkOpenSameTab(driver, xpathSelector, url, textExpected, menuItem, xpathExpected);
		assertTrue(orderSearch.singleOrderSearch(driver, orderNumber, seller));
	}

}
