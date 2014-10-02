package core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import junit.framework.AssertionFailedError;
import junit.framework.TestResult;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.interactions.Actions;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class HomePageTest {
	
	WebDriver driver = new FirefoxDriver();
	private String baseUrl = "http://dev.fortwarehouse.com:8950";
	//private String baseUrl = "http://www.fortwarehouse.com/";
	LoginPage elem = new LoginPage();
	HomePage home = new HomePage();
	
	private String expectedUrlAfterLogin = "http://dev.fortwarehouse.com:8950";
	//private String expectedUrlAfterLogin = "https://www.fortwarehouse.com";
	private String textExpectedAfterLogin = "fortwh qa (FORT Warehouse Demo)";
	private String userName = "fortwh";
	private String password = "fortqa333";
	private File scrFile;
	private DateFormat dateFormat = new SimpleDateFormat("yyyy MM dd HH mm ss");
	private Date date = new Date();
	private String newDate = dateFormat.format(date);
	BasePage baseElement = new BasePage();
	
	@Rule
    public ErrorCollector collector = new ErrorCollector();
	public ScreenshotRule screenshotTestRule = new ScreenshotRule();
	

	@Before
	public void setUp() throws Exception {
		driver.get(baseUrl + "/login/login.cfm");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		elem.login(userName, password, driver, textExpectedAfterLogin, expectedUrlAfterLogin + "/index.cfm");
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

	// Test verifies that external links on the Home page redirects to external sites
	@Ignore
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
			try{
				home.verifyLinkOpenNewTab(driver, xpathToLink, url);
			}
			catch (Throwable t){
				collector.addError(t);
				
			}
			//home.verifyLinkOpenNewTab(driver, xpathToLink, url);
		}
		br.close();
	}
	
	// Test verifies that internal menu links redirect to right page
	// @Ignore
	@Test
	public void test_02_0002() throws IOException {
		String csvFile = "./src/main/resources/InternalMenuLinks.csv";
		BufferedReader br = null;
		String line = null;
		String cvsSplitBy = ",";
		String url = null;
		String xpathSelector = null;
		String expectedText = null;
		String menueItem = null;
		String xpathToVerify = null;
		String homeUrl = null;
		String linkText = null;
 
		br = new BufferedReader(new FileReader(csvFile));
		
		while ((line = br.readLine()) != null) {
			 
			String[] csv = line.split(cvsSplitBy);
			
			linkText = csv[0];
			xpathSelector = csv[1];
			url = csv[2];
			xpathToVerify = csv[3];
			expectedText = csv[4];
			menueItem = csv[5];
			homeUrl = csv[6];
			
			home.verifyLinkOpenSameTab(driver, xpathSelector, url, expectedText, menueItem, xpathToVerify);
			
//			//take a screenshot
//			scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
//			
//			//copy screenshot to c:\tmp\
//			FileUtils.copyFile(scrFile, new File("c:\\tmp\\screenshot" + newDate + linkText + ".png"));
			
			
			try{
				assertThat(linkText, driver.findElement(By.xpath(xpathToVerify)).getText(), is(expectedText));
			}
			catch (Throwable t){
				collector.addError(t);
				
			}
			
		
			driver.get(homeUrl);
		}
		br.close();
		
	}
	
	private class ScreenshotRule implements TestRule {

	      @Override
	      public Statement apply(final Statement base, final Description description) {
	          return new Statement() {

	              @Override
	              public void evaluate() throws Throwable {
	                  try {
	                      base.evaluate();
	                  } catch (Throwable t) {
	                      captureScreenshot(description.getMethodName().toString());
	                      throw t;
	                  }
	              }
	              
	          };
	      }
	      
	        private void captureScreenshot(String fileName) {
	            try {
	                new File("target/surefire-reports/").mkdirs();
	                FileOutputStream out = new FileOutputStream("target/surefire-reports/screenshot-" + newDate +
	                		fileName + ".png");
	                out.write(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
	                out.close();
	            } catch (Exception e) {
	            }
	        }
	      
	    }

}
