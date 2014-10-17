package core;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;


public class AddOrderPage {
	
	HomePage home = new HomePage();
	OrderSearchPage search = new OrderSearchPage();
	
	
	/** That function adds an order Orders/Add Order and verifies that order is added **/
	public void addOrderManually(WebDriver driver,String orderNumber, String sellerValue, String name, String lastName, String address1,
			String address2, String city, String state, String zip, String specialInstr, String deliveryInstr, String recipientMessage,
			String productValue, String quantity){
		
		driver.findElement(By.id("Order_Number")).clear();
		driver.findElement(By.id("Order_Number")).sendKeys(orderNumber);
		
		Select select = new Select(driver.findElement(By.xpath(".//*[@id='Seller']")));
		select.selectByValue(sellerValue);
		
		driver.findElement(By.xpath(".//*[@id='orderForm']/table/tbody/tr[7]/td/table/tbody/tr/"
				+ "td[1]/table/tbody/tr[3]/td[2]/input[1]")).sendKeys(name);
		driver.findElement(By.xpath(".//*[@id='orderForm']/table/tbody/tr[7]/td/table/tbody/tr/"
				+ "td[1]/table/tbody/tr[3]/td[2]/input[2]")).sendKeys(lastName);
		driver.findElement(By.xpath(".//*[@id='orderForm']/table/tbody/tr[7]/td/table/tbody/tr/"
				+ "td[1]/table/tbody/tr[5]/td[2]/input")).sendKeys(address1);
		driver.findElement(By.xpath(".//*[@id='orderForm']/table/tbody/tr[7]/td/table/tbody/tr/"
				+ "td[1]/table/tbody/tr[6]/td[2]/input")).sendKeys(address2);
		driver.findElement(By.xpath(".//*[@id='orderForm']/table/tbody/tr[7]/td/table/tbody/tr/"
				+ "td[1]/table/tbody/tr[7]/td[2]/input")).sendKeys(city);
		driver.findElement(By.id("Shipping_State_1")).sendKeys(state);
		driver.findElement(By.name("Shipping_Zip_1")).sendKeys(zip);
		driver.findElement(By.xpath(".//*[@id='orderForm']/table/tbody/tr[7]/td/table/tbody/tr/"
				+ "td[2]/table/tbody/tr[2]/td[2]/input")).click();
	
		Select select1 = new Select(driver.findElement(By.id("Fulfiller_1")));
		select1.selectByValue("WSNDEMOPP");
		
		Select select2 = new Select(driver.findElement(By.xpath(".//*[@id='orderForm']/table/tbody/tr[8]/td"
				+ "/table/tbody/tr[2]/td[6]/select")));
		select2.selectByValue("UP2");
		
		driver.findElement(By.xpath(".//*[@id='orderForm']/table/tbody/tr[10]/td/table/tbody/tr/"
				+ "td[1]/table/tbody/tr[2]/td/textarea")).clear();
		driver.findElement(By.xpath(".//*[@id='orderForm']/table/tbody/tr[10]/td/table/tbody/tr/"
				+ "td[1]/table/tbody/tr[2]/td/textarea")).sendKeys(specialInstr);
		
		driver.findElement(By.xpath(".//*[@id='orderForm']/table/tbody/tr[10]/td/table/tbody/tr/"
				+ "td[2]/table/tbody/tr[2]/td/textarea")).clear();
		driver.findElement(By.xpath(".//*[@id='orderForm']/table/tbody/tr[10]/td/table/tbody/tr/"
				+ "td[2]/table/tbody/tr[2]/td/textarea")).sendKeys(deliveryInstr);
		
		driver.findElement(By.xpath(".//*[@id='orderForm']/table/tbody/tr[10]/td/table/tbody/tr/"
				+ "td[3]/table/tbody/tr[2]/td/textarea")).clear();
		driver.findElement(By.xpath(".//*[@id='orderForm']/table/tbody/tr[10]/td/table/tbody/tr/"
				+ "td[3]/table/tbody/tr[2]/td/textarea")).sendKeys(recipientMessage);
		
		Select select3 = new Select(driver.findElement(By.xpath(".//*[@id='Available_SKU_1']")));
		select3.selectByValue(productValue);
		
		driver.findElement(By.id("Quantity_1")).clear();
		
		driver.findElement(By.id("Quantity_1")).sendKeys(quantity);
		
		driver.findElement(By.xpath(".//*[@id='orderForm']/table/tbody/tr[11]/td/table/tbody/tr[2]/td[10]/input")).click();
		
		driver.findElement(By.xpath(".//*[@id='orderForm']/table/tbody/tr[16]/td/table/tbody/tr/td[2]/input")).click();
				
		assertEquals(driver.findElement(By.xpath("html/body/div[1]/div/a")).getText(), "Go to order");
		
		
		
		
		
	}
	
	/** That function searches for order by number, deletes order and verifies that order is deleted **/
	public void deleteOrder(WebDriver driver, String orderNumberToDelete, String companyName, String baseUrl){
		
		search.orderSearch(driver, "", companyName);
		List<WebElement> list = driver
				.findElements(By
						.xpath("html/body/div[1]/div/form[1]/table[1]/tbody/tr[/*]/td[2]/input"));
				
		for (WebElement element1 : list) {
			if(element1.getAttribute("value").matches(".*" + orderNumberToDelete + ".*")){
				element1.click();
				driver.findElement(By.name("BTN_DELETE")).click();
				home.verifyLinkOpenSameTab(driver, ".//*[@id='qm0']/div[1]/a[3]", baseUrl + "/wsn_v2/index.cfm?fuseaction=orders.home",
						"Format:", "Orders", "html/body/div[1]/div/form/table/tbody/tr[25]/td[1]/label");
			    assertFalse(search.orderSearch(driver, orderNumberToDelete, companyName));
			    
				break;
			}
								
		}
			
	}
	
	public void deleteOrder(WebDriver driver){
		
		driver.findElement(By.name("BTN_DELETE")).click();
		
	}

}
