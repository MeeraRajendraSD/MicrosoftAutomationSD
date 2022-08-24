package microsoft.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import microsoft.qa.base.TestBase;

public class ProductDetailsPage extends TestBase{
	
	
	@FindBy (xpath="//button[contains(@id,'getOrRemoveButton')]/following-sibling::div/span")
	WebElement priceTage;
	
	public ProductDetailsPage(){
		PageFactory.initElements(driver, this);
	}
	
	
	public String getProductPrice() {
		return priceTage.getText();
		
	}

}
