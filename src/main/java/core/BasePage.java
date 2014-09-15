package core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static org.junit.Assert.assertEquals;

public class BasePage {
	public boolean isElementPresent(String locatorKey, WebDriver driver) {
		try {
			driver.findElement(By.xpath(locatorKey));
			return true;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			return false;
		}
	}
	
	public void logout(WebDriver driver,String url, String expectedText){
		driver.findElement(By.xpath("html/body/table[1]/tbody/tr[2]/td/table/tbody/tr/td[2]/a/span")).click();
		assertEquals(url, driver.getCurrentUrl());
		assertEquals(expectedText, driver.findElement(By.xpath(".//*[@id='datepicker']/div/div[1]/div")).getText());
	}

}
