package core;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class LoginPageTest {
	
	WebDriver driver = new FirefoxDriver();
	private String baseUrl = "http://dev.fortwarehouse.com:8950";
	private String expectedUrlAfterLogin = baseUrl + "/index.cfm";
	private String userName = "fortwh";
	private String password = "fortqa333";
	private String textExpectedAfterLogin = "fortwh qa (FORT Warehouse Demo)";
	private String textExpectedAfterLogout = "News Reader";
	private String invalidPassword = "invalid";
	private String errorMessageExpectedInvalidLogin = "Error: Invalid user name or password! Please try again.";
	LoginPage elem = new LoginPage();
	BasePage base = new BasePage();

	@Before
	public void setUp() throws Exception {
		driver.get(baseUrl + "/login/login.cfm");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

	
	// This test case verifies that user can login with valid credentials
	//@Ignore
	@Test
	public void test_01_0001() {
		elem.login(userName, password, driver, textExpectedAfterLogin, expectedUrlAfterLogin);
	}
	
	// This test case verifies that user can't login with invalid credentials
	//@Ignore
	@Test
	public void test_01_0002() {
		elem.ivalidLogin(userName, invalidPassword, driver, errorMessageExpectedInvalidLogin);
	}
	
	// This test case verifies that user can login and logout
	//@Ignore
	@Test
	public void test_01_0003() {
		base.logout(driver, baseUrl, textExpectedAfterLogout);
	}

}
