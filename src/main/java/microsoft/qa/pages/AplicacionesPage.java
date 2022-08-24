package microsoft.qa.pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;



import microsoft.qa.base.TestBase;

public class AplicacionesPage extends TestBase {
	
	@FindBy (xpath= "//div[@id='R1MarketRedirect-1']/button[contains(@class,'cancel')]")
	WebElement translatePopupCancelButton;
	
	
	public AplicacionesPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void moveToPage(int pageNo) {
		driver.findElement(By.xpath("//a[text()="+pageNo+"]")).click();
		if (translatePopupCancelButton.isDisplayed()) {
			translatePopupCancelButton.click();
		}
		
	}
	
	public ArrayList<String> getNameOfAllProductsOnPage () {
		
		List<WebElement> productsNames= driver.findElements(By.xpath("//div[@class='m-channel-placement-item']/a//h3"));
		//System.out.println(productsNames.size());
		ArrayList<String> appNames= new ArrayList<String>();
		for(WebElement appName : productsNames) {
		//System.out.println(appName.getText());
		appNames.add(appName.getText());	
		}
		return appNames;
	}
	
	public Hashtable<String,String> getPriceOfAllAppsOnPage() {
		
		Hashtable<String,String> productPrice= new Hashtable<String,String>();
		
		List<WebElement> productsNames= driver.findElements(By.xpath("//div[@class='m-channel-placement-item']/a//h3"));
		
		for(WebElement appName : productsNames) {
			if (!(driver.findElements(By.xpath("//div[@class='m-channel-placement-item']/a//h3[contains(text(),'"+appName.getText()+"')]/following-sibling::div[contains(@class,'price')]//span[@itemprop=\"price\"]")).isEmpty())) {
				String appPrice = driver.findElement(By.xpath("//div[@class='m-channel-placement-item']/a//h3[contains(text(),'"+appName.getText()+"')]/following-sibling::div[contains(@class,'price')]//span[@itemprop=\"price\"]")).getText();
				//System.out.println(appPrice);
				productPrice.put(appName.getText(), appPrice);
			}else {
				productPrice.put(appName.getText(), "No Price Tag");
				// System.out.println("No Price Tag");
			}
				
		}
		return productPrice;
	}
	
	public ProductDetailsPage selectAndOpenApp(String appName) {
		
		WebElement applink= driver.findElement(By.xpath("//div[@class='m-channel-placement-item']/a//h3[contains(text(),'"+appName+"')]/../parent::a"));
		applink.click();
		/*if (translatePopupCancelButton.isDisplayed()) {
			translatePopupCancelButton.click();
		}*/
		return new ProductDetailsPage();
	}

}
