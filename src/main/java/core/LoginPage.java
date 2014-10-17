package core;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
	
	/** The method logs in with valid credentials **/
	public void login(String userName, String password, WebDriver driver, String textExpected, String expectedUrl){
		driver.findElement(By.id("UserName")).clear();
		driver.findElement(By.id("UserName")).sendKeys(userName);
		driver.findElement(By.id("pwd")).clear();
		driver.findElement(By.id("pwd")).sendKeys(password);
		driver.findElement(
				By.xpath("html/body/div[1]/table/tbody/tr/td[2]/div[2]/div/form/div/input[4]"))
				.click();

		assertEquals(textExpected,driver.findElement(By.xpath("html/body/table[1]/tbody/tr[2]"
				+ "/td/table/tbody/tr/td[2]/b")).getText());
				
		assertEquals(expectedUrl, driver.getCurrentUrl());
		
	}
	
	/** The method logs in with invalid credentials and verifies error message is displayed **/
	public void ivalidLogin(String userName, String password, WebDriver driver, String errorMessageExpectedInvalidLogin){
		driver.findElement(By.id("UserName")).clear();
		driver.findElement(By.id("UserName")).sendKeys(userName);
		driver.findElement(By.id("pwd")).clear();
		driver.findElement(By.id("pwd")).sendKeys(password);
		driver.findElement(
				By.xpath("html/body/div[1]/table/tbody/tr/td[2]/div[2]/div/form/div/input[4]"))
				.click();

		assertEquals(errorMessageExpectedInvalidLogin, driver.findElement(By.xpath("html/body/div[1]/table/tbody/tr/td[2]/div[2]/div/div/b")).getText());
		
	}

}
