package microsoft.qa.pages;

import java.util.Set;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import microsoft.qa.base.TestBase;

public class WindowsPage extends TestBase{
	
	// Page Factory - Object 
	
	@FindBy (id = "search")
	WebElement searchButton;
	
	@FindBy (id= "cli_shellHeaderSearchInput")
	WebElement searchInputField;
	
	@FindBy (xpath= "//div[@id='R1MarketRedirect-1']/button[contains(@class,'cancel')]")
	WebElement translatePopupCancelButton;
	
	public WindowsPage() {
		PageFactory.initElements(driver, this);
	}
	
	public SearchResultPage performSearch(String searchStr) {
		searchButton.click();
		searchInputField.sendKeys(searchStr);
		searchButton.click();
		Set<String> windowHandler= driver.getWindowHandles();
		if (translatePopupCancelButton.isDisplayed()) {
			translatePopupCancelButton.click();
		}
		return new SearchResultPage();
		
		
	}
		
		

}
