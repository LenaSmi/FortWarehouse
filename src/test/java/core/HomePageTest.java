package core;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class HomePageTest {
	
	WebDriver driver = new FirefoxDriver();
	private String baseUrl = "http://dev.fortwarehouse.com:8950/login/login.cfm";
	LoginPage elem = new LoginPage();
	HomePage home = new HomePage();
	private String fortUrl = "http://www.fortsystems.com/";
	private String expectedUrlAfterLogin = "http://dev.fortwarehouse.com:8950/index.cfm";
	private String textExpectedAfterLogin = "fortwh qa (FORT Warehouse Demo)";
	private String userName = "fortwh";
	private String password = "fortqa333";
	

	@Before
	public void setUp() throws Exception {
		driver.get(baseUrl);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		elem.login(userName, password, driver, textExpectedAfterLogin, expectedUrlAfterLogin);
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

	// Test verifies that external links on the Home page redirects to external sites
	//@Ignore
	@Test
	public void test_02_0001() throws IOException {
		String csvFile = "./src/main/resources/ExternalLinks.csv";
		BufferedReader br = null;
		String line = null;
		String cvsSplitBy = ",";
		String url = null;
		String xpathToLink = null;
 
		br = new BufferedReader(new FileReader(csvFile));
		
		while ((line = br.readLine()) != null) {
			 
			String[] csv = line.split(cvsSplitBy);
 
			xpathToLink = csv[0];
			url = csv[1];
			home.verifyLinkOpenNewTab(driver, xpathToLink, url);
		}
		br.close();
	}
	
	// Test verifies that internal menu link redirect to right page
	// @Ignore
	@Test
	public void test_02_0002() throws IOException {
		String csvFile = "./src/main/resources/InternalMenuLinks.csv";
		BufferedReader br = null;
		String line = null;
		String cvsSplitBy = ",";
		String url = null;
		String linkText = null;
		String expectedText = null;
		String menueItem = null;
		String xpathToVerify = null;
 
		br = new BufferedReader(new FileReader(csvFile));
		
		while ((line = br.readLine()) != null) {
			 
			String[] csv = line.split(cvsSplitBy);
 
			linkText = csv[0];
			url = csv[1];
			xpathToVerify = csv[2];
			expectedText = csv[3];
			menueItem = csv[4];
			home.verifyLinkOpenSameTab(driver, linkText, url, expectedText, menueItem, xpathToVerify);
		}
		br.close();
	}

}
