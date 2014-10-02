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
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class AddOrderPageTest {
	
	WebDriver driver = new FirefoxDriver();
	private String baseUrl = "http://dev.fortwarehouse.com:8950";
	//private String baseUrl = "http://www.fortwarehouse.com/";
	LoginPage elem = new LoginPage();
	HomePage home = new HomePage();
	AddOrderPage addOrder = new AddOrderPage();
	private String expectedUrlAfterLogin = "http://dev.fortwarehouse.com:8950/index.cfm";
	private String textExpectedAfterLogin = "fortwh qa (FORT Warehouse Demo)";
	private String userName = "fortwh";
	private String password = "fortqa333";
	

	@Before
	public void setUp() throws Exception {
		driver.get(baseUrl + "/login/login.cfm");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		elem.login(userName, password, driver, textExpectedAfterLogin, expectedUrlAfterLogin);
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

	// Test verifies that user can add single order manually 
	//@Ignore
	@Test
	public void test_04_0001() throws FileNotFoundException, IOException{
		String xpathSelector = null;
		String url = null;
		String textExpected = null;
		String menuItem = null;
		String xpathExpected = null;
		String companyValue = null;
		String name = null;
		String lastName = null;
		String address1 = null;
		String address2 = null;
		String city = null;
		String state = null;
		String zip = null;
		String specialInstr = null;
		String deliveryInstr = null;  
		String recipientMessage = null;
		String productValue = null;
		String quantity =null;
		
			
		Properties properties = new Properties();

		properties.load(new FileInputStream("./src/main/resources/test_04_0001.properties"));

		xpathSelector = properties.getProperty("xpathSelector1");
		url = properties.getProperty("url1");
		textExpected = properties.getProperty("textExpected1");
		menuItem = properties.getProperty("menuItem");
		xpathExpected = properties.getProperty("xpathExpected1");
		companyValue = properties.getProperty("companyValue");
		name = properties.getProperty("name");
		lastName = properties.getProperty("lastName");
		address1 = properties.getProperty("address1");
		address2 = properties.getProperty("address2");
		city = properties.getProperty("city");
		state = properties.getProperty("state");
		zip = properties.getProperty("zip");
		specialInstr = properties.getProperty("specialInstr");
		deliveryInstr = properties.getProperty("deliveryInstr");
		recipientMessage = properties.getProperty("recipientMessage");
		productValue = properties.getProperty("productValue");
		quantity = properties.getProperty("quantity");
		
		home.verifyLinkOpenSameTab(driver, xpathSelector, url, textExpected, menuItem, xpathExpected);
		addOrder.addOrderManually(driver, companyValue, name, lastName, address1, address2, city, state, zip, specialInstr, deliveryInstr,  
				recipientMessage, productValue, quantity);
		assertEquals(driver.findElement(By.xpath("html/body/div[1]/div/a")).getText(), "Go to order");
	
	}

}
