package core;

import static org.junit.Assert.*;

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

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;

import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


@FixMethodOrder

public class OrderSearchPageTest {
	
		
	
	static WebDriver driver = new FirefoxDriver();
	
	private String baseUrl = "http://dev.fortwarehouse.com:8950";
	//private String baseUrl = "http://www.fortwarehouse.com/";
	LoginPage elem = new LoginPage();
	HomePage home = new HomePage();
	OrderSearchPage orderSearch = new OrderSearchPage();
	private String expectedUrlAfterLogin = baseUrl + "/index.cfm";
	private String textExpectedAfterLogin = "fortwh qa (FORT Warehouse Demo)";
	private String userName = "fortwh";
	private String password = "fortqa333";
	private String seller = "Moscow";
	
	private DateFormat dateFormat = new SimpleDateFormat("yyyy MM dd HH mm ss");
	private Date date = new Date();
	private String newDate = dateFormat.format(date);
	
	@Rule
	public TestWatcher watcher = new TestWatcher() {

	    @Override
	    protected void failed(Throwable e, Description description) {
	        File scrFile = ((TakesScreenshot) driver)
	                .getScreenshotAs(OutputType.FILE);
	        try {
	            FileUtils.copyFile(scrFile, new File(
	                    "c:\\tmp\\screenshot" + newDate + ".png"));
	        } catch (IOException e1) {
	            
	        }

	    }
	    
	};

	@Before
	public void setUp() throws Exception {
		driver.get(baseUrl + "/login/login.cfm");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		elem.login(userName, password, driver, textExpectedAfterLogin, expectedUrlAfterLogin);
	}

	@AfterClass
	public static void tearDown() throws Exception {
		driver.quit();
	}

	// Test verifies that user can find an order by order number 
	//@Ignore
	@Test
	public void test_04_0001() throws FileNotFoundException, IOException {
		
		
				
		String orderNumber = null;
		Properties properties = new Properties();
		properties.load(new FileInputStream("./src/main/resources/test_04_0001.properties"));
			
		orderNumber = properties.getProperty("orderNumber");
				
		home.verifyLinkOpenSameTab(driver, ".//*[@id='qm0']/div[1]/a[3]", baseUrl + "/wsn_v2/index.cfm?fuseaction=orders.home",
				"Format:", "Orders", "html/body/div[1]/div/form/table/tbody/tr[25]/td[1]/label");
		
		assertTrue("Order was not found",orderSearch.orderSearch(driver, orderNumber, seller));
				
		
	}
	
}
