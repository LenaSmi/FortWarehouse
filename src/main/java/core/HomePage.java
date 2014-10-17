package core;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;


public class HomePage {
	
	/** That function open a link in a new tab and verifies that correct url is opened **/	
	public void verifyLinkOpenNewTab(WebDriver driver,String xpath, String expectedUrl){
		
		driver.findElement(By.xpath(xpath)).click();
		ArrayList<String> handles = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(handles.get(1));
		assertEquals(expectedUrl, driver.getCurrentUrl());
		driver.close();
		driver.switchTo().window(handles.get(0));
		
		
	}
	
	/** That function open a link in same tab and verifies that correct url is opened **/
	public void verifyLinkOpenSameTab(WebDriver driver, String xpathSelector, String expectedUrl,
		String expectedText, String menueItem,String xpathToVerify){
		
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.linkText(menueItem))).perform();
		driver.findElement(By.xpath(xpathSelector)).click();
			
		assertThat(driver.getCurrentUrl(), is(expectedUrl));
	}		
		
}
