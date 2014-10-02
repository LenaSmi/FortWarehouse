package core;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class OrderSearchPage {
	
	public boolean singleOrderSearch(WebDriver driver,String orderNumber, String seller){
		
		WebElement element = driver.findElement(By.xpath(".//*[@id='Seller']"));
		Select selection = new Select(element);
		selection.selectByVisibleText(seller);
						
		driver.findElement(By.xpath("html/body/div[1]/div/form/table/tbody/tr[27]/td[2]/input")).click();

		Alert alt = driver.switchTo().alert();
		alt.dismiss();
		
		List<WebElement> list = driver
				.findElements(By
						.xpath("html/body/div[1]/div/form[1]/table[1]/tbody/tr[/*]/td[3]/a"));
		
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
	
	public void singleOrderDelete(WebDriver driver, String orderNumber){
		
		driver.findElement(By.name("BTN_DELETE")).click();
		
	}

}
