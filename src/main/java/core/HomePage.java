package core;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;


public class HomePage {
	
	public void verifyLinkOpenNewTab(WebDriver driver,String xpath, String expectedUrl){
		
		driver.findElement(By.xpath(xpath)).click();
		ArrayList<String> handles = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(handles.get(1));
		assertEquals(expectedUrl, driver.getCurrentUrl());
		driver.close();
		driver.switchTo().window(handles.get(0));
		
		
	}
	
	public void verifyLinkOpenSameTab(WebDriver driver, String xpathSelector, String expectedUrl,
		String expectedText, String menueItem,String xpathToVerify){
		
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.linkText(menueItem))).perform();
		driver.findElement(By.xpath(xpathSelector)).click();
		assertEquals(expectedUrl, driver.getCurrentUrl());
		
		
		
	}

}
