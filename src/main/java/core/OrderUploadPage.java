package core;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class OrderUploadPage {
	
	public void singleOrderUpload(WebDriver driver,String seller,String location, String depLocation,
			String shipmentType,String format,String uploadMode,String reconfigurationMode,
			String container,String allocation, String charges,String checkAddress, String order){
		
	}
	
	public void singleOrderUpload(WebDriver driver, String seller, String order, String uploadFormat){
		
		WebElement element = driver.findElement(By.xpath(".//*[@id='Seller']"));
		Select selection = new Select(element);
		selection.selectByVisibleText(seller);
		
		WebElement element1 = driver.findElement(By.xpath(".//*[@id='version']"));
		Select selection1 = new Select(element1);
		selection1.selectByVisibleText(uploadFormat);
		
		driver.findElement(By.id("orderText")).clear();
		
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(order), null);
		driver.findElement(By.id("orderText")).sendKeys(Keys.CONTROL + "v");
		
		driver.findElement(By.xpath(".//*[@id='uploadForm']/table[3]/tbody/tr[20]/td[2]/input")).click();
				
	}
	
	public void multipleOrdersUploadTabDelFile(WebDriver driver, String seller, String uploadFormat, String pathToFile) throws InterruptedException, AWTException{
		
		WebElement element = driver.findElement(By.xpath(".//*[@id='Seller']"));
		Select selection = new Select(element);
		selection.selectByVisibleText(seller);
		
		WebElement element1 = driver.findElement(By.xpath(".//*[@id='version']"));
		Select selection1 = new Select(element1);
		selection1.selectByVisibleText(uploadFormat);
		
		
		File fileName = new File(pathToFile);
		String textFile = fileName.getAbsolutePath(); 
				
		WebElement upload = driver.findElement(By.xpath(".//*[@id='orderFile']"));
		
		upload.sendKeys(textFile);
		

        driver.findElement(By.xpath(".//*[@id='uploadForm']/table[3]/tbody/tr[24]/td[2]/input")).click();
		
		
	}

}
