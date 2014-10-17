package core;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class OrderSearchPage {
	
	/** That function searches for an order by number and company in Orders/Order Search. Returns True if order is found **/
	public boolean orderSearch(WebDriver driver,String orderNumber, String seller){
		
		
		
		WebElement element = driver.findElement(By.xpath(".//*[@id='Seller']"));
		Select selection = new Select(element);
		selection.selectByVisibleText(seller);
						
		driver.findElement(By.xpath("html/body/div[1]/div/form/table/tbody/tr[27]/td[2]/input")).click();

		Alert alt = driver.switchTo().alert();
		
		try { 
			alt.dismiss();
			
	     }   // try 
	     catch (NoAlertPresentException Ex){ 
	    	 System.out.println("Alert is not presented here!");
	     }   // catch 
		
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
	
	

}
