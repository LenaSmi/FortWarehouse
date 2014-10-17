package core;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ShippingOrderSearchPage {
	
	public boolean shippingOrderSearch(WebDriver driver, String seller, String status, String orderNumber){
		
		
		
		WebElement element = driver.findElement(By.xpath(".//*[@id='Seller']"));
		Select selection = new Select(element);
		selection.selectByVisibleText(seller);
						
		
		driver.findElement(By.id("CALBUTTONOrder_From_Date")).click();
		driver.findElement(By.xpath(".//*[@id='calendarframe']/tbody/tr[2]/td/table/tbody/tr/td[2]")).click();
		driver.findElement(By.id("CALBUTTONUpload_From_Date")).click();
		driver.findElement(By.xpath(".//*[@id='calendarframe']/tbody/tr[2]/td/table/tbody/tr/td[2]")).click();
		driver.findElement(By.xpath(".//*[@id='Shipping_Search_Form']/table/tbody/tr[12]/td[2]/a[2]")).click();
		
		
		WebElement statusAll = driver.findElement(By.xpath(".//*[@id='Labels_Setting']"));
		Select selection1 = new Select(statusAll);
		selection1.selectByVisibleText(status);
		
		driver.findElement(By.id("btnShowOrders")).click();
		
		List<WebElement> list = driver
				.findElements(By
						.xpath("html/body/div[1]/table/tbody/tr[/*]/td[10]/a"));
		
			
		List<String> webList = new ArrayList<String>();
		// Transfer List<WebElement> into ArrayList
		for (WebElement element1 : list) {
			webList.add(element1.getText());
		}
		for(String str: webList) {
		    if(str.contains(orderNumber))
		       return true;
		}
		return false;
		
			
		
	}
	
	public boolean shippingOrderSearch(WebDriver driver, String status, String orderNumber){
		
		driver.findElement(By.id("CALBUTTONOrder_From_Date")).click();
		driver.findElement(By.xpath(".//*[@id='calendarframe']/tbody/tr[2]/td/table/tbody/tr/td[2]")).click();
		driver.findElement(By.id("CALBUTTONUpload_From_Date")).click();
		driver.findElement(By.xpath(".//*[@id='calendarframe']/tbody/tr[2]/td/table/tbody/tr/td[2]")).click();
		driver.findElement(By.xpath(".//*[@id='Shipping_Search_Form']/table/tbody/tr[12]/td[2]/a[2]")).click();
		
		driver.findElement(By.xpath(".//*[@id='Shipping_Search_Form']/table/tbody/tr[16]/td[2]/input[1]")).sendKeys(orderNumber);
		driver.findElement(By.xpath(".//*[@id='Shipping_Search_Form']/table/tbody/tr[16]/td[2]/input[2]")).sendKeys(orderNumber);
				
		WebElement statusAll = driver.findElement(By.xpath(".//*[@id='Labels_Setting']"));
		Select selection1 = new Select(statusAll);
		selection1.selectByVisibleText(status);
		
		driver.findElement(By.id("btnShowOrders")).click();
		
		List<WebElement> list = driver
				.findElements(By
						.xpath("html/body/div[1]/table/tbody/tr[/*]/td[10]/a"));
		
			
		List<String> webList = new ArrayList<String>();
		// Transfer List<WebElement> into ArrayList
		for (WebElement element1 : list) {
			webList.add(element1.getText());
		}
		for(String str: webList) {
		    if(str.contains(orderNumber))
		       return true;
		}
		return false;
		
			
		
	}
}	