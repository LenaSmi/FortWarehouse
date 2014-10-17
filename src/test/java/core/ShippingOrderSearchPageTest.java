package core;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

@FixMethodOrder
public class ShippingOrderSearchPageTest {
	
	WebDriver driver = new FirefoxDriver();
	
	private String baseUrl = "http://dev.fortwarehouse.com:8950";
	//private String baseUrl = "http://www.fortwarehouse.com/";
	LoginPage elem = new LoginPage();
	HomePage home = new HomePage();
	AddOrderPage addOrder = new AddOrderPage();
	ShippingOrderSearchPage shipping = new ShippingOrderSearchPage();
	private String expectedUrlAfterLogin = baseUrl + "/index.cfm";
	private String textExpectedAfterLogin = "fortwh qa (FORT Warehouse Demo)";
	private String userName = "fortwh";
	private String password = "fortqa333";
	private String seller = "Moscow";
	private String orderNumber = "000001automation";
	

	@Before
	public void setUp() throws Exception {
		driver.get(baseUrl + "/login/login.cfm");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		elem.login(userName, password, driver, textExpectedAfterLogin, expectedUrlAfterLogin);
		
		//add order 
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
		String quantity = null;
		
		
		Properties properties = new Properties();

		properties.load(new FileInputStream("./src/main/resources/test_05_0001.properties"));

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
		addOrder.addOrderManually(driver, orderNumber, companyValue, name, lastName, address1, address2,
				city, state, zip, specialInstr, deliveryInstr, recipientMessage, productValue, quantity);
		
	}

	@After
	public void tearDown() throws Exception {
		
		// delete order that was added before
		home.verifyLinkOpenSameTab(driver, ".//*[@id='qm0']/div[1]/a[3]", baseUrl + "/wsn_v2/index.cfm?fuseaction=orders.home",
				"Format:", "Orders", "html/body/div[1]/div/form/table/tbody/tr[25]/td[1]/label");
		
		String companyName = null;
		Properties properties = new Properties();
		properties.load(new FileInputStream("./src/main/resources/test_05_0002.properties"));
		companyName = properties.getProperty("companyName");
		
		addOrder.deleteOrder(driver, orderNumber, companyName, baseUrl);

		//close driver
		driver.quit();
		
	}

	// This test verifies that user can find order by date in Shipping Order Search(today's date is used)
	//@Ignore
	@Test
	public void test_06_0001() {
				
		//driver goes to shipping page
		home.verifyLinkOpenSameTab(driver, ".//*[@id='qm0']/div[1]/a[5]", baseUrl + "/wsn_v2/index.cfm?fuseaction=shipping11.home",
				"System Order ID:", "Orders", ".//*[@id='Shipping_Search_Form']/table/tbody/tr[18]/td[1]/label");
		
		// assert that order that was added before can be found in Shipping Order Search
		assertTrue("Order not found", shipping.shippingOrderSearch(driver, seller, "All", orderNumber));
	}
	
	// This test verifies that user can find order by order number in Shipping Order Search
	//@Ignore
	@Test
	public void test_06_0002() {
		
		//driver goes to shipping page
		home.verifyLinkOpenSameTab(driver, ".//*[@id='qm0']/div[1]/a[5]", baseUrl + "/wsn_v2/index.cfm?fuseaction=shipping11.home",
				"System Order ID:", "Orders", ".//*[@id='Shipping_Search_Form']/table/tbody/tr[18]/td[1]/label");
		
		// assert that order that was added before can be found in Shipping Order Search
		assertTrue("Order not found", shipping.shippingOrderSearch(driver, "All", orderNumber));
	}

}
